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

                <div class="box-body">
                    <form action="/ticket/invalid" method="post" id="invalidForm">
                        <div class="form-group">
                            <label>请输入作废卡号</label>
                            <input type="number" name="ticketNum" class="form-control"  placeholder="请输入作废卡号">
                        </div>
                        <div class="form-group" id="exceptionMsg">
                            <c:if test="${not empty message}">
                                ${message}
                            </c:if>
                        </div>
                        <div class="form-group">
                            <button class="btn btn-primary" id="invalidBtn">作废</button>
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
<script src="/static/plugins/layer/layer.js"></script>
<script>
    $(function () {
        //验证添加账号表单
        $("#invalidBtn").click(function () {
            $("#invalidForm").submit();
        });
  /*      $("#invalidForm").validate({
            errorClass:"text-danger",
            errorElement:"span",
            rules:{
                ticketNum:{
                    required:true
                }
            },
            messages:{
                ticketNum:{
                    required:"请输入作废卡号"
                }
            },
            submitHandler:function(){
                $.post("/ticket/invalid",$("#invalidForm").serialize()).done(function (data) {
                    if(data.state == 'success') {
                        window.location.href = "/home";
                    } else {
                        var exceptionMsg = data.message;
                        var html = '<span>'+exceptionMsg+'</span>';
                        $("#exceptionMsg").append(html);
                        alert(data.msg);
                    }
                }).error(function () {
                    layer.msg("服务器忙，请稍后");
                });
            }
        });*/
    });
</script>
</body>
</html>
