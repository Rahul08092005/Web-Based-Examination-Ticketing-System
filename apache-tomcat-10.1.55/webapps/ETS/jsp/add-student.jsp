<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>

    <title>Add Student</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

    <style>

        body{
            background:#f4f7fc;
            min-height:100vh;
        }

        .form-card{
            max-width:800px;
            margin:auto;
            margin-top:60px;
            border:none;
            border-radius:20px;
            box-shadow:0 8px 30px rgba(0,0,0,0.12);
        }

        .card-header{
            background:#1e4fa8;
            color:white;
            border-radius:20px 20px 0 0 !important;
            padding:20px;
        }

        .form-label{
            font-weight:600;
        }

        .btn-save{
            background:#1e4fa8;
            color:white;
            border:none;
        }

        .btn-save:hover{
            background:#163b7a;
            color:white;
        }

    </style>

</head>

<body>

<div class="container">

    <div class="card form-card">

        <div class="card-header">

            <h3 class="mb-0">
                <i class="fa-solid fa-user-plus"></i>
                Add New Student
            </h3>

        </div>

        <div class="card-body p-4">

            <form action="<%=request.getContextPath()%>/add-student"
                  method="post">

                <div class="row">

                    <div class="col-md-6 mb-3">

                        <label class="form-label">
                            Full Name
                        </label>

                        <input type="text"
                               name="name"
                               class="form-control"
                               required>

                    </div>

                    <div class="col-md-6 mb-3">

                        <label class="form-label">
                            USN
                        </label>

                        <input type="text"
                               name="usn"
                               class="form-control"
                               required>

                    </div>

                </div>

                <div class="row">

                    <div class="col-md-6 mb-3">

                        <label class="form-label">
                            Branch
                        </label>

                        <input type="text"
                               name="branch"
                               class="form-control"
                               placeholder="Computer Science - Data Science"
                               required>

                    </div>

                    <div class="col-md-6 mb-3">

                        <label class="form-label">
                            Semester
                        </label>

                        <select name="semester"
                                class="form-select">

                            <option value="1">Semester 1</option>
                            <option value="2">Semester 2</option>
                            <option value="3">Semester 3</option>
                            <option value="4">Semester 4</option>
                            <option value="5">Semester 5</option>
                            <option value="6">Semester 6</option>
                            <option value="7">Semester 7</option>
                            <option value="8">Semester 8</option>

                        </select>

                    </div>

                </div>

                <div class="row">

                    <div class="col-md-12 mb-4">

                        <label class="form-label">
                            Department
                        </label>

                        <input type="text"
                               name="department"
                               class="form-control"
                               placeholder="CSE-DS"
                               required>

                    </div>

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
                        Save Student

                    </button>

                </div>

            </form>

        </div>

    </div>

</div>

</body>
</html>