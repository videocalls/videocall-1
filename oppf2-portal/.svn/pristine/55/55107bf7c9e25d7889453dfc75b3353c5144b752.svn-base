$(document).ready(function(){
	
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

	// tab_menu 
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

	// accordion_list
	if($(".accordion_list").length > 0){
		$(".accordion_list dd").hide();
		$(".accordion_list dt").click(function(){
			
			if( $(this).next().is(':hidden') ) {
				$(".accordion_list dt").removeClass('active').next().slideUp("fast");
				$(this).addClass("active");
				$(this).next().slideDown("fast");
			}else{
				$(this).removeClass("active");
				$(".accordion_list dd").slideUp("fast");
			}
			return false;
		});
	}

	// layer_popup
	$(".wrap #layer_open").each(function() {
		$(this).click(function() {
			//해당 레이어(ID) 노출
			$(".layer_popup").show();
			var layerBox = $(this).attr("href"); 
			$(layerBox).show(); 
			// layer_popup(화면 중앙정렬)
			var width_size = $(".layer_box").innerWidth()/2;
			$(".layer_box").css("margin-left",-width_size);
			$("body").css("overflow-y","hidden");
		});		

		// layer_popup(닫기버튼 실행)
		$(".layer_close").click(function() {
			$(".layer_box").hide();
			$(".layer_popup").hide();
			$("body").css("overflow-y","auto");
		});	

		// layer_popup(화면 중앙정렬)
		var width_size = $(".layer_box").innerWidth()/2;
		$(".layer_box").css("margin-left",-width_size);
	});

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
