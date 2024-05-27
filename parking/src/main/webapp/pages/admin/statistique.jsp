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

            <title>Immobilisation</title>

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


                                    <!-- Content Row -->
                                    <div class="row">



                                        <!-- Donut Chart -->
                                        <div class="col-xl-4 col-lg-5">
                                            <div class="card shadow mb-4">
                                                <!-- Card Header - Dropdown -->
                                                <div class="card-header py-3">
                                                    <h6 class="m-0 font-weight-bold text-primary">Statistique </h6>
                                                </div>
                                                <!-- Card Body -->
                                                <div class="card-body">
                                                    <div class="chart-pie pt-5">
                                                        <canvas id="myPieChart"></canvas>
                                                    </div>
                                                    <hr>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <!-- /.container-fluid -->

                        </div>
                        <!-- End of Main Content -->

                        <!-- Footer -->
                        <footer class="sticky-footer bg-white">
                            <div class="container my-auto">
                                <div class="copyright text-center my-auto">
                                    <span>Copyright &copy; Your Website 2020</span>
                                </div>
                            </div>
                        </footer>
                        <!-- End of Footer -->

                    </div>
                    <!-- End of Content Wrapper -->

            </div>
            <!-- End of Page Wrapper -->

            <!-- Scroll to Top Button-->
            <a class="scroll-to-top rounded" href="#page-top">
                <i class="fas fa-angle-up"></i>
            </a>



            <!-- Bootstrap core JavaScript-->
            <script src="vendor-admin/jquery/jquery.min.js"></script>
            <script src="vendor-admin/bootstrap/js/bootstrap.bundle.min.js"></script>

            <!-- Core plugin JavaScript-->
            <script src="vendor-admin/jquery-easing/jquery.easing.min.js"></script>

            <!-- Custom scripts for all pages-->
            <script src="js-admin/sb-admin-2.min.js"></script>

            <!-- Page level plugins -->
            <script src="vendor-admin/chart.js/Chart.min.js"></script>

            <!-- Page level custom scripts -->
            <!-- <script src="js-admin/demo/chart-pie-demo.js"></script> -->
            <script>
                // Set new default font family and font color to mimic Bootstrap's default styling
                // Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
                // Chart.defaults.global.defaultFontColor = '#858796';

                // Pie Chart Example
                fetch("chart/stat").then(response => {
                                            return response.json();
                                        }).then(jsondata => {
                                            var label = []
                                            var cons = []

                                            var labels = jsondata.map(function (item) {
                                                console.log(item);
                                                console.log(item.designation);
                                                label.push(item.designation );
                                            });

                                            var datas = jsondata.map(function (item) {
                                                cons.push(item.nombre);
                                            });
                                            //cons.push(10);

                var ctx = document.getElementById("myPieChart");
                var myPieChart = new Chart(ctx, {
                    type: 'doughnut',
                    data: {
                        labels: label,
                        datasets: [{
                            labels: '#',
                            data: cons,
                            backgroundColor: ['#4e73df', '#1cc88a', '#36b9cc'],
                            hoverBackgroundColor: ['#2e59d9', '#17a673', '#2c9faf'],
                            hoverBorderColor: "rgba(234, 236, 244, 1)",
                        }],
                    },
                    options: {
                        maintainAspectRatio: false,
                        tooltips: {
                            backgroundColor: "rgb(255,255,255)",
                            bodyFontColor: "#858796",
                            borderColor: '#dddfeb',
                            borderWidth: 1,
                            xPadding: 15,
                            yPadding: 15,
                            displayColors: false,
                            caretPadding: 10,
                        },
                        legend: {
                            display: false
                        },
                        cutoutPercentage: 80,
                    },
                })
            });

            </script>

        </body>

        </html>