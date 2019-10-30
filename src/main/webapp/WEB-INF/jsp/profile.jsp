<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="upor" tagdir="/WEB-INF/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <div class="row">
            <div class="col-5 offset-3">
                <h3>${userTo.name} <spring:message code="app.profile"/></h3>
                <%--@elvariable id="passwordFormTo" type="rzd.zrw.upor.to.PasswordFormTo"--%>
                <form:form class="form-group" modelAttribute="passwordFormTo" method="post" action="profile"
                           charset="utf-8" accept-charset="UTF-8">
                    <upor:inputField labelCode="user.old_password" name="oldPassword" inputType="password"/>
                    <upor:inputField labelCode="user.new_password" name="newPassword" inputType="password"/>
                    <upor:inputField labelCode="user.confirm_password" name="confirmPassword" inputType="password"/>
                    <div class="text-right">
                        <a class="btn btn-secondary" href="#" onclick="window.history.back()">
                            <span class="fa fa-close"></span>
                            <spring:message code="common.cancel"/>
                        </a>
                        <button type="submit" class="btn btn-primary" >
                            <span class="fa fa-check"></span>
                            <spring:message code="common.save"/>
                        </button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
<%--<script type="text/javascript">--%>
<%--        //function savePass() {--%>
<%--           // debugger;--%>
<%--            // var newpass = $("#newPassword").val();--%>
<%--            // var valid = newpass == $("#confirmPassword").val();--%>
<%--            // if (!valid) {--%>
<%--            //     //     $("#error").show();--%>
<%--            //     alert("bad passw");--%>
<%--            //     return;--%>
<%--            // }--%>
<%--            //  else {--%>
<%--            //      window.location.href = '/';--%>
<%--            //  }--%>
<%--                 //window.location.href = '/';--%>
<%--        }--%>
<%--</script>--%>
</body>
</html>