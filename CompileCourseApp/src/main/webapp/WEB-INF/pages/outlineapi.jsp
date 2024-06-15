<%-- 
    Document   : outlineapi
    Created on : Jun 15, 2024, 4:38:32 PM
    Author     : Huy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table>
    <c:forEach items="${outline}" var="o">
        <p>${o.id}</p>
    </c:forEach>
</table>
