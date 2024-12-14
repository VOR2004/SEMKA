<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../signUpp.css"/>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Audiowide&family=Doto:wght@100..900&family=Libre+Baskerville:ital,wght@0,400;0,700;1,400&family=Micro+5&family=Orbitron:wght@400..900&family=Press+Start+2P&family=Rubik:ital,wght@0,300..900;1,300..900&display=swap" rel="stylesheet">
    <title>Sign Up</title>
</head>
<body>
<div class="flex-box">
    <div class="divInputs">
        <form action="<c:url value='/signUp'/>" method="post">
            <label class="emailAsk" for="email">Enter your email:</label>
            <input class="css-inputEmail" id="email" name="email" type="email" placeholder="mr.vor2006@main.ru" required/>
            <br>
            <label class="nicknameAsk" for="nickname">Enter your nickname:</label>
            <input class="css-inputNickname" id="nickname" name="nickname" placeholder="?" required/>
            <br>
            <label class="passwordAsk" for="password">Enter your password:</label>
            <input class="css-inputPassword" id="password" name="password" type="password" required/>
            <br>
            <input class="buttonSignUp" type="submit" value="Sign Up"/>
        </form>
    </div>
</div>
</body>
</html>
