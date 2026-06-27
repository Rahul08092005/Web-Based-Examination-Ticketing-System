```jsp
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    String id =
        request.getParameter("id");

    String name =
        request.getParameter("name");

    String usn =
        request.getParameter("usn");

    String branch =
        request.getParameter("branch");

    String semester =
        request.getParameter("semester");

    String department =
        request.getParameter("department");
%>

<!DOCTYPE html>
<html>
<head>

    <title>Edit Student</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

    <style>

        body{
            background:#f4f7fc;
            min-height:100vh;
        }

        .edit-card{
            max-width:800px;
            margin:auto;
            margin-top:50px;
            border:none;
            border-radius:20px;
            overflow:hidden;
            box-shadow:0 10px 30px rgba(0,0,0,0.1);
        }

        .card-header{
            background:#0d6efd;
            color:white;
            padding:20px;
        }

        .form-control,
        .form-select{
            border-radius:10px;
            padding:12px;
        }

        .btn-save{
            background:#198754;
            color:white;
            border:none;
        }

        .btn-save:hover{
            background:#157347;
            color:white;
        }

        .info-box{
            background:#eef5ff;
            border-left:4px solid #0d6efd;
            padding:15px;
            border-radius:10px;
        }

    </style>

</head>

<body>

<div class="container">

    <div class="card edit-card">

        <div class="card-header">

            <h3 class="mb-0">

                <i class="fa-solid fa-user-pen"></i>
                Edit Student

            </h3>

        </div>

        <div class="card-body p-4">

            <div class="info-box mb-4">

                Update student information and save changes.

            </div>

            <form action="<%=request.getContextPath()%>/update-student"
                  method="post">

                <input type="hidden"
                       name="id"
                       value="<%=id%>">

                <div class="row">

                    <div class="col-md-6 mb-3">

                        <label class="form-label">
                            Full Name
                        </label>

                        <input type="text"
                               class="form-control"
                               name="name"
                               value="<%=name%>"
                               required>

                    </div>

                    <div class="col-md-6 mb-3">

                        <label class="form-label">
                            USN
                        </label>

                        <input type="text"
                               class="form-control"
                               name="usn"
                               value="<%=usn%>"
                               required>

                    </div>

                </div>

                <div class="row">

                    <div class="col-md-6 mb-3">

                        <label class="form-label">
                            Branch
                        </label>

                        <input type="text"
                               class="form-control"
                               name="branch"
                               value="<%=branch%>"
                               required>

                    </div>

                    <div class="col-md-6 mb-3">

                        <label class="form-label">
                            Semester
                        </label>

                        <input type="number"
                               class="form-control"
                               name="semester"
                               value="<%=semester%>"
                               required>

                    </div>

                </div>

                <div class="mb-4">

                    <label class="form-label">
                        Department
                    </label>

                    <input type="text"
                           class="form-control"
                           name="department"
                           value="<%=department%>"
                           required>

                </div>

                <div class="d-flex justify-content-between">

                    <a href="<%=request.getContextPath()%>/students"
                       class="btn btn-secondary">

                        <i class="fa-solid fa-arrow-left"></i>
                        Back

                    </a>

                    <button type="submit"
                            class="btn btn-save px-4">

                        <i class="fa-solid fa-floppy-disk"></i>
                        Save Changes

                    </button>

                </div>

            </form>

        </div>

    </div>

</div>

</body>
</html>
```
