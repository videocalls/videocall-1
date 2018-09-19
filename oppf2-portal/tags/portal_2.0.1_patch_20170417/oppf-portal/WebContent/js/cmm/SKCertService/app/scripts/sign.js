var params = (function () {
    'use strict';

    var Parameters = function () {
        var _plain,
            _option,
            _callback,
            _errorcallback;
        var params = this;

        params.setParameters = function (plain, option, callback, errorcallback) {
            _plain = plain;
            _option = JSON.parse(option);
            _callback = callback;
            _errorcallback = errorcallback;
        };

        params.getPlain = function () {
            return _plain;
        };

        params.getOption = function () {
            return _option;
        };

        params.getCallback = function () {
            return _callback;
        };

        params.getErrorcallback = function () {
            return _errorcallback;
        };

        params.getParameters = function () {
            return {
                plain: _plain,
                option: _option,
                callback: _callback,
                errorcallback: _errorcallback
            };
        };
    };

    return new Parameters();
})();

var cert_select_controller = (function (doc, $, vest, params, vestSign) {
    'use strict';

    if (typeof(vestSign) === 'undefined') {
        alert(signLang(42));
        //window.close();
        return false;
    }

    var _pfx;
    var _lastStorage = '';
    var _selected;
    var _valid;
    var _certificates = [];
    var _config = vestSign.getConfig();
    var _parent = vestSign;
    var _isLoad;
    var _tokenNumber;
    var _firstTrigger = "H";
    var _keySafer;
    //var _passwordCountLimit = _config.kos_var.passwordCount;
    var _passwordCountLimit = 0;
    var _menuTabIndex = 5;
    var _subMenuTabIndex;       //certListTabIndex
    var _OID = vest.util.policies;
    var _classOID = vest.util.classPolicies.makeClassPolicies(_config.kos_var.policyMode);

    var certPin = $('#passwordInput');
    var pfxPin = $('#pfxPasswordInput');
    var mainContent = $("#mainContent");
    var mainCertList = $("#mainCertList"),
        mainInfo = ((vest.browser.isMSIE() > 9) || (vest.browser.isMSIE() == false)) ? $("#mainInfo") : $("#mainInfoIE9");
    var p12View = $("#P12");
    var fileInputBtn = $('#fileInputBtn');
    var fileSelect = $('#fileInputHidden');
    var fileBackGround = $('#fileBackGround');
    var isInfoPage = $("#isInfoPage");
    var pwdText = $("#pwdText");
    
    var CERTIFICATE_STATUS = {
        VALID: '',
        WARNNING: 'warnning',
        EXPIRED: 'expired'
    };
    
    var title = {
        0: signLang(117),
        1: signLang(118),
        2: signLang(119),
        3: signLang(120),
        4: signLang(121),
        5: signLang(122),
        6: signLang(123),
        7: signLang(124)
    };
    
    var args = [];

    var caSite = {
        CrossCert: 'http://www.crosscert.com/glca/01_5_05_2.jsp',
        KICA: 'https://www.signgate.com/policy/certRule/pyCertRule.sg',
        KISA: 'http://www.rootca.or.kr/kor/accredited/accredited02.jsp',
        SignKorea: 'http://www.signkorea.com/footer/rule_idx.jsp',
        TradeSign: 'http://www.tradesign.net/cps.html',
        yessign: 'http://www.yessign.or.kr/home/subIndex/577.do'
    };
    
    var useMenu = {
        PHONE: '0',
        USB_DISK: '0',
        HARD_DISK: '0',
        SECURE_TOKEN: '0',
        SAVE_TOKEN: '0',
        CERTIFICATE_FILE: '0',
        SECURE_DISK: '0'
    };

    //HARD_DISK('H'), REMOVABLE('R'),   PKCS11_KEY('U'), SMART_CARD('S')
    var deviceViewOrder = function (order) {
        if (typeof(order) === 'undefined' || order == '' || order == 0) {
            order = 'RUSH';
        }
        for (var i = 0; i < order.length; i++) {
            switch (order[i]) {
                case 'R':       // 이동식 디스크 
                    args.push({arg1: 'usb_disk', arg2: useMenu['USB_DISK']});
                    break;

                case 'U':       // 보안 토큰
                    args.push({arg1: 'secure_token', arg2: useMenu['SECURE_TOKEN']});
                    break;

                case 'S':       // 저장 토큰
                    args.push({arg1: 'save_token', arg2: useMenu['SAVE_TOKEN']});
                    break;

                case 'H':       // 하드디스크
                    args.push({arg1: 'hard_disk', arg2: useMenu['HARD_DISK']});
                    break;
            }
        }
        args.push({arg1: 'certificate_file', arg2: useMenu['CERTIFICATE_FILE']});     // 찾아보기
        args.push({arg1: 'phone_certification', arg2: '0'});      // 휴대폰인증
        args.push({arg1: 'secure_disk', arg2: '0'});      // 안전디스크
    };

    var deviceOrder = function (order, func) {
        if (((typeof(_config.kos_var.matchedList) === 'undefined' || _config.kos_var.matchedList === "" || _config.kos_var.matchedList.length === 0) && (_config.kos_var.scanByDialogChoiceMode != 1))) {
            // no setMatched
            vest.token.getTokenList(vest.token.TYPE.ALL, function (list) {
                for (var i = 0; i < list.length; i++) {
                    var object = list[i];

                    if (object.type == "DISK DRIVE" && (typeof(object.systemDrive) !== "undefined" && object.systemDrive)) {
                        // 하드 디스크
                        useMenu['HARD_DISK'] = "1";
                    }
                    else if (object.type == "DISK DRIVE" && (typeof(object.systemDrive) !== "undefined" && !(object.systemDrive))) {
                        // 이동식 디스크
                        useMenu['USB_DISK'] = "1";
                    }
                    else if (object.type == "PKCS#11 TOKEN") {
                        // 보안토큰
                        useMenu['SECURE_TOKEN'] = "1";
                    }
                    else if (object.type == "SmartCard TOKEN") {
                        useMenu['SAVE_TOKEN'] = "1";
                    }
                    else {

                    }
                    //이런식으로 활성화 세팅.
                }

                func();

            }, function (err) {
                func();
                vestCertErrorHandler(err);
                addLoadingEvent(function () {
                    setTimeoutVestCert(function () {
                        deviceOrder(order, func);
                    })
                });
            });
        }
        else if (_config.kos_var.scanByDialogChoiceMode == 1) {
            // 옵션값.
            useMenu['PHONE'] = "1";
            useMenu['USB_DISK'] = "1";
            useMenu['HARD_DISK'] = "1";
            useMenu['SECURE_TOKEN'] = "1";
            useMenu['SAVE_TOKEN'] = "1";
            useMenu['CERTIFICATE_FILE'] = "1";
            useMenu['SECURE_DISK'] = "1";
            func();
        }
        else {
            // setMatched 
            var list = _config.kos_var.matchedList;

            for (var i = 0; i < list.length; i++) {
                var object = list[i];
                if (object.tokenType == "DISK DRIVE" && (typeof(object.systemDrive) !== 'undefined' && object.systemDrive)) {
                    // 하드 디스크
                    useMenu['HARD_DISK'] = '1';
                }
                else if (object.tokenType == "DISK DRIVE" && (typeof(object.systemDrive) !== 'undefined' && !(object.systemDrive))) {
                    // 이동식 디스크
                    useMenu['USB_DISK'] = '1';
                }
                else if (object.tokenType == "PKCS#11 TOKEN") {
                    // 보안토큰
                    useMenu['SECURE_TOKEN'] = "1";
                }
                else if (object.tokenType == "SmartCard TOKEN") {
                    useMenu['SAVE_TOKEN'] = "1";
                }
                else {

                }
            }
            func();
        }
    };

    var setFirstFocus = function (order) {
        // 로딩시 설정된 값에 해당하는 메뉴가 활성화 되어있는지 체크하고 비활성화시 다음우선순위로 포커스 수정.
        //var tmp = (order == "" ? (_config.kos_var.pViewOrderStr != "" ? _config.kos_var.pViewOrderStr : "RUSH") : order);
        var tmp = (order == "" ?  "SHUR" : order);
        //if (typeof(_config.kos_var.matchedList) === 'undefined') _firstTrigger = "H";

        for (var i = 0; i < tmp.length; i++) {
            switch (tmp[i]) {
                case 'R':       // 이동식 디스크
                    if (useMenu['USB_DISK'] == '1') {
                        _firstTrigger = "R";
                        return;
                    }
                    break;
                case 'U':       // 보안 토큰
                    if (useMenu['SECURE_TOKEN'] == '1') {
                        _firstTrigger = "U";
                        return;
                    }
                    break;
                case 'S':       // 저장 토큰
                    if (useMenu['SAVE_TOKEN'] == '1') {
                        _firstTrigger = "S";
                        return;
                    }
                    break;
                case 'H':       // 하드디스크
                    if (useMenu['HARD_DISK'] == '1') {
                        _firstTrigger = "H";
                        return;
                    }
                    break;
            }
        }
    };

    var startFocus = function () {
        // 로딩시 첫번째 포커스 설정
        switch (_firstTrigger) {
            case 'R':       // 이동식 디스크
                $("a.ico1").click();
                break;
            case 'U':       // 보안 토큰
                $("a.ico2").click();
                break;
            case 'S':       // 저장 토큰
                $("a.ico3").click();
                break;
            case 'H':       // 하드디스크
                $("a.ico4").click();
                break;
        }
    }

    var disableToken = function () {
        // 내보내기시.. 기능구현 안되있는 것들 비활성화
        useMenu['SECURE_TOKEN'] = "0";
        useMenu['CERTIFICATE_FILE'] = "0";
        useMenu['SECURE_DISK'] = "0";
    };
    /* 저장매체 리스트 부분 */
    /*    var args = [
     {arg1:'phone_certification', arg2:'0'},
     {arg1:'usb_disk', arg2:'1'},
     {arg1:'hard_disk', arg2:'1'},      
     {arg1:'secure_token', arg2:'1'},
     {arg1:'save_token', arg2:'0'},
     {arg1:'certificate_file', arg2:'1'},
     {arg1:'secure_disk', arg2:'0'}
     ];*/
    //fixArg1:매체아이디, fixArg2: 매체클래스명, fixArg3: 매체이름, fixArg4: 사용여부(1:사용,0:미사용), fixArg5: 순서
    var fixArgs = [
        //{fixArg1: 'phone_certification', fixArg2: 'ico7', fixArg3: '휴대폰인증', fixArg4: '1', fixArg5: ''},
        {fixArg1: 'hard_disk', fixArg2: 'ico4', fixArg3: signLang(35), fixArg4: '1', fixArg5: ''},
        {fixArg1: 'usb_disk', fixArg2: 'ico1', fixArg3: signLang(36), fixArg4: '1', fixArg5: ''},
        {fixArg1: 'secure_token', fixArg2: 'ico2', fixArg3: signLang(37), fixArg4: '1', fixArg5: ''},
        {fixArg1: 'save_token', fixArg2: 'ico3', fixArg3: signLang(112), fixArg4: '1', fixArg5: ''},
        {fixArg1: 'certificate_file', fixArg2: 'ico5', fixArg3: signLang(38), fixArg4: '1', fixArg5: ''},
        {fixArg1: 'secure_disk', fixArg2: 'ico6', fixArg3: signLang(39), fixArg4: '1', fixArg5: ''}
    ];

    function getError(error) {
        if (typeof error === 'string') {
            //alert(error);
            $("#outCertComment").val(error);
        }
        else if ((typeof(error.getReason) !== 'undefined') && (typeof(error.code) !== 'undefined')) {
            //var str = error.getReason().toString(),
            //    index = str.indexOf('reason');
            //alert(str.substring(index + 7) + ' [' + error.code + ']');
            var message = vest.error.getErrorMessage(error.code, _config.language);
            if(vest.error.getErrorMessage(error.code, _config.language) == 'undefined') message = error.getReason();
            
            $("#outCertComment").val(message + ' [' + error.code + ']');
        }
        else {
            //alert('Unknown Error');
            $("#outCertComment").val('Unknown Error');
        }
    };

    function issuerSplit(issuer, select) {
        var issuerLsit = issuer.split(',');
        var result;
        for (var i = 0; i < issuerLsit.length; i++) {
            if (issuerLsit[i].indexOf(select + '=') !== -1) {
                result = issuerLsit[i].split('=');
                return result[1];
            }
        }
    };

    function getCommonName(issuer) {
        return issuerSplit(issuer, 'CN');
    };

    function parseDnString(dn) {
        var temp,
            result;

        var arr = dn.split(',');
        for(var index in arr){
            var str = arr[index].trim();
            if (str.toUpperCase().indexOf('CN') !== -1) {
                temp = str;
                result = temp.split('=')[1].trim();
                break;
            }else if(str.toUpperCase().indexOf('OU') !== -1){
                temp = str;
                result = temp.split('=')[1].trim();
                break;
            }
            else {
                result = dn;
            }
        }
        return (result.length > 20) ? result.substring(0, 20) + '...' : result;
    };

    function isValid(subDay) {
        if (subDay < 0) {
            return CERTIFICATE_STATUS.EXPIRED;
        }
        else if (subDay < 30) {
            return CERTIFICATE_STATUS.WARNNING;
        }
        else {
            return CERTIFICATE_STATUS.VALID;
        }
    };

    function getField(str, token){
        var arr, i = 0, result = '', count = 0;
        arr = str.split(',');
        count = arr.length;

        if(token == '') return count;
        
        while(typeof(arr[i]) != 'undefined'){
            result = arr[i].split('=');
            if(result[0].toLowerCase() == token.toLowerCase()){
                return result[1];
            }
            i++;
        }

        return count;
    }
    
    function matchedCert(item) {
        for (var i = 0; i < (_config.kos_var.matchedList).length; i++) {
            if (item.getIdentifier().cert == _config.kos_var.matchedList[i].certIdentifier) return true;
        }
        return false;
    }
    
    function matchedUserID(item) {
        var count = getField(_config.kos_var.userID, '');
        var cn = getField(item.getSubject(), 'cn');
        var configCn = getField(_config.kos_var.userID, 'cn');
        
        if(configCn == 1) return false;
        
        if(count > 1){
            if(item.getSubject().toLowerCase().indexOf(_config.kos_var.userID.toLowerCase()) !== -1) return true;
        }else{
            if(cn.indexOf(configCn) !== -1) return true;
        }

        return false;
    }
    
    function keyNewFilter(item){
        if(_config.kos_var.keyNew == "KM_ONLY"){
            if((item.getSubject().indexOf("OU=corporation4EC") !== -1) || ((item.getSubject().indexOf("ou=corporation4EC") !== -1) )) return true;
            return false;
        }else{
            if((item.getSubject().indexOf("OU=corporation4EC") !== -1) || ((item.getSubject().indexOf("ou=corporation4EC") !== -1) )) return false;
            return true;
        }
    }
    
    function classOIDFilter(item){
        for(var i=0; i<_classOID.length; i++){
            if(_classOID[i] == item.getPolicy()){
                return true;
            }
        }
        return false;
    }

    function policyOIDFilter(item){
        if(_config.kos_var.policyOID == 0 || _config.kos_var.policyOID == '0') return true;

        var arr = _config.kos_var.policyOID.split(';');
        for(var i=0; i<arr.length; i++) {
            if(arr[i] == item.getPolicy()) {
                return true;
            }
        }
        return false
    }

    function OIDFilter(item){
        if(!(_config.kos_var.policyOID == 0 || _config.kos_var.policyOID == '0') && !(_config.kos_var.policyMode == 0 || _config.kos_var.policyMode == '0')){
            // 둘다 값이 설정되어있음
            return (classOIDFilter(item) || policyOIDFilter(item));
        }
        else if((_config.kos_var.policyOID == 0 || _config.kos_var.policyOID == '0') && !(_config.kos_var.policyMode == 0 || _config.kos_var.policyMode == '0')){
            return (classOIDFilter(item));
        }
        else if((_config.kos_var.policyOID == 0 || _config.kos_var.policyOID == '0') && !(_config.kos_var.policyMode == 0 || _config.kos_var.policyMode == '0')) {
            return (policyOIDFilter(item));
        }
        return true;
    }

    function matchedList(item) {
        var list = [];
        if(_config.kos_var.matchedList == "")
        {
            return item;
        }
        for (var i = 0; i <item.length; i++) {
            for (var j = 0; j < (_config.kos_var.matchedList).length; j++) {
                if (item[i].name == _config.kos_var.matchedList[j].tokenName) {
                    list.push(item[i]);
                    break;
                }
            }
        }
        return list;
    };

    function outCertList(args) {
        var _count = 1;

        $('#outCertList').empty();
        $("#outCertComment").val("");
        
        $.each(args, function (index, item) {
            if (!(_config.kos_var.matchedList.length == 0 || typeof(_config.kos_var.matchedList) === 'undefined')) {
                if (!matchedCert(item)) return;
            }

            if(!(_config.kos_var.userID == '')){
                if (!(matchedUserID(item))) return;
            }
            
            if(!(_config.kos_var.keyNew == '')){
                if(!(keyNewFilter(item))) return;
            }

            var caInfo = _OID[item.getPolicy()];
            if (typeof(_OID[item.getPolicy()]) === 'undefined')
                caInfo = {caName: "test", usage: signLang(43)};

            var pClass = "";

            if ((isValid(item.getAvailableDays()) === CERTIFICATE_STATUS.VALID))
                pClass = "m_bullet";
            else if ((isValid(item.getAvailableDays()) === CERTIFICATE_STATUS.EXPIRED))
                pClass = "m_bullet_exp";
            else if ((isValid(item.getAvailableDays()) === CERTIFICATE_STATUS.WARNNING))
                pClass = "m_bullet_time";

            $(document.createElement('li'))
                .append($(document.createElement('input'))
                    .attr({type: 'hidden'})
            )
                .append($(document.createElement('ul'))
                    .addClass('list_list clfix')
                    //.attr("onClick", "outCertComment('"+item.getIdentifier().cert+"', '"+ item.getSubject() +"','"+pClass+"')")
                    .on("click", function () {
                        outCertComment(item.getIdentifier(), item.getSubject(), pClass);
                        if (!(typeof(item.tokenNumber) === 'undefined')) _tokenNumber = item.tokenNumber;
                    })
                    .append($(document.createElement('li'))
                        .append($(document.createElement('p'))
                            .on("keydown", function(key) {
                                keyEvent(key, function() {
                                    outCertComment(item.getIdentifier(), item.getSubject(), pClass);
                                    if (!(typeof(item.tokenNumber) === 'undefined')) _tokenNumber = item.tokenNumber;
                                });
                            })
                            .attr({"tabindex": _subMenuTabIndex + _count})
                            .addClass(pClass)
                            .append(parseDnString(item.getSubject()))
                    )
                )
                    .append($(document.createElement('li'))
                        .append(item.getValidityTo('YYYY.MM.DD'))
                )
                    .append($(document.createElement('li'))
                        .append(caInfo ? caInfo.usage : '')
                )
                    .append($(document.createElement('li'))
                        .append(getCommonName(item.getIssuer()))
                )
            )
                .appendTo($('#outCertList')).appendTo($('#outCertList'));
            _count++;
        });

        $('#outCertList li:nth-child(odd) ul').attr("class", "list_list_ clfix");
        //if (!($("#outCertList li ul").length == 0 || $("#outCertList li ul") == [])) $("#outCertList li ul")[0].click();
        if (!($("#outCertList li ul").length == 0 || $("#outCertList li ul") == [])){
            if(_config.kos_var.webAccess) {
                $("#outCertList li ul")[0].click();
                $("#outCertList li ul li p")[0].focus();
                webAccess.certListFocus();
                webAccess.firstFocus();
            }
            else{
                $("#outCertList li ul")[0].click();
            }
        }
    };

    function detailCertificate(certificate, certIdentifier) {
        vest.token.getCertificate(_tokenNumber, certIdentifier, function (_certificate) {
            (certificate)(_certificate);
        }, vestCertErrorHandler);
    };

    function CAdetailCertificate(type, callback) {
        vest.token.getCACertificate(_tokenNumber, _selected.cert, type, function (_certificate) {
            if (typeof(_certificate) === 'undefined') {
                return;
            } else {
                callback(_certificate);
            }
        }, vestCertErrorHandler);
    };

    function detailCertTabView(certificate) {
        var args = [
            {arg1: signLang(44), arg2: certificate.getSubject()}
            , {arg1: signLang(45), arg2: certificate.getVersion()}
            //, {arg1: '일련번호', arg2: certificate.getSerialNumber()}
            , {
                arg1: signLang(46),
                arg2: (certificate.getSerialNumber().length > 8) ? certificate.getSerialNumber() : parseInt(certificate.getSerialNumber(), 16)
            }
            , {arg1: signLang(47), arg2: certificate.getIssuer()}
            , {arg1: signLang(48), arg2: certificate.getValidityFrom('YYYY-MM-DD HH:mm:ss')}
            , {arg1: signLang(49), arg2: certificate.getValidityTo('YYYY-MM-DD HH:mm:ss')}
            , {arg1: signLang(50), arg2: certificate.getSignatureAlgorithm()}
            , {arg1: signLang(51), arg2: certificate.getPublickeyAlgorithm()}
            , {arg1: signLang(52), arg2: certificate.getPublickey()}
            , {arg1: signLang(53), arg2: certificate.getSignatureValue()}
            , {arg1: signLang(54), arg2: certificate.getKeyUsage()}
            , {arg1: signLang(55), arg2: certificate.getIssuerKeyIdentifier()}
            , {arg1: signLang(56), arg2: certificate.getSubjectKeyIdentifier()}
            , {arg1: signLang(57), arg2: certificate.getExtKeyUsage()}
            , {arg1: signLang(58), arg2: certificate.getRealName()}
            , {arg1: signLang(59), arg2: certificate.getBasicConstranints()}
            , {arg1: signLang(60), arg2: certificate.getPolicy()}
            , {arg1: signLang(61), arg2: certificate.getCrlDistributionPoint()}
            , {arg1: signLang(62), arg2: certificate.getAuthorityInfoAccess()}
            , {arg1: signLang(63), arg2: certificate.getSaveCert()}
        ];
        return args;
    };

    function detailOpenCert() {
        var detailSignFlg = false,
            detailKeyFlg = false,
            detailCAFlg = false,
            detailRootFlg = false;

        var tabCheck = 1;
        if (_selected.cert == _selected.kmCert)
            tabCheck = 0;

        // 0: 안보이게, 1: 보이게.
        var arglist0 = [
            {arg1: signLang(64), arg2: 'dialogCertTab_tab_li1', arg3: '1'}
            , {arg1: signLang(65), arg2: 'dialogCertTab_tab_li2', arg3: tabCheck}
            , {arg1: signLang(66), arg2: 'dialogCertTab_tab_li3', arg3: '1'}
            , {arg1: signLang(67), arg2: 'dialogCertTab_tab_li4', arg3: '1'}
        ];

        $("#dialogCertTab_tab_ul").empty();
        $("#dialogCertTab_text").val("");

        $.each(arglist0, function (index, item) {

            if (item.arg3 != "1") return;

            $(document.createElement('li'))
                .attr("id", item.arg2)
                .append($(document.createElement('a'))
                    .attr({"href": "javascript:;"})
                    .append(item.arg1)
            )
                .appendTo($("#dialogCertTab_tab_ul"));
        });

        $("#dialogCertTab_tab_ul li").unbind("click");
        $("#dialogCertTab_tab_ul li").click(function () {

            $("#dialogCertTab_attrList").empty();
            $("#dialogCertTab_text").val("");

            var _certificate;

            if (this.id == "") return;
            detailCertBtn(this.id);

            for (var i = 0; i < $("#dialogCertTab_tab_ul li").length; i++) {
                $("#dialogCertTab_tab_ul li:eq(" + i + ")").removeClass("CERT_on");
                $("#dialogCertTab_tab_ul li:eq(" + i + ") a").removeClass("CERT_on");
            }

            $("#dialogCertTab_tab_ul li:eq(" + $(this).index() + ")").attr("class", "CERT_on");
            $("#dialogCertTab_tab_ul li:eq(" + $(this).index() + ") a").attr("class", "CERT_on");

            switch (this.id) {
                case "dialogCertTab_tab_li1":
                    detailCertificate(function (certificate) {
                        _certificate = certificate;
                        if (!detailSignFlg) {
                            detailMsg(certificate.getUserNotice());
                            detailSignFlg = true;
                        }
                        detailCertTabList(detailCertTabView(certificate));
                    }, _selected.cert);
                    break;
                case "dialogCertTab_tab_li2":
                    detailCertificate(function (certificate) {
                        _certificate = certificate;
                        if (!detailKeyFlg) {
                            detailMsg(certificate.getUserNotice());
                            detailKeyFlg = true;
                        }
                        detailCertTabList(detailCertTabView(certificate));
                    }, _selected.kmCert);
                    break;
                case "dialogCertTab_tab_li3":
                    CAdetailCertificate('ca', function (certificate) {
                        if (typeof(certificate) === 'undefined') {
                            return;
                        }
                        _certificate = certificate;
                        if (!detailCAFlg) {
                            detailMsg(certificate.getUserNotice());
                            detailCAFlg = true;
                        }
                        detailCertTabList(detailCertTabView(certificate));
                    });
                    break;
                case "dialogCertTab_tab_li4":
                    CAdetailCertificate('root', function (certificate) {
                        if (typeof(certificate) === 'undefined') {
                            return;
                        }
                        _certificate = certificate;
                        if (!detailRootFlg) {
                            detailMsg(certificate.getUserNotice());
                            detailRootFlg = true;
                        }
                        detailCertTabList(detailCertTabView(certificate));
                    });
                    break;
            }

            $("#certAdd_btn").unbind("click");
            $("#certAdd_btn").click(function () {
                var _url = '';
                if(_certificate.getIssuer().toLowerCase().indexOf('crosscert') !== -1){
                    _url = caSite['CrossCert'];
                }
                else if(_certificate.getIssuer().toLowerCase().indexOf('kica') !== -1 || _certificate.getIssuer().toLowerCase().indexOf('signgate') !== -1){
                    _url = caSite['KICA'];
                }
                else if(_certificate.getIssuer().toLowerCase().indexOf('kisa') !== -1){
                    _url = caSite['KISA'];
                }
                else if(_certificate.getIssuer().toLowerCase().indexOf('signkorea') !== -1){
                    _url = caSite['SignKorea'];
                }
                else if(_certificate.getIssuer().toLowerCase().indexOf('tradesign') !== -1){
                    _url = caSite['TradeSign'];
                }
                else if(_certificate.getIssuer().toLowerCase().indexOf('yessign') !== -1){
                    _url = caSite['yessign'];
                }
                else {
                    _url = 'undefined';
                }
                window.open(_url);
            });

            $("#certVerify_btn").unbind("click");
            $("#certVerify_btn").click(function () {
                validateCertificate(function (result) {
                    $("#dialogCertVid").dialog({
                        autoOpen: true,
                        modal: true,
                        resizable: false,
                        open: function (type, data) {
                            $("#dialogCertVid_text").text(validateMsg(result, _certificate));

                            $(".ui-dialog-titlebar", $(this).parent()).remove();
                            $("#dialogCertVid").dialog({width: "auto", height: "auto"});
                            $("#dialogCertVid").css('overflow', 'hidden');
                        }
                    });
                })
            });

            $("#certHash_btn").unbind("click");
            $("#certHash_btn").click(function () {
                alert(signLang(68));
            });

        });

        detailCertView();
    }

    function detailOpenPFX() {
        var detailSignFlg = false;

        // 0: 안보이게, 1: 보이게.
        var arglist0 = [
            {arg1: signLang(64), arg2: 'dialogCertTab_tab_li1', arg3: '1'}
            , {arg1: signLang(65), arg2: 'dialogCertTab_tab_li2', arg3: '0'}
            , {arg1: signLang(66), arg2: 'dialogCertTab_tab_li3', arg3: '0'}
            , {arg1: signLang(67), arg2: 'dialogCertTab_tab_li4', arg3: '0'}
        ];

        $("#dialogCertTab_tab_ul").empty();
        $("#dialogCertTab_text").val("");

        $.each(arglist0, function (index, item) {

            if (item.arg3 != "1") return;

            $(document.createElement('li'))
                .attr("id", item.arg2)
                .append($(document.createElement('a'))
                    .attr({"href": "javascript:;"})
                    .append(item.arg1)
            )
                .appendTo($("#dialogCertTab_tab_ul"));
        });

        $("#dialogCertTab_tab_ul li").unbind("click");
        $("#dialogCertTab_tab_ul li").click(function () {

            $("#dialogCertTab_attrList").empty();
            $("#dialogCertTab_text").val("");

            var _certificate;

            if (this.id == "") return;
            detailCertBtn(this.id);

            for (var i = 0; i < $("#dialogCertTab_tab_ul li").length; i++) {
                $("#dialogCertTab_tab_ul li:eq(" + i + ")").removeClass("CERT_on");
                $("#dialogCertTab_tab_ul li:eq(" + i + ") a").removeClass("CERT_on");
            }

            $("#dialogCertTab_tab_ul li:eq(" + $(this).index() + ")").attr("class", "CERT_on");
            $("#dialogCertTab_tab_ul li:eq(" + $(this).index() + ") a").attr("class", "CERT_on");

            switch (this.id) {
                case "dialogCertTab_tab_li1":
                    _certificate = _certificates[0];
                    if (!detailSignFlg) {
                        detailMsg(_certificate.getUserNotice());
                        detailSignFlg = true;
                    }
                    detailCertTabList(detailCertTabView(_certificate));
                    break;
            }

            $("#certAdd_btn").unbind("click");
            $("#certAdd_btn").click(function () {
                var _url = '';
                if(_certificate.getIssuer().toLowerCase().indexOf('crosscert') !== -1){
                    _url = caSite['CrossCert'];
                }
                else if(_certificate.getIssuer().toLowerCase().indexOf('kica') !== -1 || _certificate.getIssuer().toLowerCase().indexOf('signgate') !== -1){
                    _url = caSite['KICA'];
                }
                else if(_certificate.getIssuer().toLowerCase().indexOf('kisa') !== -1){
                    _url = caSite['KISA'];
                }
                else if(_certificate.getIssuer().toLowerCase().indexOf('signkorea') !== -1){
                    _url = caSite['SignKorea'];
                }
                else if(_certificate.getIssuer().toLowerCase().indexOf('tradesign') !== -1){
                    _url = caSite['TradeSign'];
                }
                else if(_certificate.getIssuer().toLowerCase().indexOf('yessign') !== -1){
                    _url = caSite['yessign'];
                }
                else {
                    _url = 'undefined';
                }
                window.open(_url);
            });

            $("#certVerify_btn").unbind("click");
            $("#certVerify_btn").click(function () {
                validateCertificate(function (result) {
                    $("#dialogCertVid").dialog({
                        autoOpen: true,
                        modal: true,
                        resizable: false,
                        open: function (type, data) {
                            $("#dialogCertVid_text").text(validateMsg(result, _certificate));

                            $(".ui-dialog-titlebar", $(this).parent()).remove();
                            $("#dialogCertVid").dialog({width: "auto", height: "auto"});
                            $("#dialogCertVid").css('overflow', 'hidden');
                        }
                    });
                })
            });

            $("#certHash_btn").unbind("click");
            $("#certHash_btn").click(function () {
                alert(signLang(68));
            });

        });

        detailCertView();
    }

    function detailMsg(msg) {
        $("#dialogCertMsg").dialog({
            autoOpen: true,
            modal: true,
            resizable: false,

            open: function (type, data) {
                $("#dialogCertMsg_text").text(msg);

                $(".ui-dialog-titlebar", $(this).parent()).remove();
                $("#dialogCertMsg").dialog({width: "auto", height: "auto"});
                $("#dialogCertMsg").css('overflow', 'hidden');
            }
        });

        $("#dialogCertMsg_xBtn").unbind("click");
        $("#dialogCertMsg_xBtn").click(function () {
            $("#dialogCertMsg").dialog("close");
        });

        $("#dialogCertMsg_confirmBtn").unbind("click");
        $("#dialogCertMsg_confirmBtn").click(function () {
            $("#dialogCertMsg").dialog("close");
        });
    };

    function detailCertView() {
        $("#dialogCertTab").dialog({
            autoOpen: true,
            //height: 210,
            //width: 368,
            scroll: "no",
            modal: true,
            resizable: false,
            button: {
                cancel: function () {
                    dialog.dialog("close");
                }
            },
            //closeOnExcape: false,
            close: function () {
                //alert("aa");
            },
            open: function (type, data) {
                $(".ui-dialog-titlebar", $(this).parent()).remove();

                $("#dialogCertTab").dialog({width: "auto", height: "auto"});
                $("#dialogCertTab").css('overflow', 'hidden');

                $("#dialogCertTab_tab_li1").trigger("click");
            },
            resizeStop: function () {
                $(this).dialog({height: "auto"});
            }
        });

        $("dialogCertMsg_xBtn").click(function () {
            $("#dialogCertMsg").dialog("close");
        });
    }

    function detailCertBtn(thisId) {
        $("#dialogCertTab_tab_subBtn_div").empty();

        if (thisId == "dialogCertTab_tab_li4") {
            $(document.createElement('p'))
                .attr("class", "CERT_btnBg_sbtn")
                .append($(document.createElement('a'))
                    .attr({"href": "#", "id": "certHash_btn"})
                    .append(signLang(69))
            )
                .appendTo($("#dialogCertTab_tab_subBtn_div"));
        }

        if (thisId == "dialogCertTab_tab_li2" || thisId == "dialogCertTab_tab_li1" || thisId == "dialogCertTab_tab_li3") {
            $(document.createElement('p'))
                .attr("class", "CERT_btnBg_sbtn4")
                .append($(document.createElement('a'))
                    .attr({"href": "#", "id": "certAdd_btn"})
                    .append(signLang(70))
            )
                .appendTo($("#dialogCertTab_tab_subBtn_div"));
        }

        $(document.createElement('p'))
            .attr("class", "CERT_btnBg_sbtn")
            .append($(document.createElement('a'))
                .attr({"href": "#", "id": "certVerify_btn"})
                .append(signLang(71))
        )
            .appendTo($("#dialogCertTab_tab_subBtn_div"));
    }

    function detailCertTabList(args) {
        $("#dialogCertTab_attrList").empty();
        if (args == [] || typeof(args) === 'undefined' || args == "") return;

        //다이얼로그 출력
        $.each(args, function (index, item) {
            $(document.createElement('li'))
                .addClass('clfix')
                .append($(document.createElement('ul'))
                    .addClass('CERT_list_list clfix')
                    .append($(document.createElement('li'))
                        .append($(document.createElement('p'))
                            .addClass("CERT_bullet")
                            .attr("title", item.arg1)
                            .append(item.arg1)
                    )
                )
                    .append($(document.createElement('li'))
                        .append(item.arg2)
                )
            )
                .appendTo($('#dialogCertTab_attrList'));
        });
        for (var i = 0; i < (6 - args.length); i++) {
            $(document.createElement('li'))
                .addClass('clfix')
                .append($(document.createElement('ul'))
                    .addClass('CERT_list_list clfix')
                    .append($(document.createElement('li'))
                        .append($(document.createElement('p'))
                            .addClass("CERT_bullet")
                        //.append(item.arg1)
                    )
                )
                    .append($(document.createElement('li'))
                    //.append(item.arg2)
                )
            )
                .appendTo($('#dialogCertTab_attrList'));
        }
        $('#dialogCertTab_attrList li:nth-child(odd) ul').attr("class", "CERT_list_list_ clfix");
    }

    function validateCertificate(callback) {
        vest.token.validateCertificate(_tokenNumber, _selected, undefined, callback, callback);
    }

    function validateMsg(result, certificate) {
        var msg = "";

        msg += signLang(72);
        msg += signLang(73) + certificate.getIssuer() + "\n";
        msg += signLang(74) + certificate.getSubject() + "\n";
        msg += signLang(75) + certificate.getValidityFrom('YYYY-MM-DD') + "\n";
        msg += signLang(76) + certificate.getValidityTo('YYYY-MM-DD') + "\n";
        msg += signLang(77) + (result.validCode == 999 ? signLang(78) : signLang(79)) + "\n";
        if(result.resultCode == 0){
            msg += signLang(80) + ": " + (result.validCode == 999 ? signLang(81) : result.validCode) + "\n";
            msg += signLang(82) + ": " + (result.validCode == 999 ? signLang(81) : result.validCode) + "\n";
        }
        else{
            msg += signLang(80) + ": " + result.code + "\n";
            msg += signLang(82) + ": " + vest.error.getErrorMessage(result.code, _config.language) + "\n";
        }
        
        msg += signLang(83) + "\n";
        if (result.validCode == 11) {
            msg += signLang(84);
        }

        return msg;
    }

    function bulletTimeCheck(callback) {
        // 웹전근성으로 인해 alt 속성 값 추가.
        $("#dialogCertWarnnig_clostBtn").attr('alt', signLang(33));

        vest.token.getCertificate(_tokenNumber, _selected.cert, function (certificate) {
            $("#warnnigTo").empty();
            $("#warnnigTo").append(certificate.getValidityTo('YYYY.MM.DD'));
        });

        $("#dialogCertWarnnig").dialog({
            autoOpen: true,
            scroll: "no",
            modal: true,
            resizable: false,
            button: {
                cancel: function () {
                    dialog.dialog("close");
                }
            },
            close: function () {
            },
            open: function (type, data) {
                $(".ui-dialog-titlebar", $(this).parent()).remove();

                $("#dialogCertWarnnig").dialog({width: "auto", height: "auto"});
                $("#dialogCertWarnnig").css('overflow', 'hidden');
            },
            resizeStop: function () {
                $(this).dialog({height: "auto"});
            }
        });

        //$(document).on("click", "#cancel_exp", function () {
        //    $("#dialogCertWarnnig").dialog("close");
        //});

        $("#now_update").unbind("click");
        $("#now_update").click(function () {
            $("#dialogCertWarnnig").dialog("close");

            parent.parent.parent.window.location.href = _config.kos_var.pCertNewUrl;
            //window.open(_config.kos_var.pCertNewUrl);
        });

        $("#after_update").unbind("click");
        $('#after_update').keydown(function (key) {
            keyEvent(key, function() {
                $("#dialogCertWarnnig").dialog("close");
                callback(true);
            });
        });
        $("#after_update").click(function () {
            $("#dialogCertWarnnig").dialog("close");
            callback(true);
        });

        $(document).on("click", "#dialogCertWarnnig_clostBtn", function () {
            _config.kos_var.flag = 1;

            vest.util.cookie.removeCookie('expireCheck');
            vest.util.cookie.setCookie('expireCheck', _config.kos_var.flag);

            $("#dialogCertWarnnig").dialog("close");
        });

        $("#x_warnnig").click(function () {
            $("#dialogCertWarnnig").dialog("close");
        });

        $(".CERT_txt1301").focus();
    };

    // ui show/hide
    function removeLoadingEvnet() {
        //hideCertificateList();
        //loading.show();
        removeTokenEvent();
        $('#submit_btn').unbind('click');
    }

    function addLoadingEvent(func) {
        if (typeof func === 'function') {
            func();
        }
        //loading.hide();
        addTokenEvent();
        addOkayClickEvent(function () {
            okButtonEvent();
        });
    }

    function showIsInfoPage() {
        hideP12View();
        hideCertListUI();
        hideMainInfo();
        isInfoPage.show();
    }

    function hideIsInfoPage() {
        isInfoPage.hide();
    }

    function showMainInfo() {
        hideP12View();
        hideCertListUI();
        hideIsInfoPage();
        hideMainContent();
        mainInfo.show();
    }

    function hideMainInfo() {
        mainInfo.hide();
    }

    function showP12Password() {
        certPin.hide();
        pfxPin.show();
    };

    function hideP12Password(){
        pfxPin.hide();
        certPin.show();
    };

    function disableP12Password() {
        pfxPin.attr("readonly",true);
        pfxPin.attr("disabled",true);
    }

    function enableP12Password() {
        pfxPin.val('');
        pfxPin.attr("readonly",false);
        pfxPin.attr("disabled",false);
    }

    function showP12View() {
        hideCertListUI();
        hideMainInfo();
        hideIsInfoPage();
        hideFileBackGround();
        showMainContent();
        showP12Password();
        enableP12Password();
        p12View.show();
    };

    function hideP12View() {
        hideP12Password();
        hideMainContent();
        p12View.hide();
    };

    function showFileBackGround() {
        fileBackGround.show();
    }

    function hideFileBackGround() {
        fileBackGround.hide();
    }

    function showMainContent() {
        mainContent.show();
    }

    function hideMainContent() {
        mainContent.hide();
    }

    function showCertListUI() {
        hideP12View();
        hideP12Password();
        hideMainInfo();
        hideIsInfoPage();
        showMainContent();
        mainCertList.show();
    }

    function hideCertListUI() {
        hideMainContent();
        mainCertList.hide();
    }

    // ui click event
    function setPwdText(str) {
        pwdText.text(str);
    }
    function keyEvent(key, func) {
        //if (key.keyCode === 13 || key.keyCode === 31) {
        if (key.keyCode === 13) {
            func();
        }
    };

    function addOkayClickEvent(func) {
        $('#submit_btn').unbind('click').unbind('keydown');
        $('#submit_btn').click(function () {
            func();
        });
        $('#submit_btn').keydown(function (key) {
            keyEvent(key, func);
        });
//        return _selected;
//        $("#passwordInput").unbind("keydown");
//        $("#passwordInput").keydown(function (key) {
//            keyEvent(key, function () {
//                $("#submit_btn").click();
//                key.preventDefault();
//            });
//        });
    };

    //$('#passwordInput').keydown(function (key) {
    //    if (key.keyCode == 13)   // 입력된 key가 Enter라면
    //        $('#submit_btn').click();
    //});

    $('#cancel_btn').click(function () {
        signClose();
    });

    $('#x_btn').click(function () {
        signClose();
    });

    function signClose() {
        //_parent.close();
        try {
            _parent.getParameters();
        } catch (e) {
            getError(signLang(88));
            _parent.close();
            return;
        }
        var param = params.getParameters();
        var error = {
            code: 12025,
            message: signLang(116)
        };
        param.errorcallback(error);
        _parent.close();
    }

    $(document).on("click", "#dialogCertTab_xBtn, #dialogCertTab_confirmBtn, #dialogCertTab_cancelBtn", function () {
        $("#dialogCertTab").dialog("close");
    });

    $('#certDetail_btn').click(function () {
        if (_selected == null) {
            getError(signLang(40));
            return;
        }

        //detailOpenCert();
        if(typeof(_selected) === 'string') detailOpenPFX();
        else detailOpenCert();

    });

    $('.CERT_list_list').click(function () {
        $('.CERT_detailbox').text(this.children[1].childNodes[0].nodeValue);
    });

    $("#vestCertSetup").click(function () {
        parent.window.location.href = _config.SKCertServiceDownload;
    });
    $("#vestCertSetup1").click(function () {
        parent.window.location.href = _config.SKCertServiceDownload;
    });
    $("#refresh").click(function () {
        parent.window.location.href = "Santiago://";
    });
    $("#refresh1").click(function () {
        parent.window.location.href = "Santiago://";
    });
    $("#certInstall").click(function () {
        parent.window.open("../help/certInstall.html");
    });
    $("#certInstall1").click(function () {
        parent.window.open("../help/certInstall.html");
    });

    // 인증서 선택했을때
    function outCertComment(selectedInfo, arg, valid) {
        //인증서 선택했을때 이벤트
        _selected = selectedInfo;
        _valid = valid;
        $("#outCertComment").val(arg);

        if (_config.ablePwd && !(pfxPin.prop('readonly') && pfxPin.prop('disabled'))) {
            certPin.val('');
            certPin.focus();
            if(typeof(_keySafer.clearPassword) !== 'undefined') _keySafer.clearPassword();
        }
        else {
            $("#submit_btn").focus();
        }
    };

    // vestCert 관련...
    var okButtonEvent = function (bulletFlg) {

        var bullet = function(pwd){
            if (_config.kos_var.flag == 0 && _valid == "m_bullet_time" && !(_config.kos_var.isBullet) && !(vest.util.cookie.getCookie("expireCheck") == 1)) {
                bulletTimeCheck(function(){
                    event(pwd);
                });
                return;
            }

            event(pwd);
        };

        var event = function(pwd) {
            try {
                _parent.getParameters();
            } catch (e) {
                getError(signLang(88));
                _parent.close();
                return;
            }

            var param = params.getParameters();

            _config.kos_var.matchedTokenNumber = _tokenNumber;
            _config.kos_var.matchedIdentifier = _selected;
            _config.kos_var.matchedList = [];

            var _option = param.option;

            //if (!(certPin[0].getAttribute("disabled") === "disabled") && _config.verifyPin) {
            if (_config.verifyPin) {
                if (_passwordCountLimit == 0) {
                    vest.token.verifyPin(_tokenNumber, _selected, pwd, function () {
                        param.callback(_tokenNumber, _selected, pwd, _option);
                    }, param.errorcallback);
                }
                else {
                    vest.token.verifyPin(_tokenNumber, _selected, pwd, function () {
                        param.callback(_tokenNumber, _selected, pwd, _option);
                    }, vestCertErrorHandler);
                    _passwordCountLimit--;
                }
            }
            else {
                param.callback(_tokenNumber, _selected, pwd, _option);
            }
        };
        
        //var password = certPin.val();
        if (_selected == null) {
            getError(signLang(40));
            return;
        }

        if (!(certPin[0].getAttribute("disabled") === "disabled")) {
            if (certPin.val().length == 0) {
                getError(signLang(41));
                return;
            }
        };


        if(_config.ablePwd){
            if (!_keySafer.initalizeCheck() || _keySafer.getType() == 'undefined') {
                bullet(certPin.val());
            }
            else {
                _keySafer.getPassword(function(password){
                    bullet(password);
                });
            }
        }
        else{
            bullet();
        }
    };

    var vestCertErrorHandler = function (error, callback) {
        switch (error.code) {
            case vest.token.ERROR.NOT_LOAD:
                if (_config.kos_var.isInfoPage) {
                    showMainInfo();
                }
                else {
                    showIsInfoPage();
                }
                break;
            case vest.token.ERROR.LOST_CONNECTION:
                if (_config.kos_var.isInfoPage) {
                    showMainInfo();
                } else {
                    showIsInfoPage();
                }
                break;
            case 12020 :
                if (typeof(callback) == "function" && _config.kos_var.count == 1) {
                    _config.kos_var.errorCode = "2417";
                    //_config.kos_var.errorMessage = error.reason;
                    _config.kos_var.errorMessage = vest.error.getErrorMessage(error.code, _config.language);
                    callback(error);
                    //_parent.close();
                } else {
                    getError(signLang(97) + ' [' + error.code + ']');
                }
                break;
            case 12021 :
                getError(signLang(95) + "\n" + signLang(96) + ' [' + error.code + ']');
                break;
            case 12039 :
                if (typeof(callback) == "function" && _config.kos_var.count == 1) {
                    _config.kos_var.errorCode = "2417";
                    //_config.kos_var.errorMessage = error.reason;
                    _config.kos_var.errorMessage = vest.error.getErrorMessage(error.code, _config.language);
                    callback(error);
                    //_parent.close();
                }
                else {
                    getError(signLang(97) + ' [' + error.code + ']');
                }
                break;
            case 12040 :
                getError(signLang(97) + ' [' + error.code + ']');
                break;
            case 12045 :
                getError(signLang(98) + "\n" + signLang(99) + ' [' + error.code + ']');
                break;
            case 12046 :
                getError(signLang(98) + "\n" + signLang(99) + ' [' + error.code + ']');
                break;
            case 12026 :
                getError(signLang(97) + ' [' + error.code + ']');
                break;
            case 12030 :
                getError(signLang(100) + "\n" + signLang(101) + ' [' + error.code + ']');
                break;
            case 11002 :
                getError(signLang(100) + "\n" + signLang(101) + ' [' + error.code + ']');
                break;
            case 11003 :
                getError(signLang(102));
//                window.open(_config.infovine.url, '인포바인', 'menubar=no');
                break;
            case 11004 :
                getError(signLang(103));
//                window.open(_config.infovine.url, '인포바인', 'menubar=no');
                break;
            case 12027 :
                break;
            default:
                getError(error);
        }
        addLoadingEvent();
    };

    var getCertificateListFromVestCert = function (tokenNumber) {
        var options = {};
        options.pkitype = _config.useGPKI;

        removeLoadingEvnet();
        showCertListUI();

        vest.token.getCertificates(tokenNumber, _config.kos_var.policyMode, _config.kos_var.policyOID, options, function (certificates) {
            outCertList(certificates);

            _tokenNumber = tokenNumber;
            addOkayClickEvent(function () {
                okButtonEvent();
            });

            addLoadingEvent();
        }, vestCertErrorHandler);
    };

    var getCertificateListWithTokenListFromVestCert = function (tokenList) {
        removeLoadingEvnet();
        showCertListUI();

        var certList = [];

        function getCertitificates(tokenList, certList) {
            var options = {};
            options.pkitype = _config.useGPKI;

            if (tokenList.length == 0) {
                _tokenNumber = undefined;

                outCertList(certList);
                addOkayClickEvent(function () {
                    okButtonEvent();
                });

                addLoadingEvent();
                return;
            }

            var tokenNumber = tokenList.shift().tokenIdentifier;
            vest.token.getCertificates(tokenNumber, _config.kos_var.policyMode, _config.kos_var.policyOID, options, function (certificates) {
                for (var i = 0; i < certificates.length; i++) {
                    certificates[i].tokenNumber = tokenNumber;
                }
                certList = certList.concat(certificates);
                getCertitificates(tokenList, certList);
            }, vestCertErrorHandler);
        }

        getCertitificates(tokenList, certList);
    };

    var getTokenListFromVestCert = function (type) {
        removeLoadingEvnet();
        $("#outCertComment").val('');
        certPin.val('');

        vest.token.getTokenList(type, function (list) {
            if (list.length === 0) {
                if (type === vest.token.TYPE.SYSTEM) {
                    getError(signLang(104));
                }
                if (type === vest.token.TYPE.LOCALDISK) {
                    getError(signLang(105));
                }
                if (type === vest.token.TYPE.TOKEN) {
                    getError(signLang(106));
                }
                if (type === vest.token.TYPE.SAVETOKEN) {
                    getError(signLang(107));
                }
                addLoadingEvent();
            }
            else if (list.length === 1) {
                if (type === vest.token.TYPE.UBIKEY) {
                    vest.token.setTokenOptions(list[0].tokenIdentifier, _config.infovine, function () {
                        getCertificateListFromVestCert(list[0].tokenIdentifier);
                    }, vestCertErrorHandler);
                }
                else {
                    getCertificateListFromVestCert(list[0].tokenIdentifier);
                }
            }
            else {  // list.length <= 2
                if (type === vest.token.TYPE.SYSTEM) {
                    getCertificateListWithTokenListFromVestCert(list);
                }
                else if (type === vest.token.TYPE.LOCALDISK) {
                    getCertificateListWithTokenListFromVestCert(list);
                }
                else if (type === vest.token.TYPE.TOKEN) {
                    list = matchedList(list);
                    if(list.length === 1){
                        getCertificateListFromVestCert(list[0].tokenIdentifier);
                    }
                    else if(list.length > 1){
                        dOpen(list);

                        $('#dialog_list li a').unbind('click');
                        $("#dialog_list li a").click(function () {
                            getCertificateListFromVestCert($(this).attr("tokenIdentifier"));
                        });
                    }
                    else {
                    }
                }
                else if (type === vest.token.TYPE.SAVETOKEN) {
                    list = matchedList(list);
                    if(list.length === 1){
                        getCertificateListFromVestCert(list[0].tokenIdentifier);
                    }
                    else if(list.length > 1){
                        dOpen(list);

                        $('#dialog_list li a').unbind('click');
                        $("#dialog_list li a").click(function () {
                            getCertificateListFromVestCert($(this).attr("tokenIdentifier"));
                        });
                    }
                    else {
                    }
                }
            }
            addLoadingEvent();
        }, vestCertErrorHandler);
    };

    var vestCertVersion = function (func) {
        vest.token.getVersion(function (versions) {
                if (vest.util.certVersion.availableVersion(vest.util.certVersion.getVersion('VestCert', versions), _config.version.SKCertService)
                    && vest.util.certVersion.availableVersion(vest.util.certVersion.getVersion('VestCertSecurityService', versions), _config.version.SantiagoSecurityService)) {
                    func();
                }
                else {
                    vest.token.unload();
                    addLoadingEvent(function () {
                        showMainInfo();
                        setTimeoutVestCert(func);
                    });
                }
            }, function (err) {
                vest.token.unload();
                vestCertErrorHandler(err);
                addLoadingEvent(function () {
                    //showMainInfo();
                    setTimeoutVestCert(func)
                });
            }
        );
    };

    var getVestCertVersion = function (callback) {
        vest.token.getVersion(function (version) {
            callback(vest.util.certVersion.getVersion('VestCert', version));
        }, vestCertErrorHandler);
    };

    var setTimeoutVestCert = function (func) {
        if (_isLoad) {
            clearTimeout(_isLoad);
        }

        _isLoad = setTimeout(function () {
            vestCertVersion(func);
        }, 5000);
    };

    //certificateFile
    var PFXokButtonEvent = function (certificate) {
        var password = pfxPin.val();
        var event = function() {
            try {
                _parent.getParameters();
            } catch (e) {
                getError(signLang(88));
                _parent.close();
                return;
            }

            var param = params.getParameters();

            _config.kos_var.matchedList = [];

            param.callback(certificate, certificate, password, param.option);
            //try{
            //    certificate.makeSignature(param.plain, password, param.option, function (result) {
            //        param.callback(certificate, result);
            //        //param.callback(_tokenNumber, _selected, pwd, _option);
            //    });
            //}catch(e){
            //    var error = {
            //        code: vest.error.errorCode.ScriptError_PFX_MAKESIGNATURE_ERORR
            //    };
            //    param.errorcallback(error);
            //}
        };

        if (_selected == null) {
            getError(signLang(40));
            return;
        }

        event();
    };

    var certificateFileEvent = function () {
        var certificate,
            certList = [],
            password = pfxPin.val();

        if(!_pfx){
            getError(signLang(90));
            return;
        }

        if(typeof(password) !== 'string' || password === ''){
            getError(signLang(41));
            return;
        }

        try{
            certificate = vest.pki.CertificateSet.fromPFX(_pfx, password);
        }catch(e){
            getError(signLang(92));
            pfxPin.val('');
            pfxPin.focus();
            return;
        }

        // OID 체크 로직 추가.. 요부분도 나중에 함수로 빼자.. 지금은 귀찮다.
        var toShortCert = certificate.getCertificate().toShortCert();
        _certificates.push(certificate.getCertificate());
        if (!(OIDFilter(toShortCert))) {
                // 해당 OID 없음
            getError(signLang(93));
            return;
        }



        certList.push(toShortCert);
        showCertListUI();
        disableP12Password();
        showP12Password();
        outCertList(certList);



        addOkayClickEvent(function(){
            PFXokButtonEvent(certificate);
        });
    };

    var readFile = function (file){
        var reader = new FileReader();

        reader.onload = function () {
            _pfx = reader.result;
            showFileBackGround();
            removeFileEvent();

            pfxPin.focus();
        };

        reader.readAsArrayBuffer(file);
    };

    var handleDrop = function (evt) {
        evt.stopPropagation();
        evt.preventDefault();

        var files = evt.originalEvent.dataTransfer.files;
        readFile(files[0]);
    };

    var handleFileSelect = function (evt) {
        var files = evt.target.files;
        readFile(files[0]);
    };

    var addFileEvent = function () {
        fileInputBtn.keydown(function (key) {
            keyEvent(key, function () {
                fileSelect.click();
            });
        });

        fileSelect.val('');
        fileSelect.prop('disabled', false);

        p12View.bind('dragenter dragover', false)
            .bind('drop', handleDrop);
        fileSelect.change(handleFileSelect);
    };

    var removeFileEvent = function () {
        p12View = $('#P12');
        fileSelect = $('#fileInputHidden');

        fileSelect.prop('disabled', true);

        p12View.unbind('dragenter dragover')
            .unbind('drop');
        fileSelect.unbind('change');
    };

    // menu
    function init() {
        _pfx = undefined;
        _certificates = [];
        _selected = undefined;
        clearTimeout(_isLoad);
        addOkayClickEvent(function () {
        });

        $('#outCertList').empty();
        $("#outCertComment").val('');
        certPin.val('');
        enableP12Password();
    };

    var tokenSetting = function () {
        //출력용 저장매체 목록 배열정보 수정
        $.each(args, function (index, item) {
            $.each(fixArgs, function (indexs, items) {
                if (item.arg1 == items.fixArg1) {
                    items.fixArg4 = item.arg2;
                    items.fixArg5 = index;
                }
            });
        });

        //출력용 저장매체 목록 배열정보 정렬
        fixArgs.sort(function (a, b) {
            if (a.fixArg5 > b.fixArg5) return 1;
            if (a.fixArg5 < b.fixArg5) return -1;
            return 0;
        });

        $("#menu_btn").empty();
        
        //저장매체 출력
        $.each(fixArgs, function (index, item) {

            if (item.fixArg4 == "1") {
                $(document.createElement('li'))
                    .attr("id", item.fixArg1)
                    .append($(document.createElement('a'))
                        .attr({
                            "href": "javascript:;",
                            "class": item.fixArg2,
                            "tabindex": _menuTabIndex,
                            "onclick": "javascript:tab($(this).parent().index());"
                        })
                        .append(item.fixArg3)
                )
                    .appendTo($("#menu_btn"));
                _menuTabIndex++;
            } else {
                $(document.createElement('li'))
                    .attr("class", item.fixArg2 + "_disable")
                    .append(item.fixArg3)
                    .appendTo($("#menu_btn"));
            }
        });
        _subMenuTabIndex = _menuTabIndex + 1;
    };

    var removeTokenEvent = function () {
        $('#hard_disk').unbind('click');
        $('#usb_disk').unbind('click');
        $('#secure_token').unbind('click');
        $('#save_token').unbind('click');
        $('#certificate_file').unbind('click');
        $('#secure_disk').unbind('click');
    };

    function disableMenu(menu) {
        menu.addClass('none');
        menu.unbind('click');

        menu.attr({tabindex: -1});
    }

    var setHardDisk = function () {
        init();
        removeLoadingEvnet();
        vestCertVersion(function () {
            setPwdText(signLang(7));
            getTokenListFromVestCert(vest.token.TYPE.SYSTEM);
        });
    };

    var setUsbDisk = function () {
        init();
        removeLoadingEvnet();
        vestCertVersion(function () {
            setPwdText(signLang(7));
            getTokenListFromVestCert(vest.token.TYPE.LOCALDISK);
        });
    };

    var setSecureToken = function () {
        init();
        removeLoadingEvnet();
        vestCertVersion(function () {
            setPwdText(signLang(128));
            getTokenListFromVestCert(vest.token.TYPE.TOKEN);
        });
    };

    var setSaveToken = function () {
        init();
        removeLoadingEvnet();
        vestCertVersion(function () {
            setPwdText(signLang(7));
            getTokenListFromVestCert(vest.token.TYPE.SAVETOKEN);
        });
    };

    var setCertificateFile = function () {
        init();
        removeLoadingEvnet();
        showP12View();
        addFileEvent();
        addLoadingEvent();

        setPwdText(signLang(7));

        addOkayClickEvent(certificateFileEvent);
    };

    var defaultBtn = function () {
        init();
    };

    var addTokenEvent = function () {
        //기존에 이벤트가 있을시 삭제하고 재등록.
        removeTokenEvent();

        $('#hard_disk').click(setHardDisk);
        $('#usb_disk').click(setUsbDisk);
        $('#secure_token').click(setSecureToken);
        $('#save_token').click(setSaveToken);
        $('#certificate_file').click(setCertificateFile);
        $('#secure_disk').click(defaultBtn);
    };

    var getE2EInfo = function (callback) {
        var vender;

        if(_keySafer.getNumber() == 7) {
            vender = {
                type: _keySafer.getNumber()
            };
        }

        vest.token.getE2EInfo(vender, callback, vestCertErrorHandler);
    };

    var keySaferScriptLoading = function (i, func) {
        var script = _keySafer.getScript();
        var scriptLen = script.length;

        if (scriptLen == 0) return;
        
        var callback = function() {
            if (i == scriptLen - 1) {
                if (_keySafer.isCert()) {
                    getE2EInfo(function (result) {
                        var data = {
                            publicKey: result.publicKey,
                            sessionID: result.sessionID,
                            indexTable: result.indexTable,
                            keySaferPath: _config.keySaferPath,
                            getE2EInfoFun: getE2EInfo
                        };
                        func(data);
                    });
                } else {
                    func();
                }
                return;
            }
            keySaferScriptLoading(++i, func);
        };

        var head = document.getElementsByTagName("head")[0];
        var keyScript = document.createElement("script");
        keyScript.src = _keySafer.getPath() + script[i];
        

        var flag = false;
        if(keyScript.addEventListener) {
            keyScript.addEventListener("load", callback);
        }
        else if(keyScript.readyState){
            keyScript.onreadystatechange = function () {
                if (this.readyState == "loaded" || this.readyState == "complete") {
                    if (!flag) {
                        flag = true;
                        callback();
                    }
                }
            };
        }
        else{
            _keySafer = keySafer();
        }

        head.appendChild(keyScript);
    };
    // ready
    $(document).ready(function () {
        if (!(vest.isSSL())) {
            getError('"https:/"로 접속 가능합니다.');
            _parent.close();
        }

        // 타이틀 설정
        //$("#titleText").append(_config.title);
        $("#titleText").append(title[_config.title]);
        
        // 버전 출력
        getVestCertVersion(function (version) {
            $("#versionText").append("( Ver " + version + " )")
        });
        // 비밀번호 활성화 여부
        if (!_config.ablePwd) certPin.attr({"disabled": true});

        // 이벤트 등록
        addLoadingEvent();

        //setMatched select
        deviceOrder(_config.kos_var.pOrderStr, function () {
            if(typeof(_config.disableToken) !== "undefined" && _config.disableToken)  disableToken();
            deviceViewOrder(_config.kos_var.pViewOrderStr);
            setFirstFocus(_config.kos_var.pOrderStr);
            tokenSetting();
            addTokenEvent();

            // 제일먼저 선택
            startFocus();
        });

        // remove wheel event
        $("#mainCertList").mousedown(function (e) {
            if (e.which == 2) {
                e.preventDefault();
            }
        });
        
        if (_config.ablePwd){
            _keySafer = keySafer(_config.keystrokeEncryption, document.getElementById('passwordInput'), _config.keySaferConfig);
            keySaferScriptLoading(0, _keySafer.init);
        }
        
        $("#passwordInput").keydown(function (key) {
            keyEvent(key, function () {
                $("#submit_btn").click();
                key.preventDefault();
            });
        });
        $("#pfxPasswordInput").keydown(function (key) {
            keyEvent(key, function () {
                $("#submit_btn").click();
                key.preventDefault();
            });
        });

        //pfx 활성화 여부 체크 (우선 임시처리.. 분기태우는게 이상함..)
        // fileAPI 지원여부, _config.tile=0 : 인증서 선택 (서명, 식별검증만)
        // 서명, 식별검증 구분.... 모르겠네...
        if(vest.util.file.hasFileAPI() && _config.title == 0) useMenu['CERTIFICATE_FILE'] = '1';
        //useMenu['CERTIFICATE_FILE'] = '0';

        if(_config.kos_var.webAccess){
            //webAccess.webAccessInit("titleText", "cancel_btn");
            webAccess.webAccessInit(1, 9999);
        }
    });

})(document, jQuery, (function () {
    'use strict';
    var vest = undefined;
    if (window.opener) {
        if (!(typeof(window.opener.vest) === 'undefined' || window.opener.vest == "")) return window.opener.vest;
        //} else if (window.parent.vestSign !== 'undefined') {
    } else if (window.parent.vest) {
        if (!(typeof(window.parent.vest) === 'undefined' || window.parent.vest == "")) return window.parent.vest;
    }
    else {
        return vest;
    }
})(), params, (function () {
    'use strict';
    var vestSign = undefined;
    if (window.opener) {
        if (!(typeof(window.opener.vestSign) === 'undefined' || window.opener.vestSign == "")) return window.opener.vestSign;
        //} else if (window.parent.vestSign !== 'undefined') {
    } else if (window.parent.vestSign) {
        if (!(typeof(window.parent.vestSign) === 'undefined' || window.parent.vestSign == "")) return window.parent.vestSign;
    }
    else {
        return vestSign;
    }
})());
//version: _9e92e1a4679ae67ad09a3dab0da4932d22385cc9
//update: Mon May 23 16:07:48 2016 +0900
//version:  v1.0.0
