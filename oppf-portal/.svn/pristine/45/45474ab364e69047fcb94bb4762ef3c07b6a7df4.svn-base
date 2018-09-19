(function($) {
	var printAreaCount = 0;

	$.fn.printArea = function() {
		// 로딩 호출
		gfn_setLoading(true, "인쇄 처리중입니다.");

		var ele = $(this);

		var idPrefix = "printArea_";

		removePrintArea(idPrefix + printAreaCount);

		printAreaCount++;

		var iframeId = idPrefix + printAreaCount;

		var iframe = document.createElement('IFRAME');

		$(iframe)
				.attr('style',
						'position:absolute;width:0px;height:0px;left:-500px;top:-500px;');
		$(iframe).attr('id', iframeId);

		document.body.appendChild(iframe);

		var doc = iframe.contentWindow.document;

		var links = window.document.getElementsByTagName('link');

		// [s] css pending 현상으로 <style> inline 처리 2017-04-05 한유진
		// for( var i = 0; i < links.length; i++ )
		// if( links[i].rel.toLowerCase() == 'stylesheet' )
		// doc.write('<link type="text/css" rel="stylesheet" href="' +
		// links[i].href + '"></link>');
		// style
		// doc.write('<head>');
		doc.write('<style>');
		doc
				.write('body {font-family:"Malgun Gothic", "돋움", Dotum,Arial,sans-serif; font-weight:normal;color:#222222; -webkit-text-size-adjust:none;}');
		doc.write('.btn_area{margin-top:20px; text-align: center;}');
		doc
				.write('.btn_type5 {display:inline-block;*display:inline;*zoom:1; min-width:100px; padding:7px 10px 8px 10px; background:#b02601; color:#fff !important; font-size:14px; line-height:15px; text-align:center; vertical-align:middle; white-space:nowrap; text-decoration:none; margin:0 3px;}');
		doc.write('.btn_type5.type2 {background:#403b38;}');
		doc.write('</style>');
		// doc.write('</head>');
		// [e] css pending 현상으로 <style> inline 처리 2017-04-05 한유진

		// onload
		doc.write('<script type="text/javascript">');
		doc.write('window.onload = function(){ ');
		doc.write(' setTimeout(loadingPage, 1000); ');
		doc.write('}\n');

		doc.write('function loadingPage(){ ');
		doc
				.write(' if(document.readyState=="complete"){ parent.gfn_setLoading(false); }else{  setTimeout(loadingPage, 1000); } ');
		doc.write('}\n');

		doc.write('</script>');

		doc.write('<div class="' + $(ele).attr("class") + '" style="'
				+ $(ele).attr("style") + '">' + $(ele).html() + '</div>');
		doc.close();
		
		//[s] iframe 로딩완료 전 프린트로 인쇄실패 -> 시간차 인쇄 2017-04-10 이한별
		setTimeout(function() {
			iframe.contentWindow.focus();
			iframe.contentWindow.print();
		}, 500);
		// iframe.contentWindow.focus();
		// iframe.contentWindow.print();
		// [e] iframe 로딩완료 전 프린트로 인쇄실패 -> 시간차 인쇄 2017-04-10 이한별

		
	}

	var removePrintArea = function(id) {
		$("iframe#" + id).remove();
	};

})(jQuery);
