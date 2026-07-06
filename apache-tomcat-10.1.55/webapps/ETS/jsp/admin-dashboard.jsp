<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>


<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">


<style>


*{
    margin:0;
    padding:0;
    box-sizing:border-box;
    font-family:'Poppins',sans-serif;
}


body{
    background:#f4f7fc;
}


/* SIDEBAR */


.sidebar{
    width:250px;
    height:100vh;
    position:fixed;
    background:#1f4ea8;
    color:white;
}


.logo{
    padding:25px;
    font-size:22px;
    font-weight:700;
    border-bottom:1px solid rgba(255,255,255,.2);
}


.menu{
    margin-top:20px;
}


.menu a{
    display:block;
    color:white;
    text-decoration:none;
    padding:15px 25px;
    transition:.3s;
}


.menu a:hover{
    background:rgba(255,255,255,.15);
}


/* MAIN */


.main{
    margin-left:250px;
}


/* TOPBAR */


.topbar{
    background:white;
    height:70px;
    display:flex;
    justify-content:space-between;
    align-items:center;
    padding:0 40px;
    box-shadow:0 2px 10px rgba(0,0,0,.08);
}


.topbar h2{
    color:#333;
}


.profile{
    font-weight:600;
    color:#1f4ea8;
}


/* CONTENT */


.content{
    padding:40px;
}


.cards{
    display:grid;
    grid-template-columns:repeat(4,1fr);
    gap:25px;
}


.card{
    background:white;
    border-radius:15px;
    padding:25px;
    box-shadow:0 5px 15px rgba(0,0,0,.08);
    transition:.3s;
}


.card:hover{
    transform:translateY(-5px);
}


.card-title{
    color:#777;
    font-size:15px;
}


.card-value{
    font-size:40px;
    font-weight:700;
    margin-top:10px;
    color:#1f4ea8;
}


.section{
    margin-top:35px;
}


.panel{
    background:white;
    border-radius:15px;
    padding:25px;
    box-shadow:0 5px 15px rgba(0,0,0,.08);
}


.panel h3{
    margin-bottom:15px;
}


.activity{
    padding:12px 0;
    border-bottom:1px solid #eee;
}


/* QUICK ACTIONS */


.actions{
    display:flex;
    gap:15px;
    margin-top:20px;
}


.btn{
    padding:12px 20px;
    background:#1f4ea8;
    color:white;
    text-decoration:none;
    border-radius:8px;
    font-size:14px;
}


.btn:hover{
    background:#163d86;
}


</style>
</head>


<body>


<!-- SIDEBAR -->


<div class="sidebar">


    <div class="logo">
        ETS Portal
    </div>


    <div class="menu">


        <a href="<%=request.getContextPath()%>/dashboard">
            Dashboard
        </a>


        <a href="<%=request.getContextPath()%>/students">
            Students
        </a>


        <a href="<%=request.getContextPath()%>/subjects">
            Subjects
        </a>


        <a href="#">
            Attendance
        </a>


        <a href="#">
            Eligibility
        </a>


        <a href="#">
            Admit Cards
        </a>


        <a href="<%=request.getContextPath()%>/logout">
            Logout
        </a>


    </div>


</div>


<!-- MAIN -->


<div class="main">


    <!-- TOP BAR -->


    <div class="topbar">


        <h2>Dashboard</h2>


        <div class="profile">
            Welcome Admin
        </div>


    </div>


    <!-- CONTENT -->


    <div class="content">


        <div class="cards">


            <div class="card">
                <div class="card-title">
                    Total Students
                </div>


                <div class="card-value">
                    ${studentCount}
                </div>
            </div>


            <div class="card">
                <div class="card-title">
                    Subjects
                </div>


                <div class="card-value">
                    12
                </div>
            </div>


            <div class="card">
                <div class="card-title">
                    Faculty
                </div>


                <div class="card-value">
                    18
                </div>
            </div>


            <div class="card">
                <div class="card-title">
                    Eligible Students
                </div>


                <div class="card-value">
                    4
                </div>
            </div>


        </div>


        <div class="section">


            <div class="panel">


                <h3>Quick Actions</h3>


                <div class="actions">


                    <a class="btn"
                       href="<%=request.getContextPath()%>/students">
                        Manage Students
                    </a>


                    <a class="btn"
                       href="<%=request.getContextPath()%>/jsp/import-students.jsp">
                        Import CSV
                    </a>


                    <a class="btn"
                       href="<%=request.getContextPath()%>/jsp/export-filter.jsp">
                        Export CSV
                    </a>


                </div>


            </div>


        </div>


        <div class="section">


            <div class="panel">


                <h3>Recent Activity</h3>


                <div class="activity">
                    Student records updated
                </div>


                <div class="activity">
                    CSV imported successfully
                </div>


                <div class="activity">
                    Dashboard loaded
                </div>


            </div>


        </div>


    </div>


</div>


</body>
</html>
