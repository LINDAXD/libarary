// 신착도서/대출BEST Tab을 각각 선택할 경우 거기에 해당하는 탭과 거기와 연관된 도서목록으로 이동하도록 하는 js
$(document).ready(function(){
    
  $('ul.tabs li').click(function(){
      var tab_id = $(this).attr('data-tab');

      $('ul.tabs li').removeClass('current');
      $('.tab-content').removeClass('current');

      $(this).addClass('current');
      $("#"+tab_id).addClass('current');
  })

});


$(document).ready(function(){
    //태그추가
    $("#main_navi").before("<div class='bg'></dib>");

    //
    $(".bg, .sub-menu").hide();
    $(".gnb-ul").mouseenter(function(){
        $(".bg, .sub-menu").stop().slideDown("fast");
    }).mouseleave(function(){
        $(".bg, .sub-menu").stop().slideUp();
    });

    $(".bg").mouseover(function(){
        $(this).stop().show();
        $(".sub-menu").stop().show();
    }).mouseout(function(){
        $(this).stop().slideUp();
        $(".sub-menu").stop().slideUp();
    });
    });