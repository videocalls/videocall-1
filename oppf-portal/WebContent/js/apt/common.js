/**
 * 설명 		: 로그인 필요한 페이지 이동 시 페이지 이동 공통 처리
 * 사용방식 	: gfn_loginNeedMove(url : 처리 url, param: return 후 처리될 param, msg : 페이지 이동시 사용할 msg)
 * 주의 		: 
 * 리턴 		: 없음
 */
function gfn_loginNeedMove(url, param, msg){
	var loginUrl = "/apt/cmm/loginView.do";
		
	//로그인 처리 호출
	gfn_loginMove(loginUrl, url, param, msg);
}