<%@ include file="/jsp/taglib.jspf" %>
<html>
<head>
    <title>
        <fmt:message key="key.welcome"/>
    </title>
    <%@ include file="jsp/head.jspf" %>
</head>
<body>
<%@ include file="jsp/header.jspf" %>
<div class="header">
    <fmt:message key="key.welcome"/>
</div>
<br><br>
<div class="welcome_form">
    <form id="login_form" action="controller" method="POST">
        <input type="hidden" name="service" value="login"/>
        <div class="field">
            <label> <fmt:message key="key.login"/>
            </label> <input type="text" name="email" required/>
        </div>
        <br>
        <div class="field">
            <label> <fmt:message key="key.password"/>
            </label> <input type="password" name="password" required/>
        </div>
        <div class="field">
            <input type="submit"
                   value="<fmt:message key="key.login"/>">
        </div>
        <br>
        <div class="field">
            <fmt:message key="key.not_registered_msg"/>
            <a href="controller?service=registration_client"><fmt:message
                    key="key.register_here_msg"/></a>
        </div>
    </form>
</div>
<c:if test="${not empty error_message}">
    <c:out value="${errorMessage}"></c:out>
</c:if>

<c:if test="${not empty successful_message}">
    <c:out value="${successfulMessage}"></c:out>
</c:if>
</body>
</html>