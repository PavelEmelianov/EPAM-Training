<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<html>
<head>
	<title>Calculator Result</title>
</head>
<body>
	${param.x} ${param.op} ${param.y} = ${res}
	<br/>	
	<a href='calc.html'>back</a>
</body>
</html>