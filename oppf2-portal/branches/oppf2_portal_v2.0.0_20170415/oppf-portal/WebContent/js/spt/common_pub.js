$(document).ready(function(){
	
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

	if($(".tab_menu2").length > 0){
		$(".tab_cont2:not(:first)").hide();
		$(".tab_menu2 li a").click(function() {
			tabCont = $(this).attr("href");
			$(".tab_cont2").hide();
			$(tabCont).show();
			$(".tab_menu2 li").removeClass("on");
			$(this).parent().addClass("on");
			return false;
		});
	}

	if($(".tab_menu3").length > 0){
		$(".tab_cont3:not(:first)").hide();
		$(".tab_menu3 li a").click(function() {
			tabCont = $(this).attr("href");
			$(".tab_cont3").hide();
			$(tabCont).show();
			$(".tab_menu3 li").removeClass("on");
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

	/******************* mobile **********************/
	
	/* 전체메뉴 열고닫기 */
	$(".btn_gnb_menu").click(function() {
		$(".gnb_menu").show();
		$(".gnb_menu").animate({"right": "0"}, "fast");		
		$(".wrap.mobile_wrap").css("position","fixed");
		$(".wrap.mobile_wrap").animate({"right": "100%"}, "fast");		
		
		var gnbHeight = $(window).innerHeight();
		$(".gnb_menu > ul").css("height",gnbHeight-67);
		return false;
	});
	
	$(".gnb_menu .btn_close").click(function() {
		$(".gnb_menu").animate({"right": "-100%"}, "fast");	
		$(".gnb_menu").hide();			
		$(".wrap.mobile_wrap").animate({"right": "0"}, "fast");	
		$(".wrap.mobile_wrap").css("position","relative");
	});

	// GNB서브메뉴 열고닫기 */
	if($(".mobile_wrap").length > 0){
		$(".mobile_wrap .gnb_menu > ul > li > a").click(function(){			
			if( $(this).next(".sub_menu").is(':hidden') ) {
				$(".mobile_wrap .gnb_menu > ul > li a").removeClass('on');
				$(this).addClass("on");
			}else{
				$(this).removeClass("on");
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
