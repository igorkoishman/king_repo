<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>
        <%@ page contentType="text/html;charset=UTF-8" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

        <html>
        <head>
            <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css"/>
            <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
            <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
            <link rel="stylesheet" href="/resources/demos/style.css"/>
            <title>Add Employee Form</title>
            <style>
                .error {
                    color: #ff0000;
                    font-weight: bold;
                }
            </style>
        </head>

<body>
<h2><spring:message code="lbl.page" text="Add New Employee"/></h2>
<h2 align="right">Today's date: <%= (new java.util.Date()).toLocaleString()%>
</h2>
<br/>
<form:form method="post"  modelAttribute="employee">
    <form:errors path="*" cssClass="error"/>
    <table>
        <tr>
            <td><spring:message code="lbl.firstName" text="First Name"/></td>
            <td><form:input path="firstName"/></td>
            <td><form:errors path="firstName" cssClass="error"/></td>
        </tr>
        <tr>
            <td><spring:message code="lbl.lastName" text="Last Name"/></td>
            <td><form:input path="lastName"/></td>
            <td><form:errors path="lastName" cssClass="error"/></td>
        </tr>
        <tr>
            <td><spring:message code="lbl.email" text="Email Id"/></td>
            <td><form:input path="email"/></td>
            <td><form:errors path="email" cssClass="error"/></td>
        </tr>
        <tr>
            <td><spring:message code="lbl.Date" text="Date"/></td>
            <td><form:input type="text" path="date" class="datepicker"/></td>
                <%--<label for="datepicker">Enter date:</label>--%>
                <%--<input type="text" name="selDate" id="datepicker">    --%>
        </tr>
        <tr>
            <td colspan="3"><input type="submit" value="Add Employee"/></td>
        </tr>
    </table>
</form:form>
<h2 align="center" class="form-heading">${msg}</h2>

</body>
</html>
</title>
</head>
<body>

</body>
</html>
