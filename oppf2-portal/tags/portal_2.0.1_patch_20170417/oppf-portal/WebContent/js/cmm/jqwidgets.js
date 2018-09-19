/**
 * @Name  : jqwudgets.js
 * @Description : jqwudgets 공통 스크립트
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *    수정일    	수정자   	수정내용
 *  ----------  ------  ----------
 *  2016.05.09  신동진  	최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.05.09
 * @version 1.0
 *
 */

/**
 * 설명 		: grid init option
 * 사용방식 	: gfn_gridOption(grid_id, 컬럼option, height, width)
 * 주의 		: 없음
 * 리턴 		: 없음
 */
function gfn_gridOption(){
    //매개변수값 저장
    var a = arguments;
    switch (a.length){
         case 1: //-- 매개변수가 하나일 때
             gfn_gridOptionLoding(a[0],null,null,null);
             break;
         case 2: //-- 매개변수가 두 개 일 때
        	 gfn_gridOptionLoding(a[0],null,null,a[1]);
             break;
         case 3: //-- 매개변수가 세 개 일 때
        	 gfn_gridOptionLoding(a[0],a[2],null,a[1]);
             break;
         case 4: //-- 매개변수가 네 개 일 때
        	 gfn_gridOptionLoding(a[0],a[2],a[3],a[1]);
             break;
         case 5: //-- 매개변수가 다섯 개 일 때
        	 gfn_gridOptionLoding(a[0],a[2],a[3],a[1]);
             break;
    }
}

/**
 * 설명 		: grid init option load
 * 사용방식 	: gfn_gridOptionLoding(grid_id, height, width, 컬럼option)
 * 주의 		: 없음
 * 리턴 		: 없음
 */
function gfn_gridOptionLoding(id,heightInfo,widthInfo,objA){
    //페이지 처리 custerm start
    /*
	var pagerrenderer = function () {
		fnCellendeditEvent(id);
		
		var element = $("<div style='margin-right: 10px; margin-top: 5px; width: 100%; height: 100%;'></div>");
		var datainfo = $("#"+id).jqxGrid('getdatainformation');
		var paginginfo = datainfo.paginginformation;
		label = $("<div style='font-size: 11px; margin: 2px 3px; font-weight: bold; float: left;'></div>");
		label.text(datainfo.rowscount+' 건이 조회 되었습니다.');
		label.appendTo(element);
		self.label = label;
		
		return element;
	}
    */
  
    //페이지 처리 custerm end
	var defaultOption = new Object(); 
	defaultOption.theme = "custom"; 
	    
	defaultOption.width = "100%";
	defaultOption.height = "500px"; 
	// defaultOption.autosavestate = true;
	//defaultOption.autorowheight = true;
	  
	defaultOption.sortable = true;
	defaultOption.enabletooltips = true;
	defaultOption.altrows = true;
	defaultOption.columnsresize = true;       //column resize
	defaultOption.columnsreorder= true;       //drop & down
	
	//page option
	//defaultOption.pageable = true;
	//defaultOption.pagesize = 100000;
	//defaultOption.pagerrenderer = pagerrenderer;
	  
	//filter option
	//defaultOption.showfilterrow = true;
	//defaultOption.filterable = true;
	//defaultOption.selectionmode =  'multiplecellsextended';
	  
	//scroll mode
	//defaultOption.scrollmode = 'deferred';
	//defaultOption.pagesizeoptions = ['15','30', '50', '100','200','1000','5000','9000']; 
  
	if(heightInfo != undefined || heightInfo != null){
	    defaultOption.height=heightInfo;
	}
	
	if(widthInfo != undefined || widthInfo != null){
	    defaultOption.width=widthInfo;
	}
	
	if(objA != undefined || objA != null){
	    $.extend(defaultOption, objA);
	}
  
    $("#"+id).jqxGrid(defaultOption);
}

/**
 * 설명 		: grid 데이터 로드
 * 사용방식 	: gfn_gridData(data, grid_id[option])
 * 주의 		: 없음
 * 리턴 		: 없음
 */
function gfn_gridData(data, grid){
	if(util_chkReturn(grid, "s") == "") grid = "jqxgrid";
	
	var source = {
		datatype: "json",                               
		localdata: data
	};
	
	var dataAdapter = new $.jqx.dataAdapter(source);

	// create jqxgrid.
	$("#"+grid).jqxGrid(
	{
	    source: dataAdapter
	});	
}


/**
 * 설명 		: line chart 셋팅
 * 사용방식 	: gfn_chart4LineLoding(chartId, data, options)
 * 주의 		: options에 필수값 있음 확인 후 코딩필요 
 * 리턴 		: 없음
 */
function gfn_chart4LineLoding(id, data, options){
	var settingData = new Object();
	//title
	if(util_chkReturn(options.title, "s") != "") {
		settingData.title = options.title;
	}else{
		settingData.title = "";
	}
	
	//titlePadding { left: 0, top: 0, right: 0, bottom: 10 }
	if(util_chkReturn(options.titlePadding, "s") != "") {
	    settingData.titlePadding = options.titlePadding;
	}else{
	    settingData.titlePadding = "";
	}
	
	//description
	if(util_chkReturn(options.description, "s") != "") {
        settingData.description = options.description;
	}else{
		settingData.description = "";
	}
	
	//padding
	//ex) { left: 20, top: 10, right: 50, bottom: 5 }
	if(util_chkReturn(options.padding, "s") != "") {
		settingData.padding = options.padding;
	}else{
		settingData.padding = { left: 20, top: 10, right: 50, bottom: 5 };
	}
		
	//xAxis[필수]
	/**
	{
        dataField: "날짜",        //x 기준 데이터 필드
        unitInterval: 1,         //x 의 unit 사이의 간격
        labels :{
            angle  : 20,         //라벨의 기울임 정보
            autoRotate : true    //true로 셋팅
        }
    }
	*/
	if(util_chkReturn(options.xAxis, "s") != "") {
        settingData.xAxis = options.xAxis;
      
        //라벨
        if(util_chkReturn(options.xAxis.labels, "s") == "") {
            settingData.xAxis.labels = 
            {
	  			angle  : 20,
	            autoRotate : true	
      	    };
        }
	}else{
		alert("그래프의 셋팅값이 옳지 않습니다.");
		return false;
	}
	
	//valueAxis[필수]
	/*
	{
      minValue: 0,            //최소 값
      maxValue: 100,          //최대 값
      unitInterval: 10        //값사이 interval
      title: {text: 'Time in minutes'}    //text 무시
  	}
	*/
	settingData.valueAxis = {visible: true};
	/*if(util_chkReturn(options.valueAxis, "s") != "") {
		settingData.valueAxis = options.valueAxis;
	}else{
		alert("그래프의 셋팅값이 옳지 않습니다.");
		return false;
	}*/
	
	//colorScheme
	if(util_chkReturn(options.colorScheme, "s") != "") {
		settingData.colorScheme = options.colorScheme;
	}else{
		settingData.colorScheme = "scheme01";  //scheme01 ~ scheme11
	}
	
	//seriesGroups[필수]
	//type을 여러개 셋팅 가능하다.
	/*
	[
      	{
          	type: 'stackedline',        //그래프 type
          	series: 
          	[
                  	{ dataField: 'Keith', displayText: 'Keith'},        //데이터 기준점
                  	...
                  	{ dataField: 'Erica', displayText: 'Erica'}         //데이터 기준점
          	]
      	}
  	]
	*/
	if(util_chkReturn(options.seriesGroups, "s") != "") {
		settingData.seriesGroups = options.seriesGroups;
	}else{
		alert("그래프의 셋팅값이 옳지 않습니다.");
		return false;
	}
	
	var settings = {
        title: settingData.title,
        description: settingData.description,
        padding: settingData.padding,
        titlePadding: settingData.titlePadding,
        source: data,
        xAxis: settingData.xAxis,
        valueAxis: settingData.valueAxis,
        colorScheme: settingData.colorScheme,
        seriesGroups: settingData.seriesGroups
	};
	
	//setting
	$('#'+id).jqxChart(settings);
}

/**
 * 설명 		: pie chart 셋팅
 * 사용방식 	: gfn_chart4PieLoding(chartId, data, options)
 * 주의 		: options에 필수값 있음 확인 후 코딩필요 
 * 리턴 		: 없음
 */
function gfn_chart4PieLoding(id, data, options){
	var settingData = new Object();
	//title
	if(util_chkReturn(options.title, "s") != "") {
		settingData.title = options.title;
	}else{
		settingData.title = "";
	}
	
	//titlePadding { left: 0, top: 0, right: 0, bottom: 10 }
	if(util_chkReturn(options.titlePadding, "s") != "") {
	    settingData.titlePadding = options.titlePadding;
	}else{
	    settingData.titlePadding = "";
	}
	
	//description
	if(util_chkReturn(options.description, "s") != "") {
        settingData.description = options.description;
	}else{
		settingData.description = "";
	}
	
	//padding
	//ex) { left: 5, top: 10, right: 50, bottom: 5 }
	if(util_chkReturn(options.padding, "s") != "") {
		settingData.padding = options.padding;
	}else{
		settingData.padding = { left: 5, top: 50, right: 5, bottom: 20 };
	}
	
	//colorScheme
	if(util_chkReturn(options.colorScheme, "s") != "") {
		settingData.colorScheme = options.colorScheme;
	}else{
		settingData.colorScheme = "scheme04";  //scheme01 ~ scheme11
	}
	
	//seriesGroups[필수]
	//type을 여러개 셋팅 가능하다.
	/*
	[		
        {
            type: 'pie',
            showLegend: true,
            enableSeriesToggle: true,
            series:
			[
	            {
	                dataField: 'Share',
	                displayText: 'Browser',
	                
	                showLabels: true,
	                labelRadius: 160,
	                labelLinesEnabled: true,
	                labelLinesAngles: true,
	                labelsAutoRotate: false,
	                initialAngle: 0,
	                radius: 125,
	                minAngle: 0,
	                maxAngle: 180,
	                centerOffset: 0,
	                offsetY: 170,
	                formatFunction: function (value, itemIdx, serieIndex, groupIndex) {
	                    if (isNaN(value))
	                        return value;
	                    return value + '%';
	                }
	            }
			]
		}
    ]
	*/
	if(util_chkReturn(options.seriesGroups, "s") != "") {
		settingData.seriesGroups = options.seriesGroups;
	}else{
		alert("그래프의 셋팅값이 옳지 않습니다.");
		return false;
	}
	
	// prepare jqxChart settings
    var settings = {
		source: data,
        title: settingData.title,
        description: settingData.description,
        enableAnimations: false,
        showLegend: true,
        showBorderLine: true,
        padding: settingData.padding,
        titlePadding: settingData.titlePadding,
        colorScheme: settingData.colorScheme,
        seriesGroups: settingData.seriesGroups
    };
    
    //setting
	$('#'+id).jqxChart(settings);
}

/**
 * 설명 		: stackedcolumn chart 셋팅
 * 사용방식 	: gfn_chart4StackedcolumnLoding(chartId, data, options)
 * 주의 		: options에 필수값 있음 확인 후 코딩필요 
 * 리턴 		: 없음
 */
function gfn_chart4StackedcolumnLoding(id, data, options){
	var settingData = new Object();
	//title
	if(util_chkReturn(options.title, "s") != "") {
		settingData.title = options.title;
	}else{
		settingData.title = "";
	}
	
	//titlePadding { left: 0, top: 0, right: 0, bottom: 10 }
	if(util_chkReturn(options.titlePadding, "s") != "") {
	    settingData.titlePadding = options.titlePadding;
	}else{
	    settingData.titlePadding = "";
	}
	
	//description
	if(util_chkReturn(options.description, "s") != "") {
        settingData.description = options.description;
	}else{
		settingData.description = "";
	}
	
	//padding
	//ex) { left: 20, top: 10, right: 50, bottom: 5 }
	if(util_chkReturn(options.padding, "s") != "") {
		settingData.padding = options.padding;
	}else{
		settingData.padding = { left: 20, top: 10, right: 50, bottom: 5 };
	}
		
	//xAxis[필수]
	/**
	{
        dataField: "날짜",        //x 기준 데이터 필드
        unitInterval: 1,         //x 의 unit 사이의 간격
        labels :{
            angle  : 20,         //라벨의 기울임 정보
            autoRotate : true    //true로 셋팅
        }
    }
    {
        dataField: 'Day',		//x 기준 데이터 필드
        unitInterval: 1,		//x 의 unit 사이의 간격
        axisSize: 'auto',
        tickMarks: {
            visible: true,
            interval: 1,
            color: '#BCBCBC'
        },
        gridLines: {
            visible: true,
            interval: 1,
            color: '#BCBCBC'
        }
    }
	*/
	if(util_chkReturn(options.xAxis, "s") != "") {
        settingData.xAxis = options.xAxis;
      
        //라벨
        if(util_chkReturn(options.xAxis.labels, "s") == "") {
            settingData.xAxis.labels = 
            {
	  			angle  : 20,
	            autoRotate : true	
      	    };
        }
        if(util_chkReturn(options.xAxis.tickMarks, "s") == "") {
            settingData.xAxis.tickMarks = 
            {
        		visible: true,
                interval: 1,
                color: '#BCBCBC'
      	    };
        }
        if(util_chkReturn(options.xAxis.gridLines, "s") == "") {
            settingData.xAxis.gridLines = 
            {
        		visible: true,
                interval: 1,
                color: '#BCBCBC'
      	    };
        }
	}else{
		alert("그래프의 셋팅값이 옳지 않습니다.");
		return false;
	}
	
	//valueAxis[필수]
	/*
	{
      minValue: 0,            //최소 값
      maxValue: 100,          //최대 값
      unitInterval: 10        //값사이 interval
	  title: {text: 'Time in minutes'} ,   		//text 무시
	  labels: { horizontalAlignment: 'right' },	//text 무시
	  tickMarks: { color: '#BCBCBC' }			//text 무시
  	}
	*/
	if(util_chkReturn(options.valueAxis, "s") != "") {
		settingData.valueAxis = options.valueAxis;
	}else{
		alert("그래프의 셋팅값이 옳지 않습니다.");
		return false;
	}
	
	//colorScheme
	if(util_chkReturn(options.colorScheme, "s") != "") {
		settingData.colorScheme = options.colorScheme;
	}else{
		settingData.colorScheme = "scheme01";  //scheme01 ~ scheme11
	}
	
	//seriesGroups[필수]
	//type을 여러개 셋팅 가능하다.
	/*
	[
      	{
          	type: 'stackedcolumn',        //그래프 type
          	series: 
          	[
                  	{ dataField: 'Keith', displayText: 'Keith'},        //데이터 기준점
                  	...
                  	{ dataField: 'Erica', displayText: 'Erica'}         //데이터 기준점
          	]
      	}
  	]
	*/
	if(util_chkReturn(options.seriesGroups, "s") != "") {
		settingData.seriesGroups = options.seriesGroups;
	}else{
		alert("그래프의 셋팅값이 옳지 않습니다.");
		return false;
	}
	
	var settings = {
        title: settingData.title,
        description: settingData.description,
        enableAnimations: true,
        showLegend: true,
        padding: settingData.padding,
        titlePadding: settingData.titlePadding,
        source: data,
        xAxis: settingData.xAxis,
        valueAxis: settingData.valueAxis,
        colorScheme: settingData.colorScheme,
        seriesGroups: settingData.seriesGroups
    };

	//setting
	$('#'+id).jqxChart(settings);
}