<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS-库存管理系统 | 首页</title>
    <%@include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../include/header.jsp"%>
    <!-- =============================================== -->

    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="home"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Blank page
                <small>it all starts here</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="#">Examples</a></li>
                <li class="active">Blank page</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <c:if test="${not empty message}">
                ${message}
            </c:if>
            <div class="body">
                <form action="/ticket/new" method="post" id="addForm">
                    <div class="form-group">
                        <label>请输入年票起始卡号</label>
                        <input type="number" name="ticketNum" class="form-control" value="${lastTicketNum + 1}"  placeholder="请输入年票起始卡号">
                    </div>
                    <div class="form-group">
                        <label>请输入入库数量</label>
                        <input type="number" name="cardNum" class="form-control" placeholder="请输入入库数量">
                    </div>
                    <div class="form-group">
                        <label>请输入作废卡号，以逗号隔开</label>
                        <input type="text" name="invalidCards" class="form-control" placeholder="请输入作废卡号，以逗号隔开">
                    </div>
                    <div class="form-group">
                        <button class="btn btn-primary" id="addBtn">入库</button>
                    </div>
                </form>
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <%@include file="../include/footer.jsp"%>
    <script src="/static/plugins/validate/jquery.validate.min.js"></script>

</div>
<!-- ./wrapper -->

<%@include file="../include/js.jsp"%>
<script src="/static/plugins/validate/jquery.validate.min.js"></script>
<script>
    $(function () {

        $("#addBtn").click(function () {
            $("#addForm").submit();
        });
        $("#addForm").validate({
            errorClass:"text-danger",
            errorElement:"span",
            rules:{
                ticketNum:{
                    required:true
                },cardNum:{
                    required:true
                }
            },
            messages:{
                ticketNum:{
                    required:"请输入年票卡号"
                },cardNum:{
                    required:"请输入有效数字"
                }
            }
        });
    });
</script>
</body>
</html>
