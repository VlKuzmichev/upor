<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<nav class="navbar navbar-expand-md navbar-light bg-light py-0">
    <div class="container">
        <a href="" class="navbar-brand"><img src="resources/images/upor.png"> <spring:message
                code="app.titleshort"/></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <sec:authorize access="isAuthenticated()">
                        <form:form class="form-inline my-2" action="logout" method="post">
<%--                            <sec:authorize access="hasRole('ROLE_ADMIN')">--%>
<%--                                <a class="btn btn-secondary mr-1" href="users"><spring:message code="user.title"/></a>--%>
<%--                            </sec:authorize>--%>
                            <a class="btn btn-dark mr-1" href="profile">
                                    <%-- <sec:authentication property="principal.userTo.name"/>--%>
                                <spring:message code="app.profile"/></a>
                            <div class="mx-1">
                                Здравствуйте, <sec:authentication property="principal.userTo.name"/>
                            </div>

                            <button class="btn btn-danger" type="submit">
                                <span class="fa fa-sign-out"></span>
                            </button>
                        </form:form>
                    </sec:authorize>
                    <sec:authorize access="isAnonymous()">
                        <form:form class="form-inline my-2" id="login_form" action="spring_security_check"
                                   method="post">
                            <input class="form-control mr-1" type="text" placeholder="Email" name="username">
                            <input class="form-control mr-1" type="password" placeholder="Password" name="password">
                            <button class="btn btn-success" type="submit">
                                <span class="fa fa-sign-in"></span>
                            </button>
                        </form:form>
                    </sec:authorize>
                </li>
                <li class="nav-item dropdown">
                    <a class="dropdown-toggle nav-link my-1 ml-2"
                       data-toggle="dropdown">${pageContext.response.locale}</a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="${requestScope['javax.servlet.forward.request_uri']}?lang=en">English</a>
                        <a class="dropdown-item" href="${requestScope['javax.servlet.forward.request_uri']}?lang=ru">Русский</a>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container-top" id="menu">
    <ul class="navbar-nav ml-lg-5">
        <li class="nav-item">
            <a class="btn btn-secondary mr-1" href="/"><spring:message code="app.incidents"/></a>
            <a class="btn btn-secondary mr-1" href="/"><spring:message code="app.vehicle"/></a>
            <a class="btn btn-secondary mr-1" href="/"><spring:message code="app.tasks"/></a>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <a class="btn btn-danger mr-1" href="users"><spring:message code="user.title"/></a>
            </sec:authorize>
        </li>
    </ul>
</div>

<script type="text/javascript">
    const localeCode = "${pageContext.response.locale}";
</script>