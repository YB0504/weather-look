$(function(){
    //메뉴영역

    $(".main li").mouseover(function(){
        $(".sub").stop().slideDown();
    })//

    $(".main li").mouseout(function(){
        $(".sub").stop().slideUp();
    })//

    //슬라이드영역

        var n = 0; // 0 1 2 

        // setInterval(function(){

        //     if(n < 2){
        //         n++;
        //     } else{
        //         n=0;
        //     }
        //     console.log("n : " , n)
        //     pos = -n * 300 + "px"
        //     $(".view").animate({top : pos }, 500)

        // }, 2500);

        setInterval(function(){

            $(".view").animate({top : "-=300px"}, 2500, function(){
                $(".view").append($(".view li").first());
                $(".view").css({top:"+=300px"})
            })
        })
    //탭메뉴
    //팝업
})//jq