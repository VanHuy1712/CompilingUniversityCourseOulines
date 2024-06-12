<%-- 
    Document   : header
    Created on : Jun 11, 2024, 3:10:15 PM
    Author     : Huy
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Outline</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/"/>">Trang chủ</a>
                </li>
                <c:forEach items="${academicterms}" var="a">
                    <li class="nav-item">
                        <a class="nav-link" href="#">${a.name}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</nav>
