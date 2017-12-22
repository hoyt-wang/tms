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

            <div class="body">
                <form action="/ticket/out"  method="post" id="outForm">
                    <div class="form-group">
                        <label>请输入下发数量</label>
                        <input type="text" name="cardNum"class="form-control" placeholder="请输入下发数量">
                    </div>
                    <div class="form-group">
                        <label>请输入作废卡号，以逗号隔开</label>
                        <input type="text" name="invalidCards" class="form-control" placeholder="请输入作废卡号，以逗号隔开">
                    </div>
                    <div class="form-group">
                        <label>请选择售票点</label>
                        <select name="storeAccount" class="form-control">
                            <option value=""></option>
                            <c:forEach items="${storeAccountList}" var="storeAccount">
                                <option value="${storeAccount.storeAccount}">${storeAccount.storeAccount}</option>
                                <input type="hidden" name="storeAccountId" value="${storeAccount.id}">
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <button class="btn btn-primary" id="outBtn">下发</button>
                    </div>
                </form>

            </div>





            <!-- /.box -->

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

        $("#outBtn").click(function () {
            $("#outForm").submit();
        });
        $("#outForm").validate({
            errorClass:"text-danger",
            errorElement:"span",
            rules:{
                cardNum:{
                    required:true
                },storeAccount:{
                    required:true
                }
            },
            messages:{
                ticketNum:{
                    required:"请输入年票卡号"
                },storeAccount:{
                    required:"请选择售票账户"
                }
            }
        });
    });
</script>
</body>
</html>
