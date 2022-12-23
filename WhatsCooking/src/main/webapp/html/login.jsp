<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
 <!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Login</title>
</head>
<body>
  <form action=loginServlet method=post>
    <label for=username>UserName:</label><br>
    <input type=text id=username name=username><br>
    <label for=password>Password:</label><br>
    <input type=password id=password name=password><br><br>
    <input type=submit value="Submit">
    </form>
</body>
</html>