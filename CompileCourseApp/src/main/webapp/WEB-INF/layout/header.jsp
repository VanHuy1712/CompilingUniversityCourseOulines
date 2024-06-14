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
                <li class="nav-item">
                    <a class="nav-link text-success" href="<c:url value="/stats" />">Thống kê</a>
                </li>
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.name == null}">
                        <li class="nav-item">
                            <a class="nav-link text-info" href="<c:url value="/login" />">Đăng nhập</a>
                        </li>
                    </c:when>
                    <c:when test="${pageContext.request.userPrincipal.name != null}">
                        <li class="nav-item">
                            <a class="nav-link text-info" href="<c:url value="/" />">Chào ${pageContext.request.userPrincipal.name}!</a>
                        </li>
                    </c:when>
                </c:choose>
            </ul>
            <form action="<c:url value="/" />" class="d-flex ms-auto">
                <input class="form-control me-2" name="kw" type="text" placeholder="Nhập tên...">
                <input class="form-control me-2" name="courseCredit" type="text" placeholder="Nhập tín chỉ...">
                <input class="form-control me-2" name="teacherName" type="text" placeholder="Nhập tên giảng viên...">
                <input class="form-control me-2" name="term" type="text" placeholder="Nhập khóa...">
                <button class="btn btn-primary" type="submit">Tìm</button>
            </form>
        </div>
    </div>
</nav>
