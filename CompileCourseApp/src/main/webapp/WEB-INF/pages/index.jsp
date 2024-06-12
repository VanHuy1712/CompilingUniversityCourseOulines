<%-- 
    Document   : index
    Created on : Jun 4, 2024, 3:23:20 PM
    Author     : Huy
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-info mt-1">QUẢN TRỊ ĐỀ CƯƠNG</h1>

<div>
    <a class="btn btn-success" href="#">Biên soạn đề cương mới</a>
</div>
<table class="table table-striped mt-1">
    <thead>
        <tr>
            <th>Id</th>
            <th>Môn học</th>
            <th>Khóa</th>
            <th>Giảng viên biên soạn</th>
            <th>Ngày tạo</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${outlines}" var="o">
            <tr>
                <td>${o.id}</td>

                <td><a class="btn btn-infor" href="<c:url value="/outline/${o.id}"/>">${o.course.name}</a></td>

                <c:forEach items="${outlineTerms}" var="t">
                    <c:if test="${t.outlineId.id eq o.id}">
                        <td>${t.academicId.name}</td>
                    </c:if>
                </c:forEach>

                <td>${o.user.firstName} ${o.user.lastName}</td>
                <td>${o.createDate}</td>
                <td>
                    <button class="btn btn-info">Cập nhật</button>

                    <button class="btn btn-danger" >Xóa</button>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
