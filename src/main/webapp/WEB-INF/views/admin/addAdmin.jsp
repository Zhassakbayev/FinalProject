<%@ include file="/jsp/taglib.jspf" %>
<html>
<head>
    <title>
        <c:if test="${">
        </c:if>
    </title>
    <%@ include file="/jsp/head.jspf" %>
</head>
<body>
<%@ include file="/jsp/header.jspf" %>
<div class="header">
    <fmt:message key="key.enter_admin_info_msg" />
</div>
<br>
<br>
<div class="form">
    <form id="registration_form" method="POST" action="controller">
        <input type="hidden" name="service" value="registration_admin" />
        <div class="field">
            <label for="language"><fmt:message key="key.language"/> </label>
            <select name="lang">
            <option value="ru">Russian</option>
            <option value="en">English</option>
            </select>
        </div>
        <div class="field">
            <label><fmt:message key="key.first_name" /></label>
            <input type="text" name="first_name" value="" required />
        </div>
        <div class="field">
            <label><fmt:message key="key.last_name" /></label>
            <input type="text" name="last_name" value="" required />
        </div>
        <div class="field">
            <label><fmt:message key="key.second_name" /></label>
            <input type="text" name="second_name" value="" required />
        </div>
        <div class="field">
            <label><fmt:message key="key.date_of_birth" /></label>
            <input type="text"  name="date_of_birth" placeholder="yyyy-mm-dd" value="" required/>
        </div>
        <div class="field">
            <label><fmt:message key="key.email" /></label>
            <input type="text" name="email" value="" required />
        </div>
        <div class="field">
            <label> <fmt:message key="key.password" /></label>
            <input type="password" name="password" value="" required />
        </div>
        <br>
        <div class="field">
            <input type="reset" value="<fmt:message key="key.button.reset" />" />
        </div>
        <br>
        <div class="field">
            <input type="submit" value="<fmt:message key="key.button.submit" />" />
        </div>
    </form>
</div>
</body>
</html>
