<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : main.jsp
 * @Description : 서비스포털 main
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.05.02  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.05.02
 * @version 1.0
 * @apt
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<OBJECT classid="CLSID:EC5D5118-9FDE-4A3E-84F3-C2B711740E70" codeBase="http://www.signkorea.com/SKCommAX.cab#version=9,9,1,9" id="CertManX" width="0" height="0"></OBJECT>
<!-- <object classid="CLSID:EC5D5118-9FDE-4A3E-84F3-C2B711740E70" codebase="http://www.signkorea.com/SKCommAX.cab#version=9,9,0,5" id="CertManX" width="1" height="1"></object> -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script type="text/javascript" src="<c:url value='/js/cmm/SKCertService/app/library/json3.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/cmm/SKCertService/app/library/iecompatibility.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/cmm/SKCertService/app/vestsign.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/cmm/SKCertService/app/js/koscom.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/cmm/SKCertService/app/library/dragiframe.js'/>"></script>

<c:if test="${isMobile eq 'true'}">
<!-- 반응형 페이지일 경우 추가 -->
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width"> 
<!-- // 반응형 페이지일 경우 추가 -->
</c:if>

<script type="text/javascript" src="<c:url value='/js/cmm/jquery.bxslider.min.js'/>"></script>
<script type="text/javascript">
<!--
    $(document).ready(function(){
      //메인 상단비주얼
      $('.slider1').bxSlider({  
          //mode: 'fade',
          auto:true,
          speed: 2000, //속도
          pause: 8000, //시간
          autoControls: true,
          prevSelector: '.visual_slider .controls',
          nextSelector: '.visual_slider .controls'                        
      });
    
      //메인 하단배너
      $('.slider2').bxSlider({      
          prevSelector: '.banner_slider .controls',
          nextSelector: '.banner_slider .controls',           
          auto:true,
          autoControls: true,
          pager:false,
          speed: 500,
          minSlides: 8,
          maxSlides: 8,
          slideWidth: 100,
          slideMargin: 10,
          moveSlides: 1
        });
    });
//-->
</script>
<script language="javascript" type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
var Top = 0;
var Left = 0;



/*******************************************
 * 기능 함수
 *******************************************/

/* 보안3종 체크 함수 */
function fn_checkSecu3(){
	//보안3종관련
    var tmout = 750;
    $ASTX2.init(
        function onSuccess(){
        // success
        	//console.log('ASTX 설치완료');
        },
        function onFailure(){
            var errno = $ASTX2.getLastError();
            
            //goto install page
            if(errno == $ASTX2_CONST.ERROR_NOTINST){
            	util_movePage("<c:url value='/cmm/aos2/certIndex.do'/>");
            
            //error handling
            }else{
                alert('ASTX error handling');
            }
        },
        tmout
    );
    
    //공인인증서
    var CertManX;
    if(ytMain){
        CertManX = ytMain;
    }
    CertManX.SetInfoPage(1);    // 1로 세팅시 CertManX.CertServiceSetup실행 후 errorCode 로직 실행됨.
    //모듈이 설치되어 있는지 확인하는 함수
    CertManX.CertServiceSetup(function(result){
        if(result == ""){
            var errorCode = CertManX.GetLastErrorCode();
            if(errorCode == 90000){
                alert("모듈 설치 필요.");
                util_movePage("<c:url value='/cmm/aos2/certIndex.do'/>");
                return false;
            }
            if(errorCode == 90001 || errorCode == 90002){
                util_movePage("<c:url value='/cmm/aos2/certIndex.do'/>");
                return false;
            }
            else{
                alert(errorCode + "//" + CertManX.GetLastErrorMsg());
                util_movePage("<c:url value='/cmm/aos2/certIndex.do'/>");
                return false;
            }
        }
    });
}
 
/* 메뉴이동 */ 
function fn_moveMenu(url, menuId){
	if(url.indexOf("http://") <= -1){
		url = "<c:url value='"+url+"'/>";
	}
	
	var objParam = new Object;
	if(util_chkReturn(menuId, "s") != ""){
	    objParam.paramMenuId = menuId;
	}
	
	util_movePage(url, objParam);
}

/* [공지사항 상세]이동함수 */
function fn_moveDetail(seq){
    var objParam = new Object();
    objParam.pageIndex = "1";
    objParam.noticeSeq = seq;
    objParam.paramMenuId = "04002";
    
    util_movePage("<c:url value='/cmm/noti/notiDtl.do'/>",objParam);
}



/*******************************************
 * ajax,ajax Callback 함수
 *******************************************/

/* ajax call 함수 */
function fn_ajaxCall_getList(){
	var url = "<c:url value='/cmm/noti/selectNotiListPop.ajax'/>";
    var listOrder = "";//$("#listOrder").val();
    
    var objParam = new Object();
    objParam.listOrder = listOrder;
    objParam.noticePopYn = "Y";
    objParam.searchKind = $("#searchKind").val();
    
    $.ajax({
        type    : "post"
       ,url     : url
       ,data    : objParam
       ,success : function(data){ 
           //alert("정보수정 성공");
           fn_ajaxCallback_getList(data);
       }
       ,error   : function(){
           //alert("정보수정 실패");
       }
   });
    
} 

/* ajax callback 함수 */
function fn_ajaxCallback_getList(data){
	
	var resultList = data.resultList;
	
	if(resultList != null){
        for(var i=0; i<resultList.length; i++){
        	//일주일간 보지 않기 쿠키값을 체크합니다.
        	fn_pop_cookie(resultList[i]);        	
        }
    }else{
        alert("<spring:message code='fail.alert.select' />");
        return;
    }
}



/*******************************************
 * 이벤트 함수
 *******************************************/

function getCookie(name) { 
	var Found = false 
	var start, end 
	var i = 0 
	
	while(i <= document.cookie.length) { 
	  start = i 
	  end = start + name.length 
	
	  if(document.cookie.substring(start, end) == name) { 
	    Found = true 
	    break 
	  } 
	  i++ 
	} 
	
	if(Found == true) { 
	  start = end + 1 
	  end = document.cookie.indexOf(";", start) 
	    if(end < start) 
	      end = document.cookie.length 
	  return document.cookie.substring(start, end) 
	} 
	return "" 
} 

function fn_pop_cookie(resultList){		
	var title = resultList.noticeSeq;    
    var titleCompare = String(title+"=");   
    var a = document.cookie;    
    var x = a.split(";");
    for(var i=0; i<x.length; i++){
        var y = $.trim(x[i]);
        if(titleCompare == y){
            return false;
        }
    }
    fn_pop_open(Top, Left, resultList);
    
    var noticePopWidth = resultList.noticePopWidth;
    var noticePopHeight = resultList.noticePopHeight;
    
    //테이블에 null값이 저장되어 있을 경우 - 팝업 간격 조정용
    if(noticePopWidth == null || noticePopWidth == "" || noticePopWidth == "0"){
        noticePopWidth = "300";
    }
    if(noticePopHeight == null || noticePopHeight == "" || noticePopHeight == "0"){
        noticePopHeight = "300";
    }
    
    //화면에 보이는 팝업들 간의 간격을 조정합니다.
    Left += Number(noticePopWidth)+17;
    LeftControl = ((window.screen.width - Number(noticePopWidth)) - Left);
    if (LeftControl < 0){
        Left = 0;
        Top += Number(noticePopHeight)+60;
        TopControl = ((window.screen.height - Number(noticePopHeight)) - Top);
        if (TopControl < 0){
            Top = 100;
        }
    }
}

function fn_pop_open(Top, Left, resultList){
	//팝업창 중에 일주일간 보지 않기 체크를 하지 않은 팝업만 생성합니다. 
    var title = resultList.noticeSeq;
    var url = '/cmm/noti/notiDtlPop.do?noticeSeq='+resultList.noticeSeq+'&popTilte='+title;
    var popupwidth = resultList.noticePopWidth;
    var popupheight = resultList.noticePopHeight;
    
    //테이블에 null값이 저장되어 있을 경우 - 팝업 사이즈 조정용
    if(popupwidth == null || popupwidth == "" || popupwidth == "0"){
        popupwidth = "300";
    }else{
        popupwidth = String(resultList.noticePopWidth);
    }
    if(popupheight == null || popupheight == "" || popupheight == "0"){
        popupheight = "300";
    }else{
        popupheight = String(resultList.noticePopHeight);
    }
    
    Future = "fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no, scrollbars=yes,resizable=no,left=" + Left + ",top=" + Top + ",width=" + popupwidth + ",height=" + popupheight;
    PopUpWindow = window.open(url, title, Future);
    PopUpWindow.focus();
}
 
/* onload 호출 */
function fn_onload_searchNotice(){
    fn_ajaxCall_getList(); //ajax call 함수호출
}

<%-- 개인사용자 약관동의 팝업 --%>
function fn_customerTermsPop(){
	var url = "<c:url value='/usr/mbrReg/customerTermsListPopup.do'/>";
    var objOption = new Object();
    objOption.type = 'modal';
    objOption.width = '900'; 
    objOption.height = '565';
    
    var objParam = new Object();
    
    util_modalPage(url, objOption, objParam);
}

<%-- 로그인 처리 --%>
function fn_login(){
	var url = "<c:url value='/'/>";
    gfn_loginNeedMove(url, null);
}

function fn_openCommonTermsPopup() {
    var url = "<c:url value='/usr/svcAppl/commonTermsPopup.do'/>";
    var objOption = new Object();
    objOption.type = 'modal';
    objOption.width = '900';
    objOption.height = '700';

    var objParam = new Object();
    objParam.callBakFunc = "fn_commonTermsCallBack";

    util_modalPage(url, objOption, objParam);
}

function fn_commonTermsCallBack(rsCd,rsMsg) {
    if(rsCd == '00'){
        alert("<spring:message code='success.alert.process' />");
    } else {
        if(util_chkReturn(rsMsg, "s") != ""){
            alert(rsMsg+'\n['+rsCd+']');
        }
    }
}
/* 화면 로드 처리 */
$(document).ready(function(){
	
	//공지사항
	fn_onload_searchNotice();
	
	//개인사용자 약관동의 팝업
	<c:if test="${not empty resultTermsList || fn:length(resultTermsList) > 0 || not empty companyTermsList || fn:length(companyTermsList) > 0}">
	    fn_customerTermsPop();
	</c:if>

/*    <c:if test="${checkedCommonTerms ne null}">
        fn_openCommonTermsPopup();
    </c:if>*/
	/*
    //로딩start
    gfn_setLoading(true);
    
	//보안3종 체크함수 호출
    fn_checkSecu3();
	
    //로딩end
    gfn_setLoading(false);
    */
});

<%-- 앱 link 이동 --%>
function fn_moveAppLink(link){
	if(util_chkReturn(link, "s") != ""){
        if(link.indexOf("http") <= -1) link = "http://" + link;
        window.open(link, "_blank");
    }else{
    	fn_moveMenu('/cmm/intro/introFintechApp.do', '03001');
    }
}

</script>
</head>
<body>
<form id="mainVO" name="mainVO">
<input type="hidden" name="paramMenuId" id="paramMenuId" value="" />

<input type="hidden" name="searchKind" id="searchKind" value="${SYSTEM_KIND}" /> <!-- //검색조건:공지사항 노출 -->
</form>
<div class="wrap main_wrap <c:if test="${isMobile eq 'true'}">mobile_wrap</c:if>"><!-- 메인페이지 .main_wrap 추가 --><!-- 반응형 페이지일 경우 .mobile_wrap 추가 -->

    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 대메뉴 영역 --%>

    <!-- section -->
    <section>

        <div class="container">

            <!-- main_content -->
            <article id="main_content">
                <!-- main_visual -->
                <div class="main_visual">
                    <div class="visual_slider">
                        <div class="controls">
                        </div>                
                        <ul class="slider1">
                            <li>
                                <div class="m_hide"><img src="<c:url value="/images/spt/main/img_visual_01.jpg"/>" alt="증권파생시장 ? 금융투자업계 IT 인프라를 책임지고 있는 자본시장 IT솔루션 리더  KOSCOM 금융투자회사, 핀테크기업 등과 함께 자본시장의 상생과 혁신을 추구합니다."></div>
                                <div class="m_show"><img src="<c:url value="/images/spt/main/img_visual_m_01.jpg"/>" alt="증권파생시장 ? 금융투자업계 IT 인프라를 책임지고 있는 자본시장 IT솔루션 리더  KOSCOM 금융투자회사, 핀테크기업 등과 함께 자본시장의 상생과 혁신을 추구합니다."></div>
                                <!-- <div class="txt">
                                    <p class="tit1">증권파생시장 ? 금융투자업계 IT 인프라를 책임지고 있는<br><span>자본시장 IT솔루션 리더  KOSCOM</span></p>
                                    <p class="tit2">금융투자회사, 핀테크기업 등과 함께<br>자본시장의 상생과 혁신을<br>추구합니다.</p>
                                </div> -->
                            </li>
                            <li>
                                <div class="m_hide"><img src="<c:url value="/images/spt/main/img_visual_10.jpg"/>" alt="금융투자회사의 경험, 핀테크 기업의 기술 - 세상에 없던 새로운 금융 서비스를 위해 금융투자회사와 핀테크기업이 손을 잡았습니다."></div>
                                <div class="m_show"><img src="<c:url value="/images/spt/main/img_visual_m_10.jpg"/>" alt="금융투자회사의 경험, 핀테크 기업의 기술 - 세상에 없던 새로운 금융 서비스를 위해 금융투자회사와 핀테크기업이 손을 잡았습니다."></div>
                            </li>
                            <li>
                                <div class="m_hide"><img src="<c:url value="/images/spt/main/img_visual_11.jpg"/>" alt="똑똑한 금융생활의 시작 - 자산증식을 위한 핀테크서비스를 금융투자 핀테크 포탈에서 시작하세요."></div>
                                <div class="m_show"><img src="<c:url value="/images/spt/main/img_visual_m_11.jpg"/>" alt="똑똑한 금융생활의 시작 - 자산증식을 위한 핀테크서비스를 금융투자 핀테크 포탈에서 시작하세요."></div>
                            </li>
                        </ul>
                    </div>
                </div>  
                <!-- // main_visual -->

                <!-- main_con -->
                <div class="main_con">
                    <div class="m_box1">
                        <dl>
                            <dt><a href="javascript:fn_moveMenu('<c:url value="/cmm/intro/introFintech.do"/>', '02001');">금융투자 핀테크 포털 소개</a></dt>
                            <dd>
                                <p class="txt">증권회사를 포함한 금융투자회사의 오랜 경험과 핀테크 기업의<br>아이디어&middot;기술이 합쳐져 새로운 금융서비스를 제공합니다.</p>
                                <a href="javascript:fn_moveMenu('<c:url value="/cmm/intro/introFintech.do"/>', '02001');">
                                    <ul>
                                        <li class="icon1"><div>금융정보의 안전한 이용</div></li>
                                        <li class="icon2"><div>통합금융서비스환경</div></li>
                                        <li class="icon3"><div>혁신적 금융서비스</div></li>
                                    </ul>
                                </a>
                            </dd>
                        </dl>                       
                    </div>
                    <div class="m_box2">
                        <dl>
                            <dt><a href="javascript:fn_moveMenu('<c:url value="/cmm/intro/introFintechApp.do"/>', '03001');">핀테크 App. 소개</a></dt>
                            <dd>
                                <p class="txt">오픈API를 활용한 다양한 핀테크 서비스를 제공합니다.<br>TOP 5 핀테크 서비스의 주인공이 되어 보세요.</p>
                                   <c:choose>
						            <c:when test="${empty resultAppList}" >
						                <div class="nodata">서비스가 등록되어있지 않습니다.</div>
						            </c:when>
						            <c:otherwise>
						                <ul>
						                <c:forEach var="resultAppList" items="${resultAppList}" varStatus="status">
						                    <li>
		                                        <div>
		                                        
		                                        <a href="javascript:fn_moveAppLink('<c:out value='${resultAppList.appDlUrl}'/>');">
		                                            <img id="appIconImg" src="<c:url value='/cmm/appImg/${resultAppList.appId}.do'/>" alt="App. 아이콘"
			                                        onerror="this.src='<c:url value='/images/cmm/icon/icon_app_none.png'/>'" 
			                                        />
		                                            <p class="txt"><c:out value='${resultAppList.appName}'/></p>
		                                        </a>
		                                        </div>
		                                    </li>                                                
						                </c:forEach>
						                </ul>
						            </c:otherwise>
						        </c:choose>
                                <a href="javascript:fn_moveMenu('<c:url value="/cmm/intro/introFintechApp.do"/>', '03001');" class="btn_more">+더보기</a>
                            </dd>
                        </dl>   
                    </div>
                    <div class="m_box3">
                        <dl>
                            <dt>핀테크 서비스 신청</dt>
                            <dd>
                                <p class="txt">코스콤과 핀테크 기업이 제공하는 핀테크 서비스를 시작하세요.</p>
                                <ul>
                                    <li class="icon1">
                                        <div>
                                            <c:if test="${empty LoginVO}">
                                                <a href="javascript:util_movePage('<c:url value='/usr/mbrReg/mbrNewReg.do'/>');">
                                            </c:if>
                                            <c:if test="${not empty LoginVO}">
                                                <a href="javascript:alert('이미 로그인 하셨습니다.');">
                                            </c:if>
                                            <span>회원가입</span>
                                            </a>
                                        </div>
                                    </li>
                                    <li class="icon4"><div><a href="javascript:fn_moveMenu('<c:url value="/usr/svcAppl/appList.do"/>', '06002');"><span>핀테크앱 선택</span></a></div></li>
                                    <li class="icon3"><div><a href="javascript:fn_moveMenu('<c:url value="/myp/cert/cert.do"/>', '05003');"><span>인증서등록/<br>정보제공동의</span></a></div></li>
                                    <li class="icon2"><div><a href="javascript:fn_moveMenu('<c:url value="/myp/asumAcnt/asumAcnt.do"/>', '05001');"><span>가상계좌<br>발급/연결</span></a></div></li>
                                    <li class="icon5"><div><a href="javascript:fn_moveMenu('<c:url value="/usr/svcAppl/appList.do"/>', '06002');"><span>핀테크앱 이용</span></a></div></li>
		                        </ul>
                            </dd>
                        </dl>   
                    </div>      
                    <div class="m_box4">
                        <dl>
                            <dt>서비스 지원</dt>
                            <dd>
                                <ul>
                                    <li class="icon1"><div><a href="mailto:open@koscom.co.kr">문의하기</a></div></li>
                                    <li class="icon2"><div><a href="javascript:fn_moveMenu('<c:url value="/cmm/faq/faqList.do"/>', '04001');">자주 하는 질문</a></div></li>
                                </ul>
                            </dd>
                        </dl>   
                    </div>
                    <div class="m_box5">
                        <dl>
                            <dt>공지사항</dt>
                            <dd>
                                <c:choose>
                                    <c:when test="${empty resultBordNotiList}" >
                                        <div class="nodata">공지사항이 없습니다.</div>
                                    </c:when>
                                    <c:otherwise>
                                        <ul>
                                        <c:forEach var="resultBordNotiList" items="${resultBordNotiList}" varStatus="status">
                                            <li>
                                                <a href="javascript:fn_moveDetail('${resultBordNotiList.noticeSeq}')">
                                                    <span class="tit"><c:out value='${resultBordNotiList.noticeTitle}'/></span>
                                                    <span class="date"><c:out value='${resultBordNotiList.createDate}'/></span>
                                                </a>
                                            </li>                                           
                                        </c:forEach>
                                        </ul>
                                    </c:otherwise>
                                </c:choose>
                                <a href="javascript:fn_moveMenu('<c:url value="/cmm/noti/notiList.do"/>', '04002');" class="btn_more">+더보기</a>                               
                            </dd>
                        </dl>   
                    </div>                  
                </div>
                <!-- // main_con -->

                <!-- main_banner -->
                <div class="main_banner">
                    <div class="banner_slider">                     
                        <div class="banner_tit">
                            <p>참여사</p>
                            <div class="controls">
                            </div>
                        </div>
                        <div class="banner_box">
                            <!-- 2016-06-25 수정 -->
                            <ul class="slider2">
<!--                            <li><div><a href="http://www.samsungfn.com" target="_blank" title="새창열림"><img src="<c:url value="/images/spt/main/img_banner_04.jpg"/>" alt="samsungpop"></a></div></li>  -->
                                <li><div><a href="http://www.ebestsec.co.kr" target="_blank" title="새창열림"><img src="<c:url value="/images/spt/main/img_banner_07.jpg"/>" alt="이베스트투자증권"></a></div></li>
                                <li><div><a href="http://www.sks.co.kr" target="_blank" title="새창열림"><img src="<c:url value="/images/spt/main/img_banner_13.jpg"/>" alt="SK증권"></a></div></li>
                                <li><div><a href="https://www.samsungpop.com" target="_blank" title="새창열림"><img src="<c:url value="/images/spt/main/img_banner_21.jpg"/>" alt="삼성증권"></a></div></li>
                                <li><div><a href="https://www.eugenefn.com/main.do" target="_blank" title="새창열림"><img src="<c:url value="/images/spt/main/img_banner_55.jpg"/>" alt="유진투자증권"></a></div></li>
                                <li><div><a href="http://www.db-fi.com" target="_blank" title="새창열림"><img src="<c:url value="/images/spt/main/img_banner_02.jpg"/>" alt="DB금융투자"></a></div></li>
                                <li><div><a href="http://www.daishin.com" target="_blank" title="새창열림"><img src="<c:url value="/images/spt/main/img_banner_01.jpg"/>" alt="대신증권"></a></div></li>
                                <li><div><a href="http://www.hi-ib.com/" target="_blank" title="새창열림"><img src="<c:url value="/images/spt/main/img_banner_33.jpg"/>" alt="하이투자증권"></a></div></li>
                                <li><div><a href="http://www.kbsec.co.kr/index.jsp" target="_blank" title="새창열림"><img src="<c:url value="/images/spt/main/img_banner_28.jpg"/>" alt="KB증권"></a></div></li>
                                <li><div><a href="http://www.shinhaninvest.com/index.html" target="_blank" title="새창열림"><img src="<c:url value="/images/spt/main/img_banner_05.jpg"/>" alt="신한금융투자"></a></div></li>
                                <li><div><a href="http://www.nhis.co.kr" target="_blank" title="새창열림"><img src="<c:url value="/images/spt/main/img_banner_12.jpg"/>" alt="NH투자증권"></a></div></li>
                                <li><div><a href="https://www.myasset.com" target="_blank" title="새창열림"><img src="<c:url value="/images/spt/main/img_banner_06.jpg"/>" alt="유안타증권"></a></div></li>

                                <li><div><a href="http://www.bccard.com" target="_blank" title="새창열림"><img src="<c:url value="/images/spt/main/img_banner_39.jpg"/>" alt="BC카드"></a></div></li>
                            </ul>
                            <!-- // 2016-06-25 수정 -->
                        </div>
                    </div>
                </div>
                <!-- //main_banner -->
            </article>
            <!-- // main_content -->
        </div>
    </section>
    <!-- section -->

    <%-- footer --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-footer.jspf" %>
    <%-- footer --%>

</div>

</body>
</html>