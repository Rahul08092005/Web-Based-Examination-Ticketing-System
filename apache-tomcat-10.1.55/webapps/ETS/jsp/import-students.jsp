<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>

    <title>Import Students CSV</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

    <style>

        body{
            background:#f4f7fc;
            min-height:100vh;
        }

        .upload-card{
            max-width:850px;
            margin:auto;
            margin-top:60px;
            border:none;
            border-radius:20px;
            overflow:hidden;
            box-shadow:0 8px 25px rgba(0,0,0,0.1);
        }

        .card-header{
            background:#198754;
            color:white;
            padding:20px;
        }

        .upload-zone{
            border:2px dashed #198754;
            border-radius:15px;
            padding:50px;
            text-align:center;
            background:#f8fffa;
            transition:.3s;
        }

        .upload-zone:hover{
            background:#eefcf3;
        }

        .upload-icon{
            font-size:60px;
            color:#198754;
            margin-bottom:15px;
        }

        .btn-upload{
            background:#198754;
            color:white;
            border:none;
        }

        .btn-upload:hover{
            background:#146c43;
            color:white;
        }

        .info-box{
            background:#f8f9fa;
            border-left:4px solid #198754;
            padding:15px;
            border-radius:10px;
        }

    </style>

</head>

<body>

<div class="container">

    <div class="card upload-card">

        <div class="card-header">

            <h3 class="mb-0">
                <i class="fa-solid fa-file-csv"></i>
                Import Students CSV
            </h3>

        </div>

        <div class="card-body p-4">

            <div class="info-box mb-4">

                <strong>CSV Format Required:</strong>

                <br><br>

                Full Name, Department Name, USN, Temp Roll(If not available, leave blank), Semester, Department Code

                <br>

                Example:

                <br>

                Radhika D Chougale,
                Computer Science - Data Science,
                1DT24CD036,
                ,
                4,
                CS-DS

                <br>
              
                <br>

                Rahul,
                Computer Science - Data Science,
                1DT24CD037,
                ,
                4,
                CSE-DS

            </div>

            <form action="<%=request.getContextPath()%>/import-students"
                  method="post"
                  enctype="multipart/form-data">

                <div class="upload-zone">

                    <div class="upload-icon">

                        <i class="fa-solid fa-cloud-arrow-up"></i>

                    </div>

                    <h4>Select Student CSV File</h4>

                    <p class="text-muted">
                        Upload student records in CSV format
                    </p>

                    <input type="file"
                           name="csvFile"
                           class="form-control mt-3"
                           accept=".csv"
                           required>

                </div>

                <div class="d-flex justify-content-between mt-4">

                    <a href="<%=request.getContextPath()%>/students"
                       class="btn btn-secondary">

                        <i class="fa-solid fa-arrow-left"></i>
                        Back

                    </a>

                    <button type="submit"
                            class="btn btn-upload px-4">

                        <i class="fa-solid fa-upload"></i>
                        Upload CSV

                    </button>

                </div>

            </form>

        </div>

    </div>

</div>

</body>
</html>