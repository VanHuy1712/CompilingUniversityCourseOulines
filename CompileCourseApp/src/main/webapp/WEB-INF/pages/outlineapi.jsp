<%-- 
    Document   : outlineapi
    Created on : Jun 15, 2024, 4:38:32 PM
    Author     : Huy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1 class="text-center text-info mt-1">BIÊN SOẠN ĐỀ CƯƠNG</h1>


<c:url value="/outlines" var="action" />
<form:form method="post" action="${action}" modelAttribute="compilation">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />

    <div class="form-floating mb-3">
        <form:select class="form-select" path="course">
            <form:option value="" label="-- Chọn môn học --"/>
            <form:options items="${courses}" itemValue="id" itemLabel="name" />
        </form:select>
        <label for="course" class="form-label">Môn học:</label>
    </div>






    <div class="form-group mb-3 mt-3">
        <label>Ngôn ngữ:</label><br>
        <form:radiobutton path="language" value="Việt Nam" label="Việt Nam"/>
        <form:radiobutton path="language" value="Tiếng Anh" label="Tiếng Anh"/>
        <form:radiobutton path="language" value="Cả hai" label="Cả hai"/>
    </div>

    <div class="form-group mb-3 mt-3">
        <label>Phương pháp giảng dạy:</label><br>
        <form:radiobutton path="techingMethod" value="Trực tiếp" label="Trực tiếp"/>
        <form:radiobutton path="techingMethod" value="Trực tuyến" label="Trực tuyến"/>
        <form:radiobutton path="techingMethod" value="Kết hợp" label="Kết hợp"/>
    </div>

    <div class="form-group mb-3 mt-3">
        <label>Kiến thức:</label><br>
        <form:radiobutton path="knowledge" value="Kiến thức cơ sở" label="Kiến thức cơ sở"/>
        <form:radiobutton path="knowledge" value="Kiến thức ngành" label="Kiến thức ngành"/>
    </div>

    <div class="form-floating mb-3">
        <form:input class="form-control" id="credit" placeholder="Số tín chỉ" path="credit" />
        <label for="credit" class="form-label">Số tín chỉ:</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:textarea class="form-control" id="description" path="description" placeholder="Mô tả"/>
        <label for="description">Mô tả</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:textarea class="form-control" id="objectives" placeholder="Mục tiêu" path="objectives" />
        <label for="objectives">Mục tiêu</label>
    </div>



    <div class="form-floating mb-3 mt-3">
        <form:textarea class="form-control" id="policy" placeholder="Quy định" path="policy" />
        <label for="policy">Quy định</label>
    </div>

    <div class="form-floating">
        <button class="btn btn-info mt-1" type="submit">
            <c:choose>
                <c:when test="${compilation.id > 0}"> Cập nhập </c:when>
                <c:otherwise> Lưu </c:otherwise>
            </c:choose>
        </button>
        <form:hidden path="id" />
    </div>
</form:form>

