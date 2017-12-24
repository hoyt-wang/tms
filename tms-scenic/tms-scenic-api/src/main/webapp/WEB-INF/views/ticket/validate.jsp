<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS-景区管理系统 | 验票</title>
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
                刷卡校验系统
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <c:if test="${not empty message}">
                ${message}
            </c:if>
            <div class="box">
                <div class="box-body">
                    <form action="/ticket/validate" method="post" id="validateForm">
                        <div class="form-group">
                            <label>请输入卡号</label>
                            <input type="number" name="ticketNum" class="form-control" placeholder="请输入年票卡号">
                            <input type="hidden" name="scenicAccountId" value="<shiro:principal property="id"/> ">
                        </div>
                        <div class="form-group">
                            <button class="btn btn-primary" id="validateBtn">校验</button>
                        </div>
                    </form>
                </div>
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <%@include file="../include/footer.jsp"%>

</div>
<!-- ./wrapper -->

<%@include file="../include/js.jsp"%>
<script src="/static/plugins/validate/jquery.validate.min.js"></script>
<script>
    $(function () {

        $("#validateBtn").click(function () {
            $("#validateForm").submit();
        });
        $("#validateForm").validate({
            errorClass:"text-danger",
            errorElement:"span",
            rules:{
                ticketNum:{
                    required:true
                }
            },
            messages:{
                ticketNum:{
                    required:"请输入年票卡号"
                }
            }
        });
    });
</script>
</body>
</html>
