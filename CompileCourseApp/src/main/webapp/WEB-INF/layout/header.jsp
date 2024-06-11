<%-- 
    Document   : header
<<<<<<< HEAD
    Created on : Jun 11, 2024, 3:10:15 PM
    Author     : Huy
=======
    Created on : Jun 10, 2024, 8:36:59 PM
    Author     : DELL
>>>>>>> f99b71e5e95a248a02d841e27b7aabf1b71ab969
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Outline</a>   
>>>>>>> f99b71e5e95a248a02d841e27b7aabf1b71ab969
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

>>>>>>> f99b71e5e95a248a02d841e27b7aabf1b71ab969
