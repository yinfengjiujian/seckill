//javaScript 模块化
var seckill = {
    //封装秒杀相关的ajax的URL
    URL: {
        getNow: function () {
            return '/seckill/time/now';
        },
        exporseUrl: function (seckillId) {
            return '/seckill/' + seckillId + '/exposer';
        },
        execution: function (seckillId, md5) {
            return '/seckill/' + seckillId + '/' + md5 + '/execution';
        }
    },
    /**
     * 秒杀业务逻辑判断处理
     * @param seckillId
     * @param node
     */
    handleSeckilled: function (seckillId, node) {
        //对当前的节点增加按钮
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        //获取秒杀地址，控制逻辑
        $.post(seckill.URL.exporseUrl(seckillId), {}, function (result) {
            //在回调函数中，执行交互流程
            if (result && result['success']) {
                var exposer = result['data'];
                if (exposer['exposed']) {
                    //开始秒杀
                    //获取秒杀地址
                    var md5 = exposer['md5'];
                    //绑定一次点击事件
                    $('#killBtn').one('click', function () {
                        //执行秒杀请求
                        //1、先禁用按钮
                        $(this).addClass('disabled');
                        //2、发送秒杀请求行为，进行秒杀
                        seckill.seckillAction(seckill.URL.execution(seckillId, md5), node);
                    })
                    node.show();
                } else {
                    //未开启秒杀
                    var nowTime = exposer['now'];
                    var startTime = exposer['start'];
                    var endTime = exposer['end'];
                    if (!$.isEmptyObject(nowTime) || !$.isEmptyObject(startTime) || !$.isEmptyObject(endTime)) {
                        //重新计算时间，进行业务逻辑处理
                        seckill.countWebDown(seckillId, nowTime, startTime, endTime);
                    }
                }
            } else {
                alert(result['error']);
            }
        });

    },

    /**
     * 根据秒杀Url 和后台进行数据交互，执行秒杀操作
     * @param killUrl
     */
    seckillAction: function (killUrl, node) {
        //向后台发起请求，进行秒杀
        $.post(killUrl, {}, function (result) {
            if (result && result['success']) {
                //获取后台返回的秒杀成功对象数据
                var killResult = result['data'];
                var state = killResult['state'];
                var stateInfo = killResult['stateInfo'];
                //秒杀成功，显示秒杀结果
                node.html('<span class="label label-success">' + stateInfo + '</span>');
            } else {
                var stateInfo = result['data']['stateInfo'];
                //秒杀失败，显示秒杀结果
                node.html('<span class="label label-success">' + stateInfo + '</span>');
            }
        });
    },
    /**
     * 验证手机号的合法性
     * -->合格就返回 true  不合格就返回 false
     * @param phone
     * @returns {boolean}
     */
    validatePhone: function (phone) {
        if (phone && phone.length == 11 && !isNaN(phone)) {
            return true;
        } else {
            return false;
        }
    },
    /**
     * 时间判断函数，用于全局秒杀的时间计算
     * @param seckillId     秒杀商品Id
     * @param nowTime       当前系统时间
     * @param startTime     商品秒杀开始时间
     * @param endTime       商品秒杀结束时间
     */
    countWebDown: function (seckillId, nowTime, startTime, endTime) {
        var seckillBox = $('#seckill-box');
        //当前系统时间已经大于商品的秒杀结束时间
        if (nowTime > endTime) {
            //秒杀结束
            seckillBox.html('秒杀结束！');
        } else if (nowTime < startTime) {
            //秒杀未开始，进行倒计时处理并显示
            var killTime = new Date(startTime + 1000);
            seckillBox.countdown(killTime, function (event) {
                //时间格式
                var format = event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒');
                seckillBox.html(format);
                //当秒杀倒计时为 '0' 的时候，也就是当前系统时间  等于 秒杀开始时间  开始被被执行这段代码
            }).on('finish.countdown', function () {
                //秒杀开始
                seckill.handleSeckilled(seckillId, seckillBox);
            });
        } else {
            //秒杀开始
            seckill.handleSeckilled(seckillId, seckillBox);
        }
    },
    //详情页秒杀逻辑
    detail: {
        //详情页初始化
        init: function (params) {
            //手机验证、登录   计时交互
            //在cookie中查找手机号
            var killPhone = $.cookie('killPhone');
            //验证手机号 cookie中没有数据的那么没有登录，则进行输入手机号写入cookie中
            if (!seckill.validatePhone(killPhone)) {
                //绑定手机号
                var killPhoneModel = $('#killPhoneModel');
                killPhoneModel.modal({
                    show: true,//显示弹出层
                    backdrop: 'static',//禁止位置关闭
                    keyboard: false    //关闭键盘事件
                });
                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killPhoneKey').val();
                    if (seckill.validatePhone(inputPhone)) {
                        //电话写入cookie
                        $.cookie('killPhone', inputPhone, {expires: 7, path: '/seckill'});
                        //刷新页面
                        window.location.reload();
                    } else {
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">手机号错误！</label>').show(300);
                    }
                })
            }

            //已经登录 则进行后面的业务流程
            var seckillId = params['seckillId'];
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            $.get(seckill.URL.getNow(), {}, function (result) {
                if (result && result['success']) {
                    var nowTime = result['data'];
                    seckill.countWebDown(seckillId, nowTime, startTime, endTime);
                } else {
                    alert(result['error']);
                }
            });

        }
    }
}