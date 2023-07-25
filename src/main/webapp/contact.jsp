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
                <div style="align-items: center">
                    <img src="/images/contact.jpg">
                </div>
            </div>

            <div class="col-7 ml-4">
                <form class="padding-small text-center" method="" action="">
                    <h1 class="text-color-darker">Napisz do nas</h1>
                    <div class="form-group">
                        <input type="text" class="form-control" id="name" name="name" placeholder="podaj imię">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="surname" name="surname" placeholder="podaj nazwisko">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="email" name="email" placeholder="podaj email">
                    </div>
                    <div class="form-group">
                        <textarea rows="6" cols="30" class="form-control" id="message" name="message" placeholder="Wpisz treść wiadomości"></textarea>
                    </div>

                    <button class="btn btn-color rounded-0" type="submit">Wyślij</button>
                </form>
            </div>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp"/>

</body>
</html>