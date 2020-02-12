<%-- 
    Document   : index
    Created on : 23-Nov-2019, 00:35:01
    Author     : ndfmac
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Event</title>
        <!-- Stylesheets -->
        <link href="assets/css/bootstrap.css" rel="stylesheet">
        <link href="assets/css/style.css" rel="stylesheet">
        <link href="assets/css/responsive.css" rel="stylesheet">
        <!--Color Switcher Mockup-->
        <link href="assets/css/color-switcher-design.css" rel="stylesheet">

        <link rel="shortcut icon" href="assets/img/favicon.png" type="image/x-icon">
        <link rel="icon" href="assets/img/favicon.png" type="image/x-icon">
        <link href="assets/css/sweetalert2.min.css" rel="stylesheet" type="text/css"/>
        <!-- Responsive -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    </head>


    <body>

        <div id="page-wrapper">
            <!-- Preloader -->
            <div class="preloader"></div>


            <jsp:include page="pages/specific/index/index.jsp"></jsp:include>
        </div>

        <!--Scroll to top-->
        <div class="scroll-to-top scroll-to-target" data-target="html"><span class="fa fa-angle-double-up"></span></div>
        <script src="assets/js/jquery.js"></script>
        <script src="assets/js/popper.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/jquery-ui.js"></script>
        <script src="assets/js/jquery.fancybox.js"></script>
        <script src="assets/js/jquery.countdown.js"></script>
        <script src="assets/js/appear.js"></script>
        <script src="assets/js/owl.js"></script>
        <script src="assets/js/wow.js"></script>
        <script src="assets/js/parallax.min.js"></script>
        <script src="assets/js/validate.js"></script>
        <script src="assets/js/script.js"></script>
        <script src="assets/js/appScript.js"></script>
        <script src="assets/js/helper.js"></script>
        <!-- Color Setting -->
        <script src="assets/js/color-settings.js"></script>
        <script src="assets/js/sweetalert2.min.js" type="text/javascript"></script>
        <script src="https://js.paystack.co/v1/inline.js"></script>
    </body>
</html>
