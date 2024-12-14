<jsp:useBean id="likeCountAll" scope="request" type="java.lang.Object"/>
<jsp:useBean id="userName" scope="session" type="java.lang.String"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="userId" scope="session" type="java.lang.Long"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <title>Profile</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Audiowide&family=Doto:wght@100..900&family=Libre+Baskerville:ital,wght@0,400;0,700;1,400&family=Micro+5&family=Orbitron:wght@400..900&family=Press+Start+2P&family=Rubik:ital,wght@0,300..900;1,300..900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../profil.css"/>
</head>
<body>

<div class="headerMain">
    <a href="<c:url value='/main'/>">
        <img src="../images/logo.png" alt="Numeric square" />
    </a>
</div>

<div class="content">
    <div class="box">
        <img src="<c:url value='/avatar?id=${userId}'/>" alt="Uploaded Image" class="avatar" style="width: 150px; height: 150px;">

        <table>
            <thead>
            <tr>
                <th>Hello, ${userName}! Your like count is: ${likeCountAll}</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>
                    <form enctype="multipart/form-data" action="<c:url value='/avatar'/>" method="post" onsubmit="return validateFileSize()">
                        <input class="file" type="file" id="file-input" accept="image/jpeg" name="file"/>
                        <input type="submit" value="Change avatar"/>
                        <p id="file-error"></p>
                    </form>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<script>
    function validateFileSize() {
        const fileInput = document.getElementById('file-input');
        const fileError = document.getElementById('file-error');
        const maxSizeInMB = 10;
        const maxSizeInBytes = maxSizeInMB * 1024 * 1024;

        if (fileInput.files.length > 0) {
            const fileSize = fileInput.files[0].size;

            if (fileSize > maxSizeInBytes) {
                fileError.textContent = `Файл должен быть меньше 10 MB.`;
                return false;
            } else {
                fileError.textContent = '';
                return true;
            }
        } else {
            fileError.textContent = 'Пожалуйста, выберите файл.';
            return false;
        }
    }

</script>

</body>
</html>
