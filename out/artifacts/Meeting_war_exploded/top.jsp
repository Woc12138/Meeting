<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="page-header">
    <div class="header-title">
       Meeting会议管理系统
    </div>
    <div class="header-quicklink">
        <strong><c:if test="${loginUser!=null}">${loginUser.employeename}</c:if></strong>
        <a href="login.jsp">注销</a>
    </div>
</div>
