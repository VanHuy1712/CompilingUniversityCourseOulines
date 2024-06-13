<%-- 
    Document   : sreachoutlines
    Created on : Jun 13, 2024, 8:05:39 PM
    Author     : Huy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form action="<c:url value="/sreachoutlines" />" class="d-flex ms-auto mt-3">
    <input class="form-control me-2" name="nameCourse" type="text" placeholder="Nhập tên...">
    <input class="form-control me-2" name="credit" type="text" placeholder="Nhập tín chỉ...">
    <input class="form-control me-2" name="teacherName" type="text" placeholder="Nhập tên giảng viên...">
    <!--<input class="form-control me-2" name="term" type="text" placeholder="Nhập khóa...">-->
    <button class="btn btn-primary" type="submit">Tìm</button>
</form>
<table class="table table-striped mt-3">
    <thead>
        <tr>
            <td>Tên đề cương</td>
            <td>Tín chỉ</td>
            <td>Tên giảng viên</td>
            <!--<td>Khóa học</td>-->
        </tr>
    </thead>
    <tbody>
        <c:choose>
            <c:when test="${not empty soutlines}">
                <c:forEach items="${soutlines}" var="o">
                    <tr>
                        <td>${o.nameCourse}</td>
                        <td>${o.credit}<td>
                        <td>${o.teacherName}</td>
                        <!--<td>${o.term}</td>-->
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="4" class="text-center">Không có dữ liệu</td>
                </tr>
            </c:otherwise>
        </c:choose>
    </tbody>
</table>
