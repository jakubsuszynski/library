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
        <div class="justify-content-center">
            <div id="searchBox" class="whiteBox shadow p-md-5 p-sm-2 mt-5 bg-white rounded col-md-12">
                <div>
                    <label for="searchInput">Wyszukaj książkę do edycji</label>
                    <input type="text" class="form-control" id="searchInput"
                           placeholder="Tytuł, autor lub ISBN"/>
                    <a id="searchButton" class="btn btn-dark mt-3" style="color: white">Szukaj</a>
                    <a id="openAddBookModalButton" class="btn btn-dark mt-3" style="color: white; float: right;" data-toggle="modal"
                       data-target="#add-book-modal">Dodaj książkę</a>
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

<!-- AddBook Modal -->
<div class="modal fade" id="add-book-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Dodaj książkę</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                    <div class="form-group">
                        <label for="title">Tytuł</label>
                        <input type="text" class="form-control" name="title" id="title"
                               placeholder="Tytuł">
                    </div>
                    <div class="form-group">
                        <label for="author">Autor</label>
                        <input type="text" class="form-control" name="author" id="author"
                               placeholder="Autor">
                    </div>
                    <div class="form-group">
                        <label for="isbn">ISBN</label>
                        <input type="text" class="form-control" name="ISBN" id="isbn"
                               placeholder="ISBN">
                    </div>
                    <div class="form-group">
                        <label for="category">Gatunek</label>
                        <input type="text" class="form-control" name="category" id="category"
                               placeholder="Gatunek">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-dark" data-dismiss="modal">Cofnij</button>
                        <button type="submit" id="addButton" class="btn btn-secondary" data-dismiss="modal" value="submit">Dodaj</button>
                    </div>
            </div>
        </div>
    </div>
</div>


<script src="../../resources/js/jquery.min.js"></script>
<script src="../../resources/js/popper.min.js"></script>
<script src="../../resources/js/bootstrap.min.js"></script>
<script src="../../resources/js/findToManage.js"></script>

</body>
</html>