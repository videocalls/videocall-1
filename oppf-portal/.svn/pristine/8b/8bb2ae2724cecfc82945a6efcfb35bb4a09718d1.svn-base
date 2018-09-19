<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : manageSIFAPI.jsp
 * @Description : 금투사 연계서버의 금투사별 API관리 페이지
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.08.02  신영규        최초  생성
 *  2016.08.04  신영규        금투사연계서버 리스트 추가
 * </pre>
 *
 * @author 신영규
 * @since 2016.08.02
 * @version 1.0
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<script>
$(document).ready(function(){
	<%-- 로그인 처리 --%>
    <c:if test="${empty LoginVO}">
        fn_login();
        return;
    </c:if>
	fn_getServer();
	$("#ip").change(function() {
		fn_getComAliases($(this).val());
	});
	$("#comAlias").change(function() {
		fn_getMsgType($(this).val());
	});
	$("#btnCheckConnection").click(function() {
		fn_btnCheckConnection();
	});
	$("#btnReload").click(function() {
		fn_btnReload();
	});
	$("#btnDisconnect").click(function() {
		fn_btnDisconnect();
	});
});

<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/apt/sif/manageSIFAPI.do'/>";
    var param = new Object();
    param.paramMenuId = "13001";
    
    gfn_loginNeedMove(url, param);
}

function fn_btnCheckConnection() {
	$("#message").html("");
    var url = "<c:url value='/apt/sif/checkConnection.ajax'/>?ip="+$("#ip").val()+"&comAlias=" + $("#comAlias").val() + "&msgType=" + $("#msgType").val();
    $.ajax({
         type    : "get"
        ,url     : url
        ,success : function(data){
        	if(Object.keys(data).length > 0) {
        		//var table_html = "<table><th>API 서비스 제공자</th><th>API 서비스</th><th>IP</th><th>Port</th><th>URI</th><th>Method</th><th>최대연결개수</th><th>Active연결개수</th><th>최대유휴연결개수</th><th>유휴연결개수</th>";
        		var table_html = "<table><th>API 서비스 제공자</th><th>API 서비스</th><th>IP</th><th>Port</th><th>Method</th><th>최대연결개수</th><th>Active연결개수</th><th>최대유휴연결개수</th><th>유휴연결개수</th>";
        		Object.keys(data).forEach(function(key) {
        			var rowspan = 0;
        			var subdata = data[key];
        			table_html+='<tr><td #rowspan>'+key+'</td>';
        			if(Object.keys(subdata).length > 0) {
        				Object.keys(subdata).forEach(function(subkey) {
        					var apidata=subdata[subkey];
        					table_html += '<td rowspan="'+apidata.length+'">'+subkey+'</td>';
        					for(var i = 0;i<apidata.length;i++) {
        						//table_html += '<td>'+apidata[i].ip+'</td><td>'+apidata[i].port+'</td><td>'+((apidata[i].url != null)?apidata[i].url:'')+'</td><td>'+((apidata[i].method != null)?apidata[i].method:'')+'</td><td>'+apidata[i].maxConnection+'</td><td>'+apidata[i].activeNum+'</td><td>'+apidata[i].maxIdle+'</td><td>'+apidata[i].idle+'</td></tr>';
        						table_html += '<td>'+apidata[i].ip+'</td><td>'+apidata[i].port+'</td><td>'+((apidata[i].method != null)?apidata[i].method:'')+'</td><td>'+apidata[i].maxConnection+'</td><td>'+apidata[i].activeNum+'</td><td>'+apidata[i].maxIdle+'</td><td>'+apidata[i].idle+'</td></tr>';
        						rowspan++;
        					}
        				});
        			}
        			table_html = table_html.replace('#rowspan', 'rowspan="'+rowspan+'"');
        		});
        		table_html+="</table>";
        		$("#message").html(table_html);
        	} else {
        		alert('연결정보가 없습니다');
        	}
        }
        ,error   : function(){
    		alert('연결정보가 없습니다');
        }
    });
}

function fn_btnReload() {
	$("#message").html("");
    var url = "<c:url value='/apt/sif/reload.ajax'/>?ip="+$("#ip").val()+"&comAlias=" + $("#comAlias").val() + "&msgType=" + $("#msgType").val();
    $.ajax({
         type    : "get"
        ,url     : url
        ,success : function(data){
        	if(data.result == 'ok') {
        		$("#message").html("성공");
        	} else {
        		$("#message").html("실패 - "+data.message);
        	}
        }
        ,error   : function(){
    		$("#message").html("실패 - "+data.message);
       }
    });
}

function fn_btnDisconnect() {
	$("#message").html("");
    var url = "<c:url value='/apt/sif/disconnect.ajax'/>?ip="+$("#ip").val()+"&comAlias=" + $("#comAlias").val() + "&msgType=" + $("#msgType").val();
    $.ajax({
         type    : "get"
        ,url     : url
        ,success : function(data){
        	if(data.result == 'ok') {
        		$("#message").html("성공");
        	} else {
        		$("#message").html("실패 - "+data.message);
        	}
        }
        ,error   : function(){
    		$("#message").html("실패 - "+data.message);
        }
    });
}

function fn_initComAliases() {
	$("#comAlias").find("option").remove().end();
	$("#comAlias").append("<option value=\"\">선택하세요</option>");
}

function fn_initMsgType() {
	$("#msgType").find("option").remove().end();
	$("#msgType").append("<option value=\"\">선택하세요</option>");
}

function fn_getComAliases(ip) {
	if(ip == '') {
		fn_initComAliases();
		fn_initMsgType();
		return;
	}
    var url = "<c:url value='/apt/sif/getCompany.ajax'/>?ip="+ip;
    $.ajax({
         type    : "get"
        ,url     : url
        ,success : function(data){
        	if(data.length > 0) {
        		fn_initComAliases();
        		fn_initMsgType();
	       		for(var i=0;i<data.length;i++) {
        			$("#comAlias").append("<option value=\""+data[i]+"\">" + data[i] + "</option>");
        		}
        	} else {
        		alert('금투사 별칭이 없습니다');
        	}
        }
        ,error   : function(){
    		alert('금투사 별칭이 없습니다');
        }
    });
}

function fn_getServer() {
    var url = "<c:url value='/apt/sif/getServer.ajax'/>";
    $.ajax({
         type    : "get"
        ,url     : url
        ,success : function(data){
        	if(data.length > 0) {
	       		for(var i=0;i<data.length;i++) {
        			$("#ip").append("<option value=\""+data[i]+"\">" + data[i] + "</option>");
        		}
        	} else {
        		alert('금투사 연계 서버 정보가 없습니다');
        	}
        }
        ,error   : function(){
    		alert('금투사 연계 서버 정보가 없습니다');
        }
    });
}

function fn_getMsgType(comAlias) {
	if(comAlias == '') {
		fn_initMsgType();
		return;
	}
    var url = "<c:url value='/apt/sif/getMsgType.ajax'/>?ip="+$("#ip").val()+"&comAlias=" + comAlias;
    $.ajax({
         type    : "get"
        ,url     : url
        ,success : function(data){
        	if(data.length > 0) {
        		fn_initMsgType();
        		for(var i=0;i<data.length;i++) {
        			$("#msgType").append("<option value=\""+data[i]+"\">" + data[i] + "</option>");
        		}
        	} else {
        		alert('서비스 API목록이 없습니다');
        	}
        }
        ,error   : function(){
    		alert('서비스 API목록이 없습니다');
        }
    });
}
</script>
</head>
<body>
    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 대메뉴 영역 --%>
    
    <!-- // wrap_top -->
    <article class="container">
        <div>
            <%-- lnb(좌측메뉴) 영역 --%>
            <%@ include file="/WEB-INF/view/cmm/common-include-left.jspf" %>
            <%-- lnb(좌측메뉴) 영역 --%>
            
            <!-- content -->
			<section class="content">
			    <div class="location">
			        <h2>커넥션 풀 관리</h2>
			    </div>
			    <div class="tb_write1">
			    	<table>
                        <colgroup>
                            <col style="width:15%;">
                            <col style="width:*;">
                        </colgroup>
			    		<tbody>
			    			<tr>
				    			<th>
				    				금투사연계서버
				    			</th>
				    			<td class="txt_l">
				    				<select name="ip" id="ip">
				    					<option value="">선택하세요</option>
				    				</select>
				    			</td>
			    			</tr>
			    			<tr>
				    			<th>
				    				API 서비스 제공자
				    			</th>
				    			<td class="txt_l">
				    				<select name="comAlias" id="comAlias">
				    					<option value="">선택하세요</option>
				    				</select>
				    			</td>
			    			</tr>
			    			<tr>
				    			<th>
				    				API 서비스 이름
				    			</th>
				    			<td class="txt_l">
				    				<select name="msgType" id="msgType">
				    					<option value="">선택하세요</option>
				    				</select>
				    			</td>
			    			</tr>
			    			<tr>
				    			<th>
				    				결과
				    			</th>
				    			<td class="txt_l">
				    				<div id="message"></div>
				    			</td>
			    			</tr>
			    		</tbody>
			    	</table>
			    </div>
			                <div class="btn_type3">
			                    <span class="btn_gray1"><a href="javascript:void(0);" id="btnCheckConnection">연결확인</a></span>
			                    <span class="btn_gray1"><a href="javascript:void(0);" id="btnReload">재기동</a></span>
			                    <span class="btn_gray1"><a href="javascript:void(0);" id="btnDisconnect">연결끊기</a></span>
			                </div>
			 </section>
		</div>
	</article>
</body>
</html>