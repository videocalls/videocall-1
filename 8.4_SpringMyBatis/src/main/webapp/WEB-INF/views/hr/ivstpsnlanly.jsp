<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">
 <head> 
  <title>개인투자성향분석</title> 
 </head> 
 <body onunload="" oncontextmenu="return false"> 
  <!-- wrapper(s) --> 
  <div class="wrapper"> 
   <!-- header(s) --> 
   <!-- session 객체에 pre,post 여부 저장.. --> 
   <div id="layoutType" style="display:none">
    pre
   </div> 
   <header class="header pre"> 
    <script type="text/javascript">
        var _SESSSION_RESET_COUNT   = "";
        
        var _THIS_TIME_             = 0;
        var _TIMEOUT_TIMER          = 0;
        
        var _IS_PRODSYSTEM_         = "PROD";
        var _SESSION_TIMER ;
        var _LANG_CODE =jexjs.getLang();        //KO, EN
        var _LOCALE_CODE = "K";   //K,E
//      alert(_IS_PRODSYSTEM_);

//              alert(language);
</script> 
    <form id="cm_searchForm">
     <input type="hidden" id="cm_QUERY" name="cm_QUERY" value="">
     <input type="hidden" id="isCmSearch" name="isCmSearch" value="Y">
    </form> 
    <script>

//      alert(_IS_PRODSYSTEM_);

//      alert(language);
        </script> 
    <!-- XFS관련 스크립트 (s) --> 
    <script type="text/javascript">
        try{
            if( top.location.host == self.location.host )
            {
                document.documentElement.style.display='block';
            }else{
                 top.location = self.location;
            }
        }catch(e){ top.location = self.location; }
    </script> 
    <!-- //XFS관련 스크립트 (e) --> 
   </header> 
   <!--// header(e) --> 
   <div class="container"> 
    <div id="content"> 
     <!-- location(s) --> 
     <div class="locationArea hiddenTM"> 
     </div> 
     <!--// location(e) --> 
     <form id="form1" name="form1" method="POST" action="" autocomplete="off"> 
      <!-- 타이틀(s) --> 
      <h1 class="titH2" data-jex-ml="">개인별투자성향분석</h1> 
      <!--// 타이틀(e) --> 
      <!-- bannerModule(s) --> 
      <div class="bannerModuleWrap"> 
       <div class="bannerModule"> 
        <div class="bannerImgWrap"> 
         <div class="bannerImg" style="background-image:url(/img/pre/visaul_fnd_asvc1.jpg)"></div> 
        </div> 
        <div class="bannerContWrap"> 
         <div class="bannerCont"> 
          <p class="titH3" data-jex-ml="">고객님의 투자성향과 목적에 맞게 다양한 상품을 쉽게 선택할 수 있도록 안내해 드립니다.</p> 
          <p class="txt" data-jex-ml="">간단한 분석절차를 거쳐 고객님의 투자성향과 선택 상품의 위험도를 비교하실 수 있으며, 투자성향별 추천상품을 참고하실 수 있습니다.</p> 
         </div> 
        </div> 
       </div> 
      </div> 
      <!-- //bannerModule(e) --> 
      <!-- 진행 단계(s) --> 
      <div class="progIndicator"> 
       <ol> 
        <li class="active"><span data-jex-ml="">설문작성</span> <em class="blind" data-jex-ml="">진행중</em></li> 
        <li><span data-jex-ml="">투자성향분석결과</span></li> 
        <li><span data-jex-ml="">추천상품보기</span></li> 
       </ol> 
      </div> 
      <!--// 진행 단계(e) --> 
      <!-- 설문리스트(s) --> 
      <ul class="txtList lineList"> 
       <li> <strong class="tit" data-jex-ml="">고객님의 연령대를 선택해 주십시오.</strong> 
        <div class="inputComp"> 
         <div class="inputGroup horizontal"> 
          <div class="formWrap"> 
           <input type="radio" id="AGE1" name="ANSWER1" value="6">
           <label for="AGE1" data-jex-ml="">만 65세 미만</label> 
          </div> 
          <div class="formWrap"> 
           <input type="radio" id="AGE2" name="ANSWER1" value="5">
           <label for="AGE2" data-jex-ml="">만 65세 이상 ~ 만 79세 이하</label> 
          </div> 
          <div class="formWrap"> 
           <input type="radio" id="AGE3" name="ANSWER1" value="4">
           <label for="AGE3" data-jex-ml="">만 80세 이상</label> 
          </div> 
         </div> 
        </div> </li> 
       <li> <strong class="tit" data-jex-ml="">귀하의 재무적 목표를 달성하기 위해 현재 씨티은행에 개설된 계좌를 통한 투자기간을 어느 정도로 생각하고 계십니까? (재무 목표에는 자녀교육, 은퇴계획 등이 포함될 수 있습니다.)</strong> 
        <div class="inputComp"> 
         <div class="inputGroup horizontal"> 
          <div class="formWrap"> 
           <input type="radio" id="PERIOD1" name="ANSWER2" value="4">
           <label for="PERIOD1" data-jex-ml="">3년 이하</label> 
          </div> 
          <div class="formWrap"> 
           <input type="radio" id="PERIOD2" name="ANSWER2" value="5">
           <label for="PERIOD2" data-jex-ml="">7년 이하</label> 
          </div> 
          <div class="formWrap"> 
           <input type="radio" id="PERIOD3" name="ANSWER2" value="6">
           <label for="PERIOD3" data-jex-ml="">10년 이하</label> 
          </div> 
          <div class="formWrap"> 
           <input type="radio" id="PERIOD4" name="ANSWER2" value="6">
           <label for="PERIOD4" data-jex-ml="">15년 이하</label> 
          </div> 
          <div class="formWrap"> 
           <input type="radio" id="PERIOD5" name="ANSWER2" value="6">
           <label for="PERIOD5" data-jex-ml="">30년 이하</label> 
          </div> 
         </div> 
        </div> </li> 
       <li> <strong class="tit" data-jex-ml="">씨티은행을 통해 관리하고 계신 자산이 고객님의 전체 순유동자산에서 차지하는 비중은 어느 정도입니까?</strong> 
        <div class="inputComp"> 
         <div class="inputGroup horizontal"> 
          <div class="formWrap"> 
           <input type="radio" id="ASSETS1" name="ANSWER3" value="6">
           <label for="ASSETS1" data-jex-ml="">25% 미만</label> 
          </div> 
          <div class="formWrap"> 
           <input type="radio" id="ASSETS2" name="ANSWER3" value="5">
           <label for="ASSETS2" data-jex-ml="">25%~50%</label> 
          </div> 
          <div class="formWrap"> 
           <input type="radio" id="ASSETS3" name="ANSWER3" value="4">
           <label for="ASSETS3" data-jex-ml="">50%이상</label> 
          </div> 
         </div> 
        </div> <p class="refList">순유동자산 = (1)투자자산 - (2)총부채</p> </li> 
       <li> <strong class="tit" data-jex-ml="">예상 정규 소득과 연간 지출을 고려했을 때, 올해 씨티은행 계좌를 통해 충당하려고 생각하시는 지출 규모는 어느정도 입니까?</strong> 
        <div class="inputComp"> 
         <div class="inputGroup"> 
          <div class="formWrap"> 
           <input type="radio" id="EXPENSE1" name="ANSWER4" value="6">
           <label for="EXPENSE1" data-jex-ml="">투자금액의 25% 미만을 사용할 예정입니다.</label> 
          </div> 
          <div class="formWrap"> 
           <input type="radio" id="EXPENSE2" name="ANSWER4" value="4">
           <label for="EXPENSE2" data-jex-ml="">투자금액의 25% ~ 50% 를 사용할 예정입니다.</label> 
          </div> 
          <div class="formWrap"> 
           <input type="radio" id="EXPENSE3" name="ANSWER4" value="3">
           <label for="EXPENSE3" data-jex-ml="">투자금액의 50% 이상을 사용할 예정입니다.</label> 
          </div> 
         </div> 
        </div> </li> 
       <li> <strong class="tit" data-jex-ml="">다음 중 고객님의 투자목적을 가장 잘 설명한 것은 무엇입니까?</strong> 
        <div class="inputComp"> 
         <div class="inputGroup"> 
          <div class="formWrap"> 
           <input type="radio" id="PURPOSE1" name="ANSWER5" value="1">
           <label for="PURPOSE1" data-jex-ml="">원금보존 <p class="small" data-jex-ml="">원금보존을 선호하며 단기금융시장 금리 수준의 수익이 기대되는 비교적 안전한 투자 선호</p></label> 
          </div> 
          <div class="formWrap"> 
           <input type="radio" id="PURPOSE2" name="ANSWER5" value="2">
           <label for="PURPOSE2" data-jex-ml="">방어 <p class="small" data-jex-ml="">원금보존 및 단기금융시장 금리보다 조금 더 나은 수준의 정기적 소득창출 선호</p> </label> 
          </div> 
          <div class="formWrap"> 
           <input type="radio" id="PURPOSE3" name="ANSWER5" value="2">
           <label for="PURPOSE3" data-jex-ml="">소득지향 <p class="small" data-jex-ml="">자산가격상승 목표 달성보다는 소득창출에 중점을 둔 투자 선호</p> </label> 
          </div> 
          <div class="formWrap"> 
           <input type="radio" id="PURPOSE4" name="ANSWER5" value="3">
           <label for="PURPOSE4" data-jex-ml="">성장&amp;소득 <p class="small" data-jex-ml="">소득창출과 자산가격상승 사이의 균형을 추구하는 투자/투자전략 선호</p> </label> 
          </div> 
          <div class="formWrap"> 
           <input type="radio" id="PURPOSE5" name="ANSWER5" value="4">
           <label for="PURPOSE5" data-jex-ml="">성장지향 <p class="small" data-jex-ml="">정기적인 소득보다는 주로 자산가격상승을 목표로 수익을 추구하는 투자/투자전략 선호</p> </label> 
          </div> 
          <div class="formWrap"> 
           <input type="radio" id="PURPOSE6" name="ANSWER5" value="4">
           <label for="PURPOSE6" data-jex-ml="">성장 <p class="small" data-jex-ml="">공격적인 자산가격상승을 추구하는 투자/투자전략 선호</p> </label> 
          </div> 
          <div class="formWrap"> 
           <input type="radio" id="PURPOSE7" name="ANSWER5" value="6">
           <label for="PURPOSE7" data-jex-ml="">전문적 투자 <p class="small" data-jex-ml="">금융자산 가격의 단기적 이상 움직임을 이용한 시세차익을 노리는 공격적 자산가격상승을 목표로 한 투자/트레이딩 전략 선호</p> </label> 
          </div> 
         </div> 
        </div> </li> 
       <li> <strong class="tit" data-jex-ml="">다음 중 고객님의 투자자산에 대해 감내할 수 있는 위험 변동성 수준을 가장 잘 설명한 것은 무엇입니까?</strong> 
        <div class="inputComp"> 
         <div class="inputGroup"> 
          <div class="formWrap"> 
           <input type="radio" id="RISK1" name="ANSWER6" value="1">
           <label for="RISK1" data-jex-ml="">안전지향적<p class="small" data-jex-ml="">향후 1년간 포트폴리오 가치 변동성이 최소 수준을 넘지 않기를 바라고, 일반적으로 거래가 빈번하며 매도요청시에 빠른 시일 (일주일 이내) 내 최근 시장가로 매도 가능한 투자상품에 대한 매입 의사만 있는 투자자</p></label> 
          </div> 
          <div class="formWrap"> 
           <input type="radio" id="RISK2" name="ANSWER6" value="2">
           <label for="RISK2" data-jex-ml="">보수적<p class="small" data-jex-ml="">향후 1년간 포트폴리오 손실이 소규모를 넘지 않기를 원하고, 일반적으로는 거래가 빈번하며 매도요청시 빠른 시일 (일주일 이내) 내 매도 가능한 투자상품에 대한 매입 의사가 있기는 하나, 때로는 상대적으로 위험도가 큰 개별 투자상품을 매입하기도 하는 투자자</p></label> 
          </div> 
          <div class="formWrap"> 
           <input type="radio" id="RISK3" name="ANSWER6" value="3">
           <label for="RISK3" data-jex-ml="">중도적<p class="small" data-jex-ml="">보다 장기적인 성과 향상을 추구하며, 향후 1년간 포트폴리오 손실이 적정 수준을 넘지 않기를 원하고, 일반적으로는 거래가 빈번하며 매도요청시 안정적인 시장에서 빠른 시일(일주일 이내) 내 매도 가능한 투자상품에 대한 매입 의사가 있지만, 때로는 상대적으로 위험도가 크고 유동성이 낮은 개별 투자상품을 매입하기도 하는 투자자</p></label> 
          </div> 
          <div class="formWrap"> 
           <input type="radio" id="RISK4" name="ANSWER6" value="4">
           <label for="RISK4" data-jex-ml="">공격적<p class="small" data-jex-ml="">보다 장기적인 성과 향상을 추구하면서, 향후 1년간 보다 큰 폭의 포트폴리오 손실을 감내할 수 있고, 단기간 내 매도/거래종료가 어렵고 특정시점의 실현가치(수익)가 불확실한 투자상품을 매입하거나 계약을 체결하려는 의사가 있는 투자자</p></label> 
          </div> 
          <div class="formWrap"> 
           <input type="radio" id="RISK5" name="ANSWER6" value="5">
           <label for="RISK5" data-jex-ml="">매우 공격적<p class="small" data-jex-ml="">1년 동안 포트폴리오의 최대 100%까지 대규모 손실을 감내할 수 있고, 일반적으로는 장기간 매도/거래종료가 어렵고 특정시점의 실현가치(수익)가 불확실한 투자상품을 매입하거나 계약을 체결하려는 의사가 있는 투자자</p></label> 
          </div> 
          <div class="formWrap"> 
           <input type="radio" id="RISK6" name="ANSWER6" value="6">
           <label for="RISK6" data-jex-ml="">전문가적 투자<p class="small" data-jex-ml="">1년 동안 전체 포트폴리오가 위험에 처하는 것을 감내할 수 있을 뿐만 아니라, 포트폴리오 손실을 만회하기 위해 원래 투자했던 금액 이상으로 추가 자본을 유입할 준비가 되어 있으며, 일반적으로 장기간 매도/거래종료가 어렵고 특정시점의 실현가치(수익)가 불확실한 투자상품을 매입하거나 계약을 체결하려는 의사가 있는 투자자</p></label> 
          </div> 
         </div> 
        </div> </li> 
       <li> <strong class="tit" data-jex-ml="">다음 중 고객님의 투자 지식 및 경험을 가장 잘 기술한 것은 무엇입니까?</strong> 
        <div class="inputComp"> 
         <div class="inputGroup"> 
          <div class="formWrap"> 
           <input type="radio" id="TENDENCY1" name="ANSWER7" value="3">
           <label for="TENDENCY1" data-jex-ml="">제한적 <p class="small" data-jex-ml="">은행 예금 또는 정기 예금 이외에는 기타 투자상품에 대한 지식이 전무하거나 거의 없습니다.</p> </label> 
          </div> 
          <div class="formWrap"> 
           <input type="radio" id="TENDENCY2" name="ANSWER7" value="4">
           <label for="TENDENCY2" data-jex-ml="">보통 <p class="small" data-jex-ml="">은행 예금 또는 정기 예금 이외의 투자상품에 대한 일반적인 지식과 이해가 있으며 투자와 관련된 일반적인 위험을 인지하고 있습니다.</p> </label> 
          </div> 
          <div class="formWrap"> 
           <input type="radio" id="TENDENCY3" name="ANSWER7" value="6">
           <label for="TENDENCY3" data-jex-ml="">전문적 <p class="small" data-jex-ml="">일반적으로 투자상품에 대한 광범위한 지식 및 이해를 겸비하고 있으며 스스로 투자 결정을 내리는 데에 무리가 없을 정도로 충분한 경험을 가지고 있습니다.</p> </label> 
          </div> 
         </div> 
        </div> </li> 
      </ul> 
      <!-- //설문리스트(e) --> 
      <!-- 버튼 영역(s) --> 
      <div class="btnArea"> 
       <a href="#" class="btnType1 large arrowR" data-jex-ml="" onclick="javascript:uf_submit(); return false;">결과보기</a> 
      </div> 
      <!--// 버튼 영역(e) --> 
     </form> 
    </div>
    <script>
    $(function () {

    });

    var uf_init = function (data) {

    };

    function uf_checkForm(){
        var questCnt    = 7;
        var answerCnt   = 0;
        for( var i = 1; i < questCnt+1; i++ ){
            var answerObj   = $('input[name="ANSWER'+i+'"]');
            for( var j = 0; j < answerObj.length; j++ ){
                if( answerObj.eq(j).is(":checked") ){
                    answerCnt++;
                }
            }

            if( answerCnt == 0 ){
                cf_alert(i+"번째 질문에 답해주세요.");
                return false;
            } else {
                answerCnt = 0;
            }
        }

        return true;
    }


    function uf_submit(){
        if( !uf_checkForm() ){
            return;
        }

        $("#form1").attr("target","_self");
    	$("#form1").attr("action","FndAsvcAnly0200.act");
    	cf_submit($("#form1"));
    }    
    
$(function () {
    var use_init = false;
    // 추가인증초기화.
    cf_set2ChAuth(true);
    
    try {
        use_init = !!uf_init;
    } catch (e) {
    }    
    if (use_init) {
        uf_init(_data);
    }
    
    var is_uf_signVrfy = false;    
    try {
        is_uf_signVrfy = !!init_signVrfy;
    } catch (e) {
    }
    if (is_uf_signVrfy) {
        var jsSignOrgData = ''; //'{"a":{"a1":"a11","a2","a22"},"b":"b22"}';
        init_signVrfy(jsSignOrgData);
    }
    
    var is_uf_2ChCallback = false;    
    try {
        is_uf_2ChCallback = !!init_2ChCallback;
    } catch (e) {
    }
    
    
    
    
});
</script> 
   <script>

function uf_srhAtm(url) {

    
    if(url.indexOf("#")>-1){
        return false;   
    }
    
    //cf_newWin(url,'BRANCH',780,730,'yes', '', '', 'toolbar=no, menubar=no, location=yes, scrollbars=no, status=no, resizable=no');
    window.open(url,'BRANCH','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,width=780,height=730,top=0,left=0');
}

function uf_popAtmSrch(){
    var url="/AtmSrch10.act"
    window.open(url,'BRANCH','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,width=780,height=730,top=0,left=0');
}

function uf_popAtmDetail(type,idx){
    var url="/AtmInfo10.act?type="+type+"&idx="+idx;
    window.open(url,'BRANCH','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,width=780,height=730,top=0,left=0');
}
    

function uf_newWinOpen(strobj) {
	
	var url = $("#"+strobj).val();

	if( !cf_isEmpty( url )   ){
	
		document.selboxForm.target = "_blank";
        document.selboxForm.action = url;
        document.selboxForm.submit();
        document.selboxForm.target = "_self";
        
	    //$(obj).next().attr('href',$(obj).val());
	    //cf_newWin( url,'BRANCH',780,730,'yes', '', '', 'toolbar=no, menubar=no, location=yes, scrollbars=no, status=no, resizable=no');
	}
	
}   


function uf_newMenuLocation(obj){
    
    cf_menuLocation($(obj).val()); 
}

function uf_newEtcSeviceOpen(obj) {
    
    if ('http://www.knote.kr/' == $(obj).find("option:selected").val()) {
        outLink('금융결제원 어음정보시스템',$(obj).find("option:selected").val());
    } else {
        cf_menuLocation($(obj).find("option:selected").val()); 
    }
        
    
}   

function uf_Stpl(url) {

    //var url = 'ComStplCnts0100p.act';
    
    window.open(url, 'BRANCH','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,width=800,height=600,top=0,left=0');
        
}    
    
</script> 
   <!-- after window load, control(s) --------------> 
   <script>
    
    var  _isRunnedPilotMenuHidden = false;
    
    function pilotMenuHidden(){
        for(var i = 0 ; i < $('.pilotMenu').length ; ++i ){
              var target = $('.pilotMenu').eq(i);
              target.removeAttr('href');
              target.removeAttr('onclick');
              target.removeAttr('onchange');
              target.removeAttr('id');
              
              
              
              target.addClass('hiddenMenu');
              
        }
        
        _isRunnedPilotMenuHidden = true;
    }


     //window load 완료후 
     $(window).load(function(){
        
         
           //1.IE10버전의 경우 , touchEn 모듈 initTranskey 후  TK_Rescan() 을 한번 해준다. (IE10버전의 문제점 해결)
           if(cf_WebBrowserUtil.isIE() && cf_WebBrowserUtil.getBrowserVer() == "10"){  
               
                //키보드 보안이 적용된 경우 
                if(cf_isRaonSecu() && cf_isTouchEn()){
                    TK_Rescan();
                } 
           }
           
         
           
           

      });
</script> 
   <!-- //after window load, control(e) --------------> 
  </div>
  <!--// wrapper(e) --> 
  <script language="JavaScript" type="text/javascript"> 
    try{
            
            if( _pg_title != undefined  &&  _pg_title != "" ){
            	 document.title = _pg_title  + "[13]" ; 
            }else{
            	document.title = "Koscom 개인투자성향분석";
            }

    }catch(e){}
</script>  
 </body>
</html>