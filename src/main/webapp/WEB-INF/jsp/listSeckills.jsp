<%--
  <p>Company:东软集团:neusoft</p> 
  <p>Copyright:Copyright(c)2018</p> 
  User: 段美林
  Date: 2018/5/17 0:15
  Description: 秒杀详细列表页面
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <title>秒杀列表页面</title>
    <%@ include file="common/head.jsp"%>
</head>
<body>
    <!--页面显示部分-->
    <div class="container">
        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <h1>秒杀列表</h1>
            </div>
            <div class="panel-body">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>名称</th>
                            <th>库存</th>
                            <th>开始时间</th>
                            <th>结束时间</th>
                            <th>创建时间</th>
                            <th>详情页面</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="sk" items="${seckillList}">
                            <tr>
                                <td>${sk.name}</td>
                                <td>${sk.number}</td>
                                <td>
                                    <fmt:formatDate value="${sk.startTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                                </td>
                                <td>
                                    <fmt:formatDate value="${sk.endTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                                </td>
                                <td>
                                    <fmt:formatDate value="${sk.createTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                                </td>
                                <td>
                                    <a class="btn btn-info" href="/seckill/${sk.seckillId}/detail" target="_blank">link</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="/resources/static/jquery-3.3.1/jquery-3.3.1.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="/resources/static/bootstrap/js/bootstrap.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>
</html>
