<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
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
            <div>
                <a class="login-button" href="#">Zaloguj</a>
            </div>
        </div>
    </nav>
</header>

<main role="main">
    <div class="container">
        <div class="justify-content-center">
            <div id="searchBox" class="whiteBox shadow p-md-5 p-sm-2 mt-5 bg-white rounded col-md-12">
                <div>
                    <label>Wyszukaj książkę
                        <input type="text" id="searchInput" placeholder="tytuł, autor lub ISBN"/>
                    </label>
                    <a id="searchButton" class="btn btn-dark" style="color: white">Szukaj</a>
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
<script src="../../resources/js/jquery.min.js"></script>
<script src="../../resources/js/popper.min.js"></script>
<script src="../../resources/js/bootstrap.min.js"></script>
<script src="../../resources/js/findByArg.js"></script>

</body>
</html>