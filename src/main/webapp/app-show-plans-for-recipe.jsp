<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
          integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
</head>

<body>
<jsp:include page="app-header.jsp"/>
<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <jsp:include page="app-side-menu.jsp"/>

        <div class="container pt-4 pb-4 text-center align-items-center justify-content-center">
            <div class="view-height">
                <div class="container w-75">
                    <form class="alert-info" method="post" action="/app/recipe/delete">
                        <p class="text-color-darker">Ten przepis znajduje się w planie/planach:</p>
                            <c:forEach items="${recipePlans}" var="recipePlan">
                                <div class="d-flex justify-content-center">
                                    <a href="/app/plan/details/?id=${recipePlan.id}"
                                            class="btn btn-info rounded-0 text-light m-1">${recipePlan.name}</a>
                                </div>
                            </c:forEach>
                        <p class="text-color-darker">najpierw musisz usunąć ten przepis z planów</p>
                        <button type="submit" name="id" value="0" class="btn btn-danger rounded-0 text-light m-1">anuluj
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<%@include file="footer.jsp" %>
</body>
</html>
