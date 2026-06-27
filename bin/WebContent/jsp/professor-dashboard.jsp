<%
if(session.getAttribute("user") == null){

    response.sendRedirect(
            request.getContextPath()
            + "/jsp/login.jsp"
    );

    return;
}
%>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Professor Dashboard</title>
</head>

<body>

<h1>Welcome Professor</h1>

<a href="${pageContext.request.contextPath}/logout">
    Logout
</a>

</body>
</html>