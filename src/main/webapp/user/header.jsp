<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Dashtreme Admin - Free Dashboard for Bootstrap 4 by Codervent</title>
    <!-- loader-->
    <link href="/assets/css/pace.min.css" rel="stylesheet"/>
    <script src="/assets/js/pace.min.js"></script>
    <!-- favicon -->
    <link rel="icon" href="../assets/images/favicon.ico" type="image/x-icon">
    <!-- Bootstrap core CSS -->
    <link href="/assets/css/bootstrap.min.css" rel="stylesheet"/>
    <!-- animate CSS -->
    <link href="/assets/css/animate.css" rel="stylesheet" type="text/css"/>
    <!-- Icons CSS -->
    <link href="/assets/css/icons.css" rel="stylesheet" type="text/css"/>
    <!-- Custom Style -->
    <link href="/assets/css/app-style.css" rel="stylesheet"/>

    <!--favicon-->
    <!-- simplebar CSS-->
    <link href="/assets/plugins/simplebar/css/simplebar.css" rel="stylesheet"/>
    <!-- Bootstrap core CSS-->

    <!-- Icons CSS-->
    <!-- Sidebar CSS-->
    <link href="/assets/css/sidebar-menu.css" rel="stylesheet"/>
    <!-- Custom Style-->

</head>

<body class="bg-theme bg-theme2">

<!-- start loader -->
<div id="pageloader-overlay" class="visible incoming"><div class="loader-wrapper-outer"><div class="loader-wrapper-inner" ><div class="loader"></div></div></div></div>
<!-- end loader -->

<!-- Start wrapper-->
<div id="wrapper">

    <!--Start sidebar-wrapper-->
    <div id="sidebar-wrapper" data-simplebar="" data-simplebar-auto-hide="true">
        <div class="brand-logo">
            <a href="index.html">
                <h5 class="logo-text">IMMOBILIER</h5>
            </a>
        </div>
        <ul class="sidebar-menu do-nicescrol">
            <li class="sidebar-header">MAIN NAVIGATION</li>
            <li>
                <a href="index.html">
                    <i class="zmdi zmdi-view-dashboard"></i> <span>Dashboard</span>
                </a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/listAllProperties">
                    <i class="zmdi zmdi-home"></i> <span>Liste des immeubles</span>
                </a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/listuser">
                    <i class="zmdi zmdi-plus"></i> <span>Liste des utilisateurs</span>
                </a>
            </li>



        </ul>

    </div>
    <!--End sidebar-wrapper-->

    <!--Start topbar header-->
    <header class="topbar-nav">
        <nav class="navbar navbar-expand fixed-top">
            <ul class="navbar-nav mr-auto align-items-center">
                <li class="nav-item">
                    <a class="nav-link toggle-menu" href="javascript:void();">
                        <i class="icon-menu menu-icon"></i>
                    </a>
                </li>

            </ul>
            <div class="float-right d-flex">
                <span>${sessionScope.user.lastName}</span>
                <form action="${pageContext.request.contextPath}/logout" method="post">
                    <button class="btn btn-sm btn-warning" type="submit"> Deconnexion</button>
                </form>
            </div>
            </ul>
        </nav>
    </header>
    <!--End topbar header-->


    <div class="content-wrapper">