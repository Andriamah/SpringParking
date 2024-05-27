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
						<h2 class="fh5co-heading animate-box" data-animate-effect="fadeInLeft">Nos Parkings</h2>
						<div class="row row-bottom-padded-md">

							<liste:if test="${inssufisance}">
								<div class="alert alert-danger" role="alert">
									<p>Desolee somme insuffisant!
										<button type="button" class="close" data-dismiss="alert" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</p>
								</div>
								<br>
							</liste:if>
							<liste:if test="${nondisponible}">
								<div class="alert alert-warning" role="alert">
									<p>Ce parking est occuper!
										<button type="button" class="close" data-dismiss="alert" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</p>
								</div>
								<br>
							</liste:if>
							<liste:if test="${nonpossibilite}">
								<div class="alert alert-warning" role="alert">
									<p>Ce parking est occuper a cette heure!
										<button type="button" class="close" data-dismiss="alert" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</p>
								</div>
								<br>
							</liste:if>
							<p>
							<h4>Nombre total : ${nombre}________
							Nombre Occuper : ${nboccupe}________
							Nombre Libre : ${nblibre}________
							Nombre Infraction : ${nbinfraction}________
							Heure : ${date}</h4>
							</p>
							<liste:forEach items="${listeParking}" var="parking">

								<div class="col-md-3 col-sm-6 col-padding animate-box" data-animate-effect="fadeInLeft">
									<div class="blog-entry">

										<div class="desc">
											<liste:choose>
												<liste:when test="${parking.getDISPONIBILITE()== -1}">
													<div style="background-color: rgb(235, 220, 135)"><a href="/formulaireReservation?idParking=${parking.getIDPARKING()}"
															class="blog-img"><img src="images/occupe.jpg"
																class="img-responsive"
																alt="Free HTML5 Bootstrap Template by FreeHTML5.co"></a>
														<h3><a
																href="/formulaireReservation?idParking=${parking.getIDPARKING()}">${parking.getTITRE()}</a>
														</h3>
														<h2 >Infraction</h2>
													</div>
												</liste:when>
												<liste:when test="${parking.getDISPONIBILITE()== 0}">
													<div style="background-color:rgb(226, 108, 108)">
													<a href="/formulaireReservation?idParking=${parking.getIDPARKING()}"
														class="blog-img"><img src="images/occupe.jpg"
															class="img-responsive"
															alt="Free HTML5 Bootstrap Template by FreeHTML5.co"></a>
													<h3><a
															href="/formulaireReservation?idParking=${parking.getIDPARKING()}">${parking.getTITRE()}</a>
													</h3>
													<h2>Occuper</h2>
													</div>
												</liste:when>
												<liste:otherwise>
													<div style="background-color:  rgb(121, 243, 174)">
													<a href="/formulaireReservation?idParking=${parking.getIDPARKING()}"
														class="blog-img"><img src="images/libre.jpg"
															class="img-responsive"
															alt="Free HTML5 Bootstrap Template by FreeHTML5.co"></a>
													<h3><a
															href="/formulaireReservation?idParking=${parking.getIDPARKING()}">${parking.getTITRE()}</a>
													</h3>
													<h2>libre</h2>
													</div>
												</liste:otherwise>
											</liste:choose>
											<button type="button" class="btn btn-primary" data-toggle="modal"
												data-target="#${parking.getIDPARKING()}">
												Voir
											</button>
										</div>
									</div>
								</div>

								<div class="modal fade" id="${parking.getIDPARKING()}" tabindex="-1" role="dialog"
									aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
									<liste:choose>
										<liste:when test="${parking.getDISPONIBILITE()==1}">
											<div class="modal-dialog modal-dialog-centered" role="document">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="exampleModalLongTitle">Voir parking
														</h5>
														<button type="button" class="close" data-dismiss="modal"
															aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
													</div>
													<div class="modal-body">
														<h3>${parking.getTITRE()} est libre</h3>
														<form action="/reserver" method="get">
															<div class="row">
																<div class="col-md-12">
																	<div class="row">
																		<div class="col-md-6">
																			<div class="form-group">
																				<label>Tarif : </label>
																				<select name="idtarif">
																					<liste:forEach items="${listeTarif}"
																						var="tarif">
																						<option
																							value="${tarif.getId()}">
																							${tarif.getDuree()} minutes
																						</option>
																					</liste:forEach>
																				</select>
																				<br>
																				<label>Date debut : ${date}</label>
																				<input type="datetime-local"
																					class="form-control form-control-user"
																					name="datedebut" value="${date}">
																				<label>MATRICULATION : </label>
																				<input type="text" class="form-control"
																					name="matriculation">
																				<input type="hidden"
																					value="${parking.getIDPARKING()}"
																					name="idParking">
																			</div>
																			<div class="form-group">
																				<input type="submit"
																					class="btn btn-primary btn-md"
																					value="Valider">
																			</div>
																		</div>
																		<div class="col-md-6">

																		</div>

																	</div>
																</div>

															</div>
														</form>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary"
															data-dismiss="modal">Close</button>
														<!-- <a type="button" class="btn btn-primary"
															href="/formulaireReservation?idParking=${parking.getIDPARKING()}">Reserver</a> -->
													</div>
												</div>
											</div>
										</liste:when>
										<liste:when test="${parking.getDISPONIBILITE()==2}">
											<div class="modal-dialog modal-dialog-centered" role="document">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="exampleModalLongTitle">Voir parking
														</h5>
														<button type="button" class="close" data-dismiss="modal"
															aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
													</div>
													<div class="modal-body">
														<h3>${parking.getTITRE()} est libre pour le moment :</h3>
														<h4>Avant : ${parking.getDATE_DEBUT()}</h4>
														<h4>Apres : ${parking.getDATE_FIN()}</h4>
														<form action="/reserver" method="get">
															<div class="row">
																<div class="col-md-12">
																	<div class="row">
																		<div class="col-md-6">
																			<div class="form-group">
																				<label>Tarif : </label>
																				<select name="idtarif">
																					<liste:forEach items="${listeTarif}"
																						var="tarif">
																						<option
																							value="${tarif.getId()}">
																							${tarif.getDuree()} minutes
																						</option>
																					</liste:forEach>
																				</select>
																				<br>
																				<label>Date debut : ${date}</label>
																				<input type="datetime-local"
																					class="form-control form-control-user"
																					name="datedebut" value="${date}">
																				<label>MATRICULATION : </label>
																				<input type="text" class="form-control"
																					name="matriculation">
																				<input type="hidden"
																					value="${parking.getIDPARKING()}"
																					name="idParking">
																			</div>
																			<div class="form-group">
																				<input type="submit"
																					class="btn btn-primary btn-md"
																					value="Valider">
																			</div>
																		</div>
																		<div class="col-md-6">

																		</div>

																	</div>
																</div>

															</div>
														</form>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary"
															data-dismiss="modal">Close</button>
														<!-- <a type="button" class="btn btn-primary"
															href="/formulaireReservation?idParking=${parking.getIDPARKING()}">Reserver</a> -->
													</div>
												</div>
											</div>
										</liste:when>
										<liste:otherwise>
											<liste:choose>
												<liste:when test="${parking.getRESTANT()>0}">
													<div class="modal-dialog modal-dialog-centered" role="document">
														<div class="modal-content">
															<div class="modal-header">
																<h5 class="modal-title" id="exampleModalLongTitle">Voir
																	parking</h5>
																<button type="button" class="close" data-dismiss="modal"
																	aria-label="Close">
																	<span aria-hidden="true">&times;</span>
																</button>
															</div>
															<div class="modal-body">
																<h3>${parking.getTITRE()} est occupe</h3>
																<h3>Immatriculation : ${parking.getMATRICULATION()}</h3>
																<h3>Arrive : ${parking.getDATE_DEBUT()}</h3>
																<h3>Supposer depart : ${parking.getDATE_FIN()}</h3>
																<h3>Duree : ${parking.getDUREE()}</h3>
																<h3>Delai de depart : ${parking.getRESTANT()} Minutes
																</h3>
															</div>
															<div class="modal-footer">
																<button type="button" class="btn btn-secondary"
																	data-dismiss="modal">Close</button>
															</div>
														</div>
													</div>
												</liste:when>
												<liste:otherwise>
													<div class="modal-dialog modal-dialog-centered" role="document">
														<div class="modal-content">
															<div class="modal-header">
																<h5 class="modal-title" id="exampleModalLongTitle">Voir
																	parking</h5>
																<button type="button" class="close" data-dismiss="modal"
																	aria-label="Close">
																	<span aria-hidden="true">&times;</span>
																</button>
															</div>
															<div class="modal-body">
																<h3>${parking.getTITRE()} a une Infraction</h3>
																<h3>Immatriculation : ${parking.getMATRICULATION()}</h3>
																<h3>Arrive : ${parking.getDATE_DEBUT()}</h3>
																<h3>Supposer depart : ${parking.getDATE_FIN()}</h3>
																<h3>Duree : ${parking.getDUREE()}</h3>
																<h3>Delai de depart : ${parking.getRESTANT()} Minutes
																</h3>
															</div>
															<div class="modal-footer">
																<button type="button" class="btn btn-secondary"
																	data-dismiss="modal">Close</button>
															</div>
														</div>
													</div>
												</liste:otherwise>
											</liste:choose>
										</liste:otherwise>

									</liste:choose>
								</div>
							</liste:forEach>



						</div>
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