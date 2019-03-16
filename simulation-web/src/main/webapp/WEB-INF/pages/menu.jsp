<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../jspf/lib.jspf" %>
<nav id="menu" class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div style="font-weight: bold;" class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/main_page">Network</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/stops/all_stops">All Stops</a></li>
                <li><a href="${pageContext.request.contextPath}/routes/all_routes">All Routes</a></li>
                <li><a href="${pageContext.request.contextPath}/buses/all_buses">All buses</a></li>
                <li><a href="${pageContext.request.contextPath}/simulation_info">Simulation Info</a></li>
            </ul>
        </div>
    </div>
</nav>
