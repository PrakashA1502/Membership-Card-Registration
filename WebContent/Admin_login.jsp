<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Login Page</title>

<!-- CDN  Content Delivery Network -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</head>
<body>
 
 <header>
   <nav class="navbar navbar-expand-lg navbar-light bg-dark">
    <div class="container-fluid">
    <a class="navbar-brand" href="#" style="color:white">Admin Access</a>
    </div>
   </nav>
 </header>

<div class="container" style="width:50%">
   <h2 align="center">Admin SignUp!..</h2>
   <hr>

   <form action="adminValide" method="post">
   <div class="mb-3">
   <label for="exampleFormControlInput1" class="form-label">Enter Name :</label>
   <input type="text" class="form-control" id="exampleFormControlInput1"  placeholder="Enter Admin Name" required="required" name="tbName">
   </div>
  
   <div class="mb-3">
   <label for="exampleFormControlInput2" class="form-label">Enter Password :</label>
   <input type="password" class="form-control" id="exampleFormControlInput2" placeholder="Enter Password" required="required" name="tbPass">
   </div>
   
    <div><input class="btn btn-success" type="submit" value="Login"></div>
    </form>
    
 </div>
</body>
</html>