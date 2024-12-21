<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Error</title>
</head>
<body>


<h1>Error: ${param.err}</h1>



<hr>

<a href="<c:url value="/main"/>">На главную</a>

</body>
</html>
