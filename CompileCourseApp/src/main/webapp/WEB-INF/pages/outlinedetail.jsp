<%-- Document : outline
    Created on : Jun 10, 2024, 9:46:50 PM
    Author : DELL
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-info mt-1">ĐỀ CƯƠNG MÔN HỌC</h1>
<div class="section">
    <div class="section-title"><strong>I. Thông Tin Tổng Quát</strong></div> <br>
    <div class="content">
        <p><strong>Tên Môn Học:</strong> <span>${outline.course.name}</span></p>
        <p><strong>Phương Thức Giảng Dạy:</strong> <span>${outline.techingMethod}</span></p>
        <p><strong>Ngôn Ngữ Giảng Dạy:</strong> <span>${outline.language}</span></p>
        <p><strong>Thuộc Khối Kiến Thức:</strong> <span>${outline.knowledge}</span></p>
        <p><strong>Số Tín Chỉ:</strong> <span>${outline.credit}</span></p>
    </div>
</div>
<div class="section">
    <div class="section-title"><strong>II. Thông Tin Về Môn Học</strong></div> <br>
    <div class="content">
        <p><strong>Mô Tả Môn Học:</strong> <br> <span>${outline.description}</span> </p>
        <p><strong>Mục Tiêu Môn Học:</strong> <br> <span>${outline.objectives}</span></p>
        <p><strong>Phương Pháp Đánh Giá:</strong></p>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Thành phần đánh giá</th>
                    <th>Mô tả</th>
                    <th>Tỷ lệ</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="totalPercent" value="0" />
                <c:forEach items="${outlineMethods}" var="m">
                    <c:if test="${m.outline.id eq outline.id}">
                        <tr>
                            <td>${m.evaluationMethod.name}</td>
                            <td>${m.name}</td>
                            <td>${m.weight}%</td>
                            <c:set var="totalPercent" value="${totalPercent + m.weight}" />
                        </tr>
                    </c:if>
                </c:forEach>
                <tr class="total">
                    <td colspan="2"><strong>Tổng cộng:</strong></td>
                    <td>${totalPercent} %</td>
                </tr>
            </tbody>
        </table>
        <p><strong>Quy Định Môn Học:</strong> <br> <span>${outline.policy}</span> </p>
    </div>
</div>


<link rel="stylesheet" href="<c:url value="/css/styles.css" />" />