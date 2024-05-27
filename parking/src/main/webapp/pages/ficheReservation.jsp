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

                        <liste:if test="${succes}">
                            <div class="alert alert-success" role="alert">
                                <p>Merci de votre participation!</p>
                                <p>${deduit} Ar a ete deduit de votre compte</p>
                                <p><a href="/ticket">Prendre le Ticket</a></p>
                            </div>
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <br>
                        </liste:if>
                        <liste:if test="${depassement}">
                            <div class="alert alert-danger" role="alert">
                                <p>Vous avez depasse le contrat!</p>
                            </div>
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <br>
                        </liste:if>
                        <liste:if test="${nullite}">
                            <div class="alert alert-success" role="alert">
                                <p>Vous n'avez pas d'amende!</p>
                            </div>
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <br>
                        </liste:if>
                        <liste:if test="${erreurDate}">
                            <div class="alert alert-warning" role="alert">
                                <p>Verifier la date !
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </p>
                            </div>
                            <br>
                        </liste:if>
                        <liste:if test="${monreservation}">
                            <liste:forEach items="${reservation}" var="reservation">
                                <div class="row row-bottom-padded-md">
                                    <div class="col-md-6 animate-box" data-animate-effect="fadeInLeft">
                                        <img class="img-responsive" src="images/occupe.jpg"
                                            alt="Free HTML5 Bootstrap Template by FreeHTML5.co">
                                    </div>
                                    <div class="col-md-6 animate-box" data-animate-effect="fadeInLeft">
                                        <h2 class="fh5co-heading">Votre parking </h2>
                                        <h2 class="fh5co-heading">Immatriculation : ${reservation.getMATRICULATION()}
                                        </h2>
                                        <h3 class="fh5co-heading"> Date debut : ${reservation.getDATE_DEBUT()}</h3>
                                        <h3 class="fh5co-heading"> Duree : ${reservation.getDUREE()}</h3>
                                        <h3 class="fh5co-heading"> Montant : ${reservation.getVALEUR()}</h3>
                                        <h3 class="fh5co-heading"> Restant : ${reservation.getRESTANT()}</h3>
                                        <!-- <p><a href="/quitterParking?idReservation=${reservation.getID()}"
                                                class="btn btn-primary">Quitter le parking</a></p> -->
                                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                            data-target="#${reservation.getID()}">
                                            Quitter parking
                                        </button>
                                    </div>
                                </div>
                                <!-- Button trigger modal -->
                                <!-- <button type="button" class="btn btn-primary" data-toggle="modal"
                                    data-target="#exampleModalCenter">
                                    Launch demo modal
                                </button> -->

                                <!-- Modal -->
                                <div class="modal fade" id="${reservation.getID()}" tabindex="-1" role="dialog"
                                    aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLongTitle">Quitter parking</h5>
                                                <button type="button" class="close" data-dismiss="modal"
                                                    aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <h3>Quitter le parking</h3>
                                                <form action="/quitterParking" method="get">
                                                    <label>Date debut :</label>
                                                    <input type="datetime-local" class="form-control form-control-user"
                                                        name="datefin" value="${date}">
                                                    <input type="hidden" name="idReservation"
                                                        value="${reservation.getID()}">
                                                    <input type="submit" value="Valider" class="btn btn-secondary">
                                                </form>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary"
                                                    data-dismiss="modal">Close</button>
                                                <button type="button" class="btn btn-primary">Save changes</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </liste:forEach>

                            <p><a href="/depassement" class="btn btn-primary">Voir amende</a></p>
                        </liste:if>
                        <liste:if test="${nullReservation}">
                            <div class="row row-bottom-padded-md">
                                <div class="col-md-6 animate-box" data-animate-effect="fadeInLeft">
                                    <img class="img-responsive" src="images/libre.jpg"
                                        alt="Free HTML5 Bootstrap Template by FreeHTML5.co">
                                </div>
                                <div class="col-md-6 animate-box" data-animate-effect="fadeInLeft">
                                    <h2 class="fh5co-heading">Vous n'avez pas de parking!</h2>
                                    <p><a href="/" class="btn btn-primary">Chercher parking</a></p>
                                </div>
                            </div>
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