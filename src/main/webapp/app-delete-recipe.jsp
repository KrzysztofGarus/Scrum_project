<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="headerLogged.jsp"%>
<div class="border-dashed view-height">
    <form class="center alert-info " method="post" action="${pageContext.request.contextPath}/app/recipe/delete">
        <label class="label-size">Czy na pewno chcesz usunąć przepis?</label>
        <button type="submit" name="id" value="${id}" class="btn btn-danger rounded-0 text-light m-1">Ok</button>
    </form>
</div>
</section>
<%@include file="footer.jsp"%>
</body>
</html>
