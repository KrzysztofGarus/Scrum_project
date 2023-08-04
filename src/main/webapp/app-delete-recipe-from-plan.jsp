<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Zaplanuj Jedzonko</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
        integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
        crossorigin="anonymous">
  <link href="https://fonts.googleapis.com/css?family=Charmonman:400,700|Open+Sans:400,600,700&amp;subset=latin-ext"
        rel="stylesheet">
  <link rel="stylesheet" href="/css/style.css">
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
          <form  class="alert-info" method="post" action="/app/recipe/plan/delete">
            <h3 class="text-color-darker">Czy na pewno chcesz usunąć przepis z planu?</h3>
            <input type="hidden" name="recipeId" value="${recipeId}">
            <input type="hidden" name="dayName" value="${dayName}">
            <input type="hidden" name="mealName" value="${mealName}">
            <input type="hidden" name="planId" value="${planId}">
            <button type="submit" name="agree" value="agreement" class="btn btn-danger rounded-0 text-light m-1">Ok</button>
            <button type="submit" name="agree" value="0" class="btn btn-info rounded-0 text-light m-1">Anuluj</button>
          </form>
        </div>
      </div>
    </div>
</section>
<%@include file="footer.jsp"%>
</body>
</html>
