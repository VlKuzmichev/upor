<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Авторизация</title>
    <link rel="stylesheet" href="webjars/bootstrap/4.3.1/css/bootstrap.min.css">
    <link href="resources/css/signin.css" rel="stylesheet">
    <script type="text/javascript" src="webjars/bootstrap/4.3.1/js/bootstrap.min.js" defer></script>
    <script type="text/javascript" src="webjars/jquery/3.3.1-2/jquery.min.js" defer></script>
</head>
<%--<jsp:include page="fragments/headTag.jsp"/>--%>
<body class="text-center">
<form:form class="form-signin" id="login_form" action="spring_security_check" method="post">
    <img class="mb-4" src="resources/images/upor.png" alt="" width="52" height="50">
    <h1 class="h3 mb-3 font-weight-normal">Пожалуйста, авторизуйтесь</h1>
    <input class="form-control" id="Email" type="text" placeholder="Email" name="username">
    <input class="form-control" id="inputPassword" type="password" placeholder="Password" name="password">
    <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
    <p class="mt-5 mb-3 text-muted">&copy; 2019</p>
    <c:if test="${param.error}">
        <div class="error">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
    </c:if>
    <c:if test="${not empty param.message}">
        <div class="message"><spring:message code="${param.message}"/></div>
    </c:if>
    <br/>
    <sec:authorize access="isAnonymous()">
        <p>
            <button type="submit" class="btn btn-lg btn-primary" onclick="login('usersv@yandex.ru', 'password')">
                <spring:message code="app.login"/> User
            </button>
            <button type="submit" class="btn btn-lg btn-primary" onclick="login('disp@yandex.ru', 'dispet')">
                <spring:message code="app.login"/> Disp
            </button>
            <button type="submit" class="btn btn-lg btn-primary" onclick="login('admin@gmail.com', 'admin')">
                <spring:message code="app.login"/> Admin
            </button>
        </p>
    </sec:authorize>
    <br/>
</form:form>

<script type="text/javascript">
    <c:if test="${not empty param.username}">
    setCredentials("${param.username}", "");
    </c:if>

    function login(username, password) {
        setCredentials(username, password);
        $("#login_form").submit();
    }
    function setCredentials(username, password) {
        $('input[name="username"]').val(username);
        $('input[name="password"]').val(password);
    }

</script>

</body>
</html>
