<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <link rel="stylesheet" href="<c:url value='./greetingsy.css'/>"/>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Audiowide&family=Doto:wght@100..900&family=Libre+Baskerville:ital,wght@0,400;0,700;1,400&family=Micro+5&family=Orbitron:wght@400..900&family=Press+Start+2P&family=Rubik:ital,wght@0,300..900;1,300..900&display=swap" rel="stylesheet">
    <title>Welcome to my resource</title>

</head>

<body>

<div class = boxer>
    <a class="logo">
        <img src="<c:url value='/images/logo.png'/>" alt="Numeric square" style="height: 150px;"/>
    </a>
    <h1>Добро пожаловать на Цифровую Площадь</h1>

    <h2>Дадим ответ на любой вопрос!</h2>


    <div class="container">
        <div class="box leftBox">
            <a class="button toSignUp" href="<c:url value='/signUp'/>">Sign Up</a>
            <label class="start"><br>...and create your forum!</label>
        </div>

        <span class="orText">OR</span>

        <div class="box rightBox">
            <a class="button toSignIn" href="<c:url value='/signIn'/>">Sign In</a>
            <label class="oldStart"><br>...and continue your work!</label>
        </div>
    </div>
</div>

</body>
</html>
