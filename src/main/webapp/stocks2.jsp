<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create an account</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css">
    <script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
    <script src="/js/datatable.js"></script>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 60%;
            align-items: center;
            align-self: center;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
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

    <div class="container">
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <form id="logoutForm" method="POST" action="${contextPath}/logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>

            <h2 align="center"> Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
        </c:if>
    </div>

</div>

<h2 align="center" class="form-heading">${msg}</h2>
<c:choose>
    <c:when test="${!empty list}">
        <h1 align="center" class="form-heading">Employees Table</h1>
        <table align="center">
            <c:forEach items="${list}" var="item">
                <tr>
                    <td><c:out value="${item.symbol}"/></td>
                    <td><c:out value="${item.companyName}"/></td>
                    <td><c:out value="${item.time}"/></td>
                    <td><c:out value="${item.value}"/></td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
</c:choose>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
