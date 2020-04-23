<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <title>spring boot jsp</title>
</head>
<body>
Message: ${message}
<br/>
<img alt="" src="images/1.png">
</body>
<form action="students/post" method="post">
    First time: <input type="text" name="time" />
    <input type="submit" value="Submit" />
</form>
</html>