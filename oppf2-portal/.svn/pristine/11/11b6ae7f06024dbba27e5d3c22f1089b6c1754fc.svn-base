/**
 * Created by nori on 2015. 8. 7..
 */
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

var storageSelected = (function (doc, $, vest, params, vestSign) {

    if (vestSign === undefined) {
        alert(storageSelectedLang(11));
        //window.close();
        return false;
    }

    var _config = vestSign.getConfig();
    var _parent = vestSign;
    var _lastTokenNumber = typeof(_config.storageSelectedTokenNumber) === "undefined" ? "" : _config.storageSelectedTokenNumber;
    var _param;
    var _menuTabIndex = 5;
    var _subMenuTabIndex = 50;

    var useMenu = {
        PHONE: '0',
        USB_DISK: '0',
        HARD_DISK: '0',
        SECURE_TOKEN: '0',
        SAVE_TOKEN: '0',
        CERTIFICATE_FILE: '0',
        SECURE_DISK: '0'
    };

    var notice = {
        "hard_disk": storageSelectedLang(13),
        "usb_disk": storageSelectedLang(14),
        "secure_token": storageSelectedLang(15),
        "save_token": storageSelectedLang(16)
    };

    var args = [
        {arg1: 'phone_certification', arg2: useMenu['PHONE']},
        {arg1: 'usb_disk', arg2: useMenu['USB_DISK']},
        {arg1: 'hard_disk', arg2: useMenu['HARD_DISK']},
        {arg1: 'secure_token', arg2: useMenu['SECURE_TOKEN']},
        {arg1: 'save_token', arg2: useMenu['SAVE_TOKEN']},
        {arg1: 'certificate_file', arg2: useMenu['CERTIFICATE_FILE']},
        {arg1: 'secure_disk', arg2: useMenu['SECURE_DISK']}
    ];
    
    // 디자인 
    $('#certAgree_cancelBtn').click(function () {
        //_parent.storageSelectedClose();
        //_parent.close();
        closeEvent();
    });
    $('#certAgree_xBtn').click(function () {
        //_parent.storageSelectedClose();
        //_parent.close();
        closeEvent();
    });

    function closeEvent() {
        var error = {
            code: 12025
        };
        _param.errorcallback(error);
        _parent.close();
    }

    $('#dialog_clostBtn').click(function () {
        $("#dialog").dialog("close");
    });

    $(document).on("click", "#menu_btn li", function () {
        var param = params.getParameters();
        var _option = param.option;
        _option.pkitype = _config.GPKI;

        if (this.id == "") return;

        //var args = [
        //    {arg1:'이동식디스크', tokenNumber:'1'}
        //    , {arg1:'A', tokenNumber:'1'}
        //    , {arg1:'A', tokenNumber:'1'}
        //    , {arg1:'이동식디스크', tokenNumber:'1'}
        //    , {arg1:'이동식디스크', tokenNumber:'1'}
        //];


        //switch ($(this).parent().get(0).id) {
        switch (this.id) {
            //alert("[this.id]"+this.id);
            case "phone_certification":
                alert(storageSelectedLang(12));
                break;
            case "hard_disk":
                setMenuNotice(notice["hard_disk"]);
                vest.token.getTokenList(vest.token.TYPE.SYSTEM, function (list) {
                    if (_lastTokenNumber != '') {
                        vest.token.getCertificates(_lastTokenNumber, undefined, undefined, _option, function () {
                            okEvent(list[0].tokenIdentifier);
                        });
                    } else {
                        okEvent(list[0].tokenIdentifier);
                    }
                });
                break;
            case "usb_disk":
                setMenuNotice(notice["usb_disk"]);
                vest.token.getTokenList(vest.token.TYPE.LOCALDISK, function (list) {
                    if (list.length < 2) {
                        okEvent(list[0].tokenIdentifier);
                    } else {
                        dOpen(makeDownMenu(list));
                    }
                });
                break;
            case "secure_token":
                setMenuNotice(notice["secure_token"]);
                vest.token.getTokenList(vest.token.TYPE.TOKEN, function (list) {
                    if (list.length < 2) {
                        okEvent(list[0].tokenIdentifier);
                    } else {
                        dOpen(makeDownMenu(list));
                    }
                });
                break;
            case "save_token":
                setMenuNotice(notice["secure_token"]);
                vest.token.getTokenList(vest.token.TYPE.SAVETOKEN, function (list) {
                    if (list.length < 2) {
                        okEvent(list[0].tokenIdentifier);
                    } else {
                        dOpen(makeDownMenu(list));
                    }
                });
                break;
            case "certificate_file":
                alert(storageSelectedLang(12));
                break;
            case "secure_disk":
                alert(storageSelectedLang(12));
                break;
            default:
                alert(storageSelectedLang(12));
        }
    });

    function dOpen(args) {

        var sIndex = $("#menu_btn li").find(".on").parent().index();
        var sClass = $("#menu_btn li:eq(" + sIndex + ") a").attr("class").replace("_on on", "");

        $("#dialog").dialog({
            autoOpen: true,
            //height: 230,
            //width: 250,
            modal: true,
            open: function (type, data) {
                var _count = 2;
                $(".ui-dialog-titlebar", $(this).parent()).remove();

                $("#dialog").dialog({width: "auto", height: "auto"});
                $("#dialog").css('overflow', 'hidden');

                $("#dialog_list").empty();

                //다이얼로그 출력
                $.each(args, function (index, item) {
                    _count++;
                    $(document.createElement('li'))
                        .append($(document.createElement('a'))
                            .attr({
                                "href": "#",
                                "tabindex": _subMenuTabIndex + _count
                            })
                            .on("click", function () {
                                saveCertificate(item.tokenNumber);
                            })
                            .append($(document.createElement('span'))
                                .append(item.arg1)
                        )
                    )
                        .appendTo($("#dialog_list"));
                });

                if (args.length > 3) $("#dialog_list").css("height", "128px");
                else $("#dialog_list").css("height", (args.length * 32) + "px");

                webAccess.webAccessInit(_subMenuTabIndex, _subMenuTabIndex + _count);
            },
            resizeStop: function () {
                $(this).dialog({height: "auto"});
            }
        });
    }

    function outSaveList() {
        /*
         var args = [
         {arg1:'phone_certification', arg2:'0'},
         {arg1:'usb_disk', arg2:'1'},
         {arg1:'hard_disk', arg2:'1'},
         {arg1:'secure_token', arg2:'1'},
         {arg1:'save_token', arg2:'1'},
         {arg1:'certificate_file', arg2:'1'},
         {arg1:'secure_disk', arg2:'0'}
         ];
         */
        //fixArg1:매체아이디, fixArg2: 매체클래스명, fixArg3: 매체이름, fixArg4: 사용여부(1:사용,0:미사용), fixArg5: 순서
        var fixArgs = [
            //{fixArg1:'phone_certification', fixArg2:'ico7', fixArg3:'휴대폰인증', fixArg4:'1', fixArg5:''},
            {fixArg1: 'hard_disk', fixArg2: 'ico4', fixArg3: storageSelectedLang(6), fixArg4: '1', fixArg5: ''},
            {fixArg1: 'usb_disk', fixArg2: 'ico1', fixArg3: storageSelectedLang(7), fixArg4: '1', fixArg5: ''},
            {fixArg1: 'secure_token', fixArg2: 'ico2', fixArg3: storageSelectedLang(8), fixArg4: '1', fixArg5: ''},
            {fixArg1: 'save_token', fixArg2: 'ico3', fixArg3: storageSelectedLang(9), fixArg4: '1', fixArg5: ''},
            //{fixArg1:'certificate_file', fixArg2:'ico5', fixArg3:'찾아보기', fixArg4:'1', fixArg5:''},
            {fixArg1: 'secure_disk', fixArg2: 'ico6', fixArg3: storageSelectedLang(10), fixArg4: '1', fixArg5: ''}
        ];


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
                            "onclick": "javascript:tab($(this).parent().index()); "
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

        webAccess.firstFocus();
    }

    function makeDownMenu(list) {
        var result = [];

        for (var i = 0; i < list.length; i++) {
            result.push({arg1: list[i].name, tokenNumber: list[i].tokenIdentifier});
        }

        return result;
    }

    var disableToken = function (list) {
        for (var i = 0; i < list.length; i++) {
            var object = list[i];

            if (list[i].tokenIdentifier === _config.storageSelectedTokenNumber) {
                //if (object.type == "DISK DRIVE" && (typeof(object.systemDrive) !== undefined && object.systemDrive)) {
                //    // 하드 디스크
                //    useMenu['HARD_DISK'] = "0";
                //}
                //if (object.type == "DISK DRIVE" && (typeof(object.systemDrive) !== undefined && !(object.systemDrive))) {
                //    // 이동식 디스크
                //    useMenu['USB_DISK'] = "0";
                //}
                //if (object.type === "HARDWARE TOKEN") {
                //    // 하드웨어 토큰...
                //}

                if (object.type == "DISK DRIVE" && (typeof(object.systemDrive) !== "undefined" && object.systemDrive)) {
                    // 하드 디스크
                    useMenu['HARD_DISK'] = "0";
                }
                else if (object.type == "DISK DRIVE" && (typeof(object.systemDrive) !== "undefined" && !(object.systemDrive))) {
                    // 이동식 디스크
                    useMenu['USB_DISK'] = "0";
                }
                else if (object.type == "PKCS#11 TOKEN") {
                    // 보안토큰
                    useMenu['SECURE_TOKEN'] = "0";
                }
                else if (object.type == "SmartCard TOKEN") {
                    useMenu['SAVE_TOKEN'] = "0";
                }
                else {

                }
                //이런식으로 활성화 세팅.
            }
        }
    };

    var setTokenList = function (list) {

        // 연결된 매체에 대해서만 인에이블 시키도록 하는 로직
        for (var i = 0; i < list.length; i++) {
            var object = list[i];

            //if (object.type == "DISK DRIVE" && (typeof(object.systemDrive) !== "undefined" && object.systemDrive)) {
            //    // 하드 디스크
            //    useMenu['HARD_DISK'] = "1";
            //}
            //if (object.type == "DISK DRIVE" && (typeof(object.systemDrive) !== "undefined" && !(object.systemDrive))) {
            //    // 이동식 디스크
            //    useMenu['USB_DISK'] = "1";
            //}
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
            //if (object.type == "PKCS#11 TOKEN") {
            //    // 보안토큰 9.7~11 결정
            //    useMenu['SECURE_TOKEN'] = "1";
            //}
            //이런식으로 활성화 세팅.

        }
    };
    
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
        args.push({arg1: 'certificate_file', arg2: '0'});     // 찾아보기
        args.push({arg1: 'phone_certification', arg2: '0'});      // 휴대폰인증
        args.push({arg1: 'secure_disk', arg2: '0'});      // 안전디스크
        
        // 설정된 값에 따라 매체 표시
        // 여기 무슨부분인지 알고싶지않음
        var list = $("#button_slide_wrap #menu_btn li");
        var $list = list.length;

        $(".next").on("click", function () {
            $("#button_slide_wrap #menu_btn").animate({
                left: "-=73px"
            }, 0, function () {
                $("#button_slide_wrap #menu_btn li").eq(0).appendTo($("#button_slide_wrap #menu_btn"));
                $("#menu_btn").css("left", "2px")
            });//innerfunc
        });//next
        $(".prev").on("click", function () {
            $("#button_slide_wrap #menu_btn").animate({
                left: "0px"
            }, 0, function () {
                $("#button_slide_wrap #menu_btn li").eq($list - 1).prependTo($("#button_slide_wrap #menu_btn"));
                $("#menu_btn").css("left", "2px")
            })//innerfunc
        });//prev
        
        /* 로딩시 저장매체선택 */
        //$("a.ico4").trigger("click");
        //setUsbDisk();tab(0);
    };

    var setMenuNotice = function (str) {
        $("#menu_notice").html(str);
    };

    var okEvent = function (tokenNumber) {
        //확인버튼에 saveCertificate() 이벤트 주기.
        $("#certAgree_confirmBtn").unbind('click');
        $("#certAgree_confirmBtn").click(function () {
            saveCertificate(tokenNumber);
        });

        if(_config.kos_var.webAccess)  $("#certAgree_confirmBtn").click();
    };

    var saveCertificate = function (tokenNumber) {
        _param.callback(tokenNumber, _param.option);
        //_parent.storageSelectedClose();

    };

    var errorCallback = function (error) {
        //_parent.storageSelectedClose();
        _param.errorcallback(error);
        _parent.close();
    };

    $(document).ready(function () {
        //옵션에 따라 약관 페이지 호출.
        try {
            //_parent.storageSelectedgetParameters();
            _parent.getParameters();
        } catch (e) {
            _parent.close();
        }
        _param = params.getParameters();

        vest.token.getTokenList(vest.token.TYPE.ALL, function (list) {

            setTokenList(list);

            if (!_config.storageSelectedFlag) {
                disableToken(list);
            }

            deviceViewOrder(_config.kos_var.pViewOrderStr);
            outSaveList();

        }, function (error) {
            //_parent.storageSelectedClose();
            _param.errorcallback(error);
            _parent.close();
        }, errorCallback);

        if(_config.kos_var.webAccess){
            //webAccess.webAccessInit("titleText", "cancel_btn");
            webAccess.webAccessInit(1, 9999);
        }
    });
})(document, jQuery, (function () {
    'use strict';
    var vest = undefined;
    if (window.opener) {
        if (!(window.opener.vest == undefined || window.opener.vest == "")) return window.opener.vest;
        //} else if (window.parent.vestSign !== 'undefined') {
    } else if (window.parent.vest) {
        if (!(window.parent.vest == undefined || window.parent.vest == "")) return window.parent.vest;
    }
    else return vest;
})(), params, (function () {
    'use strict';
    if (window.opener) {
        return window.opener.vestSign;
    } else if (window.parent.vestSign) {
        return window.parent.vestSign;
    }
    else {
        return vestSign;
    }
})());


//version: _9e92e1a4679ae67ad09a3dab0da4932d22385cc9
//update: Mon May 23 16:07:48 2016 +0900
//version:  v1.0.0
