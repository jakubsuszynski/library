<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js" lang="pl">
<head>
    <title>Biblioteczka - o nas</title>
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway:400,800">
    <link rel='stylesheet' href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../../resources/css/styles.css">
</head>

<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top nav-bg">
        <a class="navbar-brand" href="/">LibSys</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/search">Wyszukaj</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/browse">Katalog</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/about">O nas</a>
                </li>
            </ul>
            <security:authorize var="loggedIn" access="isAuthenticated()"/>
            <c:choose>
                <c:when test="${loggedIn}">
                    <div>
                        <a class="btn btn-outline-light mr-3" href="/panel">Zarządzaj</a>
                    </div>
                    <div>
                        <form name='logout' action="/logout" method='POST'>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-outline-light">
                                Wyloguj
                            </button>
                        </form>
                    </div>
                </c:when>
                <c:otherwise>
                    <div>
                        <button type="button" class="btn btn-outline-light" data-toggle="modal"
                                data-target="#login-modal">
                            Zaloguj
                        </button>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </nav>
</header>

<main role="main">
    <div class="container">
        <div class="jumbotron mt-5">
            <h1 class="display-4">O nas</h1>
            <p class="lead">Biblioteka LibSys to ogromny zbiór książek dostępnych od ręki. Prowadzimy współpracę z
                uczelniami oraz firmami.</p>
            <hr class="my-4">
            <p>Zapraszamy serdecznie do współpracy i skorzystania z naszych usług.</p>
            <p>
                <a class="btn btn-secondary" data-toggle="collapse" href="#collapseExample" role="button"
                   aria-expanded="false" aria-controls="collapseExample">
                    Lokalizacja
                </a>
            </p>
            <div class="collapse" id="collapseExample">
                <div class="card card-body">
                    <p>Biblioteka mieści się przy ulicy Abrahama 3 w Gdańsku.</p>
                    <p>Numer kontaktowy: (+48) 645 231 432</p>
                    <p> Adres e-mail: biblioteka@libsys.pl</p>
                    <img src="https://image.businessinsider.com/5c9542680cf9131e9a761712?width=1100&format=jpeg&auto=webp"/>
                </div>
            </div>
        </div>
    </div>

    <footer class="container">
        <p class="float-right"><a href="#">Back to top</a></p>
        <p>2019 Programowanie Aplikacji Internetowych &middot; <a href="jsuszynski.com">Jakub Suszynski</a></p>
    </footer>
</main>


<!-- Login Modal -->
<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Logowanie</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">

                <c:if test="${not empty message}">
                    <div id="badLogin" class="pb-sm-2 font-weight-bold alert">${message}</div>
                    <c:remove var="message"/>
                </c:if>
                <form name='login' action="/login" method='POST'>
                    <div class="form-group">
                        <label for="username">Nazwa użytkownika</label>
                        <input type="text" class="form-control" name="username" id="username"
                               placeholder="Nazwa użytkownika">
                    </div>
                    <div class="form-group">
                        <label for="password">Hasło</label>
                        <input type="password" class="form-control" name="password" id="password" placeholder="Hasło">
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-dark" data-dismiss="modal">Cofnij</button>
                        <button type="submit" class="btn btn-secondary" value="submit">Zaloguj</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<script src="../../resources/js/jquery.min.js"></script>
<script src="../../resources/js/popper.min.js"></script>
<script src="../../resources/js/bootstrap.min.js"></script>
<script src="../../resources/js/findAll.js"></script>
<script src="../../resources/js/generic.js"></script>
</body>
</html>