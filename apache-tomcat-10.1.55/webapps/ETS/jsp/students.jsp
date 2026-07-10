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

        /* Semester tabs */
        .sem-tab {
            border: 2px solid #1e4fa8;
            color: #1e4fa8;
            background: white;
            font-weight: 600;
            border-radius: 10px;
            padding: 8px 18px;
        }

        .sem-tab:hover {
            background: #eaf0fd;
        }

        .sem-tab.active {
            background: #1e4fa8;
            color: white;
        }

        /* Branch cards */
        .branch-card {
            border: 2px solid #e2e8f0;
            border-radius: 14px;
            padding: 14px 16px;
            cursor: pointer;
            height: 100%;
            transition: all .15s ease;
            background: white;
        }

        .branch-card:hover {
            border-color: #1e4fa8;
            box-shadow: 0 4px 12px rgba(30,79,168,.15);
        }

        .branch-card.active {
            border-color: #1e4fa8;
            background: #eaf0fd;
        }

        .branch-card .branch-code {
            font-weight: 700;
            color: #1e3c72;
        }

        .branch-card .branch-count {
            font-size: 12px;
            color: #6b7280;
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
                        Currently Viewing
                    </h6>

                    <h2 class="text-success" id="statBranchLabel" style="font-size:1.4rem;">
                        All Branches
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

                    <h2 class="text-warning" id="statSemLabel">
                        All
                    </h2>

                </div>

            </div>

        </div>

        <div class="col-md-3">

            <div class="card stats-card">

                <div class="card-body">

                    <h6 class="text-muted">
                        Showing
                    </h6>

                    <h2 class="text-danger" id="statCountLabel">
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

    <!-- SEMESTER TABS -->
    <div class="card mb-4">
        <div class="card-body">
            <h6 class="mb-3">Select Semester</h6>
            <div class="d-flex flex-wrap gap-2" id="semTabs">
                <button type="button" class="sem-tab" onclick="selectSemester(1)">Sem 1</button>
                <button type="button" class="sem-tab" onclick="selectSemester(2)">Sem 2</button>
                <button type="button" class="sem-tab" onclick="selectSemester(3)">Sem 3</button>
                <button type="button" class="sem-tab" onclick="selectSemester(4)">Sem 4</button>
                <button type="button" class="sem-tab" onclick="selectSemester(5)">Sem 5</button>
                <button type="button" class="sem-tab" onclick="selectSemester(6)">Sem 6</button>
                <button type="button" class="sem-tab" onclick="selectSemester(7)">Sem 7</button>
                <button type="button" class="sem-tab" onclick="selectSemester(8)">Sem 8</button>
                <button type="button" class="btn btn-outline-secondary" onclick="resetFilters()">
                    <i class="fa-solid fa-rotate-left"></i> Show All
                </button>
            </div>
        </div>
    </div>

    <!-- BRANCH GRID (shown after a semester is picked) -->
    <div class="card mb-4 d-none" id="branchCard">
        <div class="card-body">
            <h6 class="mb-3">Select Branch &mdash; <span id="selectedSemLabel"></span></h6>
            <div class="row g-3" id="branchGrid"></div>
        </div>
    </div>

    <!-- TABLE -->

    <div class="card">

        <div class="card-header bg-primary text-white d-flex justify-content-between align-items-center">

            <h5 class="mb-0">

                <i class="fa-solid fa-users"></i>
                Student Records

            </h5>

            <span id="tableFilterLabel" class="small"></span>

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

                <tbody id="studentTableBody">
                </tbody>

            </table>

            <div id="noResults" class="text-center text-muted p-4 d-none">
                No students found for this selection.
            </div>

        </div>

    </div>

</div>

<script>
    const CONTEXT_PATH = "<%=request.getContextPath()%>";

    // All students rendered server-side into a JS array for client-side filtering
    const allStudents = [
        <%
        if (students != null) {
            for (int i = 0; i < students.size(); i++) {
                Student s = students.get(i);
                String safeName = s.getFullName() == null ? "" : s.getFullName().replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r");
                String usn = s.getUsn() == null ? "" : s.getUsn().replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r");
                String branch = s.getBranch() == null ? "" : s.getBranch().replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r");
                String dept = s.getDepartment() == null ? "" : s.getDepartment().replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r");
        %>
        {
            id: <%=s.getId()%>,
            name: "<%=safeName%>",
            usn: "<%=usn%>",
            branch: "<%=branch%>",
            semester: <%=s.getSemester()%>,
            department: "<%=dept%>"
        }<%= (i < students.size() - 1) ? "," : "" %>
        <%
            }
        }
        %>
    ];

    const branches = [
        { code: "AI",       name: "Artificial Intelligence and Machine Learning" },
        { code: "ARCH",     name: "Bachelor of Architecture" },
        { code: "CV",       name: "Civil Engineering" },
        { code: "CSE",      name: "Computer Science and Engineering" },
        { code: "CSD",      name: "Computer Science and Design" },
        { code: "CSE-AI",   name: "CSE (Artificial Intelligence)" },
        { code: "CSE-CY",   name: "CSE (Cyber Security)" },
        { code: "CSE-DS",   name: "CSE & Engineering (Data Science)" },
        { code: "CSE-IOT",  name: "CSE (IoT & Cyber Security incl. Blockchain)" },
        { code: "EEE",      name: "Electrical & Electronics Engineering" },
        { code: "ECE",      name: "Electronics and Communication Engineering" },
        { code: "ISE",      name: "Information Science and Engineering" },
        { code: "ME",       name: "Mechanical Engineering" }
    ];

    let currentSemester = null;
    let currentBranch = null;

    function selectSemester(sem) {
        currentSemester = sem;
        currentBranch = null;

        document.querySelectorAll(".sem-tab").forEach(function (btn) {
            btn.classList.toggle("active", btn.textContent.trim() === "Sem " + sem);
        });

        document.getElementById("selectedSemLabel").textContent = "Sem " + sem;
        document.getElementById("branchCard").classList.remove("d-none");

        renderBranchGrid();
        renderTable();
    }

    function renderBranchGrid() {
        const grid = document.getElementById("branchGrid");
        grid.innerHTML = "";

        branches.forEach(function (b) {
            const count = allStudents.filter(function (s) {
                return s.semester === currentSemester && s.department === b.code;
            }).length;

            const col = document.createElement("div");
            col.className = "col-md-3 col-sm-6";

            col.innerHTML =
                '<div class="branch-card' + (currentBranch === b.code ? ' active' : '') + '" onclick="selectBranch(\'' + b.code + '\')">' +
                    '<div class="branch-code">' + b.code + '</div>' +
                    '<div class="small">' + b.name + '</div>' +
                    '<div class="branch-count">' + count + ' student' + (count === 1 ? '' : 's') + '</div>' +
                '</div>';

            grid.appendChild(col);
        });
    }

    function selectBranch(code) {
        currentBranch = code;
        renderBranchGrid();
        renderTable();
    }

    function resetFilters() {
        currentSemester = null;
        currentBranch = null;
        document.querySelectorAll(".sem-tab").forEach(function (btn) {
            btn.classList.remove("active");
        });
        document.getElementById("branchCard").classList.add("d-none");
        renderTable();
    }

    function renderTable() {
        let filtered = allStudents;

        if (currentSemester !== null) {
            filtered = filtered.filter(function (s) { return s.semester === currentSemester; });
        }
        if (currentBranch !== null) {
            filtered = filtered.filter(function (s) { return s.department === currentBranch; });
        }

        const tbody = document.getElementById("studentTableBody");
        const noResults = document.getElementById("noResults");
        const filterLabel = document.getElementById("tableFilterLabel");

        let labelText = "Showing all students";
        document.getElementById("statBranchLabel").textContent = "All Branches";
        document.getElementById("statSemLabel").textContent = "All";

        if (currentSemester !== null && currentBranch !== null) {
            labelText = "Sem " + currentSemester + " &middot; " + currentBranch;
            document.getElementById("statBranchLabel").textContent = currentBranch;
            document.getElementById("statSemLabel").textContent = currentSemester;
        } else if (currentSemester !== null) {
            labelText = "Sem " + currentSemester + " &middot; all branches";
            document.getElementById("statSemLabel").textContent = currentSemester;
        }
        filterLabel.innerHTML = labelText;
        document.getElementById("statCountLabel").textContent = filtered.length;

        tbody.innerHTML = "";

        if (filtered.length === 0) {
            noResults.classList.remove("d-none");
            return;
        }
        noResults.classList.add("d-none");

        filtered.forEach(function (s) {
            const tr = document.createElement("tr");
            tr.innerHTML =
                "<td>" + s.id + "</td>" +
                "<td><strong>" + s.name + "</strong></td>" +
                "<td>" + s.usn + "</td>" +
                "<td>" + s.branch + "</td>" +
                '<td><span class="badge bg-info">Sem ' + s.semester + '</span></td>' +
                "<td>" + s.department + "</td>" +
                "<td>" +
                    '<a href="' + CONTEXT_PATH + '/edit-student?id=' + s.id + '" class="btn btn-warning btn-sm">' +
                        '<i class="fa-solid fa-pen"></i>' +
                    '</a> ' +
                    '<a href="' + CONTEXT_PATH + '/delete-student?id=' + s.id + '" class="btn btn-danger btn-sm" ' +
                        'onclick="return confirm(\'Are you sure you want to delete this student?\');">' +
                        '<i class="fa-solid fa-trash"></i>' +
                    '</a>' +
                "</td>";
            tbody.appendChild(tr);
        });
    }

    // Initial render: show everything until a semester is picked
    renderTable();
</script>

</body>
</html>
