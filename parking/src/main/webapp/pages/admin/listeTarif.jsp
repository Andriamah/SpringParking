<%@ taglib prefix="liste" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>

            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
            <meta name="description" content="">
            <meta name="author" content="">

            <title>Tarif</title>

            <!-- Custom fonts for this template-->
            <link href="vendor-admin/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
            <link
                href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
                rel="stylesheet">

            <!-- Custom styles for this template-->
            <link href="css-admin/sb-admin-2.min.css" rel="stylesheet">

        <body id="page-top">

            <!-- Page Wrapper -->
            <div id="wrapper">

                <!-- Sidebar -->
                <%@include file="header.jsp" %>
                    <!-- End of Sidebar -->

                    <!-- Content Wrapper -->
                    <div id="content-wrapper" class="d-flex flex-column">

                        <!-- Main Content -->
                        <div id="content">

                            <!-- Topbar -->
                            <%@include file="nav.jsp" %>
                                <!-- End of Topbar -->
                                <!-- Begin Page Content -->
                                <div class="container-fluid" id="contentToPrint">

                                    <!-- Page Heading -->
                                    <h1 class="h3 mb-2 text-gray-800">Listes Tarifs</h1>

                                    <div class="p-5">
                                        <form action="/ajoutTarif" method="get">
                                            <label>Duree :</label>
                                            <input  type="number" class="form-control form-control-user"
                                            id="exampleInputPassword" name="duree" >
                                            <lable>Montant : </lable>
                                            <input  type="number" class="form-control form-control-user"
                                            id="exampleInputPassword" name="montant" >
                                            <label>Unite :</label>
                                            <select name="idUnite">
                                                <liste:forEach items="${listeUnite}"
                                                    var="unite">
                                                    <option
                                                        value="${unite.getId()}">
                                                        ${unite.getNom()}
                                                    </option>
                                                </liste:forEach>
                                            </select>
                                            <br>
                                            <input type="submit" class="btn btn-primary btn-user"
                                                Value="Valider" />
                                        </form>
                                        <br>
                                    </div>
                                    <!-- DataTales Example -->
                                    <div class="card shadow mb-4">
                                        <div class="card-header py-3">
                                            <h6 class="m-0 font-weight-bold text-primary">Listes Tarifs</h6>
                                        </div>
                                        <div class="card-body">
                                            <div class="table-responsive">
                                                <table class="table table-bordered" id="myTable" width="100%"
                                                    cellspacing="0">
                                                    <thead>
                                                        <tr>
                                                            <th>#</th>
                                                            <th>Duree</th>
                                                            <th>Unite</th>
                                                            <th>Valeur</th>
                                                            <th>Action</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <liste:forEach var="tarif" items="${liste}">
                                                            <tr>
                                                                <td>${tarif.getId()}</td>
                                                                <td>${tarif.getDuree()}</td>
                                                                <td>Minutes</td>
                                                                <td>${tarif.getValeur()}</td>
                                                                <td> <a href="/supprimerTarif?id=${tarif.getId()}" class="btn btn-danger btn-circle">
                                                                    <i class="fas fa-trash"></i>
                                                                </a><a href="/modifierTarif?id=${tarif.getId()}" class="btn btn-info btn-circle">
                                                                    <i class="fas fa-info-circle"></i>
                                                                </a>
                                                                </td>
                                                            </tr>
                                                        </liste:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div id="elementH"></div>

                                <!-- /.container-fluid -->

                        </div>
                        <!-- End of Main Content -->

                        <!-- Footer -->
                        <%@include file="footer.jsp" %>
        </body>