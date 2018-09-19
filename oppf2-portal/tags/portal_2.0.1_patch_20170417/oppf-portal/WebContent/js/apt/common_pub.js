$(document).ready(function(){
	
	if($(".tab_menu").length > 0){
		$(".tab_cont:not(:first)").hide();
		$(".tab_menu li a").click(function() {
			tabCont = $(this).attr("href");
			$(".tab_cont").hide();
			$(tabCont).show();
			$(".tab_menu li").removeClass("on");
			$(this).parent().addClass("on");
			return false;
		});
	}
	
	//LNB
	$('.lnb > li > a').click(function(){
		if( $(this).next().is(':hidden') ) {
			$('.lnb > li > a').removeClass('active').next().slideUp("fast");
			$(this).toggleClass('active').next().slideDown("fast");
			$('.lnb > li > ul > li > a').removeClass('active').next().slideUp("fast");
		}else{
			$(this).removeClass('active').next().slideUp("");
			$('.lnb > li > ul > li > a').removeClass('active').next().slideUp("fast");
		}
		return false;
	});
	//3depth있을 경우
	/*
	$('.lnb > li > ul > li > ul').hide();
	$('.lnb > li > ul > li > a').click(function(){
		if( $(this).next().is(':hidden') ) {
			$('.lnb > li > ul > li > a').removeClass('active').next().slideUp("fast");
			$(this).toggleClass('active').next().slideDown("fast");
		}else{
			$(this).removeClass('active').next().slideUp("");
		}
		return false;
	});
	*/
	
	//체크박스 리스트 더보기
	$('.chk_list_wrap .btn_more').click(function(){
		$(this).prev('ul').toggleClass('on');
		$(this).toggleClass('on');		
		if($('.chk_list_wrap ul').hasClass('on')){
			$(this).html('닫기');
		} else {
			$(this).html('더보기');
		}
	});

	//Notice 열고닫기
	if($(".info_box .tit .btn_more").length > 0){
		$(".info_box .txt").hide();
		$(".info_box .tit .btn_more").click(function(){			
			if( $(this).parent().next().is(':hidden') ) {
				$(".info_box .tit .btn_more").removeClass('active').parent().next().hide();
				$(this).addClass("active");
				$(this).parent().next().show();
			}else{
				$(this).removeClass("active");
				$(".info_box .txt").hide();
			}
			return false;
		});
	}

	//툴팁
	$(function(){
		var msw = $('.tooltip_add .txt').outerWidth();
		$('.tooltip_add .txt').css('left', '-'+(msw/2-38)+'px');
		$('.tooltip_add .box a').on('click', function(){
			$('.tooltip_add .txt').toggle();
		});
		$('.tooltip_add .box').on('focusout', function(){
			$('.tooltip_add .txt').hide();
		});
	});

	

});
