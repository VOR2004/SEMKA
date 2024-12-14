<%@ page contentType="text/html;charset=UTF-8"%>
<jsp:useBean id="userName" scope="request" type="java.lang.String"/>
<jsp:useBean id="userId" scope="session" type="java.lang.Long"/>
<jsp:useBean id="listTopic" scope="request" type="java.util.List"/>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="../topics.css"/>
    <link href="https://fonts.googleapis.com/css2?family=Audiowide&family=Doto:wght@100..900&family=Libre+Baskerville:ital,wght@0,400;0,700;1,400&family=Micro+5&family=Orbitron:wght@400..900&family=Press+Start+2P&family=Rubik:ital,wght@0,300..900;1,300..900&display=swap" rel="stylesheet">
    <title>Topics</title>
</head>
<body>

    <div class="headerMain">
        <a href="<c:url value='/main'/>">
            <img src="../images/logo.png" alt="Numeric square" />
        </a>
    </div>


<br><br><br><br><br>



<button class="btnDialog" id="openDialogBtn">+</button>
    <a class="btnProfile" href="<c:url value='/profile'/>">Profile</a>
    <a class="btnLogout" href="<c:url value='/logout'/>">Logout</a>

<div id="dialog" class="dialog">
    <div class="dialog-content">
        <span class="close" id="closeDialogBtn"></span>
        <form action="<c:url value='/createTopic'/>" method="post">
            <label class="titleIn" for="title">Enter your title:</label>
            <br/>
            <input class="tit" id="title" name="title" type="text" placeholder="Title" required/>
            <br/>

            <label class="descIn" for="description">Enter your description:</label>
            <br/>
            <input class="desc" id="description" name="description" type="text" placeholder="Description" required/>
            <br/>

            <input class="buttonCreateTopic" type="submit" value="Create Topic"/>
        </form>
    </div>
</div>

<script src="../scriptDialog.js"></script>

<div class="conttable">
    <table style="width: 100%;">
        <c:forEach items="${listTopic}" var="comment">
            <tr class="cont" onclick="window.location.href='/topic?id=${comment.id}'" style="cursor: pointer;">
                <td class="tit" style="font-size: 24px; font-weight: bold;">
                    <div>${comment.title}</div>
                    <div class="nick">${comment.nickname}</div>
                </td>
                <td class="cTime" >${comment.createdTime}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
