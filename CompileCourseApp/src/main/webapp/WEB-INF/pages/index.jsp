<%-- 
    Document   : index
    Created on : Jun 4, 2024, 3:23:20 PM
    Author     : Huy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-info mt-1">DANH MỤC SẢN PHẨM</h1>
<a href="<c:url value="/products" />" class="btn btn-success mb-1">Thêm sản phẩm</a>
<table class="table table-striped">
    <tr>
        <th></th>
        <th>Id</th>
        <th>Tên sản phẩm</th>
        <th>Gía sản phẩm</th>
        <th></th>
    </tr>
    <c:forEach items="${products}" var="p">
        <tr>
            <td><img class="rounded img-fluid" src="${p.image}" width="200" alt="${p.name}"></td>
            <td>${p.name}</td>
            <td>${p.name}</td>
            <td>${p.price} VNĐ</td>

            <td>
                <c:url value="/products/${p.id}" var="url" />
                <c:url value="/api/products/${p.id}/" var="urlDelete" />
                <button class="btn btn-danger" onclick="deleteProduct('${urlDelete}', ${p.id})">Xóa</button>
                <a href="${url}" class="btn btn-info">Cập nhật</a>
            </td>
        </tr>
    </c:forEach>
</table>
