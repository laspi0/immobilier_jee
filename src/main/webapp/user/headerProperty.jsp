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
    <link href="${pageContext.request.contextPath}/assets/css/pace.min.css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/assets/js/pace.min.js"></script>
    <!-- favicon -->
    <link rel="icon" href="${pageContext.request.contextPath}/assets/images/favicon.ico" type="image/x-icon">
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet"/>
    <!-- animate CSS -->
    <link href="${pageContext.request.contextPath}/assets/css/animate.css" rel="stylesheet" type="text/css"/>
    <!-- Icons CSS -->
    <link href="${pageContext.request.contextPath}/assets/css/icons.css" rel="stylesheet" type="text/css"/>
    <!-- Custom Style -->
    <link href="${pageContext.request.contextPath}/assets/css/app-style.css" rel="stylesheet"/>

    <!--favicon-->
    <!-- simplebar CSS-->
    <link href="${pageContext.request.contextPath}/assets/plugins/simplebar/css/simplebar.css" rel="stylesheet"/>
    <!-- Bootstrap core CSS-->

    <!-- Icons CSS-->
    <!-- Sidebar CSS-->
    <link href="${pageContext.request.contextPath}/assets/css/sidebar-menu.css" rel="stylesheet"/>
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
            <li class="list-unstyled">
                <a href="index.html">
                    <i class="zmdi zmdi-view-dashboard"></i> <span>Dashboard</span>
                </a>
            </li>

            <li class="list-unstyled">
                <a href="${pageContext.request.contextPath}/listProperties">
                    <i class="zmdi zmdi-home"></i> <span>Liste des immeubles</span>
                </a>
            </li>

            <li class="list-unstyled">
                <a href="${pageContext.request.contextPath}/addProperty">
                    <i class="zmdi zmdi-plus"></i> <span>Ajouter un immeuble</span>
                </a>
            </li>
            <li class="list-unstyled">
                <a href="${pageContext.request.contextPath}/listUnits">
                    <i class="zmdi zmdi-home"></i> <span>Liste des unites</span>
                </a>
            </li>
            <li class="list-unstyled">
                <a href="${pageContext.request.contextPath}/listLocation">
                    <i class="zmdi zmdi-home"></i> <span>Demande de locations</span>
                </a>
            </li>
            <li class="list-unstyled">
                <a href="${pageContext.request.contextPath}/addUnit">
                    <i class="zmdi zmdi-plus"></i> <span>Ajouter une unite</span>
                </a>
            </li> <li class="list-unstyled">
                <a href="${pageContext.request.contextPath}/ownerPayments">
                    <i class="zmdi zmdi-plus"></i> <span>Mes paiements</span>
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
                ${sessionScope.user.lastName}
                <form action="${pageContext.request.contextPath}/logout" method="post">
                    <button class="btn btn-sm btn-warning" type="submit"> Deconnexion</button>
                </form>
            </div>
            </ul>
        </nav>
    </header>
    <!--End topbar header-->


    <div class="content-wrapper">