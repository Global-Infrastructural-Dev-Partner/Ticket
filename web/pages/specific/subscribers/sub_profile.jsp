<%-- 
    Document   : sub_profile
    Created on : 25-Nov-2019, 21:42:48
    Author     : ndfmac
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Subscriber Profile</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.7 -->
        <link href="../../../assets/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <!-- Font Awesome -->
        <link href="../../../assets/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <!-- Ionicons -->
        <link href="../../../assets/css/ionicons.min.css" rel="stylesheet" type="text/css"/>
        <!-- Theme style -->
        <link href="../../../assets/css/AdminLTE.min.css" rel="stylesheet" type="text/css"/>
        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <link rel="shortcut icon" href="../../../assets/img/favicon.png" type="image/x-icon">
        <link rel="icon" href="../../../assets/img/favicon.png" type="image/x-icon">
        <link href="../../../assets/css/_all-skins.min.css" rel="stylesheet" type="text/css"/>
        <link href="../../../assets/css/sweetalert2.min.css" rel="stylesheet" type="text/css"/>

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Google Font -->
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
    </head>

    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">
            <%@include file="../../../WEB-INF/jspf/general/new/header.jspf" %>
            <%@include file="../../../WEB-INF/jspf/general/new/sidebar.jspf" %>
            <jsp:include page="../../../WEB-INF/static_pages/specific/subscriber/sub_profile.jsp"></jsp:include>
            <%@include file="../../../WEB-INF/jspf/general/new/footer.jspf" %>
        </div>
        <!-- ./wrapper -->

        <!-- jQuery 3 -->
        <script src="../../../assets/js/jquery.min.js" type="text/javascript"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="../../../assets/js/bootstrap.min_1.js" type="text/javascript"></script>
        <!-- SlimScroll -->
        <script src="../../../assets/js/jquery.slimscroll.min.js" type="text/javascript"></script>
        <!-- FastClick -->
        <script src="../../../assets/js/fastclick.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <script src="../../../assets/js/adminlte.min.js" type="text/javascript"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="../../../assets/js/demo.js" type="text/javascript"></script>
        <script src="../../../assets/js/appScript.js" type="text/javascript"></script>
        <script src="../../../assets/js/helper.js" type="text/javascript"></script>
        <script src="../../../assets/js/sweetalert2.min.js" type="text/javascript"></script>
        <!-- page script -->
    </body>

</html>
