<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js" lang="pl">
<head>
    <title>Biblioteczka - szukaj</title>
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
                <security:authorize var="loggedIn" access="isAuthenticated()"/>
                <c:choose>
                    <c:when test="${loggedIn}">
                        <li class="nav-item">
                            <a class="nav-link" href="/lendPanel">Wypożycz/Zwróć</a>
                        </li>
                    </c:when>
                </c:choose>
            </ul>
            <div>
                <c:choose>
                    <c:when test="${loggedIn}">
                        <form name='logout' action="/logout" method='POST'>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-outline-light">
                                Wyloguj
                            </button>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <button type="button" class="btn btn-outline-light" data-toggle="modal"
                                data-target="#login-modal">
                            Zaloguj
                        </button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </nav>
</header>

<main role="main">
    <div class="container">
        <div class="justify-content-center">
            <div id="searchBox" class="whiteBox shadow p-md-5 p-sm-2 mt-5 bg-white rounded col-md-12">
                <div>
                    <label for="searchInput">Wyszukaj książkę</label>
                    <input type="text" class="form-control" id="searchInput"
                           placeholder="Tytuł, autor lub ISBN" />
                    <p>
                    <a id="searchButton" class="btn btn-dark mt-3" style="color: white">Szukaj</a>
                </div>
            </div>

                <div id="resultsContainer" class="whiteBox shadow mt-5 bg-white rounded col-md-12"></div>
            <hr class="featurette-divider">


        </div>
    </div>

    <footer class="container">
        <p class="float-right"><a href="/login">Back to top</a></p>
        <p>2019 Programowanie Aplikacji Internetowych &middot; <a href="jsuszynski.com">Jakub Suszynski</a></p>
    </footer>
</main>


<!-- Login Modal -->
<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">

                <c:if test="${not empty errorMessge}">
                    <div style="color:red; font-weight: bold; margin: 30px 0px;">${errorMessge}</div>
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
<script src="../../resources/js/findByArg.js"></script>

</body>
</html>