<%--
  <p>Company:东软集团:neusoft</p> 
  <p>Copyright:Copyright(c)2018</p> 
  User: 段美林
  Date: 2018/5/17 0:15
  Description: 秒杀商品的详细页面
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <title>秒杀商品的详细页面</title>
    <%@ include file="common/head.jsp" %>
</head>
<body>
<div class="container">
    <div class="panel panel-default text-center">
        <div class="panel-heading">
            <h1>${seckill.name}</h1>
        </div>
        <div class="panel-body">
            <h2 class="text-danger">
                <!--显示Time图标-->
                <span class="glyphicon glyphicon-time"></span>
                <!--显示倒计时-->
                <span class="glyphicon" id="seckill-box"></span>
            </h2>
        </div>
    </div>
</div>
<!--弹出层，输入手机号码-->
<div id="killPhoneModel" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title text-center">
                    <span class="glyphicon glyphicon-phone"></span>秒杀电话：
                </h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-8 col-xs-offset-2">
                        <input type="text" name="killPhone" id="killPhoneKey"
                               placeholder="填写手机号^o^" class="form-control">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <!--验证消息-->
                <span id="killPhoneMessage" class="glyphicon"></span>
                <button type="button" id="killPhoneBtn" class="btn btn-success">
                    <span class="glyphicon glyphicon-phone"></span>Submit
                </button>
            </div>
        </div>
    </div>
</div>
</body>
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="/resources/static/jquery-3.3.1/jquery-3.3.1.js?ver=<%=Math.random()%>"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="/resources/static/bootstrap/js/bootstrap.js?ver=<%=Math.random()%>"></script>
<!-- 使用CDN 获取公共JS http://www.bootcdn.cn/ -->
<!-- jQuery cookie 操作插件 -->
<script src="/resources/static/jquery-3.3.1/jquery.cookie.js?ver=<%=Math.random()%>"></script>
<!-- jQuery countDown 倒计时操作插件 -->
<script src="/resources/static/jquery-3.3.1/jquery.countdown.js?ver=<%=Math.random()%>"></script>

<!--开始编写JS交互逻辑-->
<script src="/resources/web-script/seckill.js?ver=<%=Math.random()%>" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        //使用EL表达式传入参数
        seckill.detail.init({
            seckillId : ${seckill.seckillId},
            startTime : ${seckill.startTime.time},
            endTime : ${seckill.endTime.time}
        });
    });
</script>
</html>
