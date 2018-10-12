<%--
  Created by IntelliJ IDEA.
  User: Dharshan
  Date: 7/1/2018
  Time: 9:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="/ShowHide.js"></script>
<link rel="stylesheet" type="text/css" href="/style.css"  />
<html>
<head>
    <title>CV Comparison</title>
</head>
<body>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Job Candidate Comparison</a>
            </div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="#">Job References</a></li>
                <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"> Candidate Results<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Page 1-1</a></li>
                        <li><a href="#">Page 1-2</a></li>
                        <li><a href="#">Page 1-3</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            </ul>
        </div>
    </nav>
    <div class="col-sm-6 col-sm-offset-3 form-box">
        <label>By ID</label>
        <input class="amount" type="radio" name="amount" value="100" />
        <%--<label>By Class</label>
        <input class="amount" type="radio" name="amount" value="150" />--%>
        <div class="form-div">
            <form action="/ServletComparison" method="get">
                <div class="JobIdentity" style="display: none">
                    <label>Job Advertisement ID</label>
                    <input type="text" id="jobID" name="JobID" placeholder="Enter your Job Advertisement ID">
                </div>

                <%--<div class="JobCatSelection" style="display: none">
                    <label>Job Category</label>
                        <select id="jobCat" name="jobCat">
                            <option value="InformationTechnology">Information Technology</option>
                            <option value="HumanResources">Human Resources</option>
                            <option value="Marketing">Marketing</option>
                            <option value="LegalLaw">Legal/ Law</option>
                            <option value="CorporateManagement">Corporate Management</option>
                            <option value="ITTelecom">IT-Telecom</option>
                            <option value="Finance">Finance/ Accounting</option>
                        </select>
                </div>--%>
                <div class="submitBtn" style="display: none">
                    <input type="submit" value="Submit">
                </div>
            </form>
        </div>
    </div>
</body>
</html>
