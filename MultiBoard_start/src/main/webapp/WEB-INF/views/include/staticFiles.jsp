<%@ page contentType="text/html; charset=utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="i18n/header" />
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="robots" content="index, follow">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" >
    <meta name="google-site-verification" content="RPK2fDgygxeiosW4a4sE8zcla1193rlzAf90zQjTtvc" />
    
    <title><fmt:message key="TITLE"/></title>
    <!-- Favicon -->
    <link href="<c:url value='/favicon.png'/>" rel="icon" type="image/png">
	
	<link rel="stylesheet" href="<c:url value='/css/default.css'/>">
    <!-- Essential styles -->
    <link rel="stylesheet" href="<c:url value='/assets/bootstrap/css/bootstrap.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/font-awesome/css/font-awesome.min.css'/>"> 
    <link rel="stylesheet" href="<c:url value='/assets/fancybox/jquery.fancybox.css?v=2.1.5" media="screen'/>"> 

    <!-- Boomerang styles -->
    <link id="wpStylesheet" rel="stylesheet" type="text/css" href="<c:url value='/css/global-style.css'/>" media="screen">  

    <!-- Boomerang styles -->
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/side-menu.css'/>" media="screen">  

    <!-- Required JS async 또는 defer 속성을 넣으면 안됩니다.-->
    <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="<c:url value='/js/jquery-ui.min.js'/>"></script>

	<!-- Essentials -->
	<script async src="<c:url value='/assets/bootstrap/js/bootstrap.min.js'/>"></script>
	<script async src="<c:url value='/js/jquery.hoverup.js'/>"></script>
	<script async src="<c:url value='/js/jquery.hoverdir.js'/>"></script>
	<script async src="<c:url value='/js/jquery.stellar.js'/>"></script>

	<!-- Boomerang mobile nav - Optional  -->
	<script async src="<c:url value='/assets/responsive-mobile-nav/js/jquery.dlmenu.js'/>"></script>
	<script async src="<c:url value='/assets/responsive-mobile-nav/js/jquery.dlmenu.autofill.js'/>"></script>

	<!-- Assets -->
	<script async src="<c:url value='/assets/hover-dropdown/bootstrap-hover-dropdown.min.js'/>"></script>
	<script async src="<c:url value='/assets/page-scroller/jquery.ui.totop.min.js'/>"></script>
	<script async src="<c:url value='/assets/mixitup/jquery.mixitup.js'/>"></script>
	<script async src="<c:url value='/assets/mixitup/jquery.mixitup.init.js'/>"></script>
	<script async src="<c:url value='/assets/fancybox/jquery.fancybox.pack.js?v=2.1.5'/>"></script>
	<script async src="<c:url value='/assets/waypoints/waypoints.min.js'/>"></script>
	<script async src="<c:url value='/assets/milestone-counter/jquery.countTo.js'/>"></script>

	<!-- Mouse Hover menu App JS -->
	<script async src="<c:url value='/js/wp.app.js'/>"></script>
	<!--[if lt IE 9]>
	    <script src="<c:url value='/js/html5shiv.js'/>"></script>
	    <script src="<c:url value='/js/respond.min.js'/>"></script>
	<![endif]-->

	<!-- google analytics -->
	<script>
	(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	})(window,document,'script','//www.google-analytics.com/analytics.js','ga');
	
	ga('create', 'UA-63011984-1', 'auto');
	ga('send', 'pageview');
	</script>

	<!-- google sign-in -->
	<meta name="google-signin-client_id" content="97906395215-c9pitm8nc7oc49si11ltdt11sskcmcn0.apps.googleusercontent.com">
	<script>
	function onSignIn(googleUser) {
		//var profile = googleUser.getBasicProfile();
		//console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
		//console.log('Name: ' + profile.getName());
		//console.log('Image URL: ' + profile.getImageUrl());
		//console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
		var id_token = googleUser.getAuthResponse().id_token;
		
		var xhr = new XMLHttpRequest();
		xhr.open('POST', '/tokensignin');
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhr.onload = function() {
			//console.log('Signed in as: ' + xhr.responseText);
			window.location = xhr.getResponseHeader("Location");
			//document.open();
		    //document.write(xhr.responseText);
		    //document.close();
		};
		xhr.send('idtoken=' + id_token);
	}

	function onLoad() {
		gapi.load('auth2', function() {
			gapi.auth2.init();
		});
	}
	
	function signOut() {
		var auth2 = gapi.auth2.getAuthInstance();
		auth2.signOut().then(
			function() {
				//console.log('User signed out.');
				var xhr = new XMLHttpRequest();
				xhr.open('GET', '/member/logout');
				xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
				xhr.onload = function() {
					console.log('User signed out.');
					window.location.replace("/");
				};
				xhr.send();
			});
	}
	</script>
	<script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>

	<!-- check upload file size -->
	<script defer src="<c:url value='/js/check_upload_filesize.js'/>"></script>
	
	<!-- board file select by drag and drop -->
	<script defer src="<c:url value='/js/file_select_by_drag_and_drop.js'/>"></script>
	
	
	<!-- Syntax highlighter styles -->
	<link rel="stylesheet" href="<c:url value='/css/syntax/shCore.css'/>">
	<link rel="stylesheet" href="<c:url value='/css/syntax/shThemeEclipse.css'/>">
	<script type="text/javascript" async src="<c:url value='/js/syntax/shCore.js'/>"></script>
	<script type="text/javascript" async src="<c:url value='/js/syntax/shBrushJScript.js'/>"></script>
	<script type="text/javascript" async src="<c:url value='/js/syntax/shLegacy.js'/>"></script>
	<script type="text/javascript" async src="<c:url value='/js/syntax/shBrushBash.js'/>"></script>
	<script type="text/javascript" async src="<c:url value='/js/syntax/shBrushCpp.js'/>"></script>
	<script type="text/javascript" async src="<c:url value='/js/syntax/shBrushCSharp.js'/>"></script>
	<script type="text/javascript" async src="<c:url value='/js/syntax/shBrushCss.js'/>"></script>
	<script type="text/javascript" async src="<c:url value='/js/syntax/shBrushDelphi.js'/>"></script>
	<script type="text/javascript" async src="<c:url value='/js/syntax/shBrushDiff.js'/>"></script>
	<script type="text/javascript" async src="<c:url value='/js/syntax/shBrushGroovy.js'/>"></script>
	<script type="text/javascript" async src="<c:url value='/js/syntax/shBrushJava.js'/>"></script>
	<script type="text/javascript" async src="<c:url value='/js/syntax/shBrushJSP.js'/>"></script>
	<script type="text/javascript" async src="<c:url value='/js/syntax/shBrushPhp.js'/>"></script>
	<script type="text/javascript" async src="<c:url value='/js/syntax/shBrushPlain.js'/>"></script>
	<script type="text/javascript" async src="<c:url value='/js/syntax/shBrushPython.js'/>"></script>
	<script type="text/javascript" async src="<c:url value='/js/syntax/shBrushRuby.js'/>"></script>
	<script type="text/javascript" async src="<c:url value='/js/syntax/shBrushScala.js'/>"></script>
	<script type="text/javascript" async src="<c:url value='/js/syntax/shBrushSql.js'/>"></script>
	<script type="text/javascript" async src="<c:url value='/js/syntax/shBrushVb.js'/>"></script>
	<script type="text/javascript" async src="<c:url value='/js/syntax/shBrushXml.js'/>"></script>
	<script type="text/javascript" async src="<c:url value='/js/syntax/myConfig.js'/>"></script>
</head>