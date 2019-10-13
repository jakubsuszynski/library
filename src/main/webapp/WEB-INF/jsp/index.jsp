<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="pl">
<head>
    <title>Biblioteczka</title>
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway:400,800">
    <link rel='stylesheet' href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/styles.css">
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
            <div>
                <a class="login-button" href="#">Zaloguj</a>
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
                    <img src="assets/search.svg" alt=""/>
                </div>
                <h2>Znajdź książkę</h2>
                <p class="desc-fixed">Nasza biblioteka zawiera bogaty zasób książek. Przejdź do wyszukiwarki aby sprawdzić czy
                    posiadamy poszukiwaną przez Ciebie pozycję lub czy nie jest obecnie wypożyczona</p>
                <p><a class="btn btn-secondary" href="search.html" role="button">Szukaj &raquo</a></p>
            </div>

            <div class="col-lg-4">
                <div class="icon-background">
                    <img src="assets/bookshelf.svg" alt=""/>
                </div>
                <h2>Przejrzyj katalog</h2>
                <p class="desc-fixed">Jeśli nie wiesz co chcesz przeczytać, przejrzyj nasz katalog według kategorii. Wygodne menu pomoże ci
                    odnaleźć się w gatunkach. Poza literaturą naukową posiadamy także pokaźny zbiór beletrystyki</p>
                <p><a class="btn btn-secondary" href="#" role="button">Katalog &raquo</a></p>
            </div>
            <div class="col-lg-4">
                <div class="icon-background">
                    <img src="assets/faq.svg" alt=""/>
                </div>
                <h2>O nas</h2>
                <p class="desc-fixed">Jeśli chcesz dowiedzieć się o nas czegoś więcej, przejdź do sekcji "O nas". Znajdziesz tam zasady
                    wypożyczania książek, nasz adres i kontakt.</p>
                <p><a class="btn btn-secondary" href="#" role="button">O nas &raquo;</a></p>
            </div>
        </div>


        <!-- START THE FEATURETTES -->

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
                <img src="assets/room.jpg" style="width: 100% "/>
            </div>
        </div>

        <hr class="featurette-divider">

        <div class="row featurette">
            <div class="col-md-7 order-md-2">
                <h2 class="featurette-heading">Sale komputerowe. <span
                        class="text-muted">Od windowsa, przez MacBooki do Ubuntu</span></h2>
                <p class="lead">Dla czytelników udostępniamy również sale komputerowe ze sprzętem najnowszej generacji.
                Wentylacja i klimatyczne oświetlenie, a także ergonomiczne stanowiska pracy sprzyjają skupieniu i przyswajaniu wiedzy</p>
            </div>
            <div class="col-md-5 order-md-1">
                <img src="assets/computerLab.jpg" style="width: 100%"/>
            </div>
        </div>

        <hr class="featurette-divider">


    </div><!-- /.container -->


    <footer class="container">
        <p class="float-right"><a href="#">Back to top</a></p>
        <p>2019 Programowanie Aplikacji Internetowych &middot; <a href="jsuszynski.com">Jakub Suszynski</a></p>
    </footer>
</main>
<script src="js/jquery.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/carousel.js"></script>
<script src="js/findBooks.js"></script>

</body>
</html>