<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!doctype html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <meta name="description" content="">
  <meta name="author" content="">

  <title>Crispy Kitchen - Bootstrap 5 HTML Template</title>

  <!-- CSS FILES -->
  <link rel="preconnect" href="https://fonts.googleapis.com">

  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

  <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;600;700&display=swap" rel="stylesheet">
  <link href="static/css/bootstrap.min.css" rel="stylesheet">

  <link href="static/css/bootstrap-icons.css" rel="stylesheet">

  <link href="static/css/tooplate-crispy-kitchen.css" rel="stylesheet">

<<<<<<< HEAD
  <style>
    /*

Tooplate 2129 Crispy Kitchen

https://www.tooplate.com/view/2129-crispy-kitchen

*/


    /*---------------------------------------
      CUSTOM PROPERTIES ( VARIABLES )
    -----------------------------------------*/
    :root {
      --white-color:                  #FFFFFF;
      --primary-color:                #f17917;
      --secondary-color:              #f3af24;
      --section-bg-color:             #000000;
      --dark-color:                   #000000;
      --title-color:                  #565758;
      --news-title-color:             #FFFFFF;
      --p-color:                      #717275;
      --border-color:                 #eaeaea;
      --border-radius-default:        .25rem;

      --body-font-family:             'Montserrat', sans-serif;

      --h1-font-size:                 76px;
      --h2-font-size:                 56px;
      --h3-font-size:                 42px;
      --h4-font-size:                 28px;
      --h5-font-size:                 26px;
      --h6-font-size:                 22px;

      --p-font-size:                  18px;
      --menu-font-size:               18px;
      --category-font-size:           14px;

      --font-weight-light:            300;
      --font-weight-normal:           400;
      --font-weight-semibold:         600;
      --font-weight-bold:             700;
    }

    body {
      background: var(--dark-color);
      font-family: var(--body-font-family);
      position: relative;
    }

    /*---------------------------------------
      TYPOGRAPHY
    -----------------------------------------*/

    h2,
    h3,
    h4,
    h5,
    h6 {
      color: var(--primary-color);
      line-height: inherit;
    }

    h1,
    h2,
    h3,
    h4,
    h5,
    h6 {
      font-weight: var(--font-weight-semibold);
    }

    h1,
    h2 {
      font-weight: var(--font-weight-bold);
    }

    h1 {
      font-size: var(--h1-font-size);
      line-height: normal;
    }

    h2 {
      font-size: var(--h2-font-size);
    }

    h3 {
      font-size: var(--h3-font-size);
    }

    h4 {
      font-size: var(--h4-font-size);
    }

    h5 {
      font-size: var(--h5-font-size);
    }

    h6 {
      font-size: var(--h6-font-size);
    }

    p,
    .list .list-item {
      color: var(--p-color);
      font-size: var(--p-font-size);
      font-weight: var(--font-weight-light);
    }

    a,
    button {
      touch-action: manipulation;
      transition: all 0.3s;
    }

    .form-label {
      color: var(--p-color);
      font-weight: var(--font-weight-semibold);
    }

    a {
      color: var(--secondary-color);
      text-decoration: none;
    }

    a:hover {
      color: var(--primary-color);
    }

    ::selection {
      background: var(--primary-color);
      color: var(--white-color);
    }

    ::-moz-selection {
      background: var(--primary-color);
      color: var(--white-color);
    }

    .section-padding {
      padding-top: 8rem;
      padding-bottom: 8rem;
    }

    b,
    strong {
      font-weight: var(--font-weight-bold);
    }


    /*---------------------------------------
      CUSTOM BUTTON
    -----------------------------------------*/
    .custom-btn {
      border: 0;
      color: var(--white-color);
      font-size: var(--menu-font-size);
      padding: 10px 35px;
    }


    /*---------------------------------------
      NAVIGATION
    -----------------------------------------*/
    .navbar {
      background: var(--dark-color);
      padding-top: 20px;
      padding-bottom: 20px;
    }

    .navbar-brand {
      color: var(--primary-color);
      font-size: var(--h5-font-size);
      font-weight: var(--font-weight-bold);
      margin-right: 0;
    }

    .navbar-expand-lg .navbar-nav .nav-link {
      padding-right: 1.5rem;
      padding-left: 1.5rem;
    }

    .navbar-expand-lg .nav-link {
      color: var(--p-color);
      font-weight: var(--font-weight-normal);
      font-size: var(--menu-font-size);
      padding-top: 15px;
      padding-bottom: 15px;
    }

    .navbar-nav .nav-link.active,
    .nav-link:focus,
    .nav-link:hover {
      color: var(--primary-color);
    }

    .nav-link:focus {
      color: var(--p-color);
    }

    .navbar-toggler {
      border: 0;
      padding: 0;
      cursor: pointer;
      margin: 0;
      width: 30px;
      height: 35px;
      outline: none;
    }

    .navbar-toggler:focus {
      outline: none;
      box-shadow: none;
    }

    .navbar-toggler[aria-expanded="true"] .navbar-toggler-icon {
      background: transparent;
    }

    .navbar-toggler[aria-expanded="true"] .navbar-toggler-icon:before,
    .navbar-toggler[aria-expanded="true"] .navbar-toggler-icon:after {
      transition: top 300ms 50ms ease, -webkit-transform 300ms 350ms ease;
      transition: top 300ms 50ms ease, transform 300ms 350ms ease;
      transition: top 300ms 50ms ease, transform 300ms 350ms ease, -webkit-transform 300ms 350ms ease;
      top: 0;
    }

    .navbar-toggler[aria-expanded="true"] .navbar-toggler-icon:before {
      transform: rotate(45deg);
    }

    .navbar-toggler[aria-expanded="true"] .navbar-toggler-icon:after {
      transform: rotate(-45deg);
    }

    .navbar-toggler .navbar-toggler-icon {
      background: var(--dark-color);
      transition: background 10ms 300ms ease;
      display: block;
      width: 30px;
      height: 2px;
      position: relative;
    }

    .navbar-toggler .navbar-toggler-icon:before,
    .navbar-toggler .navbar-toggler-icon:after {
      transition: top 300ms 350ms ease, -webkit-transform 300ms 50ms ease;
      transition: top 300ms 350ms ease, transform 300ms 50ms ease;
      transition: top 300ms 350ms ease, transform 300ms 50ms ease, -webkit-transform 300ms 50ms ease;
      position: absolute;
      right: 0;
      left: 0;
      background: var(--dark-color);
      width: 30px;
      height: 2px;
      content: '';
    }

    .navbar-toggler .navbar-toggler-icon:before {
      top: -8px;
    }

    .navbar-toggler .navbar-toggler-icon:after {
      top: 8px;
    }


    /*---------------------------------------
      SITE HEADER
    -----------------------------------------*/
    .site-header {
      background-repeat: no-repeat;
      background-position: center;
      background-size: cover;
      padding-top: 10rem;
      padding-bottom: 10rem;
      text-align: center;
      position: relative;
    }

    .site-header .container {
      position: relative;
      z-index: 2;
    }

    .site-news-detail-header {
      background: transparent;
      text-align: left;
      padding-top: 6rem;
      padding-bottom: 6rem;
    }

    .site-about-header {
      background-image: url('../images/header/briana-tozour-V_Nkf1E-vYA-unsplash.jpg');
    }

    .site-menu-header {
      background-image: url('../images/header/luisa-brimble-aFzg83dvnAI-unsplash.jpg');
    }

    .site-news-header {
      background-image: url('../images/header/priscilla-du-preez-W3SEyZODn8U-unsplash.jpg');
    }

    .site-contact-header {
      background-image: url('../images/header/rod-long-I79Pgmhmy5M-unsplash.jpg');
    }

    .overlay {
      background: linear-gradient(to top, var(--dark-color), transparent 100%);
      position: absolute;
      top: 0;
      right: 0;
      bottom: 0;
      left: 0;
    }


    /*---------------------------------------
      HERO
    -----------------------------------------*/
    .hero {
      position: relative;
      overflow: hidden;
      padding-top: 20.542rem;
      padding-bottom: 20.542rem;
    }

    .hero .container {
      position: absolute;
      z-index: 9;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      width: 100%;
    }

    .video-wrap {
      z-index: -100;
    }

    .custom-video {
      position: absolute;
      top: 0;
      left: 0;
      object-fit: cover;
      width: 100%;
      height: 100%;
    }


    /*---------------------------------------
      HERO SLIDE
    -----------------------------------------*/
    .carousel-image {
      width: 100%;
      height: 100%;
      object-fit: cover;
    }

    .carousel-thumb {
      position: relative;
    }

    .carousel-caption {
      background: linear-gradient(to top, var(--dark-color), transparent 90%);
      text-align: left;
      position: absolute;
      right: 0;
      bottom: 0;
      left: 0;
      padding: 60px 40px;
    }

    .hero-carousel .reviews-text,
    .hero-text {
      color: var(--white-color);
    }

    .price-tag {
      background: var(--white-color);
      border-radius: 100px;
      color: var(--secondary-color);
      font-size: var(--menu-font-size);
      font-weight: var(--font-weight-semibold);
      display: inline-block;
      width: 84px;
      height: 64px;
      line-height: 64px;
      text-align: center;
    }

    .hero-carousel .carousel-control-prev,
    .hero-carousel .carousel-control-next {
      position: absolute;
      top: auto;
      bottom: 0;
      opacity: 1;
    }

    .hero-carousel .carousel-control-prev,
    .hero-carousel .carousel-control-next {
      background: var(--secondary-color);
      width: 60px;
      height: 60px;
      text-align: center;
    }

    .hero-carousel .carousel-control-prev {
      left: auto;
      right: 60px;
    }

    .hero-carousel .carousel-control-next {
      background: var(--primary-color);
      right: 0;
    }

    .hero-carousel .carousel-control-prev:hover,
    .hero-carousel .carousel-control-next:hover {
      background: var(--dark-color);
    }

    .hero-carousel .carousel-control-prev-icon,
    .hero-carousel .carousel-control-next-icon {
      background-image: none;
      width: inherit;
      height: inherit;
      line-height: 60px;
    }

    .hero-carousel .carousel-control-prev-icon::before,
    .hero-carousel .carousel-control-next-icon::before {
      font-family: bootstrap-icons;
      display: block;
      margin: auto;
      font-size: var(--h5-font-size);
      color: var(--white-color);
    }

    .hero-carousel .carousel-control-prev-icon::before {
      content: "\f13f";
    }

    .hero-carousel .carousel-control-next-icon::before {
      content: "\f144";
    }

    .reviews-icon {
      color: var(--secondary-color);
    }


    /*---------------------------------------
      MENU
    -----------------------------------------*/
    .menu,
    .about,
    .news,
    .related-news,
    .newsletter,
    .comments {
      background: var(--section-bg-color);
    }

    .menu-thumb {
      position: relative;
      overflow: hidden;
    }

    .menu-info {
      padding: 20px 20px 30px 20px;
    }

    .menu-image {
      display: block;
    }

    .menu-image-wrap {
      position: relative;
    }

    .menu-tag {
      position: absolute;
      top: 0;
      right: 0;
      margin: 20px;
    }

    .newsletter-image {
      border-radius: 100%;
      object-fit: cover;
      display: block;
      margin: 0 auto;
      max-width: 450px;
      max-height: 450px;
    }


    /*---------------------------------------
      NEWS
    -----------------------------------------*/
    .news-thumb {
      position: relative;
      overflow: hidden;
    }

    .news-thumb > a {
      display: block;
      width: 100%;
      height: 100%;
      position: relative;
      z-index: 2;
      transition: all 0.3s ease-out;
    }

    .news-thumb > a:hover {
      transform: scale(1.2);
    }

    .news-text-info {
      background: var(--section-bg-color);
      position: relative;
      z-index: 2;
      padding: 20px;
      transition: all 0.3s ease-out;
    }

    .related-news .news-text-info {
      background: var(--dark-color);
    }

    .news-text-info-large {
      background: linear-gradient(to top, var(--dark-color), transparent 90%);
      position: absolute;
      z-index: 2;
      bottom: 0;
      right: 0;
      left: 0;
      pointer-events: none;
    }

    .news-text-info-large .news-title-link {
      color: var(--white-color);
    }

    .news-title-link {
      color: var(--news-title-color);
    }

    .news-image {
      display: block;
      width: 100%;
      transition: all 0.3s ease-out;
    }

    .news-image:hover {
      transform: scale(1.2);
    }

    .category-tag,
    .menu-tag {
      background: var(--dark-color);
      border-radius: var(--border-radius-default);
      color: var(--white-color);
      font-size: var(--category-font-size);
      display: inline-block;
      padding: 4px 12px;
    }

    .category-tag {
      margin-bottom: 5px;
    }

    .comment-form {
      margin-bottom: 60px;
    }

    .news-author {
      border-bottom: 1px solid rgba(0,0,0,0.05);
      margin-bottom: 30px;
      padding-left: 15px;
      padding-right: 15px;
      padding-bottom: 30px;
    }

    .news-author:last-child {
      border-bottom: 0;
      padding-bottom: 0;
    }

    .news-author-image {
      border-radius: 100px;
      object-fit: cover;
      width: 70px;
      height: 70px;
    }


    /*---------------------------------------
      CUSTOM FORM
    -----------------------------------------*/
    .custom-form .form-control {
      margin-bottom: 20px;
      padding: 14px 10px;
      transition: all 0.3s;
    }

    .custom-form button[type="submit"] {
      background: var(--dark-color);
      border: 0;
      color: var(--white-color);
      text-transform: uppercase;
    }

    .custom-form button[type="submit"]:hover {
      background: var(--primary-color);
    }


    /*---------------------------------------
      SUBSCRIBE FORM
    -----------------------------------------*/
    .subscribe-form .form-control {
      margin-top: 20px;
      margin-bottom: 10px;
    }


    /*---------------------------------------
      BOOKING FORM
    -----------------------------------------*/
    #BookingModal .modal-content {
      border: 0;
      overflow: hidden;
    }

    #BookingModal .modal-content::before {
      content: "";
      background-color: var(--dark-color);
      background-image: url('../images/sincerely-media-HoEYgBL_Gcs-unsplash.jpg');
      background-repeat: no-repeat;
      background-position: top;
      background-size: cover;
      width: 60%;
      height: 100%;
      position: absolute;
      top: 0;
      right: 0;
    }

    #BookingModal .modal-header {
      border-bottom: 0;
      position: relative;
      padding: 26px 32px 0 32px;
    }

    #BookingModal .modal-body {
      padding: 38px 32px;
      padding-right: 35%;
    }

    #BookingModal .modal-footer {
      border-top: 0;
      padding: 0;
    }

    .booking-form .form-control {
      font-weight: var(--font-weight-normal);
      padding-top: 12px;
      padding-bottom: 12px;
      margin-bottom: 25px;
      transition: all 0.3s;
    }

    .booking-form button[type="submit"] {
      background: var(--dark-color);
      border: 0;
      font-weight: var(--font-weight-semibold);
      color: var(--white-color);
      text-transform: uppercase;
      margin-bottom: 0;
    }

    .booking-form button[type="submit"]:hover {
      background: var(--primary-color);
    }

    .BgImage {
      background-image: url('../images/alex-haney-CAhjZmVk5H4-unsplash.jpg');
      background-repeat: no-repeat;
      background-size: cover;
      background-attachment: fixed;
      height: 500px;
    }


    /*---------------------------------------
      FOOTER
    -----------------------------------------*/
    .site-footer {
      background-image: url('../images/daan-evers-tKN1WXrzQ3s-unsplash.jpg');
      background-repeat: no-repeat;
      background-position: center;
      background-size: cover;
      padding-top: 7rem;
      padding-bottom: 7rem;
      position: relative;
    }

    .site-footer::before {
      content: "";
      background: linear-gradient(to top, var(--dark-color), transparent 200%);
      position: absolute;
      top: 0;
      right: 0;
      bottom: 0;
      left: 0;
    }

    .site-footer .container {
      position: relative;
    }

    .tooplate-mt30 {
      margin-top: 30px;
    }
    .tooplate-mt60 {
      margin-top: 60px;
    }

    .copyright-text {
      font-size: var(--menu-font-size);
    }

    .tel-link {
      color: var(--p-color);
    }

    .site-footer a:hover {
      color: var(--white-color);
    }

    .site-footer a {
      color: #AAA;;
    }

    .site-footer p {
      color: #AAA;
    }


    /*---------------------------------------
      SOCIAL ICON
    -----------------------------------------*/
    .social-icon {
      margin: 0;
      padding: 0;
    }

    .social-icon li {
      list-style: none;
      display: inline-block;
      vertical-align: top;
      transition: all 0.3s;
    }

    .social-icon:hover li:not(:hover) {
      opacity: 0.65;
    }

    .social-icon-link {
      color: var(--p-color);
      font-size: var(--p-font-size);
      display: inline-block;
      vertical-align: top;
      margin-top: 4px;
      margin-bottom: 4px;
      margin-right: 15px;
    }

    .social-icon-link:hover {
      color: var(--primary-color);
    }


    /*---------------------------------------
      RESPONSIVE STYLES
    -----------------------------------------*/
    @media screen and (max-width: 1180px) {
      h1 {
        font-size: 62px;
      }
    }

    @media screen and (max-width: 1170px) {
      h1 {
        font-size: 56px;
      }
    }


    @media screen and (max-width: 991px) {
      h1 {
        font-size: 38px;
      }

      h2 {
        font-size: 32px;
      }

      h3 {
        font-size: 26px;
      }

      h4 {
        font-size: 24px;
      }

      h5 {
        font-size: 20px;
      }

      h6 {
        font-size: 18px;
      }

      .site-header,
      .section-padding {
        padding-top: 5rem;
        padding-bottom: 5rem;
      }

      .navbar {
        padding-top: 10px;
        padding-bottom: 10px;
      }

      .navbar-nav .nav-link {
        padding-top: 5px;
        padding-bottom: 10px;
      }

      #BookingModal .modal-content {
        padding-bottom: 200px;
      }

      #BookingModal .modal-content::before {
        background-image: url('../images/sincerely-media-HoEYgBL_Gcs-unsplash-mobile.jpg');
        background-position: bottom;
        top: auto;
        left: 0;
        bottom: 0;
        width: 100%;
        height: 200px;
        transform: rotate(180deg);
      }

      #BookingModal .modal-body {
        padding-top: 18px;
        padding-right: 32px;
      }
    }

    @media screen and (max-width: 480px) {
      .newsletter-image {
        max-width: 310px;
        max-height: 310px;
      }

      .hero-carousel .carousel-control-prev,
      .hero-carousel .carousel-control-next {
        width: 50px;
        height: 50px;
      }
    }


  </style>`

  <!--

  Tooplate 2129 Crispy Kitchen

  https://www.tooplate.com/view/2129-crispy-kitchen

  -->
</head>

<body>Ï


<nav class="navbar navbar-expand-lg bg-gray-900 shadow-lg">
  <div class="container">
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <a class="navbar-brand" Ïhref="index.html">
      Bittle-Bittle
    </a>

    <div class="d-lg-none">
      <button type="button" class="custom-btn btn btn-danger" data-bs-toggle="modal" data-bs-target="#BookingModal">SignUp</button>
    </div>

    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav mx-auto">
        <li class="nav-item">
          <a class="nav-link active" href="index.html">Home</a>
        </li>

        <li class="nav-item">
          <a class="nav-link" href="about.html">Story</a>
        </li>

        <li class="nav-item">
          <a class="nav-link" href="menu.html">Menu</a>
        </li>

        <li class="nav-item">
          <a class="nav-link" href="news.html">Our Updates</a>
        </li>

        <li class="nav-item">
          <a class="nav-link" href="contact.html">Contact</a>
        </li>
      </ul>
    </div>

    <div class="d-none d-lg-block">
      <button type="button" class="custom-btn btn btn-danger" data-bs-toggle="modal" data-bs-target="#BookingModal">Reservation</button>
    </div>

  </div>
</nav>

<main>

  <section class="hero">
    <div class="container">
      <div class="row">

        <div class="col-lg-5 col-12 m-auto">
          <div class="heroText">

            <h1 class="text-white mb-lg-5 mb-3">Delicious Steaks</h1>

            <div class="c-reviews my-3 d-flex flex-wrap align-items-center">
              <div class="d-flex flex-wrap align-items-center">
                <h4 class="text-white mb-0 me-3">4.4/5</h4>

                <div class="reviews-stars">
                  <i class="bi-star-fill reviews-icon"></i>
                  <i class="bi-star-fill reviews-icon"></i>
                  <i class="bi-star-fill reviews-icon"></i>
                  <i class="bi-star-fill reviews-icon"></i>
                  <i class="bi-star reviews-icon"></i>
                </div>
              </div>

              <p class="text-white w-100">From <strong>1,206+</strong> Customer Reviews</p>
            </div>
          </div>
        </div>

        <div class="col-lg-7 col-12">
          <div id="carouselExampleCaptions" class="carousel carousel-fade hero-carousel slide" data-bs-ride="carousel">
            <div class="carousel-inner">
              <div class="carousel-item active">
                <div class="carousel-image-wrap">
                  <img src="static/images/slide/jay-wennington-N_Y88TWmGwA-unsplash.jpg" class="img-fluid carousel-image" alt="">
                </div>

                <div class="carousel-caption">
                                            <span class="text-white">
                                                <i class="bi-geo-alt me-2"></i>
                                                Manhattan, New York
                                            </span>

                  <h4 class="hero-text">Fine Dining Restaurant</h4>
                </div>
              </div>

              <div class="carousel-item">
                <div class="carousel-image-wrap">
                  <img src="static/images/slide/jason-leung-O67LZfeyYBk-unsplash.jpg" class="img-fluid carousel-image" alt="">
                </div>

                <div class="carousel-caption">
                  <div class="d-flex align-items-center">
                    <h4 class="hero-text">Steak</h4>
ß
                    <span class="price-tag ms-4"><small>$</small>26.50</span>
                  </div>

                  <div class="d-flex flex-wrap align-items-center">
                    <h5 class="reviews-text mb-0 me-3">3.8/5</h5>

                    <div class="reviews-stars">
                      <i class="bi-star-fill reviews-icon"></i>
                      <i class="bi-star-fill reviews-icon"></i>
                      <i class="bi-star-fill reviews-icon"></i>
                      <i class="bi-star reviews-icon"></i>
                      <i class="bi-star reviews-icon"></i>
                    </div>
                  </div>
                </div>
              </div>

              <div class="carousel-item">
                <div class="carousel-image-wrap">
                  <img src="static/images/slide/ivan-torres-MQUqbmszGGM-unsplash.jpg" class="img-fluid carousel-image" alt="">
                </div>

                <div class="carousel-caption">
                  <div class="d-flex align-items-center">
                    <h4 class="hero-text">Sausage Pasta</h4>

                    <span class="price-tag ms-4"><small>$</small>18.25</span>
                  </div>

                  <div class="d-flex flex-wrap align-items-center">
                    <h5 class="reviews-text mb-0 me-3">4.2/5</h5>

                    <div class="reviews-stars">
                      <i class="bi-star-fill reviews-icon"></i>
                      <i class="bi-star-fill reviews-icon"></i>
                      <i class="bi-star-fill reviews-icon"></i>
                      <i class="bi-star-fill reviews-icon"></i>
                      <i class="bi-star reviews-icon"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
              <span class="visually-hidden">Previous</span>
            </button>

            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
              <span class="carousel-control-next-icon" aria-hidden="true"></span>
              <span class="visually-hidden">Next</span>
            </button>
          </div>
        </div>

      </div>
    </div>

    <div class="video-wrap">
      <video autoplay="" loop="" muted="" class="custom-video" poster="">
        <source src="video/production_ID_3769033.mp4" type="video/mp4">
        Your browser does not support the video tag.
      </video>
    </div>

    <div class="overlay"></div>
  </section>

  <section class="menu section-padding">
    <div class="container">
      <div class="row">

        <div class="col-12">
          <h2 class="text-center mb-lg-5 mb-4">NEW/BEST/등등</h2>
        </div>

        <div class="col-lg-4 col-md-6 col-12">
          <div class="menu-thumb">
            <div class="menu-image-wrap">
              <img src="static/images/breakfast/brett-jordan-8xt8-HIFqc8-unsplash.jpg" class="img-fluid menu-image" alt="">

              <span class="menu-tag bg-warning">Breakfast</span>
            </div>

            <div class="menu-info d-flex flex-wrap align-items-center">
              <h4 class="mb-0">Morning Fresh</h4>

              <span class="price-tag bg-white shadow-lg ms-4"><small>$</small>12.50</span>

              <div class="d-flex flex-wrap align-items-center w-100 mt-2">
                <h6 class="reviews-text mb-0 me-3">4.3/5</h6>

                <div class="reviews-stars">
                  <i class="bi-star-fill reviews-icon"></i>
                  <i class="bi-star-fill reviews-icon"></i>
                  <i class="bi-star-fill reviews-icon"></i>
                  <i class="bi-star-fill reviews-icon"></i>
                  <i class="bi-star reviews-icon"></i>
                </div>

                <p class="reviews-text mb-0 ms-4">102 Reviews</p>
              </div>
            </div>
          </div>
        </div>

        <div class="col-lg-4 col-md-6 col-12">
          <div class="menu-thumb">
            <div class="menu-image-wrap">
              <img src="static/images/lunch/farhad-ibrahimzade-MGKqxm6u2bc-unsplash.jpg" class="img-fluid menu-image" alt="">

              <span class="menu-tag bg-warning">Lunch</span>
            </div>

            <div class="menu-info d-flex flex-wrap align-items-center">
              <h4 class="mb-0">Tooplate Soup</h4>

              <span class="price-tag bg-white shadow-lg ms-4"><small>$</small>24.50</span>

              <div class="d-flex flex-wrap align-items-center w-100 mt-2">
                <h6 class="reviews-text mb-0 me-3">3/5</h6>

                <div class="reviews-stars">
                  <i class="bi-star-fill reviews-icon"></i>
                  <i class="bi-star-fill reviews-icon"></i>
                  <i class="bi-star-fill reviews-icon"></i>
                  <i class="bi-star reviews-icon"></i>
                  <i class="bi-star reviews-icon"></i>
                </div>

                <p class="reviews-text mb-0 ms-4">50 Reviews</p>
              </div>
            </div>
          </div>
        </div>

        <div class="col-lg-4 col-md-6 col-12">
          <div class="menu-thumb">
            <div class="menu-image-wrap">
              <img src="static/images/dinner/keriliwi-c3mFafsFz2w-unsplash.jpg" class="img-fluid menu-image" alt="">

              <span class="menu-tag bg-warning">Dinner</span>
            </div>

            <div class="menu-info d-flex flex-wrap align-items-center">
              <h4 class="mb-0">Premium Steak</h4>

              <span class="price-tag bg-white shadow-lg ms-4"><small>$</small>45</span>

              <del class="ms-4"><small>$</small>150</del>

              <div class="d-flex flex-wrap align-items-center w-100 mt-2">
                <h6 class="reviews-text mb-0 me-3">3/5</h6>

                <div class="reviews-stars">
                  <i class="bi-star-fill reviews-icon"></i>
                  <i class="bi-star-fill reviews-icon"></i>
                  <i class="bi-star-fill reviews-icon"></i>
                  <i class="bi-star reviews-icon"></i>
                  <i class="bi-star reviews-icon"></i>
                </div>

                <p class="reviews-text mb-0 ms-4">86 Reviews</p>
              </div>
            </div>
          </div>
        </div>

        <div class="col-lg-4 col-md-6 col-12">
          <div class="menu-thumb">
            <div class="menu-image-wrap">
<<<<<<< HEAD
              <img src="static/images/dinner/farhad-ibrahimzade-ZipYER3NLhY-unsplash.jpg" class="img-fluid menu-image" alt="">

              <span class="menu-tag bg-warning">Dinner</span>
            </div>

            <div class="menu-info d-flex flex-wrap align-items-center">
              <h4 class="mb-0">Seafood Set</h4>

              <span class="price-tag bg-white shadow-lg ms-4"><small>$</small>86</span>

              <del class="ms-4"><small>$</small>124</del>

              <div class="d-flex flex-wrap align-items-center w-100 mt-2">
                <h6 class="reviews-text mb-0 me-3">3/5</h6>

                <div class="reviews-stars">
                  <i class="bi-star-fill reviews-icon"></i>
                  <i class="bi-star-fill reviews-icon"></i>
                  <i class="bi-star-fill reviews-icon"></i>
                  <i class="bi-star reviews-icon"></i>
                  <i class="bi-star reviews-icon"></i>
                </div>

                <p class="reviews-text mb-0 ms-4">44 Reviews</p>
              </div>
            </div>
          </div>
        </div>

        <div class="col-lg-4 col-md-6 col-12">
          <div class="menu-thumb">
            <div class="menu-image-wrap">
              <img src="static/images/breakfast/louis-hansel-dphM2U1xq0U-unsplash.jpg" class="img-fluid menu-image" alt="">

              <span class="menu-tag bg-warning">Breakfast</span>
            </div>

            <div class="menu-info d-flex flex-wrap align-items-center">
              <h4 class="mb-0">Burger Set</h4>

              <span class="price-tag bg-white shadow-lg ms-4"><small>$</small>20.50</span>

              <div class="d-flex flex-wrap align-items-center w-100 mt-2">
                <h6 class="reviews-text mb-0 me-3">4.3/5</h6>

                <div class="reviews-stars">
                  <i class="bi-star-fill reviews-icon"></i>
                  <i class="bi-star-fill reviews-icon"></i>
                  <i class="bi-star-fill reviews-icon"></i>
                  <i class="bi-star-fill reviews-icon"></i>
                  <i class="bi-star reviews-icon"></i>
                </div>

                <p class="reviews-text mb-0 ms-4">102 Reviews</p>
              </div>
            </div>
          </div>
        </div>

        <div class="col-lg-4 col-md-6 col-12">
          <div class="menu-thumb">
            <div class="menu-image-wrap">
              <img src="static/images/lunch/farhad-ibrahimzade-D5c9ZciQy_I-unsplash.jpg" class="img-fluid menu-image" alt="">

              <span class="menu-tag bg-warning">Lunch</span>
            </div>

            <div class="menu-info d-flex flex-wrap align-items-center">
              <h4 class="mb-0">Healthy Soup</h4>

              <span class="price-tag bg-white shadow-lg ms-4"><small>$</small>34.20</span>

              <div class="d-flex flex-wrap align-items-center w-100 mt-2">
                <h6 class="reviews-text mb-0 me-3">3/5</h6>

                <div class="reviews-stars">
                  <i class="bi-star-fill reviews-icon"></i>
                  <i class="bi-star-fill reviews-icon"></i>
                  <i class="bi-star-fill reviews-icon"></i>
                  <i class="bi-star reviews-icon"></i>
                  <i class="bi-star reviews-icon"></i>
                </div>

                <p class="reviews-text mb-0 ms-4">64 Reviews</p>
              </div>
            </div>
          </div>
        </div>

      </div>
    </div>
  </section>

  <section class="BgImage"></section>

  <section class="news section-padding">
    <div class="container">
      <div class="row">

        <h2 class="text-center mb-lg-5 mb-4">News &amp; Events</h2>

        <div class="col-lg-6 col-md-6 col-12">
          <div class="news-thumb mb-4">
            <a href="news-detail.html">
              <img src="static/images/news/pablo-merchan-montes-Orz90t6o0e4-unsplash.jpg" class="img-fluid news-image" alt="">
            </a>

            <div class="news-text-info news-text-info-large">
              <span class="category-tag bg-warning">Featured</span>

              <h5 class="news-title mt-2">
                <a href="news-detail.html" class="news-title-link">Healthy Lifestyle and happy living tips</a>
              </h5>
            </div>
          </div>
        </div>

        <div class="col-lg-6 col-md-6 col-12">
          <div class="news-thumb mb-4">
            <a href="news-detail.html">
              <img src="static/images/news/stefan-johnson-xIFbDeGcy44-unsplash.jpg" class="img-fluid news-image" alt="">
            </a>

            <div class="news-text-info news-text-info-large">
              <span class="category-tag bg-warning">Featured</span>

              <h5 class="news-title mt-2">
         Ï       <a href="news-detail.html" class="news-title-link">How to make a healthy meal</a>
              </h5>
            </div>
          </div>
        </div>

        <div class="col-lg-4 col-md-4 col-12">
          <div class="news-thumb mb-lg-0 mb-lg-4 mb-0">
            <a href="news-detail.html">
              <img src="static/images/news/gilles-lambert-S_LhjpfIdm4-unsplash.jpg" class="img-fluid news-image" alt="">
            </a>

            <div class="news-text-info">
              <span class="category-tag me-3 bg-info">Promotions</span>

              <strong>8 April 2022</strong>

              <h5 class="news-title mt-2">
                <a href="news-detail.html" class="news-title-link">Is Coconut good for you?</a>
              </h5>
            </div>
          </div>
        </div>

        <div class="col-lg-4 col-md-4 col-12">
          <div class="news-thumb mb-lg-0 mb-lg-4 mb-2">
            <a href="news-detail.html">
              <img src="static/images/news/caroline-attwood-bpPTlXWTOvg-unsplash.jpg" class="img-fluid news-image" alt="">
            </a>

            <div class="news-text-info">
              <span class="category-tag">News</span>

              <h5 class="news-title mt-2">
                <a href="news-detail.html" class="news-title-link">Salmon Steak Noodle</a>
              </h5>
            </div>
          </div>
        </div>

        <div class="col-lg-4 col-md-6 col-12">
          <div class="news-thumb mb-4">
            <a href="news-detail.html">
              <img src="static/images/news/louis-hansel-GiIiRV0FjwU-unsplash.jpg" class="img-fluid news-image" alt="">
            </a>

            <div class="news-text-info">
              <span class="category-tag me-3 bg-info">Meeting</span>

              <strong>30 April 2022</strong>

              <h5 class="news-title mt-2">
                <a href="news-detail.html" class="news-title-link">Making a healthy salad</a>
              </h5>
            </div>
          </div>
        </div>

      </div>
    </div>
  </section>

</main>

<footer class="site-footer section-padding">

  <div class="container">

    <div class="row">

      <div class="col-12">
        <h4 class="text-white mb-4 me-5">Crispy Kitchen</h4>
      </div>

      <div class="col-lg-4 col-md-7 col-xs-12 tooplate-mt30">
        <h6 class="text-white mb-lg-4 mb-3">Location</h6>

        <p>121 Einstein Loop N, Bronx, NY 10475, United States</p>

        <a href="https://goo.gl/maps/wZVGLA7q64uC1s886" class="custom-btn btn btn-dark mt-2">Directions</a>
      </div>

      <div class="col-lg-4 col-md-5 col-xs-12 tooplate-mt30">
        <h6 class="text-white mb-lg-4 mb-3">Opening Hours</h6>

        <p class="mb-2">Monday - Friday</p>

        <p>10:00 AM - 08:00 PM</p>

        <p>Tel: <a href="tel: 010-02-0340" class="tel-link">010-02-0340</a></p>
      </div>

      <div class="col-lg-4 col-md-6 col-xs-12 tooplate-mt30">
        <h6 class="text-white mb-lg-4 mb-3">Social</h6>

        <ul class="social-icon">
          <li><a href="#" class="social-icon-link bi-facebook"></a></li>

          <li><a href="#" class="social-icon-link bi-instagram"></a></li>

          <li><a href="https://twitter.com/search?q=tooplate.com&src=typed_query&f=live" target="_blank"
                 class="social-icon-link bi-twitter"></a></li>

          <li><a href="#" class="social-icon-link bi-youtube"></a></li>
        </ul>

        <p class="copyright-text tooplate-mt60">Copyright © 2022 Crispy Kitchen Co., Ltd.
          <br>Design: <a rel="nofollow" href="https://www.tooplate.com/" target="_blank">Tooplate</a></p>

      </div>

    </div><!-- row ending -->

  </div><!-- container ending -->

</footer>

<!-- Modal -->
<div class="modal fade" id="BookingModal" tabindex="-1" aria-labelledby="BookingModal" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="mb-0">Reserve a table</h3>

        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>

      <div class="modal-body d-flex flex-column justify-content-center">
        <div class="booking">

          <form class="booking-form row" role="form" action="#" method="post">
            <div class="col-lg-6 col-12">
              <label for="name" class="form-label">Full Name</label>

              <input type="text" name="name" id="name" class="form-control" placeholder="Your Name" required>
            </div>

            <div class="col-lg-6 col-12">
              <label for="email" class="form-label">Email Address</label>

              <input type="email" name="email" id="email" pattern="[^ @]*@[^ @]*" class="form-control" placeholder="your@email.com" required>
            </div>

            <div class="col-lg-6 col-12">
              <label for="phone" class="form-label">Phone Number</label>

              <input type="telephone" name="phone" id="phone" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" class="form-control" placeholder="123-456-7890">
            </div>

            <div class="col-lg-6 col-12">
              <label for="people" class="form-label">Number of persons</label>

              <input type="text" name="people" id="people" class="form-control" placeholder="12 persons">
            </div>

            <div class="col-lg-6 col-12">
              <label for="date" class="form-label">Date</label>

              <input type="date" name="date" id="date" value="" class="form-control">
            </div>

            <div class="col-lg-6 col-12">
              <label for="time" class="form-label">Time</label>

              <select class="form-select form-control" name="time" id="time">
                <option value="5" selected>5:00 PM</option>
                <option value="6">6:00 PM</option>
                <option value="7">7:00 PM</option>
                <option value="8">8:00 PM</option>
                <option value="9">9:00 PM</option>
              </select>
            </div>

            <div class="col-12">
              <label for="message" class="form-label">Special Request</label>

              <textarea class="form-control" rows="4" id="message" name="message" placeholder=""></textarea>
            </div>

            <div class="col-lg-4 col-12 ms-auto">
              <button type="submit" class="form-control">Submit Request</button>
            </div>
          </form>
        </div>
      </div>

      <div class="modal-footer"></div>

    </div>

  </div>
</div>

<!-- JAVASCRIPT FILES -->
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/custom.js"></script>

</body>
</html>
