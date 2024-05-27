<%@ taglib prefix="liste" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Parking</title>

        <!-- Custom fonts for this template-->
        <link href="vendor-admin/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="css-admin/sb-admin-2.min.css" rel="stylesheet">

    </head>

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
                            <div class="container-fluid">

                                <!-- Page Heading -->
                                <h1 class="h3 mb-4 text-gray-800"> Ajouter parking </h1>
                                <div class="p-5">
                                    <h4>Tarif : ${tarif.getId()}</h4>
                                    <form action="/ajoutParking" method="get">
                                        <input  type="hidden" class="form-control form-control-user"
                                        name="id" value="${tarif.getId()}">
                                        <input  type="number" class="form-control form-control-user"
                                         name="duree" value="${tarif.getDuree()}">
                                        <br>
                                        <input  type="number" class="form-control form-control-user"
                                        name="valeur" value="${tarif.getValeur()}">
                                        <br>
                                        <input type="submit" class="btn btn-primary btn-user"
                                            Value="Valider" />
                                    </form>
                                    <br>
                                </div>
                            </div>
                            <!-- /.container-fluid -->

                    </div>
                    <!-- End of Main Content -->

                    <!-- Footer -->
                    <%@include file="footer.jsp" %>


    </body>

    </html>