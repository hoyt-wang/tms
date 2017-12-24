<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS-景区管理系统 | 持卡人信息</title>
    <%@include file="include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="include/header.jsp"%>
    <!-- =============================================== -->
    <jsp:include page="include/sider.jsp">
        <jsp:param name="menu" value="home"/>
    </jsp:include>
    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-body">

                    <div>
                        <div class="box-header with-border">
                            <h3>
                                <c:if test="${not empty message}">
                                    ${message}
                                </c:if>
                            </h3>
                            <h3 class="box-title">持卡人信息</h3>
                        </div>
                        <div class="box-body no-padding">
                            <table class="table">
                                <tr>
                                    <td class="td_title">姓名</td>
                                    <td>${customer.customerName}</td>
                                    <td class="td_title">性别</td>
                                    <td>${customer.customerPhoto}</td>
                                    <td class="td_title">照片</td>
                                    <td>${customer.customerGender}</td>
                                </tr>
                            </table>
                        </div>
                    </div>

                </div>

            </div>
            <!-- /.box -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <%@include file="include/footer.jsp"%>

</div>
<!-- ./wrapper -->

<%@include file="include/js.jsp"%>
<script>
    $(function () {
      /*  var scenicAccountId = <shiro:principal property="id"/>
        $.get("/ticket/count",{"scenicAccountId":scenicAccountId}).done(function (data) {
            if(data.state == 'success') {
                var count = data.data;

                var html = '<label>'+'今日共计'+count+'人入园参观'+'</label>';
                $("#countModal").append(html);
                /!*window.location.href = "/ticket/list";*!/
            } else {
                layer.msg(data.message);
            }
        }).error(function () {
            layer.msg("服务器忙，请稍后");
        });*/
    });
</script>
</body>
</html>
