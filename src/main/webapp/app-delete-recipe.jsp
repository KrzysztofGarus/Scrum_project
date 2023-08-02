<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="headerLogged.jsp"%>
<%--<div class="border-dashed view-height center">--%>
<%--    <form class="center alert-info " method="post" action="${pageContext.request.contextPath}/app/recipe/delete">--%>
<%--        <label class="label-size">Czy na pewno chcesz usunąć przepis?</label>--%>
<%--        <button type="submit" name="id" value="${id}" class="btn btn-danger rounded-0 text-light m-1">Ok</button>--%>
<%--    </form>--%>
<%--</div>--%>
<div class="container pt-4 pb-4 text-center align-items-center justify-content-center">
    <div class="view-height">
        <div class="container w-75">
                <form  class="alert-info" method="post" action="/app/recipe/delete">
                    <h1 class="text-color-darker">Czy na pewno chcesz usunąć przepis?</h1>
                    <button type="submit" name="id" value="${id}" class="btn btn-danger rounded-0 text-light m-1">Ok</button>
                    <button type="submit" name="id" value="0" class="btn btn-info rounded-0 text-light m-1">Anuluj</button>
                </form>
        </div>
    </div>
</div>
</section>
<%@include file="footer.jsp"%>
</body>
</html>
