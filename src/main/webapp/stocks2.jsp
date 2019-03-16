<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create an account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>

<body>

<div class="container">

    <form:form method="POST" modelAttribute="employee" class="form-signin">
        <h2 class="form-signin-heading">Create your account</h2>
        <spring:bind path="symbols">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="symbols" class="form-control" placeholder="simbols"
                            autofocus="true"></form:input>
                <form:errors path="symbols" cssClass="error"/>
            </div>
        </spring:bind>

        <spring:bind path="departmentVO">
            <div class="form-group ">
                <form:select path="departmentVO" items="${allDepartments}" itemValue="id" itemLabel="name"></form:select>
                <form:errors path="departmentVO" cssClass="error"/>
            </div>
        </spring:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>

</div>

<h2 align="center" class="form-heading">${msg}</h2>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
