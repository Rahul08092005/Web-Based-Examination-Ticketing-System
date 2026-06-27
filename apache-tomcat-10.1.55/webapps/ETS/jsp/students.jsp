<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Student" %>

<%
List<Student> students =
        (List<Student>) request.getAttribute("students");
%>

<!DOCTYPE html>
<html>
<head>

    <title>Student Management</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

    <style>

        body{
            background:#f4f7fc;
        }

        .page-title{
            font-weight:700;
            color:#1e3c72;
        }

        .card{
            border:none;
            border-radius:15px;
            box-shadow:0 4px 15px rgba(0,0,0,0.08);
        }

        .stats-card h2{
            font-weight:700;
        }

        .table{
            margin-bottom:0;
        }

        .table thead{
            background:#1e4fa8;
            color:white;
        }

        .table th{
            padding:15px;
        }

        .table td{
            vertical-align:middle;
        }

        .btn{
            border-radius:10px;
        }

        .search-box{
            border-radius:10px;
        }

    </style>

</head>
<body>

<div class="container-fluid p-4">

    <!-- HEADER -->

    <div class="d-flex justify-content-between align-items-center mb-4">

        <div>

            <h2 class="page-title">
                Student Management
            </h2>

            <p class="text-muted mb-0">
                Manage students, imports, exports and records
            </p>

        </div>

        <a href="jsp/add-student.jsp"
           class="btn btn-primary">

            <i class="fa-solid fa-user-plus"></i>
            Add Student

        </a>

    </div>

    <!-- STATISTICS -->

    <div class="row mb-4">

        <div class="col-md-3">

            <div class="card stats-card">

                <div class="card-body">

                    <h6 class="text-muted">
                        Total Students
                    </h6>

                    <h2 class="text-primary">
                        <%= students == null ? 0 : students.size() %>
                    </h2>

                </div>

            </div>

        </div>

        <div class="col-md-3">

            <div class="card stats-card">

                <div class="card-body">

                    <h6 class="text-muted">
                        Department
                    </h6>

                    <h2 class="text-success">
                        CSE-DS
                    </h2>

                </div>

            </div>

        </div>

        <div class="col-md-3">

            <div class="card stats-card">

                <div class="card-body">

                    <h6 class="text-muted">
                        Semester
                    </h6>

                    <h2 class="text-warning">
                        3
                    </h2>

                </div>

            </div>

        </div>

        <div class="col-md-3">

            <div class="card stats-card">

                <div class="card-body">

                    <h6 class="text-muted">
                        Active Records
                    </h6>

                    <h2 class="text-danger">
                        <%= students == null ? 0 : students.size() %>
                    </h2>

                </div>

            </div>

        </div>

    </div>

    <!-- ACTION BAR -->

    <div class="card mb-4">

        <div class="card-body">

            <div class="row g-2">

                <div class="col-md-3">

                    <a href="jsp/import-students.jsp"
                       class="btn btn-success w-100">

                        <i class="fa-solid fa-file-import"></i>
                        Import CSV

                    </a>

                </div>

                <div class="col-md-3">

                    <a href="jsp/export-filter.jsp"
                       class="btn btn-warning text-white w-100">

                        <i class="fa-solid fa-file-export"></i>
                        Export By Branch & Semester

                    </a>

                </div>

                <div class="col-md-6">

                    <form action="search-student" method="get">

                        <div class="input-group">

                            <input type="text"
                                   name="keyword"
                                   class="form-control search-box"
                                   placeholder="Search by Name / USN / Temp Roll">

                            <button class="btn btn-primary">

                                <i class="fa-solid fa-magnifying-glass"></i>

                            </button>

                        </div>

                    </form>

                </div>

            </div>

        </div>

    </div>

    <!-- TABLE -->

    <div class="card">

        <div class="card-header bg-primary text-white">

            <h5 class="mb-0">

                <i class="fa-solid fa-users"></i>
                Student Records

            </h5>

        </div>

        <div class="card-body p-0">

            <table class="table table-hover table-striped">

                <thead>

                <tr>

                    <th>ID</th>
                    <th>Name</th>
                    <th>USN</th>
                    <th>Branch</th>
                    <th>Semester</th>
                    <th>Department</th>
                    <th width="150">Actions</th>

                </tr>

                </thead>

                <tbody>

                <%
                if(students != null){

                    for(Student s : students){
                %>

                <tr>

                    <td><%= s.getId() %></td>

                    <td>
                        <strong>
                            <%= s.getFullName() %>
                        </strong>
                    </td>

                    <td><%= s.getUsn() %></td>

                    <td><%= s.getBranch() %></td>

                    <td>
                        <span class="badge bg-info">
                            Sem <%= s.getSemester() %>
                        </span>
                    </td>

                    <td><%= s.getDepartment() %></td>

                    <td>

                        <a href="edit-student?id=<%=s.getId()%>"
                           class="btn btn-warning btn-sm">

                            <i class="fa-solid fa-pen"></i>

                        </a>

                        <a href="delete-student?id=<%=s.getId()%>"
                           class="btn btn-danger btn-sm">

                            <i class="fa-solid fa-trash"></i>

                        </a>

                    </td>

                </tr>

                <%
                    }
                }
                %>

                </tbody>

            </table>

        </div>

    </div>

</div>

</body>
</html>