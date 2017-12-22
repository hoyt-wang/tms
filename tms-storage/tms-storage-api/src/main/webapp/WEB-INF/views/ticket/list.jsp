<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS-库存管理系统 | 首页</title>
    <%@include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
<%@include file="../include/header.jsp"%>
<!-- =============================================== -->

<jsp:include page="../include/sider.jsp">
    <jsp:param name="menu" value="list"/>
</jsp:include>
<div class="content-wrapper">
    <section class="content-header">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">年票</h3>
            </div>
            <div class="panel-body">
                <form class="form-inline" id="checkForm">
                    <label>请选择年票状态 ： </label>
                    <select name="ticketState" class="form-control">

                        <option value="" name="ticketState">--选择状态--</option>
                        <c:forEach items="${ticketStateList}" var="ticketState">
                            <option name="ticketState" value="${ticketState}" ${param.ticketState == ticketState ? 'selected' : ''}>${ticketState}</option>
                        </c:forEach>
                    </select>
                    <button class="btn btn default" id="checkBtn">统计</button>
                </form>
            </div>


                <div class="panel-body" id="countModal">
                    <label>数量 ： </label>
                </div>



        </div>
    </section>

    </section>

</div>
<%@include file="../include/footer.jsp"%>

</div>

<!-- jQuery 2.2.3 -->
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/plugins/iCheck/icheck.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script>
    $(function(){
        $("#checkBtn").click(function () {
            /*$("#checkForm").submit();*/
        });
        $.post("/ticket/list",$("#checkForm").serialize()).done(function (data) {
            if(data.state == 'success') {
                var count = data.data;

                 var html = '<span>'+count+'</span>';
                 $("#countModal").append(html);
                /*window.location.href = "/ticket/list";*/
            } else {
                layer.msg(data.message);
            }
        }).error(function () {
            layer.msg("服务器忙，请稍后");
        });






    });
</script>
</body>
</html>