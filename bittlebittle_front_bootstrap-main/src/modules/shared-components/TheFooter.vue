<template>

<footer class="site-footer section-padding">

<div class="container">

  <div class="row">

    <div class="col-12">
      <h4 class="text-white mb-4 me-5">BITTLE-BITTLE</h4>
    </div>

    <div class="col-lg-4 col-md-7 col-xs-12 tooplate-mt30">
      <h6 class="text-white mb-lg-4 mb-3">Location</h6>

      <p>KOSA</p>

      <a href="https://goo.gl/maps/wZVGLA7q64uC1s886" class="custom-btn btn btn-dark mt-2">Directions</a>
    </div>

    <div class="col-lg-4 col-md-5 col-xs-12 tooplate-mt30">
      <h6 class="text-white mb-lg-4 mb-3">Opening Hours</h6>

      <p class="mb-2">Monday - Friday</p>

      <p>9:00 AM - 07:00 PM</p>

      <p>Tel: <a href="tel: 010-1111-2222" class="tel-link">010-1111-2222</a></p>
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

      <p class="copyright-text tooplate-mt60">Copyright © bittlezo Co., Ltd.
        <br>Design: <a rel="nofollow" href="https://www.tooplate.com/" target="_blank">bittlezo</a></p>

    </div>

  </div><!-- row ending -->

</div><!-- container ending -->

</footer>

</template>

<script>
export default {
  name: 'TheFooter',
  setup () {
  }
}
</script>

<style>

/*---------------------------------------
  FOOTER
-----------------------------------------*/
.site-footer {
  /* background-image: url('../images/daan-evers-tKN1WXrzQ3s-unsplash.jpg'); */
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
</style>
