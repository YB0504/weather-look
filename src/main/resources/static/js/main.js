$(function(){
    $(".menu > li > a ,.sub").hover(function(){
        $(".sub").stop().slideDown()
    },function(){
        $(".sub").stop().slideUp()
    })
    $(".m_i").slick({
        autoplay: true,
        autoplaySpeed: 3000,
        dots:true,
        arrows:false,
        swipe:true,
        controls:false
    })
})/* jq */