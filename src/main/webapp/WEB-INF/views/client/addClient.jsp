<%@ include file="/jsp/taglib.jspf" %>
<html>
<head>
    <title>welcome</title>
    <%@ include file="/jsp/head.jspf" %>
</head>
<body>
<%@ include file="/jsp/header.jspf" %>
<div class="header">
    <fmt:message key="key.enter_info_msg"/>
</div>
<br>
<br>

<div class="welcome_form">
    <form id="registration_form" action="controller" method="POST">
        <input type="hidden" name="service" value="registration_client"/>

        <div class="field">
            <label for="lang"> <fmt:message key="key.language"/></label>
            <select name="lang" required>
                <option value="ru">Russian</option>
                <option value="en">English</option>
            </select>
        </div>
        <div class="field">
            <label><fmt:message key="key.first_name"/></label>
            <input type="text" name="first_name" value="" required/>
        </div>
        <div class="field">
            <label><fmt:message key="key.last_name"/></label>
            <input type="text" name="last_name" value="" required/>
        </div>
        <div class="field">
            <label><fmt:message key="key.second_name"/></label>
            <input type="text" name="second_name" value="" required/>
        </div>
        <div class="field">
            <label><fmt:message key="key.date_of_birth"/></label>
            <input type="text"  name="date_of_birth" placeholder="yyyy-mm-dd" value="" required/>
        </div>
        <div class="field">
            <label><fmt:message key="key.email"/></label>
            <input type="text" name="email" value="" required/>
        </div>
        <div class="field">
            <label> <fmt:message key="key.password"/></label>
            <input type="password" name="password" value="" required/>
        </div>
        <div class="field">
            <label><fmt:message key="key.iin"/></label>
            <input type="text" name="iin" value="" required/>
        </div>
        <div class="field">
            <label><fmt:message key="key.city"/></label>
            <input type="text" name="city" value="" required/>
        </div>
        <div class="field">
            <label><fmt:message key="key.district"/></label>
            <input type="text" name="district" value="" required/>
        </div>
        <div class="field">
            <label> <fmt:message key="key.school_name"/></label>
            <input type="text" name="school_name" value="" required/>
        </div>
        <div class="field">
            <input type="reset"
                   value="<fmt:message
					key="key.button.reset" />"/>
        </div>
        <div class="field">
            <input type="submit"
                   value="<fmt:message
					key="key.button.submit" />"/>
        </div>
        <div class="field">
            <fmt:message key="key.already_registered_msg"/>
            <a href="welcome.jsp"><fmt:message
                    key="key.login_here_msg"/></a>
        </div>
    </form>
</div>
</body>
</html>
