<jsp:useBean id="topicOpenedBy" scope="request" type="java.lang.String"/>
<jsp:useBean id="topicInfo" scope="request" type="java.lang.Object"/>
<jsp:useBean id="listComments" scope="request" type="java.util.List"/>
<jsp:useBean id="likeCountAll" scope="request" type="java.lang.Integer"/>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <link rel="stylesheet" href="<c:url value='/topic.css'/>">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Audiowide&family=Doto:wght@100..900&family=Libre+Baskerville:ital,wght@0,400;0,700;1,400&family=Micro+5&family=Orbitron:wght@400..900&family=Press+Start+2P&family=Rubik:ital,wght@0,300..900;1,300..900&display=swap" rel="stylesheet">
    <title>${topicInfo.title}</title>

    <script>
        function toggleAnswers(commentId) {
            var answersDiv = document.getElementById('answers-' + commentId);
            if (answersDiv.style.display === 'none' || answersDiv.style.display === '') {
                answersDiv.style.display = 'block';
            } else {
                answersDiv.style.display = 'none';
            }
        }
    </script>
</head>
<body>
<div class="headerMain">
    <a href="<c:url value='/main'/>">
        <img src="<c:url value='/images/logo.png'/>" alt="Numeric square" />
    </a>
</div>


<br><br><br><br><br>

<div class="container">
    <h1>${topicInfo.title} <c:if test="${topicInfo.isClosed == true}">(closed)</c:if></h1>
    <h2>${topicInfo.description}</h2>
    <h3>${topicInfo.createdTime}</h3>

    <c:if test="${topicInfo.nickname == topicOpenedBy}">
        <form action="<c:url value='/topic?id=${topicInfo.id}'/>" method="post">
            <input class="buttonCloseOpen" type="submit" value="Close/open topic"/>
        </form>
    </c:if>

    <script>
        function validateFileSize() {
            const fileInput = document.getElementById('file-input');
            const fileError = document.getElementById('file-error');
            const maxSizeInMB = 10;
            const maxSizeInBytes = maxSizeInMB * 1024 * 1024;

            const fileSize = fileInput.files[0].size;

            if (fileSize > maxSizeInBytes) {
                fileError.textContent = `Файл должен быть меньше 10 MB.`;
                return false;
            } else {
                fileError.textContent = '';
                return true;
            }

        }
    </script>

    <c:if test="${topicInfo.isClosed == false}">
        <form id="commentForm" enctype="multipart/form-data" action="<c:url value='/createComment?id=${topicInfo.id}'/>" method="post" onsubmit="return validateFileSize()">
            <label for="comment">Enter your comment:</label>
            <input id="comment" name="comment" type="text" placeholder="Text" required/>

            <input type="file" id="file-input" accept="image/jpeg" name="file"/>
            <p id="file-error" style="color:red;"></p>

            <input class="buttonCreateComment" type="submit" value="Comment"/>
        </form>
    </c:if>

    <div class="comment-section">
        <c:forEach items="${listComments}" var="comment">
            <div class="comment">
                <img class="avatar" src="<c:url value='/avatar?id=${comment.user_id}'/>" style="max-width: 50px; max-height: 50px;" alt="">
                <div class="comment-details">
                    <a class="nickAndLike">${comment.author_nick} (${likeCountAll} б.) </a>
                    <a class="date">&nbsp;&nbsp;at ${comment.dateTime}:<br></a><br>
                    <p class="comm"> ${comment.text}</p>
                    <img class="img_comm" src="<c:url value='/imageComm?id=${comment.image_id}'/>" alt="">
                            <br><br>
                        ${comment.likesCount} likes
                </div>
            </div>

            <script>
                function likeComment(topicId, commentId) {
                var xhr = new XMLHttpRequest();
                xhr.open("POST", "<c:url value='/like?id='/>" + topicId + "&commentId=" + commentId, true);
                xhr.setRequestHeader("Content-Type", "application/json");

                xhr.onreadystatechange = function () {
                    if (xhr.readyState === XMLHttpRequest.DONE) {
                        if (xhr.status === 200) {
                            window.location.reload();
                        } else {
                            alert("An error occurred!");
                        }
                    }
                };
                xhr.send();
            }
            </script>

            <button class="buttonLike" onclick="likeComment(${topicInfo.id}, ${comment.id})">+1</button>

            <button class="toggle-answers-button" onclick="toggleAnswers('${comment.id}')">Toggle Answers</button>

            <div id="answers-${comment.id}" class="answers">
                <c:forEach items="${comment.answers}" var="answer">
                    <a class="ansNick">${answer.author_nick} </a>
                    <a class="ansDate"> at ${answer.dateTime}:</a>
                    <p class="answer">${answer.text}</p>
                </c:forEach>

                <c:if test="${topicInfo.isClosed == false}">
                    <form action="<c:url value='/createAnswer?id=${topicInfo.id}&ids=${comment.id}'/>" method="post">
                        <label for="answer">Enter your answer:</label>
                        <input id="answer" name="answer" type="text" placeholder="Text" required/>
                        <input class="buttonCreateAnswer" type="submit" value="Respond"/>
                    </form>
                </c:if>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
