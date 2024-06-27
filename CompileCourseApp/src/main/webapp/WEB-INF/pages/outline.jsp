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

    <div class="form-floating mb-3">
        <c:forEach items="${compilation.outlineTermSet}" var="outlineTerm" varStatus="status">
            <div class="form-group mb-3 outline-term">
                <label for="academicId" class="form-label">Khóa học:</label>
                <select class="form-select" name="outlineTermSet[${status.index}].academicId.id">
                    <option value="">-- Chọn khóa học --</option>
                    <c:forEach items="${academicTerms}" var="term">
                        <c:choose>
                            <c:when test="${outlineTerm.academicId.id == term.id}">
                                <option value="${term.id}" selected>${term.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${term.id}">${term.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
        </c:forEach>
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
        <table class="table">
            <thead>
                <tr>
                    <th>Bài đánh giá</th>
                    <th>Tỷ lệ %</th>
                    <th>Phương pháp đánh giá</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody id="outlineMethodRows">
                <c:forEach items="${compilation.outlineMethodSet}" var="outlineMethod" varStatus="status">
                    <tr>
                        <td><form:input path="outlineMethodSet[${status.index}].name" class="form-control"/></td>
                        <td><form:input path="outlineMethodSet[${status.index}].weight" class="form-control"/></td>
                        <td>
                            <form:select path="outlineMethodSet[${status.index}].evaluationMethod.id" class="form-select">
                                <form:option value="">-- Chọn phương pháp đánh giá --</form:option>
                                <c:forEach items="${evaluationMethods}" var="method">
                                    <form:option value="${method.id}">${method.name}</form:option>
                                </c:forEach>
                            </form:select>
                        </td>
                        <td><a href="#" class="btn btn-danger delete-row">&times;</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <button type="button" class="btn btn-primary" id="addOutlineMethod">Thêm cột điểm</button>
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
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            const addOutlineMethodButton = document.getElementById('addOutlineMethod');
            const outlineMethodRows = document.getElementById('outlineMethodRows');
            let index = outlineMethodRows.children.length;

            addOutlineMethodButton.addEventListener('click', function () {
                if (index < 5) { // Maximum 5 rows
                    const newRow = `
            <tr>
                <td><input type="text" class="form-control" name="outlineMethodSet[${index}].name" /></td>
                <td><input type="text" class="form-control" name="outlineMethodSet[${index}].weight" /></td>
                <td>
                    <select class="form-select" name="outlineMethodSet[${index}].evaluationMethod.id">
                        <option value="">-- Chọn phương pháp đánh giá --</option>
        <c:forEach items="${evaluationMethods}" var="method">
                            <option value="${method.id}">${method.name}</option>
        </c:forEach>
                    </select>
                </td>
                <td><a href="#" class="btn btn-danger delete-row">&times;</a></td>
            </tr>
        `;
                    outlineMethodRows.insertAdjacentHTML('beforeend', newRow);
                    index++; // Increment index for the next row
                } else {
                    alert('You can only add up to 5 evaluation methods.');
                }
            });

            outlineMethodRows.addEventListener('click', function (event) {
                if (event.target.classList.contains('delete-row')) {
                    event.preventDefault();
                    event.target.closest('tr').remove();
                    index--; // Decrement index when row is deleted
                }
            });
        });
    </script>
</form:form>

