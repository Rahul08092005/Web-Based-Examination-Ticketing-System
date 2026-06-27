<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>

    <title>Export Students</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

    <style>

        body{
            background:#f4f7fc;
            min-height:100vh;
        }

        .export-card{
            max-width:800px;
            margin:auto;
            margin-top:60px;
            border:none;
            border-radius:20px;
            overflow:hidden;
            box-shadow:0 8px 25px rgba(0,0,0,0.1);
        }

        .card-header{
            background:#fd7e14;
            color:white;
            padding:20px;
        }

        .info-box{
            background:#fff8f2;
            border-left:4px solid #fd7e14;
            padding:15px;
            border-radius:10px;
        }

        .btn-export{
            background:#fd7e14;
            color:white;
            border:none;
        }

        .btn-export:hover{
            background:#e86c08;
            color:white;
        }

        .form-label{
            font-weight:600;
        }

    </style>

</head>

<body>

<div class="container">

    <div class="card export-card">

        <div class="card-header">

            <h3 class="mb-0">

                <i class="fa-solid fa-file-export"></i>
                Export Students By Department & Semester

            </h3>

        </div>

        <div class="card-body p-4">

            <div class="info-box mb-4">

                <strong>
                    Export Student Records
                </strong>

                <br><br>

                Select a department and semester.

                The system will generate a CSV file
                containing all matching students.

            </div>

            <form action="<%=request.getContextPath()%>/export-students"
                  method="post">

                <div class="row">

                    <div class="col-md-6 mb-4">

                        <label class="form-label">
                            Department
                        </label>

                        <select name="department"
                                class="form-select">

                            <option value="CSE">
                                Computer Science Engineering
                            </option>

                            <option value="CSE-DS">
                                Computer Science - Data Science
                            </option>

                            <option value="ISE">
                                Information Science Engineering
                            </option>

                            <option value="ECE">
                                Electronics & Communication
                            </option>

                            <option value="EEE">
                                Electrical Engineering
                            </option>

                            <option value="ME">
                                Mechanical Engineering
                            </option>

                            <option value="CV">
                                Civil Engineering
                            </option>

                        </select>

                    </div>

                    <div class="col-md-6 mb-4">

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

                <div class="d-flex justify-content-between">

                    <a href="<%=request.getContextPath()%>/students"
                       class="btn btn-secondary">

                        <i class="fa-solid fa-arrow-left"></i>
                        Back

                    </a>

                    <button type="submit"
                            class="btn btn-export px-4">

                        <i class="fa-solid fa-download"></i>
                        Export CSV

                    </button>

                </div>

            </form>

        </div>

    </div>

</div>

</body>
</html>