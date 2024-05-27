<%@taglib prefix="liste" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>

    <!--[if gt IE 8]><!-->
    <html class="no-js">
    <!--<![endif]-->

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Parking</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Free HTML5 Website Template by FreeHTML5.co" />
        <meta name="keywords"
            content="free html5, free template, free bootstrap, free website template, html5, css3, mobile first, responsive" />
        <meta name="author" content="FreeHTML5.co" />

        <meta property="og:title" content="" />
        <meta property="og:image" content="" />
        <meta property="og:url" content="" />
        <meta property="og:site_name" content="" />
        <meta property="og:description" content="" />
        <meta name="twitter:title" content="" />
        <meta name="twitter:image" content="" />
        <meta name="twitter:url" content="" />
        <meta name="twitter:card" content="" />

        <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
        <link rel="shortcut icon" href="favicon.ico">

        <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700" rel="stylesheet">

        <!-- Animate.css -->
        <link rel="stylesheet" href="css/animate.css">
        <!-- Icomoon Icon Fonts-->
        <link rel="stylesheet" href="css/icomoon.css">
        <!-- Bootstrap  -->
        <link rel="stylesheet" href="css/bootstrap.css">
        <!-- Flexslider  -->
        <link rel="stylesheet" href="css/flexslider.css">
        <!-- Theme style  -->
        <link rel="stylesheet" href="css/style.css">

        <!-- Modernizr JS -->
        <script src="js/modernizr-2.6.2.min.js"></script>
        <!-- FOR IE9 below -->
        <!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->

    </head>

    <body>
        <div id="fh5co-page">
            <a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle"><i></i></a>
            <%@include file="header.jsp" %>
                <div id="fh5co-main">
                    <div class="fh5co-narrow-content">
                        <liste:if test="${amende}">
                            <div class="alert alert-danger" role="alert">
                                <p>Vous avez depasse le contrat! Veuillez payer l'amende</p>
                            </div>
                            <liste:forEach items="${listeAmende}" var="amende">
                                <h3>Parking : ${amende.getMATRICULATION()}</h3>
                                <h3>Immatriculation : ${amende.getMATRICULATION()}</h3>
                                <h3>Duree : ${amende.getDUREE()}</h3>
                                <h3>Depassement : ${amende.getRESTANT()*(-1)}</h3>
                                <h3>Date fin : ${amende.getDATE_FIN()}</h3>
                                <hr>
                                <p><a href="/payerAmende?idReservation=${amende.getID()}" class="btn btn-primary">Payer Amende</a></p>
                            </liste:forEach>
                            <br>
                        </liste:if>
                        <liste:if test="${nullite}">
                            <div class="alert alert-success" role="alert">
                                <p>Vous n'avez pas d'amende!</p>
                            </div>
                            <br>
                        </liste:if>

                    </div>
                </div>
        </div>

        <!-- jQuery -->
        <script src="js/jquery.min.js"></script>
        <!-- jQuery Easing -->
        <script src="js/jquery.easing.1.3.js"></script>
        <!-- Bootstrap -->
        <script src="js/bootstrap.min.js"></script>
        <!-- Waypoints -->
        <script src="js/jquery.waypoints.min.js"></script>
        <!-- Flexslider -->
        <script src="js/jquery.flexslider-min.js"></script>


        <!-- MAIN JS -->
        <script src="js/main.js"></script>

    </body>

    </html>