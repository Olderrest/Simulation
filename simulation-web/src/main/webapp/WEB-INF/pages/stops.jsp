<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Stops list</title>
</head>
<%@ include file="menu.jsp" %>
<body>
<div id="back"><a href="${pageContext.request.contextPath}/main_page">Back</a></div>
<div id="stops_list">
    <c:choose>
        <c:when test="${stopsList.size() > 0}">
            <table id="sortable" class="table">
                <caption>All stops</caption>
                <thead>
                <tr>
                    <th>Stop №</th>
                    <th>Distance from previous stop</th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="stop" items="${stopsList}">
                    <tr>
                        <td><c:out value="${stop.id}"/></td>
                        <td><c:out value="${stop.time}"/></td>
                        <td><a href="${pageContext.request.contextPath}/stops/stop_info?id=${stop.id}"
                               class="btn btn-info">Stop info</a></td>
                        <td><a href="${pageContext.request.contextPath}/stops/update_stop/${stop.id}"
                               class="btn btn-info">Update</a></td>
                        <td><a href="${pageContext.request.contextPath}/stops/delete_stop/${stop.id}"
                               class="btn btn-info">Delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <h2>Stop list is empty because the simulation was not running.</h2>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
