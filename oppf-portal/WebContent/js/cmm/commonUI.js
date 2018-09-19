

/**
 * 퍼블리싱 사용펑션 : 스킵네비게이션과 같은 앵커를통한 포커스 이동보정
 * Fixes anchor focus in Chrome/Safari/IE by setting the tabindex of the 
 * target container to -1 on click/enter
 * see -> http://stackoverflow.com/questions/3572843/skip-navigation-link-not-working-in-google-chrome/6188217#6188217
 * param : -
 */

function anchorFocus(){
	$("a[href^='#']").click(function(evt){
		var anchortarget = $(this).attr("href");
		$(anchortarget).attr("tabindex", -1).focus();
	});
	
	// Fixes anchor focus in Chrome/Safari/IE by setting the tabindex of the 
	// target container to -1 on page load
	//if (window.location.hash) {
	//	$(window.location.hash).attr("tabindex", -1).focus();
	//}
}

/**
 * 퍼블리싱 사용펑션 : 글로벌 네비게이션(1뎁스 ~ 2뎁스까지)
 * html 마크업 : server-side에서 해당1뎁스메뉴 a링크, 해당 2뎁스메뉴 a링크에 각각 class="activated"를 삽입하여 client로 내린다. 
 * 5초보험료의 활성화 역시 #check5sec a에 activated클래스 추가  
 * 사용법 : gnb()
 * param : -
 */
function gnb(){	
	var $this = $('#gnb');	
	var aLen = $this.find('a').size();
	var alphaNumeric = /([A-Za-z0-9\/]+)/g;
	var check5sec = false;
	$this.find('a').each(function(){		
		this.innerHTML = this.innerHTML.replace(alphaNumeric,'<span>$1</span>');
	});
	$this.find('ul').wrap('<div class="dep2Wrap"></div>').each(function(){
		$(this).find('li:last').addClass('last');	
	})
	$this.find('a.activated').next().find('ul').show();
	cur1DepIdx = $('>li>a.activated',$this).parent().index();
	cur2DepIdx = $('ul a.activated',$this.children('li').eq(cur1DepIdx)).parent().index();	
	if($('#check5sec a').hasClass('activated')) check5sec = true;
	
	$('>li>a',$this).bind('focusin mouseenter',function(e){	
		e.stopPropagation();	
		$('>li>a.activated',$this).removeClass('activated');
		$('#check5sec a').removeClass('activated');
		$this.find('ul').hide();
		$('>li>a.on',$this).removeClass('on')
		$(this).addClass('on');
		if($(this).next().size() > 0){	
			$(this).next().find('ul').show();
		}
	});	
	
	$('>li',$this).bind('focusin mouseenter',function(){		
		//$(this).children('a').trigger('mouseenter')
	});		
	
	$('>li',$this).bind('mouseleave',function(){
		$('ul',this).hide();
		$('>a',this).removeClass('on');
		if(cur1DepIdx >= 0) {
			$('>li:eq('+cur1DepIdx+')>a',$this).addClass('activated')
			//시연시 아래 한줄 주석처리
			.next().find('ul').show();
		}
		else if(check5sec) $('#check5sec a').addClass('activated');			
	});	
	

	$('>li:eq('+cur1DepIdx+') ul a',$this)
		.bind('focusin mouseenter',function(){						
			$(this).parent().siblings().find('a.activated').removeClass('activated');
		})
		.bind('focusout',function(){						
			$this.find('.dep2Wrap:eq('+cur1DepIdx+') li:eq('+cur2DepIdx+') a').addClass('activated');
			
		})
		.closest('ul').bind('mouseleave',function(){
			if(cur2DepIdx >= 0) $this.find('.dep2Wrap:eq('+cur1DepIdx+') li:eq('+cur2DepIdx+') a').addClass('activated');
		})

	$this.find('a').eq(aLen-1).bind('focusout',function(){
		$('>li>a.on',$this).removeClass('on');
		$(this).mouseleave();				
	});	
	
	$('#check5sec a')
		.bind('mouseenter focusin',function(){			
			$('#gnb').find('a.activated').removeClass('activated');
			$('#gnb').find('ul:visible').hide();
		})
		.bind('mouseleave focusout',function(){
			if(cur1DepIdx>=0){
				$('#gnb>li:eq('+cur1DepIdx+')>ul>li:eq('+cur2DepIdx+')').addClass('activated');
				$('#gnb>li:eq('+cur1DepIdx+')>a').addClass('activated').trigger('focusin')	
			}else if(check5sec){
				$('#check5sec a').addClass('activated')
			}		
		});
}

/**
 * 퍼블리싱 사용펑션 : 사람명수,금액 등 시간에 따른 흐름에 단계별 표현을 하는 함수
 * html 마크업 : <anyTag id="value"><span>1</span>, <span>2</span><span>3</span><span>4</span></anyTag>  
 * 사용법 : $('#value').digitFlow([param])
 * param : option으로 객체리터럴형식,flowFrom이라는 속성이 있으며, flow방향을 정할 수 있다.('right'  || 'left' or anything)
 */
(function($){
	$.fn.digitFlow = function(option){
		var settings = $.extend({
				flowFrom : 'right' // 'right'  || 'left' or anything
			},option);		
		return this.each(function(){
			var _this = this;
			
			//$('<img />').attr('src','/images/cont/imageNum.png').on('load',function(){				
				var currentText = $(_this).text();				
				var genHTML = ''
				if($.browser.msie && ($.browser.version == '6.0' || $.browser.version == '7.0')){
					currentText = currentText.replace(/\u00a0/g,"");
					genHTML += '&nbsp;';
					$(this).css('margin-left','-7px');
				}
				for(var i=0;i<currentText.length;i++){
					var curAt = currentText.charAt(i);					
					genHTML += (curAt == ',') ? '<span class="comma">'+curAt+'</span>' :'<em class="n0">'+curAt+'</em>';	
				}
				$(_this).html(genHTML);
				var $selector = settings.flowFrom == 'right' ? $($(_this).children('em').get().reverse()) : $(_this).children('em'); 		
				$selector.each(function(i){				
					var real = parseInt($(this).text())				
					//$(this).text(0)
					var self = this;
					setTimeout(function(){loop(self,real)},i*150+500);			
				});
				function loop(obj,real){				
					$(obj).animate({count:0}, {
						duration: 400,
						step: function(now, fx) {				
							$(obj).removeClass().addClass("n" + parseInt(Math.random() * 10));
						},
						complete: function() { // 애니메이션 완료
							$(obj).removeClass().addClass("n" + parseInt(real));
						}
					});				
				}
			//});							
		});
	}	
})(jQuery);

/**
 * 퍼블리싱 사용펑션 : 팝업(모달레이어팝업,윈도우팝업)을 띄우기 위한 펑션.
 * 파라미터로 모달레이어로 할지, 윈도우팝업으로 할지 결정.  
 * 사용법 : $(selector).JQmodal({option})
 * param : 팝업넓이(픽셀이며 단위생략),팝업높이(픽셀이며 단위생략),팝업타입(modal || window),모달레이어일경우 뒷배경투명도,백그라운드zIndex,팝업zIndex,팝업URL
 */
(function($){
    $.fn.JQmodal = function(option){
        var settings = $.extend({			
            width     : 300,
            height    : 300,
            type      : 'modal', // modal || window (모달레이어 혹은 윈도우팝업);
			backColor : '#000',			
            opacity   : 0.3,
            bz        : 1000001,
            iz        : 1000002,
			border:"1px solid #666666;",
			bgColor:"#FFF",
			closeBtnClass: "btnClose",
            popUrl    : 'about:blank',
			scrolling : 'no'
        },option);	
    
        return this.each(function(){
            window.modalLauncher = $(this);			
            switch (settings.type){
                case 'modal' :
                    $('<div id="modal_back" style="z-index:'+settings.bz+';position:absolute;left:0;top:0;width:'+document.documentElement.clientWidth+'px;height:'+$(document).height()+'px;background-color:'+settings.backColor+';opacity:'+settings.opacity+';filter:alpha(opacity='+settings.opacity*100+');"></div>').appendTo($('body'));
                    
                    $.each($.browser, function(name, val) {
                        if(name == 'msie' && $.browser.version.substr(0,3) == '6.0'){
                            $('<iframe src="about:blank" width="'+document.documentElement.clientWidth+'" height="'+$(document).height()+'" frameborder="0" scrolling="'+settings.scrolling+'" transparency="true" style="position:absolute;left:0;top:0;opacity:'+settings.opacity+';filter:alpha(opacity='+settings.opacity*100+');z-index:-1;"></iframe>')						
                            .appendTo($('#modalBack'))						
                        }
                    });

					$('<div id="modal_ifrmWrap" style="z-index:'+settings.iz+';position:absolute;left:50%;top:'+($(document).scrollTop()+document.documentElement.clientHeight/2)+'px;margin-left:'+(-settings.width/2)+'px;margin-top:'+(-settings.height/2)+'px;border:'+settings.border+';background-color:'+settings.bgColor+'";><iframe id="modalIfm" src="'+settings.popUrl+'" width="'+settings.width+'" height="'+settings.height+'" allowtransparency="true" frameborder="0" scrolling="'+settings.scrolling+'"></div>').appendTo($('body'));

					$('#modalIfm').load(function(){
						var targetFrame = $.browser.msie&&$.browser.version=="7.0" ? this.contentWindow.document : this.contentDocument;
						$(targetFrame).find('.popWrap').prepend('<p class="'+settings.closeBtnClass+'"><input type="button" value="레이어 닫기" onclick="gfn_closeModal(event);" /></p>');
						$(targetFrame).find('.popWrap').css({height:settings.height+'px'});
					});
                    
                    //$('#modal_back').click(function(){					
                    //    $(this).next().find('iframe').attr('src','').end().remove().end().remove();
                    //});			
                break;
                case 'window' :
                    windowModal = window.open(settings.popUrl,'','width='+settings.width+',height='+settings.height+',scrollbars='+settings.scrolling);
                    windowModal.focus();
                break;			
            }
        });		
    }
})(jQuery);

/**
 * 퍼블리싱 사용펑션 : 3뎁스인 로컬네비게이션(LNB)은 화면폭에 꽉차게 표현해야 한다.  
 * 각 네브 항목에 대해 인라인 css로 표기 하는것은 불가능하므로, 동적으로 각항목의 글자수에 비례한 넓이를 동적으로 배분해준다.
 * param : 보정할 리스트아이템 셀렉터
 */
function fullMakingList(selectorString){
	$(selectorString+' a:first').addClass('first');
	$(selectorString+' a:last').addClass('last');
	var $examStr = $('<div id="examStr" style="position:absolute;"></div>');
	$('body').append($examStr);
	var strArr = [];
	var $target = $(selectorString);
	
	$target.each(function(){
		var curTxt = $(this).text();
		$examStr.html(curTxt);
		if(curTxt == "Q&A"){
			strArr.push(50);
		}else if(curTxt == "어떤 분들께 필요한가요?"){
			strArr.push(110);
		}else{
			strArr.push($examStr.width());
		}
		
	});
	var sum = eval(strArr.join('+'))
	$.each(strArr,function(i){
		$target.eq(i).css({width:(this/sum)*100+'%'});
	});
	$examStr.remove();	
	$examStr = null;
	
	$(selectorString).last('li').addClass('last');
}

/**
 * 퍼블리싱 사용펑션 : 테이블에 금액이 표시 되어 있을경우 테이블 상단에 단위(원)이 표시되어야 한다. 
 * 단위(원)은 static한 마크업으로 하지 않고 동적으로 해당 테이블의 클래스명 tm이 있으면 순회하여 동적으로 단위(원)의 DOM을 추가해준다.
 * param : -
 */
function pushUnitTable(){
	$('table.tm,table.tm2').each(function(){
		$(this).wrap('<div class="tmWrap"></div>');
		$(this).before('<span class="unit">단위('+($(this).hasClass('tm') ? '원' : '만원')+')</span>');
	});
}

/**
 * 퍼블리싱 사용펑션 : 새창(아이프레임레이어팝업,윈도우팝업,외부새창)은 target="_blank"로 표시해야한다.
 * 해당펑션은 페이지 로드시 target="_blank" 아이템을 순회하여 새창열기를 클릭전 알려주는 비쥬얼부분에 관한 작업을 한다.
 * 해당요소에 add class 및 특정 요소 append
 * param : -
 */
function noticePopVisual(){
	$('a[target="_blank"]').each(function(){
		if($(this).find('img').length == 0) $(this).addClass('blank').append('<em class="vn">새창으로 열기</em>');
	});
}

/**
 * 퍼블리싱 사용펑션 : html5 doctype에 따른 문법오류를 해결하기 위해 cellSpacing속성을 스크립트로 삽입
 * 페이지로드시 모든 테이블을 순회하여 속성지정시킨다.
 * param : -
 */
function makeCellSpacingTable(){
	$('table').attr('cellSpacing','0');
}

/**
 * 퍼블리싱 사용펑션 : 푸터 내 계열사 링크 open/close함수 
 * param : -
 */
function relatedCo(){
	$('#relatedCo h3 a').bind('click',function(e){
		e.stopPropagation();
		$(this).parent().next().toggle();
	});	
	$('#relatedCo ul a:last').bind('focusout',function(){
		$(this).closest('ul').hide();
	});
	$(document).bind('click',function(e){
		$('#relatedCo ul').hide();
	});
}
/**
 * 퍼블리싱 사용펑션 : selector의 image 로드후 콜백함수
 * param : 이미지 로드 후 수행할 callback함수
 */
(function ($) {
	$.fn.imagesLoaded = function (callback) {
		var elems = this.filter('img'),
			len = elems.length,
			// data uri bypasses webkit log warning
			blank = "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==";
		elems.bind('load', function () {
			// check image src to prevent firing twice
			if (--len <= 0 && this.src !== blank) {
				callback.call(elems, this);
			}
		}).each(function () {
			// cached images don't fire load sometimes, so we reset src.
			if (this.complete || this.complete === undefined) {
				var src = this.src;
				// webkit hack from http://groups.google.com/group/jquery-dev/browse_thread/thread/eee6ab7b2da50e1f
				this.src = blank;
				this.src = src;
			}
		});
	};
}(jQuery));

function cancelBubbleIframeScroll(){
	$('#modalIfm').live('mousewheel',function(e,d){return false})	
}

/**
 * 퍼블리싱 사용펑션 : 4뎁스 탭UI,popup 탭 IE7 보정 그리고 셀렉트된 메뉴에 대한 자동 제목태그부여 
 * param : -
 */
function navAdj(selector){
	var $this = $(selector);
	var targetTxt = $this.find('li.selected a').text();
	$this.after('<h2 class="vn">'+targetTxt+'</h2>')
	if($.browser.msie && $.browser.version == '7.0'){
		$this.find('a').has('br').each(function(){
			$(this).addClass('adjIE7');
		});
	}
}

/**
 * 퍼블리싱 사용펑션 : 테이블 라인을 감추기위해 table을 div로 감싸줌.
 * param : -
 */
function tlbLineHidden(){
	$('.contentWrap .tblType_0, .popCont .tblType_0, .contentWrap .tblType_1, .popCont .tblType_1').each(function(){
		$(this).wrap('<div class="tblWrap"></div>');
		if($(this).hasClass('mouseInput')) $(this).parent().css({overflow:'visible'});
	});	
}

/**
 * 퍼블리싱 사용펑션 브라우저 디텍트 객체
 * 각종 브라우저/OS 스니핑을 통한 스크립트 제어 및 푸터 하단에 고객센터 상담원의 문의 받은 페이지 확인을 위해 정의해둔 숨겨진 공간에 브라우저/OS/페이지ID기록하기 위해 쓰임.
 * param : -
 */
var BrowserDetect = {
	init: function () {
		this.browser = this.searchString(this.dataBrowser) || "An unknown browser";
		this.version = this.searchVersion(navigator.userAgent)
			|| this.searchVersion(navigator.appVersion)
			|| "an unknown version";
		this.OS = this.searchString(this.dataOS) || "an unknown OS";
	},
	searchString: function (data) {
		for (var i=0;i<data.length;i++)	{
			var dataString = data[i].string;
			var dataProp = data[i].prop;
			this.versionSearchString = data[i].versionSearch || data[i].identity;
			if (dataString) {
				if (dataString.indexOf(data[i].subString) != -1)
					return data[i].identity;
			}
			else if (dataProp)
				return data[i].identity;
		}
	},
	searchVersion: function (dataString) {
		var index = dataString.indexOf(this.versionSearchString);
		if (index == -1) return;
		return parseFloat(dataString.substring(index+this.versionSearchString.length+1));
	},
	dataBrowser: [
		{
			string: navigator.userAgent,
			subString: "Chrome",
			identity: "Chrome"
		},
		{ 	string: navigator.userAgent,
			subString: "OmniWeb",
			versionSearch: "OmniWeb/",
			identity: "OmniWeb"
		},		
		{
			prop: window.opera,
			identity: "Opera",
			versionSearch: "Version"
		},
		{
			string: navigator.vendor,
			subString: "iCab",
			identity: "iCab"
		},
		{
			string: navigator.vendor,
			subString: "KDE",
			identity: "Konqueror"
		},
		{
			string: navigator.userAgent,
			subString: "Firefox",
			identity: "Firefox"
		},
		{
			string: navigator.vendor,
			subString: "Camino",
			identity: "Camino"
		},
		{		// for newer Netscapes (6+)
			string: navigator.userAgent,
			subString: "Netscape",
			identity: "Netscape"
		},
		{
			string: navigator.userAgent,
			subString: "MSIE",
			identity: "Explorer",
			versionSearch: "MSIE"
		},
		{
			string: navigator.userAgent,
			subString: "Gecko",
			identity: "Mozilla",
			versionSearch: "rv"
		},
		{ 		// for older Netscapes (4-)
			string: navigator.userAgent,
			subString: "Mozilla",
			identity: "Netscape",
			versionSearch: "Mozilla"
		}
	],
	dataOS : [
		{
			string: navigator.platform,
			subString: "Win",
			identity: "Windows"
		},
		{
			string: navigator.platform,
			subString: "Mac",
			identity: "Mac"
		},
		{
			   string: navigator.userAgent,
			   subString: "iPhone",
			   identity: "iPhone/iPod"
	    },
		{
			string: navigator.platform,
			subString: "Linux",
			identity: "Linux"
		}
	]

};

/**
 * 퍼블리싱 사용펑션 : (임시)날짜를 선택하기 위해 달력아이콘 넣기
 * param : -
 */
function datepicker(){
	$('.datepicker').each(function(){
		//$(this).css("padding-right", "0px");
		$(this).prepend('<input type="image" src="/images/common/icon_cal.gif" alt="날짜선택" class="dpl" />')
	});
}

/**
 * 퍼블리싱 사용펑션 : 시연을 위한 임시스크립트.
 * 청약, 5초보험료확인에서 보험료 확인 버튼 클릭시 로딩분기 및 보험료 노출함수호출
 * param : -
 */
function insuranceCheck(selector,top){	
	$('.motionWrap').hide();	
	$(selector).bind('click',function(){
		$('#popcerti').remove();
		var _this = this;
		if(this.id == 'dth_loading'){ // 사망1단계 보험료확인버튼일때 로딩페이지노출			
			$(this).JQmodal({popUrl:'/products/common/HPPC61P0.jsp',type:'modal',width:480,height:330});
			var timer1 = setTimeout(function(){
					if($('#modal_ifrmWrap iframe',window.parent.document).length){		
						$(parent.modalLauncher).focus();		
						parent.modalLauncher = null;
						$('#modal_ifrmWrap iframe',window.parent.document).attr('src','');
						$('#modal_back',window.parent.document).remove();
						$('#modal_ifrmWrap',window.parent.document).remove();
						location.href=_this.href;
						timer1 = null;		
					} else {
						$(window.parent).focus();
						self.close();
					}
			},3000)
		} else if(this.id == 'pnsa_loading'){ // 연금4단계 공인인증확인버튼 클릭에서 로딩페이지노출			
			$(this).JQmodal({popUrl:'/products/common/HPPD11P9.jsp',type:'modal',width:480,height:330});
			var timer2 = setTimeout(function(){
					if($('#modal_ifrmWrap iframe',window.parent.document).length){		
						$(parent.modalLauncher).focus();		
						parent.modalLauncher = null;
						$('#modal_ifrmWrap iframe',window.parent.document).attr('src','');
						$('#modal_back',window.parent.document).remove();
						$('#modal_ifrmWrap',window.parent.document).remove();
						location.href=_this.href;
						timer2 = null;		
					} else {
						$(window.parent).focus();
						self.close();
					}
			},3000)
		} else if(this.id == 'mdc_loading'){ // 의료비4단계 공인인증확인버튼 클릭에서 로딩페이지노출			
			$(this).JQmodal({popUrl:'/products/common/HPPE61P0.jsp',type:'modal',width:480,height:330});
			var timer3 = setTimeout(function(){
					if($('#modal_ifrmWrap iframe',window.parent.document).length){		
						$(parent.modalLauncher).focus();		
						parent.modalLauncher = null;
						$('#modal_ifrmWrap iframe',window.parent.document).attr('src','');
						$('#modal_back',window.parent.document).remove();
						$('#modal_ifrmWrap',window.parent.document).remove();
						location.href=_this.href;
						timer3 = null;		
					} else {
						$(window.parent).focus();
						self.close();
					}
			},3000)
		} else {
			subscriptionMotion(_this,top);
		}
		return false;		
	});
}

/**
 * 퍼블리싱 사용펑션 : 사망청약1단계,의료비청약1단계, 5초보험료확인에서 보험료 숫자 모션
 * param : obj는 보험료확인버튼객체
 */
function subscriptionMotion(obj,top){
	$page = ($.browser.msie || $.browser.mozilla) ? $('html') : $('body');
	var pageH = $(window).height();		
	if(pageH <= 968){
		$(obj).parents().next('.motionWrap').show();
		var posY = $('.motionWrap').offset().top;
		$page.animate({scrollTop:top+'px'},650)
	}
	$('.motionWrap').show();
	$('.digitFlow').digitFlow();	
}

/**
 * 퍼블리싱 사용펑션 : 고객센터 Q&A 리스트 답변 열고 닫기
 * param : -
 */
function qnaList(selector){
	var $this = $(selector);
	$this.find('dt >.question >a').bind('click',function(){
		var $thisHref = $(this).attr('href');
		$('.qnaList >li.selected').removeClass('selected');
		$('.qnaList >li dd:visible').hide();
		$(this).closest('li').addClass('selected');
		$($thisHref).show();
		return false;
	}); 
}

/**
 * 퍼블리싱 사용펑션 : header > 투명하고 쉬운 공시실 오버시 레이어 띄우기
 * param : -
 */
function disclosureLayer(){
	$(document).bind('click',function(e){
		$('#disclosure_sub').hide();
	});
	$('#fmenu #dis_sub >a').bind('click',function(e){
		e.preventDefault();		
		var thisHref = $(this).attr('href');
		$('#fmenu').css({'z-index':9});
		$(thisHref).show();
		return false;
	});
	$('#disclosure_sub a:last').bind('focusout',function(){
		$(this).closest('ul').hide();
	});
}

/**
 * 퍼블리싱 사용펑션 : 메인,로그인,왜라이프플래닛인지 서브메인의 하단에 들어오는 브랜드 그래픽 보드 영역
 * param : -
 */
function brandGraphicBoard(arg){
	var detailHTML = {
		/*
		'bgb_d2':'<img src="/images/common/bgb_2_detail.jpg" alt="아시아 최고 보험사로 선정된 교보생명에서 새로운 도전을 시작합니다. (2009 아시아 최고 생명보험사 수상,2012 아시아 최고 보험경영자 수상)">'
		,'bgb_d4':'<a href="/contact/common/HPCA00S0.jsp" title="라이프 플래닛 고객센터 바로가기"><img src="/images/common/bgb_4_detail.jpg" alt="인터넷을 통해 고객님을 만나기 때문에 고객상담에 더 노력을 기울이고자 합니다. 라이프플래닛 고객센터 1566-0999, 평일상담시간은 아침9시부터 밤10시까지이며, 토요일은 아침9시부터 저녁6시까지입니다."></a>'
		,'bgb_d5_2':'<iframe width="577" height="319" src="http://www.youtube.com/embed/QNEtPwn_j_w?feature=player_detailpage" frameborder="0" allowfullscreen></iframe><input type="image" class="closeMovie" id="cm2" src="/images/common/bgb_detail_close.gif" alt="영상프레임 닫기">'
		,'bgb_d5_1':'<iframe width="577" height="319" src="http://www.youtube.com/embed/NDZip9omS1w?feature=player_detailpage" frameborder="0" allowfullscreen></iframe><input type="image" class="closeMovie" id="cm1" src="/images/common/bgb_detail_close.gif" alt="영상프레임 닫기">'
		,'bgb_d6_2':'<iframe width="577" height="319" src="http://www.youtube.com/embed/rxD_Y6dfnbA?feature=player_detailpage" frameborder="0" allowfullscreen></iframe><input type="image" class="closeMovie" id="cm2" src="/images/common/bgb_detail_close.gif" alt="영상프레임 닫기">'
		,'bgb_d6_1':'<iframe width="577" height="319" src="http://www.youtube.com/embed/7vLvgh_IlAc?feature=player_detailpage" frameborder="0" allowfullscreen></iframe><input type="image" class="closeMovie" id="cm1" src="/images/common/bgb_detail_close.gif" alt="영상프레임 닫기">'
		,'bgb_d7':'<span class="vn">지금까지 </span> <strong class="digitFlow">430,000</strong><span class="vn">명의 고객님께서 라이프 플래닛을 경험해 주셨습니다.</span>'
		,'bgb_d8':'<a href="/insurance/HPIA01S0.jsp" title="보허미안 랩소디 에피소드 보러가기"><img src="/images/common/bgb_8_detail.jpg" alt="어려운 보험 이야기 이제 웹툰으로 쉽게 만나보세요. 보허미안 랩소디"></a>'
		,'bgb_d9':'<img src="/images/common/bgb_9_detail.jpg" alt="소셜네트워크에서 라이프플래닛을 만나보세요."><ul class="socialList">          	<li><a href="javascript:;" title="라이프플래닛 페이스북 바로가기">페이스북</a></li><li><a href="javascript:;" title="라이프플래닛 트위터 바로가기">트위터</a></li> <li><a href="javascript:;" title="라이프플래닛 구글플러스 바로가기">구글플러스</a></li></ul>'
		,'bgb_d10':'<img src="/images/common/bgb_10_detail.jpg" alt="소셜네트워크에서 라이프플래닛을 만나보세요."><ul class="socialList">          	<li><a href="javascript:;" title="라이프플래닛 페이스북 바로가기">페이스북</a></li><li><a href="javascript:;" title="라이프플래닛 트위터 바로가기">트위터</a></li> <li><a href="javascript:;" title="라이프플래닛 구글플러스 바로가기">구글플러스</a></li></ul>'
		*/
		// 개발용
		'bgb_d2':'<img src="/images/common/bgb_2_detail.jpg" alt="아시아 최고 보험사로 선정된 교보생명에서 새로운 도전을 시작합니다. (2009 아시아 최고 생명보험사 수상,2012 아시아 최고 보험경영자 수상)">'
		,'bgb_d4':'<a href=\"javascript:;\" onclick=\"util_movePage(\'/contact/common/HPCA00S0\');\" title="라이프 플래닛 고객센터 바로가기"><img src="/images/common/bgb_4_detail.jpg" alt="인터넷을 통해 고객님을 만나기 때문에 고객상담에 더 노력을 기울이고자 합니다. 라이프플래닛 고객센터 1566-0999, 평일상담시간은 아침9시부터 밤10시까지이며, 토요일은 아침9시부터 저녁6시까지입니다."></a>'
		,'bgb_d5_2':'<iframe width="577" height="319" src="http://www.youtube.com/embed/QNEtPwn_j_w?feature=player_detailpage" frameborder="0" allowfullscreen></iframe><input type="image" class="closeMovie" id="cm2" src="/images/common/bgb_detail_close.gif" alt="영상프레임 닫기">'
		,'bgb_d5_1':'<iframe width="577" height="319" src="http://www.youtube.com/embed/NDZip9omS1w?feature=player_detailpage" frameborder="0" allowfullscreen></iframe><input type="image" class="closeMovie" id="cm1" src="/images/common/bgb_detail_close.gif" alt="영상프레임 닫기">'
		,'bgb_d6_2':'<iframe width="577" height="319" src="http://www.youtube.com/embed/rxD_Y6dfnbA?feature=player_detailpage" frameborder="0" allowfullscreen></iframe><input type="image" class="closeMovie" id="cm2" src="/images/common/bgb_detail_close.gif" alt="영상프레임 닫기">'
		,'bgb_d6_1':'<iframe width="577" height="319" src="http://www.youtube.com/embed/7vLvgh_IlAc?feature=player_detailpage" frameborder="0" allowfullscreen></iframe><input type="image" class="closeMovie" id="cm1" src="/images/common/bgb_detail_close.gif" alt="영상프레임 닫기">'
		,'bgb_d7':'<span class="vn">지금까지 </span> <strong class="digitFlow">' + arg + '</strong><span class="vn">명의 고객님께서 라이프 플래닛을 경험해 주셨습니다.</span>'
		,'bgb_d8':'<a href="javascript:;" onclick=\"util_movePage(\'/insurance/HPIA01S0\');\" title="보허미안 랩소디 에피소드 보러가기"><img src="/images/common/bgb_8_detail.jpg" alt="어려운 보험 이야기 이제 웹툰으로 쉽게 만나보세요. 보허미안 랩소디"></a>'
		,'bgb_d9':'<img src="/images/common/bgb_9_detail.jpg" alt="소셜네트워크에서 라이프플래닛을 만나보세요."><ul class="socialList">          	<li><a href="https://www.facebook.com/lifeplanetinsurance" target="_blank" title="라이프플래닛 페이스북 바로가기">페이스북</a></li><li><a href="https://twitter.com/lifeplanetins" target="_blank" title="라이프플래닛 트위터 바로가기">트위터</a></li> <li><a href="http://gplus.to/Lifeplanet" target="_blank" title="라이프플래닛 구글플러스 바로가기">구글플러스</a></li></ul>'
		,'bgb_d10':'<img src="/images/common/bgb_10_detail.jpg" alt="소셜네트워크에서 라이프플래닛을 만나보세요."><ul class="socialList">          	<li><a href="https://www.facebook.com/lifeplanetinsurance" target="_blank" title="라이프플래닛 페이스북 바로가기">페이스북</a></li><li><a href="https://twitter.com/lifeplanetins" target="_blank" title="라이프플래닛 트위터 바로가기">트위터</a></li> <li><a href="http://gplus.to/Lifeplanet" target="_blank" title="라이프플래닛 구글플러스 바로가기">구글플러스</a></li></ul>'
		
	}
	$('#brandWrap .digitFlow').digitFlow();
	var isover = false;
	var evt;
	var currentScene = 1;	
	var curIdx;
	$('#brandWrap li').each(function(i){
		$(this).find('div.mask>span').delay(i*80).animate({opacity:'1'},{duration:600,complete:function(){
			$(this).css({background:'black'})	
		}})
	})
	
	$('#brandWrap').hover(function(){isover=true},function(){isover=false});
	
	$('#bgb_ctrl input').click(function(){		
		$('#bgb_1_glow').fadeIn(300,function(){$(this).delay(50).fadeOut(600)});
		var id = this.id;
		var randomMax = 800;
		var easingType = 'easeOutExpo';
		var aniDuration = 400;			
		if(id=='bgb_prev'){
			if(currentScene==1){
				$(this).next().trigger('click');
				currentScene=2;
			}else{
				$('#bgb_3>div.mask>span,#bgb_9>div.mask>span').each(function(){
					$(this).stop().delay(ranTime()).animate({left:'0px'},{duration:aniDuration,easing:easingType});
				});
				$('#bgb_8>div.mask>span,#bgb_10>div.mask>span').each(function(){
					$(this).stop().delay(ranTime()).animate({top:'0px'},{duration:aniDuration,easing:easingType});
				});
				$('#bgb_5>div.mask>span,#bgb_6>div.mask>span').each(function(){
					$(this).stop().delay(ranTime()).animate({left:'-188px'},{duration:aniDuration,easing:easingType});
				});
				currentScene=1;
			} 				
		} else {
			if(currentScene==2){
				$(this).prev().trigger('click');
				currentScene=1;
			}else{
				$('#bgb_3>div.mask>span,#bgb_9>div.mask>span').each(function(){
					$(this).stop().delay(ranTime()).animate({left:'-188px'},{duration:aniDuration,easing:easingType});
				});
				$('#bgb_8>div.mask>span,#bgb_10>div.mask>span').each(function(){
					$(this).stop().delay(ranTime()).animate({top:'-156px'},{duration:aniDuration,easing:easingType});
				});
				$('#bgb_5>div.mask>span,#bgb_6>div.mask>span').each(function(){
					$(this).stop().delay(ranTime()).animate({left:'0px'},{duration:aniDuration,easing:easingType});
				});
				currentScene=2;
			}
		}
		
		function ranTime(){
			//return	Math.floor(Math.random()*randomMax);
			return 0
		}			
	}).bind('mouseenter',function(){
		$('#brandWrap li').css({opacity:'1'})
	});
	
	setInterval(function(){		
		if(!isover){
			if(currentScene==1) {
				$('#bgb_next').trigger('click');
				currentScene = 2;
			} else {
				$('#bgb_prev').trigger('click');
				currentScene = 1;
			}			
		}
	},6000);
	
	if( /Android|webOS|iPhone|iPad|iPod|BlackBerry/i.test(navigator.userAgent) ) {
		evt = 'click';
	} else {
		evt = 'focusin mouseenter click'//'mouseenter focusin'
	}
	$('#brandWrap div.mask>span>a').bind(evt,function(e){
		e.preventDefault();
		curIdx = $(this).closest('li').index()+1;		
		e.preventDefault();		
		var _this = this;
		
		if($('.closeMovie:visible').length==0) {
			$('.bgb_detail:visible').hide().empty()
			$.data(this,'timer',setTimeout(function(){					
				$(_this).closest('li').siblings('li').css({zIndex:'1'}).end().css({zIndex:'2'});
				var who = $(_this).attr('href');		
				$(who).empty().html(detailHTML[who.substr(1)])	
				$(who).stop().fadeIn(200);
				if(curIdx==7){
					$(who).find('.digitFlow').digitFlow()	
				}	
				$(_this).closest('li').siblings('li').css({backgroundColor:'black'}).find('.mask').stop().animate({opacity:'0.8'},200);
				$(_this).closest('li').find('.bgb_detail').bind('mouseleave',function(){
					if($('.closeMovie:visible').length==0) {	
						$(_this).closest('li').siblings('li').find('.mask').stop().animate({opacity:'1'},100);
						$(this).hide();
						$(this).closest('li').css('zIndex','1');
					}
				});
			},250))
		}
		
	}).bind('mouseleave focusout',function(){
		clearTimeout($.data(this,'timer'));			
	});
	
	$('.closeMovie').live('click',clearDetail);
	
	function clearDetail(e){		
		e.preventDefault();
		var $parent = $(e.target).parent()
		$(e.target).closest('.bgb_detail').hide().empty();		
		$(e.target).next().focus();
		$('#brandWrap li').css('zIndex','1').find('.mask').stop().animate({opacity:'1'},100);
		if($parent.attr('id')=='bgb_d5_1'){
			$parent.prev().find('a:last').focus();	
		} else if($parent.attr('id')=='bgb_d5_2'){
			$('#brandWrap li').eq(curIdx).find('a:first').focus()
		}
	}
	$('#brandWrap').bind('touchstart',function(e){
		e.stopPropagation();
	})
	$(document).bind('touchstart',function(){
		$('.bgb_detail:visible').hide().empty();	
		$('#brandWrap li').css('zIndex','1').find('.mask').stop().animate({opacity:'1'},100);
	})
}

/*
function adjAlphaNumeric(){
	//var alphaNumeric = /(<*>|[ㄱ-ㅎ|ㅏ-ㅣ|가-힣|\-])([:\/\/\.A-Z\~a-z0-9\s]+)/g;
	//var alphaNumeric = /([~\-]?)([:\/\/\.A-Za-z0-9\s]+)(<|[ㄱ-ㅎ|ㅏ-ㅣ|가-힣])/g;
	//this.innerHTML = this.innerHTML.replace(alphaNumeric,'$1<span class="ls0" style="color:yellow">$2</span>');
	//this.innerHTML = this.innerHTML.replace(alphaNumeric,'$1<span class="ls0">$2</span>$3')
}
*/

/**
 * 퍼블리싱 사용펑션 : 결과박스를 라운드로 변경
 * param : -
 */
function roundBoxMake(){

	if($('.contentWrap .resultBox').length && $('.contentWrap .resultBox .midBg').length == 0){
		$('.contentWrap .resultBox').wrapInner("<div class='midBg'></div>").prepend("<div class='topBg'><span></span></div>").append("<div class='bottomBg'><span></span></div>");
	}
	
	if($('.contentWrap .boxType_2').length && $('.contentWrap .boxType_2 .midBg').length == 0){
		$('.contentWrap .boxType_2').wrapInner("<div class='midBg'></div>").prepend("<div class='topBg'><span></span></div>").append("<div class='bottomBg'><span></span></div>");
	}
	
	if($('#myInfo .boxSection').length){
	
		$('#myInfo .boxSection').css('border-bottom','0 none');
		
		if($('.btm').length == 0){
			$('.boxSection').append("<div class='btm'><span></span></div>");
			$('.boxSection:not("#insuInfo")').css('border-top','0 none').prepend("<div class='top1'><span></span></div>");
		}
	}
	
	else{}
}



/**
 * 퍼블리싱 사용펑션 :해당 페이지의 DOM ready후 수행할 공통 함수실행부
 * param : -
 */
$(function(){
	makeCellSpacingTable();
//	fullMakingList('.nav3dep >li');	
	fullMakingList('.stepArea >li');
	fullMakingList('.contentWrap .nav3dep >li');	
	pushUnitTable();
	noticePopVisual();
	relatedCo();
	anchorFocus();
	navAdj('.nav4dep,.pop_nav2dep');
	//$('.contentWrap a').each(function(){adjAlphaNumeric.call(this)});	
	tlbLineHidden();
	BrowserDetect.init();	
	datepicker(); /* 임시 */
	insuranceCheck('#quick_insuranceCheck', 230); /* 임시 : 5초보험료 */
	insuranceCheck('#dth_insuranceCheck,#mdc_insuranceCheck,#dth_loading,#pnsa_loading,#mdc_loading',580); /* 임시 : 사망1단계 보험료 확인,의료1단계 보험료 확인,사망4단계로딩,연금4단계로딩,의료4단계로딩*/	
	insuranceCheck('#pnsa_insuranceCheck',900); 
//	qnaList('.qnaList >li');
	cancelBubbleIframeScroll();
	disclosureLayer();	
	roundBoxMake();
});

