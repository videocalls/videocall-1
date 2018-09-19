<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
	<title>Home</title>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0" />
	<link rel="stylesheet" type="text/css" href="/resources/libs/ionic/css/ionic.min.css" />

	<script type="text/javascript" src="/resources/libs/jquery/dist/jquery.min.js"></script>
	<script type="text/javascript" src="/resources/libs/ionic/js/ionic.bundle.js"></script>
	<script type="text/javascript" src="/resources/libs/require.js"></script>
	<script type="text/javascript" src="/resources/libs/koscom.js"></script>
</head>
<body>
<ion-nav-bar class="bar-stable">
	<ion-nav-back-button></ion-nav-back-button>

	<ion-nav-buttons side="right">
		<button menu-toggle="right" class="button button-icon icon ion-navicon" ng-click="menuToggle()"></button>
	</ion-nav-buttons>
</ion-nav-bar>

<ion-side-menus enable-menu-with-back-views="true">
	<ion-side-menu-content>
		<ion-nav-view name="app-view"></ion-nav-view>
	</ion-side-menu-content>

	<ion-side-menu side="right">
		<ion-content class="navigation">
			<ul class="sidebar-nav">
				<li>
					<a href="#/platform" menu-close ng-click="pageMove('platform');">핀테크 플랫폼 소개</a>
				</li>
				<li>
					<a href="" ng-click="pageMove();">참여사 소개</a>
				</li>
				<li>
					<a href="" ng-click="pageMove();">App 소개</a>
				</li>
				<li>
					<a href="" ng-click="pageMove();">서비스 신청</a>
				</li>
				<li>
					<a href="" ng-click="pageMove();">자주 묻는 질문</a>
				</li>
				<li>
					<a href="#/notice" menu-close ng-click="pageMove('noticeList');">공지사항</a>
				</li>
				<li>
					<a href="" ng-click="pageMove();">가상계좌</a>
				</li>
				<li>
					<a href="" ng-click="pageMove();">신청 서비스</a>
				</li>
				<li>
					<a href="" ng-click="pageMove();">통합 계좌 조회</a>
				</li>
				<li>
					<a href="" ng-click="pageMove();">공인인증서</a>
				</li>
				<li>
					<a href="#/setting" ng-click="pageMove('setting');">설정</a>
				</li>
			</ul>
		</ion-content>
	</ion-side-menu>
</ion-side-menus>
	
<script type="text/javascript" src="/resources/app.module.js"></script>
</body>
</html>
