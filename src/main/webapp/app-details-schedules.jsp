<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="headerLogged.jsp"%>

            <div class="m-4 p-3 width-medium ">
                <div class="dashboard-content border-dashed p-3 m-4">
                    <div class="row border-bottom border-3 p-1 m-1">
                        <div class="col noPadding">
                            <h3 class="color-header text-uppercase">SZCZEGÓŁY PLANU</h3>
                        </div>
                        <div class="col d-flex justify-content-end mb-2 noPadding">
                            <a href="#" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a>
                        </div>
                    </div>

                    <div class="schedules-content">
                        <div class="schedules-content-header">
                            <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Nazwa planu
                                </span>
                                <div class="col-sm-10">
                                    <p class="schedules-text">${plan.name}</p>
                                </div>
                            </div>
                            <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Opis planu
                                </span>
                                <div class="col-sm-10">
                                    <p class="schedules-text">
                                        ${plan.description}
                                    </p>
                                </div>
                            </div>
                        </div>

                        <table class="table">
                            <c:forEach items="${details}" var="plans">
                            <thead>
                                <tr class="d-flex">
                                    <th class="col-2">${plans.dayName}</th>
                                    <th class="col-7"></th>
                                    <th class="col-1"></th>
                                    <th class="col-2"></th>
                                </tr>
                            </thead>
                                <c:forEach items="${details}" var="plans">
                                <tbody class="text-color-lighter">
                                <tr class="d-flex">
                                    <td class="col-2">${plans.mealName}</td>
                                    <td class="col-7">${plans.recipeDescription}</td>
                                    <td class="col-1 center">
                                        <a href="#" class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                                    </td>
                                    <td class="col-2 center">
                                        <a href="app-details-schedules.html" class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                                    </td>
                                </tr>
                                </c:forEach>
                                </c:forEach>
                            </tbody>
                        </table>

                        <table class="table">
                                <thead>
                                    <tr class="d-flex">
                                        <th class="col-2">Poniedziałek</th>
                                        <th class="col-7"></th>
                                        <th class="col-1"></th>
                                        <th class="col-2"></th>
                                    </tr>
                                </thead>
                                <tbody class="text-color-lighter">
                                    <tr class="d-flex">
                                        <td class="col-2">śniadanie</td>
                                        <td class="col-7">płatki owsiane z jagodami i komosą ryżową</td>
                                        <td class="col-1 center">
                                            <a href="#" class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                                        </td>
                                        <td class="col-2 center">
                                            <a href="app-details-schedules.html" class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                                        </td>
                                    </tr>
                                    <tr class="d-flex">
                                        <td class="col-2">śniadanie</td>
                                        <td class="col-7">płatki owsiane z jagodami i komosą ryżową</td>
                                        <td class="col-1 center">
                                            <a href="#" class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                                        </td>
                                        <td class="col-2 center">
                                            <a href="app-details-schedules.html" class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                                        </td>
                                    </tr>
                                    <tr class="d-flex">
                                        <td class="col-2">śniadanie</td>
                                        <td class="col-7">płatki owsiane z jagodami i komosą ryżową</td>
                                        <td class="col-1 center">
                                            <a href="#" class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                                        </td>
                                        <td class="col-2 center">
                                            <a href="app-details-schedules.html" class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>

                    </div>
                </div>
            </div>
        </div>
    </section>


<%@include file="footer.jsp"%>
</body>
</html>