<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="<c:url value='/favicon.ico'/>">
    <title>Tiny Dashboard - A Bootstrap Dashboard Template</title>


    <!-- Simple bar CSS -->
    <link rel="stylesheet" href="<c:url value='/css/simplebar.css'/>">
    <!-- Fonts CSS -->
    <link href="https://fonts.googleapis.com/css2?family=Overpass:ital,wght@0,100;0,200;0,300;0,400;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
    <!-- Icons CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/feather-icons/dist/feather.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <!-- FullCalendar CSS -->
    <link rel="stylesheet" href="<c:url value='/css/fullcalendar.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/select2.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/dropzone.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/uppy.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/jquery.steps.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/jquery.timepicker.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/quill.snow.css'/>">
    <!-- Date Range Picker CSS -->
    <link rel="stylesheet" href="<c:url value='/css/daterangepicker.css'/>">
    <!-- App CSS -->
    <link rel="stylesheet" href="<c:url value='/css/app-light.css'/>" id="lightTheme" disabled>
    <link rel="stylesheet" href="<c:url value='/css/app-dark.css'/>" id="darkTheme">
</head>
<style>
    i.lemoon::after{
        content: none!important;
    }
</style>
<body class="vertical dark">
<div class="wrapper">
    <nav class="topnav navbar navbar-light">
        <button type="button" class="navbar-toggler text-muted mt-2 p-0 mr-3 collapseSidebar">
            <i class="fa-solid fa-bars fa-2x"></i>
        </button>
        <ul class="nav">
            <li class="nav-item mb-2">
                <a class="nav-link text-muted" href="#" id="modeSwitcher" data-mode="dark">
                    <i class="fa-solid fa-moon fa-2x"></i>
                </a>
            </li>
            <li class="nav-item dropdown">

                <div class="float-right d-flex">
                    <span>${sessionScope.user.lastName}</span>
                    <form action="${pageContext.request.contextPath}/logout" method="post">
                        <button class="btn btn-sm ml-3 btn-warning" type="submit">Deconnexion</button>
                    </form>
                </div>
            </li>
        </ul>
    </nav>
    <aside class="sidebar-left border-right bg-white shadow" id="leftSidebar" data-simplebar>
        <a href="#" class="btn collapseSidebar toggle-btn d-lg-none text-muted ml-2 mt-3" data-toggle="toggle">
            <i class="fe fe-x"><span class="sr-only"></span></i>
        </a>
        <nav class="vertnav navbar navbar-light">
            <!-- nav bar -->
            <div class="w-100 mb-4 d-flex">
                <a class="navbar-brand mx-auto mt-2 flex-fill text-center" href="./index.html">
                    <svg version="1.1" id="logo" class="navbar-brand-img brand-sm" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 120 120" xml:space="preserve">
                        <g>
                            <polygon class="st0" points="78,105 15,105 24,87 87,87"/>
                            <polygon class="st0" points="96,69 33,69 42,51 105,51"/>
                            <polygon class="st0" points="78,33 15,33 24,15 87,15"/>
                        </g>
                    </svg>
                </a>
            </div>
            <ul class="navbar-nav flex-fill w-100 mb-2">
                 <li class="nav-item w-100">
                <a class="nav-link" href="${pageContext.request.contextPath}/listProperties">
                    <i class="fa-solid fa-building fa-2x"></i>
                    <span class="ml-3 item-text">Liste des immeubles</span>
                </a>
            </li>
                <li class="nav-item w-100">
                    <a class="nav-link" href="${pageContext.request.contextPath}/addProperty">
                        <i class="fa-solid fa-square-plus fa-2x"></i>
                        <span class="ml-3 item-text">Ajouter un immeuble</span>
                    </a>
                </li>
                <li class="nav-item w-100">
                <a class="nav-link" href="${pageContext.request.contextPath}/listUnits">
                    <i class="fa-solid fa-house-chimney-window fa-2x"></i>
                    <span class="ml-3 item-text">Liste des unites</span>
                </a>
            </li> <li class="nav-item w-100">
                <a class="nav-link" href="${pageContext.request.contextPath}/addUnit">
                    <i class="fa-solid fa-plus fa-2x"></i>
                    <span class="ml-3 item-text">Ajouter une unites</span>
                </a>
            </li>

                <li class="nav-item w-100">
                <a class="nav-link" href="${pageContext.request.contextPath}/listLocation">
                    <i class="fa-solid fa-bell fa-2x"></i>
                    <span class="ml-3 item-text">Demande de locations</span>
                </a>
            </li>
                <li class="nav-item w-100">
                    <a class="nav-link" href="${pageContext.request.contextPath}/ownerPayments">
                        <i class="fa-solid fa-sack-dollar fa-2x"></i>
                        <span class="ml-3 item-text">Mes paiements</span>
                    </a>
                </li>

            </ul>
        </nav>
    </aside>
    <main role="main" class="main-content">
        <div class="container-fluid">
            <div class="row justify-content-center">
                <div class="col-12">
                    <!-- Your content here -->
