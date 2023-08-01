<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String adminName = (String) session.getAttribute("adminName");%>
<header class="page-header">
  <nav class="navbar navbar-expand-lg justify-content-between">
    <a href="/" class="navbar-brand main-logo main-logo-smaller">
      Zaplanuj <span>Jedzonko</span>
    </a>
    <div class="d-flex justify-content-around">
      <h4 class="text-light mr-3"><%= adminName %></h4>
      <div class="circle-div text-center"><i class="fas fa-user icon-user"></i></div>
      <a href="/logout" class="text-light mr-3" style="padding-left: 10px;">Wyloguj</a>
    </div>
  </nav>
</header>
