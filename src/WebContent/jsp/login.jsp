<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Examination Ticketing System</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<div class="container">

    <div class="left-panel">

        <img src="${pageContext.request.contextPath}/images/college_logo.png"
             class="logo">

        <h1>Examination Ticketing System</h1>

        <h3>Dayananda Sagar Academy of Technology & Management</h3>

        <p>
            Automated Examination Ticketing &
            Eligibility Management System
        </p>

    </div>

    <div class="right-panel">

        <div class="login-card">

            <h2>Login</h2>

            <form action="${pageContext.request.contextPath}/login"
                  method="post">

                <label>Role</label>

                <select name="role" required>

                    <option value="">Select Role</option>

                    <option value="ADMIN">Admin</option>

                    <option value="HOD">HOD</option>

                    <option value="PROFESSOR">Professor</option>

                    <option value="EXAM_STAFF">Exam Staff</option>

                </select>

                <label>Username</label>

                <input type="text"
                       name="username"
                       required>

                <label>Password</label>

                <input type="password"
                       name="password"
                       required>

                <button type="submit">

                    Login

                </button>

            </form>

            <%
                String error =
                        (String)request.getAttribute("error");

                if(error != null){
            %>

            <p class="error"><%= error %></p>

            <%
                }
            %>

        </div>

    </div>

</div>

</body>
</html>