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
        <title>User Profile</title>
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
        <link href="../../../assets/css/_all-skins.min.css" rel="stylesheet" type="text/css"/>

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

            <header class="main-header">
                <!-- Logo -->
                <a href="../../index2.html" class="logo">
                    <!-- mini logo for sidebar mini 50x50 pixels -->
                    <span class="logo-mini"><b>A</b>LT</span>
                    <!-- logo for regular state and mobile devices -->
                    <span class="logo-lg"><b>Admin</b>LTE</span>
                </a>
                <!-- Header Navbar: style can be found in header.less -->
                <nav class="navbar navbar-static-top">
                    <!-- Sidebar toggle button-->
                    <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>

                    <button type="button" class="btn btn-outline-danger pull-right" style="margin-top: 8px; margin-right: 15px;"><i class="icon-cog3 mr-2"></i> Log Out</button>
                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <!-- User Account: style can be found in dropdown.less -->
                            <li class=" user user-menu">
                                <a href="#">
                                    <img src="../../../assets/img/user7-128x128.jpg" class="user-image" alt="User Image">
                                    <span class="hidden-xs">Alexander Pierce</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </header>
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="main-sidebar">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="../../../assets/img/user7-128x128.jpg" class="img-circle" alt="User Image">
                        </div>
                        <div class="pull-left info">
                            <p>Alexander Pierce</p>
                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>
                    <!-- sidebar menu: : style can be found in sidebar.less -->
                    <ul class="sidebar-menu" data-widget="tree">
                        <li class="header">MAIN NAVIGATION</li>
                        <li class="treeview active">
                            <a href="#">
                                <i class="fa fa-user"></i>
                                <span>Profile</span>
                            </a>
                        </li>
                        <li class="treeview">
                            <a href="#tickets" data-toggle="tab">
                                <i class="fa fa-ticket"></i>
                                <span>Tickets</span>
                            </a>
                        </li>
                        <li class="treeview">
                            <a href="#payments" data-toggle="tab">
                                <i class="fa fa-bank"></i>
                                <span>Payment</span>
                            </a>
                        </li>
                        <li class="treeview">
                            <a href="#wallet" data-toggle="tab">
                                <i class="fa fa-credit-card"></i>
                                <span>Wallet</span>
                            </a>
                        </li>
                    </ul>
                </section>
                <!-- /.sidebar -->
            </aside>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Admin
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">

                    <div class="row">
                        <div class="col-md-3">

                            <!-- Profile Image -->
                            <div class="box box-primary">
                                <div class="box-body box-profile">
                                    <img class="profile-user-img img-responsive img-circle" src="../../../assets/img/user7-128x128.jpg"
                                         alt="User profile picture">

                                    <h3 class="profile-username text-center">Nina Mcintire</h3>

                                    <p class="text-muted text-center">Software Engineer</p>

                                    <ul class="list-group list-group-unbordered">
                                        <li class="list-group-item">
                                            <b>Followers</b> <a class="pull-right">1,322</a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Following</b> <a class="pull-right">543</a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Friends</b> <a class="pull-right">13,287</a>
                                        </li>
                                    </ul>

                                    <a href="#" class="btn btn-primary btn-block"><b>Follow</b></a>
                                </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->

                            <!-- About Me Box -->
                            <!--                            <div class="box box-primary">
                                                            <div class="box-header with-border">
                                                                <h3 class="box-title">About Me</h3>
                                                            </div>
                                                             /.box-header 
                                                            <div class="box-body">
                                                                <strong><i class="fa fa-book margin-r-5"></i> Education</strong>
                            
                                                                <p class="text-muted">
                                                                    B.S. in Computer Science from the University of Tennessee at Knoxville
                                                                </p>
                            
                                                                <hr>
                            
                                                                <strong><i class="fa fa-map-marker margin-r-5"></i> Location</strong>
                            
                                                                <p class="text-muted">Malibu, California</p>
                            
                                                                <hr>
                            
                                                                <strong><i class="fa fa-pencil margin-r-5"></i> Skills</strong>
                            
                                                                <p>
                                                                    <span class="label label-danger">UI Design</span>
                                                                    <span class="label label-success">Coding</span>
                                                                    <span class="label label-info">Javascript</span>
                                                                    <span class="label label-warning">PHP</span>
                                                                    <span class="label label-primary">Node.js</span>
                                                                </p>
                            
                                                                <hr>
                            
                                                                <strong><i class="fa fa-file-text-o margin-r-5"></i> Notes</strong>
                            
                                                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam fermentum enim neque.</p>
                                                            </div>
                                                             /.box-body 
                                                        </div>-->
                            <!-- /.box -->
                        </div>
                        <!-- /.col -->
                        <div class="col-md-9">
                            <div class="nav-tabs-custom">
                                <ul class="nav nav-tabs">
                                    <li class="active"><a href="#tickets" data-toggle="tab">Tickets</a></li>
                                    <li><a href="#payments" data-toggle="tab">Payments</a></li>
                                    <li><a href="#wallet" data-toggle="tab">Wallet</a></li>
                                </ul>
                                <div class="tab-content">


                                    <div class="active tab-pane" id="tickets">
                                        <div class="row">
                                            <div class="col-lg-4 col-xs-6">
                                                <!-- small box -->
                                                <div class="small-box bg-aqua">
                                                    <div class="inner">
                                                        <h3>169,950</h3>

                                                        <p>Total No of Tickets Gotten<br> for Free</p>
                                                    </div>
                                                    <div class="icon">
                                                        <i class="ion ion-bag"></i>
                                                    </div>
                                                    <a href="#" class="small-box-footer"><i class="fa fa-arrow-circle-right"></i></a>
                                                </div>
                                            </div>
                                            <!-- ./col -->
                                            <div class="col-lg-4 col-xs-6">
                                                <!-- small box -->
                                                <div class="small-box bg-green">
                                                    <div class="inner">
                                                        <h3>120,000</h3>

                                                        <p>Total No of Tickets <br>Paid For</p>
                                                    </div>
                                                    <div class="icon">
                                                        <i class="ion ion-stats-bars"></i>
                                                    </div>
                                                    <a href="#" class="small-box-footer"><i class="fa fa-arrow-circle-right"></i></a>
                                                </div>
                                            </div>
                                            <!-- ./col -->
                                        </div>

                                        <div class="row">
                                            <div class="col-xs-12">
                                                <div class="box">
                                                    <!-- /.box-header -->
                                                    <div class="box-body table-responsive no-padding">
                                                        <table class="table table-hover">
                                                            <tr>
                                                                <th>Serial No</th>
                                                                <th>User Name</th>
                                                                <th>Ticket Type</th>
                                                                <th>No of Ticket<br> Bought</th>
                                                                <th>Amount Paid</th>
                                                                <th>Date</th>
                                                                <th>Time</th>
                                                                <th>Ticket Paid For</th>
                                                                <th>Free Ticket</th>
                                                                <th>Ticket Detail</th>
                                                            </tr>
                                                            <tr>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                                <td>22: 13 am</td>
                                                                <td>John Doe</td>
                                                                <td>11-7-2014</td>
                                                                <td><a href="#"><span class="label label-success" data-toggle="modal"
                                                                                      data-target="#modal-default">Detail</span></a></td>
                                                            </tr>
                                                            <tr>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                                <td>22: 13 am</td>
                                                                <td>Alexander Pierce</td>
                                                                <td>11-7-2014</td>
                                                                <td><a href="#"><span class="label label-success" data-toggle="modal"
                                                                                      data-target="#modal-default">Detail</span></a></td>
                                                            </tr>
                                                            <tr>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                                <td>22: 13 am</td>
                                                                <td>Bob Doe</td>
                                                                <td>11-7-2014</td>
                                                                <td><a href="#"><span class="label label-success" data-toggle="modal"
                                                                                      data-target="#modal-default">Detail</span></a></td>
                                                            </tr>
                                                            <tr>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                                <td>22: 13 am</td>
                                                                <td>Mike Doe</td>
                                                                <td>11-7-2014</td>
                                                                <td><a href="#"><span class="label label-success" data-toggle="modal"
                                                                                      data-target="#modal-default">Detail</span></a></td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                    <!-- /.box-body -->
                                                    <div class="box-footer clearfix">
                                                        <ul class="pagination pagination-sm no-margin pull-right">
                                                            <li><a href="#">&laquo;</a></li>
                                                            <li><a href="#">1</a></li>
                                                            <li><a href="#">2</a></li>
                                                            <li><a href="#">3</a></li>
                                                            <li><a href="#">&raquo;</a></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <!-- /.box -->
                                            </div>
                                        </div>
                                    </div>





                                    <div class="modal fade" id="modal-default">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span></button>
                                                    <h4 class="modal-title">Ticket Details</h4>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="box-body table-responsive no-padding">
                                                        <table class="table table-hover">
                                                            <tr>
                                                                <th>Serial No</th>
                                                                <th>Ticket No</th>
                                                            </tr>
                                                            <tr>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                            </tr>
                                                            <tr>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                            </tr>
                                                            <tr>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                            </tr>
                                                            <tr>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
                                                </div>
                                            </div>
                                            <!-- /.modal-content -->
                                        </div>
                                        <!-- /.modal-dialog -->
                                    </div>
                                    <!-- /.modal -->


                                    <!-- /.tab-pane -->
                                    <div class="tab-pane" id="payments">
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <div class="box">
                                                    <!-- /.box-header -->
                                                    <div class="box-body table-responsive no-padding">
                                                        <table class="table table-hover">
                                                            <tr>
                                                                <th>Serial No</th>
                                                                <th>User Name</th>
                                                                <th>Amount Paid</th>
                                                                <th>Date</th>
                                                                <th>Time</th>
                                                                <th>Ticket Type</th>
                                                                <th>Ticket No</th>
                                                                <th>No of Ticket<br> Bought</th>
                                                            </tr>
                                                            <tr>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                                <td>22: 13 am</td>
                                                                <td>John Doe</td>
                                                                <td>11-7-2014</td>
                                                                <td><span class="label label-success">Approved</span></td>
                                                            </tr>
                                                            <tr>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                                <td>22: 13 am</td>
                                                                <td>Alexander Pierce</td>
                                                                <td>11-7-2014</td>
                                                                <td><span class="label label-warning">Pending</span></td>
                                                            </tr>
                                                            <tr>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                                <td>22: 13 am</td>
                                                                <td>Bob Doe</td>
                                                                <td>11-7-2014</td>
                                                                <td><span class="label label-primary">Approved</span></td>
                                                            </tr>
                                                            <tr>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                                <td>--------</td>
                                                                <td>22: 13 am</td>
                                                                <td>Mike Doe</td>
                                                                <td>11-7-2014</td>
                                                                <td><span class="label label-danger">Denied</span></td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                    <!-- /.box-body -->
                                                    <div class="box-footer clearfix">
                                                        <ul class="pagination pagination-sm no-margin pull-right">
                                                            <li><a href="#">&laquo;</a></li>
                                                            <li><a href="#">1</a></li>
                                                            <li><a href="#">2</a></li>
                                                            <li><a href="#">3</a></li>
                                                            <li><a href="#">&raquo;</a></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <!-- /.box -->
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.tab-pane -->






                                    <div class="tab-pane" id="wallet">
                                        <div class="row">
                                            <div class="col-lg-4 col-xs-6">
                                                <!-- small box -->
                                                <div class="info-box">
                                                    <span class="info-box-icon bg-aqua"><i class="ion ion-card"></i></span>

                                                    <div class="info-box-content">
                                                        <span class="info-box-text">Amount in User Wallet</span>
                                                        <span class="info-box-number">&#8358; 12,000</span>
                                                    </div>
                                                    <!-- /.info-box-content -->
                                                </div>
                                            </div>
                                            <!-- ./col -->
                                        </div>
                                    </div>
                                    <!-- /.tab-pane -->
                                </div>
                                <!-- /.tab-content -->
                            </div>
                            <!-- /.nav-tabs-custom -->
                        </div>
                        <!-- /.col -->
                    </div>
                    <!-- /.row -->

                </section>
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->
            <footer class="main-footer">
                <div class="pull-right hidden-xs">
                    <b>Version</b> 2.4.0
                </div>
                <strong>Copyright &copy; 2014-2016 <a href="https://adminlte.io">Almsaeed Studio</a>.</strong> All rights
                reserved.
            </footer>

            <!-- Control Sidebar -->
            <aside class="control-sidebar control-sidebar-dark">
                <!-- Create the tabs -->
                <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
                    <li><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
                    <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
                </ul>
                <!-- Tab panes -->
                <div class="tab-content">
                    <!-- Home tab content -->
                    <div class="tab-pane" id="control-sidebar-home-tab">
                        <h3 class="control-sidebar-heading">Recent Activity</h3>
                        <ul class="control-sidebar-menu">
                            <li>
                                <a href="javascript:void(0)">
                                    <i class="menu-icon fa fa-birthday-cake bg-red"></i>

                                    <div class="menu-info">
                                        <h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

                                        <p>Will be 23 on April 24th</p>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)">
                                    <i class="menu-icon fa fa-user bg-yellow"></i>

                                    <div class="menu-info">
                                        <h4 class="control-sidebar-subheading">Frodo Updated His Profile</h4>

                                        <p>New phone +1(800)555-1234</p>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)">
                                    <i class="menu-icon fa fa-envelope-o bg-light-blue"></i>

                                    <div class="menu-info">
                                        <h4 class="control-sidebar-subheading">Nora Joined Mailing List</h4>

                                        <p>nora@example.com</p>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)">
                                    <i class="menu-icon fa fa-file-code-o bg-green"></i>

                                    <div class="menu-info">
                                        <h4 class="control-sidebar-subheading">Cron Job 254 Executed</h4>

                                        <p>Execution time 5 seconds</p>
                                    </div>
                                </a>
                            </li>
                        </ul>
                        <!-- /.control-sidebar-menu -->

                        <h3 class="control-sidebar-heading">Tasks Progress</h3>
                        <ul class="control-sidebar-menu">
                            <li>
                                <a href="javascript:void(0)">
                                    <h4 class="control-sidebar-subheading">
                                        Custom Template Design
                                        <span class="label label-danger pull-right">70%</span>
                                    </h4>

                                    <div class="progress progress-xxs">
                                        <div class="progress-bar progress-bar-danger" style="width: 70%"></div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)">
                                    <h4 class="control-sidebar-subheading">
                                        Update Resume
                                        <span class="label label-success pull-right">95%</span>
                                    </h4>

                                    <div class="progress progress-xxs">
                                        <div class="progress-bar progress-bar-success" style="width: 95%"></div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)">
                                    <h4 class="control-sidebar-subheading">
                                        Laravel Integration
                                        <span class="label label-warning pull-right">50%</span>
                                    </h4>

                                    <div class="progress progress-xxs">
                                        <div class="progress-bar progress-bar-warning" style="width: 50%"></div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)">
                                    <h4 class="control-sidebar-subheading">
                                        Back End Framework
                                        <span class="label label-primary pull-right">68%</span>
                                    </h4>

                                    <div class="progress progress-xxs">
                                        <div class="progress-bar progress-bar-primary" style="width: 68%"></div>
                                    </div>
                                </a>
                            </li>
                        </ul>
                        <!-- /.control-sidebar-menu -->

                    </div>
                    <!-- /.tab-pane -->
                    <!-- Stats tab content -->
                    <div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
                    <!-- /.tab-pane -->
                    <!-- Settings tab content -->
                    <div class="tab-pane" id="control-sidebar-settings-tab">
                        <form method="post">
                            <h3 class="control-sidebar-heading">General Settings</h3>

                            <div class="form-group">
                                <label class="control-sidebar-subheading">
                                    Report panel usage
                                    <input type="checkbox" class="pull-right" checked>
                                </label>

                                <p>
                                    Some information about this general settings option
                                </p>
                            </div>
                            <!-- /.form-group -->

                            <div class="form-group">
                                <label class="control-sidebar-subheading">
                                    Allow mail redirect
                                    <input type="checkbox" class="pull-right" checked>
                                </label>

                                <p>
                                    Other sets of options are available
                                </p>
                            </div>
                            <!-- /.form-group -->

                            <div class="form-group">
                                <label class="control-sidebar-subheading">
                                    Expose author name in posts
                                    <input type="checkbox" class="pull-right" checked>
                                </label>

                                <p>
                                    Allow the user to show his name in blog posts
                                </p>
                            </div>
                            <!-- /.form-group -->

                            <h3 class="control-sidebar-heading">Chat Settings</h3>

                            <div class="form-group">
                                <label class="control-sidebar-subheading">
                                    Show me as online
                                    <input type="checkbox" class="pull-right" checked>
                                </label>
                            </div>
                            <!-- /.form-group -->

                            <div class="form-group">
                                <label class="control-sidebar-subheading">
                                    Turn off notifications
                                    <input type="checkbox" class="pull-right">
                                </label>
                            </div>
                            <!-- /.form-group -->

                            <div class="form-group">
                                <label class="control-sidebar-subheading">
                                    Delete chat history
                                    <a href="javascript:void(0)" class="text-red pull-right"><i class="fa fa-trash-o"></i></a>
                                </label>
                            </div>
                            <!-- /.form-group -->
                        </form>
                    </div>
                    <!-- /.tab-pane -->
                </div>
            </aside>
            <!-- /.control-sidebar -->
            <!-- Add the sidebar's background. This div must be placed
               immediately after the control sidebar -->
            <div class="control-sidebar-bg"></div>
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
        <!-- page script -->
    </body>

</html>
