<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js" lang="pl">
<head>
    <title>Biblioteczka</title>
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

    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item carousel-first active">
                <div class="container">
                    <div class="carousel-caption">
                        <h1>Wsparcie uczelni</h1>
                        <p>Współpracujmy z wieloma uczelniami oraz placówkami badawczymi. Część zasobów pochodzi właśnie
                            od nich.</p>
                    </div>
                </div>
            </div>
            <div class="carousel-item carousel-second">
                <div class="container">
                    <div class="carousel-caption">
                        <h1>Koszty usług</h1>
                        <p>Wypożyczanie książek jest zupełnie darmowe</p>
                    </div>
                </div>
            </div>
            <div class="carousel-item carousel-third">
                <div class="container">
                    <div class="carousel-caption">
                        <h1>O krok od wiedzy</h1>
                        <p>Nasz księgozbiór jest nieoceniony przy zdobywaniu wiedzy z dziedziny informatyki, mechaniki,
                            automatyki, elektroniki a także medycyny, biologii oraz chemii.</p>
                    </div>
                </div>
            </div>
        </div>
        <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
    <div class="container marketing">

        <div class="row">
            <div class="col-lg-4">
                <div class="icon-background">
                    <img src="../../resources/assets/search.svg" alt=""/>
                </div>
                <h2>Znajdź książkę</h2>
                <p class="desc-fixed">Nasza biblioteka zawiera bogaty zasób książek. Przejdź do wyszukiwarki aby
                    sprawdzić czy
                    posiadamy poszukiwaną przez Ciebie pozycję lub czy nie jest obecnie wypożyczona</p>
                <p><a class="btn btn-secondary" href="/search" role="button">Szukaj &raquo</a></p>
            </div>

            <div class="col-lg-4">
                <div class="icon-background">
                    <img src="../../resources/assets/bookshelf.svg" alt=""/>
                </div>
                <h2>Przejrzyj katalog</h2>
                <p class="desc-fixed">Jeśli nie wiesz co chcesz przeczytać, przejrzyj nasz katalog według kategorii.
                    Wygodne menu pomoże ci
                    odnaleźć się w gatunkach. Poza literaturą naukową posiadamy także pokaźny zbiór beletrystyki</p>
                <p><a class="btn btn-secondary" href="/browse" role="button">Katalog &raquo</a></p>
            </div>
            <div class="col-lg-4">
                <div class="icon-background">
                    <img src="../../resources/assets/faq.svg" alt=""/>
                </div>
                <h2>O nas</h2>
                <p class="desc-fixed">Jeśli chcesz dowiedzieć się o nas czegoś więcej, przejdź do sekcji "O nas".
                    Znajdziesz tam zasady
                    wypożyczania książek, nasz adres i kontakt.</p>
                <p><a class="btn btn-secondary" href="/about" role="button">O nas &raquo;</a></p>
            </div>
        </div>

        <hr class="featurette-divider">

        <div class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading">Najnowocześniejsze sale wykładowe. <span
                        class="text-muted">Będziesz pod wrażeniem.</span></h2>
                <p class="lead">W naszej placówce znajdziesz 8 sal konferencyjno-wykładowych,
                    w których przeprowadzisz szkolenie lub spotkanie na najwyższym poziomie.
                    Sprzęt sprzyja prowadzeniu prezentacji oraz rozmów na odległość</p>
            </div>
            <div class="col-md-5">
                <img class="feature-img" src="../../resources/assets/room.jpg"/>
            </div>
        </div>

        <hr class="featurette-divider">

        <div class="row featurette">
            <div class="col-md-7 order-md-2">
                <h2 class="featurette-heading">Sale komputerowe. <span
                        class="text-muted">Od windowsa, przez MacBooki do Ubuntu</span></h2>
                <p class="lead">Dla czytelników udostępniamy również sale komputerowe ze sprzętem najnowszej generacji.
                    Wentylacja i klimatyczne oświetlenie, a także ergonomiczne stanowiska pracy sprzyjają skupieniu i
                    przyswajaniu wiedzy</p>
            </div>
            <div class="col-md-5 order-md-1">
                <img class="feature-img" src="../../resources/assets/computerLab.jpg"/>
            </div>
        </div>

        <hr class="featurette-divider">


    </div><!-- /.container -->


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
<script src="../../resources/js/carousel.js"></script>
<script src="../../resources/js/findByArg.js"></script>

</body>
</html>