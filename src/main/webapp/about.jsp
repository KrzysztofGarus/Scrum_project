<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Zaplanuj Jedzonko</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Charmonman:400,700|Open+Sans:400,600,700&amp;subset=latin-ext"
          rel="stylesheet">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
          integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
</head>

<body>

<jsp:include page="header.jsp"/>

<section class="padding-medium story bg-light view-height" id="about">
    <div class="container d-flex justify-content-center align-items-center">
        <div class="row">
            <div class="col-4 mr-4">
                <div class="">
                    <img src="/images/dinner.jpg">
                </div>
            </div>

            <div class="col-7 ml-4">
                <h1 class="pb-1">O aplikacji</h1>
                <h5>Aplikacja <span style="color: #ff6600">ZAPLANUJ JEDZONKO</span> pozwoli Tobie w łatwy i przejrzysty sposób zaplanować swoje dania i menu na cały tydzień.</h5>
                <h6>Twórz własne przepisy.</h6>
                <h6>Układaj plany żywieniowe.</h6>
                <h6>Odżywij się zdrowo.</h6>
                </p>
            </div>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp"/>

</body>
</html>