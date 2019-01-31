<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customer Sign Up</title>
</head>
<body>
<h1>User Sign Up</h1>


<form action="UniversityAdmissionsController" method="GET">
    <label for="email">Email: </label>
    <input type="text" name="email" id="email" value="${email}">
    <label for="password">Password: </label>
    <input type="password" name="password" id="password" value="${password}">
    <input type="submit" name="signup" value="Sign Up">
</form>
</body>
</html>