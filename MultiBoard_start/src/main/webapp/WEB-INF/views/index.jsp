<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="i18n/header" />
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/views/include/staticFiles.jsp"/>

<script defer src="<c:url value='/highcharts/highcharts.js'/>"></script>
<script defer src="<c:url value='/highcharts/themes/sand-signika.js'/>"></script>
<%--
<script defer>
/*
	var dynamicChart;
	$(function() {
		dynamicChart = new Highcharts.Chart({
			chart : {
				renderTo : 'dynamicChartContainer',
				defaultSeriesType : 'spline',
				borderColor : '#DDDDDD',
				borderRadius : 5,
				borderWidth : 1,
			},
			colors : [ 'cyan' ],
			title : {
				text : 'Dynamic Chart(SSE)',
				margin: 5,
				style: {"fontSize": "1.2em"}
			},
			xAxis : {
				type : 'datetime',
				tickPixelInterval : 1000,
				maxZoom : 10 * 100,
				title : {
					text: 'Time',
					margin : 5,
				}
			},
			yAxis : {
				min: 0, 
				max: 110,
				minPadding : 0.2,
				maxPadding : 0.2,
				title : {
					text : 'Count',
					margin : 5
				}
			},
			series : [ {
				name : 'Data',
				data : []
			} ]
		});
	});

	var source = new EventSource("<c:url value='/sse/sample'/>");
    source.addEventListener(
        "message", 
        function(event) {
        	//console.log(event.data);
        	var data = event.data.split("\n");
			var series = dynamicChart.series[0];
			var shift = series.data.length > 20;
			var time = parseInt(data[0], 10);
			var count = parseInt(data[1], 10);
			dynamicChart.series[0].addPoint([ time, count ], true, shift);
        }, 
        false
    );
*/
</script>
<script defer>
/*
	var accessLogChart;
	$(function() {
		accessLogChart = new Highcharts.Chart({
			chart : {
				renderTo : 'accessLogChartContainer',
				defaultSeriesType : 'spline',
				borderColor : 'red',
				borderRadius : 5,
				borderWidth : 1,
				events : {
					load : requestSessionCountByMinute
				}
			},
			colors : [ 'red' ],
			title : {
				text : 'Access Log(Websocket)',
				margin: 5,
				style: {"fontSize": "1.2em"}
			},
			xAxis : {
				type : 'datetime',
				tickPixelInterval : 100,
				maxZoom : 20 * 1000,
				title : {
					text: 'Time',
					margin : 5
				}
			},
			yAxis : {
				minPadding : 0.2,
				maxPadding : 0.2,
				title : {
					text : 'Count',
					margin : 5
				}
			},
			series : [ {
				name : 'Data',
				data : []
			} ]
		});
	});

	function requestSessionCountByMinute() {
		var ws = new WebSocket("ws://" + location.host + "<c:url value='/websocket/accesslog'/>");
		ws.onmessage = function(event) {
			var data = JSON.parse(event.data);
			var series = accessLogChart.series[0];
			var shift = series.data.length > 10;
			accessLogChart.series[0].addPoint([ data.time, data.count ], true, shift);
		}
	}
*/
</script>
--%>
<body>
<jsp:include page="/WEB-INF/views/include/bodyHeader.jsp"/>
<div class="container">
	<div class="pg-opt">
        <div class="row">
            <div class="col-md-6 pc">
                <h2><fmt:message key="HOME"/></h2>
            </div>
            <div class="col-md-6">
                <ol class="breadcrumb">
                    <li><fmt:message key="DASHBOARD"/></li>
                    <li class="active"><fmt:message key="HOME"/></li>
                </ol>
            </div>
        </div>
    </div>

	<div class="content">
	<div class="alert alert-warning" class="page-header">
		<h3><fmt:message key="WELCOME_MESSAGE"/></h3>
	</div>
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
			data
		</div>
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
			data
		</div>
        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
            data
        </div>
	</div>
	<div class="progress">
		<div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">
		<span class="sr-only"></span>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		<div class="thumbnail">
			<div class="caption">
			<h3>Java</h3>
			<ul>
				<li>Java Programming
				<li>Java Window Programming 
				<li>JDBC Programming
				<li>Servlet/JSP Web Programming
				<li>Spring Framework
				<li>MyBatis Framework
			</ul>
			</div>
		</div>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		<div class="thumbnail">
			<div class="caption">
			<h3>Framework</h3>
			<ul>
				<li>Spring Framework
				<li>전자정부표준프레임워크 
				<li>MyBatis Framework
				<li>Bootstrap
				<li>D3.js
				<li>Dandelion Datatables 
			</ul>
			</div>
		</div>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		<div class="thumbnail">
			<div class="caption">
			<h3>BigData</h3>
			<ul>
				<li>Hadoop
				<li>R Analytics/Visualization 
				<li>NoSQL
				<li>BigData Analytics
				<li>Host Virtualization
				<li>Cloud Computing
			</ul>
			</div>
		</div>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		<div class="thumbnail">
			<div class="caption">
			<h3>Mobile, Web</h3>
			<ul>
				<li>Android
				<li>Hybrid Mobile App 
				<li>HTML5, Web Standard
				<li>CSS3
				<li>JavaScript, JQuery
				<li>Responsive Web
			</ul>
			</div>
		</div>
		</div>
	</div>
	<div class="progress">
		<div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">
		<span class="sr-only"></span>
		</div>
	</div>
	<div class="alert alert-info">
		<ol>
			<li><a href="http://cafe.naver.com/javaspecialistgroup.cafe" class="alert-link pc" target="_blank"><fmt:message key="NAVER_CAFE_MESSAGE"/></a>
		</ol>
	</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
</body>
</html>
