<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Examination Ticketing System</title>

<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&family=Playfair+Display:wght@600;700;800&display=swap" rel="stylesheet">

<style>

*{
    margin:0;
    padding:0;
    box-sizing:border-box;
    font-family:'Poppins',sans-serif;
}

body{
    height:100vh;
    overflow:hidden;
}

.container{
    display:flex;
    height:100vh;
    width:100%;
}

/* LEFT SIDE */

.left-panel{
    width:70%;
    position:relative;
}

.left-panel img{
    width:100%;
    height:100%;
    object-fit:cover;
}

.overlay{
    position:absolute;
    inset:0;
    background:rgba(0,0,0,0.25);
}

.project-title{
    position:absolute;
    top:60px;
    left:60px;
    color:white;
}

.project-title h1{
    font-size:48px;
    font-weight:700;
    line-height:1.2;
}

.project-title p{
    margin-top:20px;
    font-size:18px;
    opacity:0.95;
}

/* RIGHT SIDE */

.right-panel{
    width:30%;
    background:white;
    display:flex;
    justify-content:center;
    align-items:center;
}

.login-card{
    width:85%;
}

.logo{
    text-align:center;
    margin-bottom:25px;
}

.logo img{
    width:110px;
}

.logo h2{
    color:#1f4ea8;
    margin-top:10px;
    font-size:28px;
    font-weight:700;
}

.logo p{
    color:#666;
    font-size:14px;
}

.form-group{
    margin-bottom:20px;
}

.form-group label{
    display:block;
    margin-bottom:8px;
    font-weight:500;
}

.form-control{
    width:100%;
    padding:14px;
    border:1px solid #d9d9d9;
    border-radius:8px;
    font-size:15px;
}

.form-control:focus{
    outline:none;
    border-color:#1f4ea8;
}

.login-btn{
    width:100%;
    padding:14px;
    border:none;
    border-radius:8px;
    background:#1f4ea8;
    color:white;
    font-size:16px;
    font-weight:600;
    cursor:pointer;
    transition:.3s;
}

.login-btn:hover{
    background:#163c84;
}

.error{
    color:red;
    margin-bottom:15px;
    text-align:center;
}

.footer-text{
    text-align:center;
    margin-top:20px;
    color:#888;
    font-size:13px;
}

@media(max-width:900px){

    .left-panel{
        display:none;
    }

    .right-panel{
        width:100%;
    }

}

</style>
</head>

<body>

<div class="container">

    <!-- LEFT IMAGE -->

    <div class="left-panel">

        <!-- Replace image path -->
        <img src="<%=request.getContextPath()%>/image/dsatm-campus.png" alt="Campus">

        <div class="overlay"></div>

        <div class="project-title">
    
</div>

    </div>

    <!-- LOGIN PANEL -->

    <div class="right-panel">

        <div class="login-card">

            <div class="logo">

                <!-- Replace logo path -->
                <img src="<%=request.getContextPath()%>/image/dsatm-logo.png"
                    alt="DSATM Logo"
                        class="dsatm-logo">
                <h2>DSATM</h2>

                <h2>Examination Management Portal</h2>

            </div>

            <% if(request.getParameter("error") != null){ %>
                <div class="error">
                    Invalid Username or Password
                </div>
            <% } %>

            <form action="<%=request.getContextPath()%>/login"
                  method="post">

                <div class="form-group">
                    <label>Username</label>
                    <input type="text"
                           name="username"
                           class="form-control"
                           required>
                </div>

                <div class="form-group">
                    <label>Password</label>
                    <input type="password"
                           name="password"
                           class="form-control"
                           required>
                </div>

                <button class="login-btn">
                    Login
                </button>

            </form>

            <div class="footer-text">
                © 2026 DSATM Examination Department
            </div>

        </div>

    </div>

</div>

</body>
</html>