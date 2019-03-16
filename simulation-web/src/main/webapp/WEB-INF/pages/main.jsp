<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Bus Network</title>
    <%@ include file="menu.jsp" %>
    <script src="${pageContext.request.contextPath}/resources/js/load.js"></script>
</head>
<body>
<div id="welcome">
    <input type="hidden" id="error" value="${error}"/>
    <input type="hidden" id="uploaded_file" value="${filename}"/>
    <c:if test="${user.login != null}">
        <div id="text">
            <h2>Welcome to the bus network simulation</h2>
        </div>
    </c:if>
    <img src="${pageContext.request.contextPath}/resources/video/giphy.gif"/>
    <div id="sim-menu">
        <p id="sim-head">Simulation menu</p>
        <c:choose>
            <c:when test="${user.login != null}">
                <div id="main_menu">
                    <p>Before using the simulation please choose:</p>
                    <ul>
                        <li>Use your file</li><button onclick="file()" class="btn btn-primary">Use file</button>
                        <li>Use default data</li><button onclick="useDefault()" class="btn btn-secondary">Use default</button>
                    </ul>
                </div>
                <div id="use_file">
                    <form:form method="post" enctype="multipart/form-data" modelAttribute="uploadedFile" action="uploadFile">
                        <p>Upload file: </p> <input type="file" class="btn btn-info" id="file" name="file" />
                        <p id="error_text" style="color: red; font-style: italic;"> <form:errors path="file"/></p>
                        <input style="margin-top: 10px;" type="submit" class="btn btn-secondary" value="Upload"/>
                    </form:form>
                    <button id="back" class="btn btn-primary" onclick="backToChoseFromFile()">Back</button>
                </div>
                <div id="start_simulation">
                    <div class="row">
                        <c:choose>
                            <c:when test="${restart == null}">
                                <div class="col-lg-6"><button type="button" id="start" class="btn btn-success btn-lg" onclick="start()">Start simulation</button></div>
                            </c:when>
                            <c:otherwise>
                                <div class="col-lg-6"><button type="button" id="restart" class="btn btn-success btn-lg" onclick="restart()">Restart simulation</button></div>
                            </c:otherwise>
                        </c:choose>
                        <div class="col-lg-6"><button type="button" class="btn btn-primary" onclick="backToChoseFromStart()">Back</button> </div>
                    </div>
                </div>
                <div id="Progress_Status">
                    <div id="myprogressBar">1%</div>
                    <div id="complete"><p>Simulation finished</p></div>
                </div>
                <div id="logout-div">
                    <a id="logout" href="${pageContext.request.contextPath}/logout" class="btn btn-warning">Logout</a>
                </div>
            </c:when>
            <c:otherwise>
                <h4>For using simulation please register or sign in</h4>
                <div class="row">
                    <div class="col-lg-6"><a href="${pageContext.request.contextPath}/go_login" type="button" class="btn btn-primary btn-lg">Log in</a></div>
                    <div class="col-lg-6"><a href="${pageContext.request.contextPath}/go_login" type="button" class="btn btn-success btn-lg">Register</a></div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>

