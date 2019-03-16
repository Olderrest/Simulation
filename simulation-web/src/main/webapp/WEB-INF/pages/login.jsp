<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/styles/login.css" />
    <script src="${pageContext.request.contextPath}/resources/js/login.js"></script>
    <title>Login</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-6">
                            <a href="#" class="active" id="login-form-link">Login</a>
                        </div>
                        <div class="col-xs-6">
                            <a href="#" id="register-form-link">Register</a>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form:form id="login-form" modelAttribute="user" action="login" method="post" style="display: block;">
                                <p style="color: red"> ${loginError}</p>
                                <div class="form-group">
                                    <form:label path="login">Login</form:label>
                                    <form:input type="text" path="login" id="login" tabindex="1" class="form-control" required="required"/>
                                    <form:errors path="login" cssStyle="color: red"/>

                                </div>
                                <div class="form-group">
                                    <form:label path="password">Password</form:label>
                                    <form:input type="password" path="password" id="password" tabindex="2" class="form-control" required="required"/>
                                    <form:errors path="password" cssClass="error" cssStyle="color: red"/>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Log in">
                                        </div>
                                    </div>
                                </div>
                            </form:form>
                            <form:form id="register-form" action="register" modelAttribute="user" method="post" style="display: none;">
                                <p style="color: red"> <c:out value="${registerError}"/></p>
                                <div class="form-group">
                                    <form:label path="login">Login</form:label>
                                    <form:input type="text" path="login" id="login" tabindex="1" class="form-control" required="required"/>
                                    <form:errors path="login" cssClass="error" cssStyle="color: red"/>
                                </div>
                                <div class="form-group">
                                    <form:label path="password">Password</form:label>
                                    <form:input type="password" path="password" id="password" tabindex="2" class="form-control" required="required"/>
                                    <form:errors path="password" cssClass="error" cssStyle="color: red"/>
                                </div>
                                <div class="form-group">
                                    <form:label path="firstName">First name</form:label>
                                    <form:input type="text" path="firstName" id="firstName" tabindex="2" class="form-control" required="required"/>
                                    <form:errors path="firstName" cssClass="error" cssStyle="color: red"/>
                                </div>
                                <div class="form-group">
                                    <form:label path="lastName">Last name</form:label>
                                    <form:input type="text" path="lastName" id="lastName" tabindex="2" class="form-control" required="required"/>
                                    <form:errors path="lastName" cssClass="error" cssStyle="color: red"/>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="Register now">
                                        </div>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>