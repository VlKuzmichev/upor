<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<%--<script type="text/javascript" src="resources/js/upor.departments.js" defer></script>--%>
<script type="text/javascript" src="resources/js/upor.common.js" defer></script>
<script type="text/javascript" src="resources/js/upor.users.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center"><spring:message code="user.title"/></h3>
        <button class="btn btn-outline-secondary" onclick="add()">
            <span class="fa fa-plus"></span>
            <spring:message code="common.add"/>
        </button>
        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th><spring:message code="user.name"/></th>
                <th><spring:message code="user.fullName"/></th>
                <th><spring:message code="user.email"/></th>
                <th><spring:message code="user.roles"/></th>
                <th><spring:message code="user.active"/></th>
                <th><spring:message code="user.registered"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="modalTitle"></h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <%--                    <sec:authorize access="hasAnyRole('admin','superadmin')" var="isAuthorizeAny"></sec:authorize>--%>
                    <input type="hidden" id="id" name="id">
                    <div class="form-group">
                        <label for="name" class="col-form-label"><spring:message code="user.name"/></label>
                        <input type="text" class="form-control" id="name" name="name"
                               placeholder="<spring:message code="user.name"/>">
                    </div>
                    <div class="form-group">
                        <label for="fullName" class="col-form-label"><spring:message code="user.fullName"/></label>
                        <input type="text" class="form-control" id="fullName" name="fullName"
                               placeholder="<spring:message code="user.fullName"/>">
                    </div>
                    <sec:authorize access="hasRole('ROLE_sADMIN')">
                        <div class="form-group">
                            <label for="departmentId" class="col-form-label"><spring:message
                                    code="user.department"/></label>
                            <select id="departmentId" name="departmentId" class="form-control mx-0">
                            </select>
                        </div>
                    </sec:authorize>

                    <%--Need refactoring. Dinamic add checkboxes using Role object for example.--%>
                    <div class="form-group">
                        <label class="col-form-label">Роли</label><BR>
                        <div class="form-row">
                            <div class="col">
                                <input id="role_user" type="checkbox" name="roles" value="ROLE_USER">ROLE_USER
                            </div>
                            <div class="col">
                                <input id="role_dispatcher" type="checkbox" name="roles" value="ROLE_DISPATCHER">ROLE_DISPATCHER
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col">
                                <input id="role_auditor" type="checkbox" name="roles" value="ROLE_AUDITOR">ROLE_AUDITOR
                            </div>
                            <div class="col">
                                <input id="role_boss" type="checkbox" name="roles" value="ROLE_BOSS">ROLE_BOSS
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="col">
                                <input id="role_admin" type="checkbox" name="roles" value="ROLE_ADMIN">ROLE_ADMIN
                            </div>
                        </div>

                        <label class="col-form-label">Глобальные роли</label><BR>
                        <div class="form-row">
                            <div class="col">
                                <input id="role_suser" type="checkbox" name="roles" value="ROLE_sUSER" disabled>ROLE_sUSER
                            </div>
                            <div class="col">
                                <input id="role_sdispatcher" type="checkbox" name="roles" value="ROLE_sDISPATCHER"
                                       disabled>ROLE_sDISPATCHER
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="col">
                                <input id="role_sauditor" type="checkbox" name="roles" value="ROLE_sAUDITOR" disabled>ROLE_sAUDITOR
                            </div>
                            <div class="col">
                                <input id="role_sboss" type="checkbox" name="roles" value="ROLE_sBOSS" disabled>ROLE_sBOSS
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="col">
                                <input id="role_sadmin" type="checkbox" name="roles" value="ROLE_sADMIN" disabled>ROLE_sADMIN
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-form-label"><spring:message code="user.email"/></label>
                        <input type="email" class="form-control" id="email" name="email"
                               placeholder="<spring:message code="user.email"/>">
                    </div>

                    <div class="form-group">
                        <label for="password" class="col-form-label"><spring:message code="user.password"/></label>
                        <input type="password" class="form-control" id="password" name="password"
                               placeholder="<spring:message code="user.password"/>">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                    <span class="fa fa-close"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button type="button" class="btn btn-primary" onclick="save()">
                    <span class="fa fa-check"></span>
                    <spring:message code="common.save"/>
                </button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
<jsp:include page="fragments/i18n.jsp">
    <jsp:param name="page" value="user"/>
</jsp:include>
<script type="text/javascript">
    var hasRoleSuAdmin = false;
</script>
<sec:authorize access="hasRole('ROLE_sADMIN')">
    <script type="text/javascript">
        hasRoleSuAdmin = true;
    </script>
</sec:authorize>
</html>