<%@taglib prefix="liste" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
    <!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
    <!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
    <!--[if gt IE 8]><!-->
    <html class="no-js">
    <!--<![endif]-->

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Marble &mdash; Free HTML5 Bootstrap Website Template by FreeHTML5.co</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Free HTML5 Website Template by FreeHTML5.co" />
        <meta name="keywords"
            content="free html5, free template, free bootstrap, free website template, html5, css3, mobile first, responsive" />
        <meta name="author" content="FreeHTML5.co" />

        <!-- 
	//////////////////////////////////////////////////////

	FREE HTML5 TEMPLATE 
	DESIGNED & DEVELOPED by FreeHTML5.co
		
	Website: 		http://freehtml5.co/
	Email: 			info@freehtml5.co
	Twitter: 		http://twitter.com/fh5co
	Facebook: 		https://www.facebook.com/fh5co

	//////////////////////////////////////////////////////
	-->

        <!-- Facebook and Twitter integration -->
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
                    <div class="fh5co-narrow-content animate-box" data-animate-effect="fadeInLeft">

                        <div class="row">
                            <div class="col-md-4">
                                <h2>Votre Compte</h2>
                            </div>
                        </div>
                        <liste:if test="${amendeSucces}">
                            <div class="alert alert-primary" role="alert">
                                <p>Payement amende succes ${valeur} !
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </p>
                            </div>
                            <br>
                        </liste:if>
                        <liste:if test="${insuffisance}">
                            <div class="alert alert-success" role="alert">
                                <p>Vous n'avez pas assez d'argent pour l'amende , Veuillez recharger!
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </p>
                            </div>

                            <br>
                        </liste:if>
                        <form action="/rechargePortefeuille" method="get">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Montant actuel : ${vola}</label>
                                                <input type="number" class="form-control" name="montant">
                                            </div>
                                            <div class="form-group">
                                                <input type="submit" class="btn btn-primary btn-md" value="Valider">
                                            </div>
                                        </div>
                                        <div class="col-md-6">

                                        </div>

                                    </div>
                                </div>

                            </div>
                        </form>
                        <div>
                          <table class="table">
                            <thead class="thead-light">
                              <tr>
                                <th scope="col">#</th>
                                <th scope="col">Entree</th>
                                <th scope="col">Sortie</th>
                                <th scope="col">Date</th>
                              </tr>
                            </thead>
                            <tbody>
                                <liste:forEach items="${liste}" var="portefeuille">
                                    <tr>
                                        <th scope="row">${portefeuille.getID()}</th>
                                        <td>${portefeuille.getVALEUR_ENTRE()}</td>
                                        <td>${portefeuille.getVALEUR_SORTIE()}</td>
                                        <td>${portefeuille.getDATE_PORTEFEILLE()}</td>
                                    </tr>
                                </liste:forEach>
                            </tbody>
                          </table>
                        </div>
                    </div>
                    <div></div>
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
        <!-- Google Map -->



        <!-- MAIN JS -->
        <script src="js/main.js"></script>

    </body>

    </html>