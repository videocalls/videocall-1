/*
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

});
*/

/**
 * 설명 		: 로그인 필요한 페이지 이동 시 페이지 이동 공통 처리
 * 사용방식 	: gfn_loginNeedMove(url : 처리 url, param: return 후 처리될 param, msg : 페이지 이동시 사용할 msg)
 * 주의 		: 
 * 리턴 		: 없음
 */
function gfn_loginNeedMove(url, param, msg){

	var loginUrl = httpsContextpath+"/spt/cmm/loginView.do";
	
	//https 변환할  url인지 판단한다.
	var httpsFlag = "N";
	if(gfn_getHttpsFlag(url)){
		httpsFlag = "Y";
	}

	//로그인 처리 호출
	gfn_loginMove(loginUrl, url, param, msg, httpsFlag);
}