/**
 * Created by nori on 2015. 10. 20..
 */
(function (window, vest, undefined) {
    'use strict';
    var classPolicies = (function() {

        var policies = [
            [   // 1
            "1.2.410.200004.5.1.1.5",
            "1.2.410.200005.1.1.1",
            "1.2.410.200004.5.2.1.2",
            "1.2.410.200004.5.3.1.9",
            "1.2.410.200004.5.4.1.1",
            "1.2.410.200012.1.1.1"
            ],
            [   // 16
                "1.2.410.200004.5.1.1.7",
                "1.2.410.200005.1.1.5",
                "1.2.410.200004.5.2.1.1",
                "1.2.410.200004.5.3.1.2",
                "1.2.410.200004.5.4.1.2",
                "1.2.410.200012.1.1.3"
            ],
            [   // 256
                "1.2.410.200004.5.1.1.9",
                "1.2.410.200004.5.2.1.7.2",
                "1.2.410.200004.5.4.1.102",
                "1.2.410.200012.1.1.103"
            ]
        ];

        function makeClass (arr, index) {
            for(var i=0; i < policies[index].length; i++)
                arr.push(policies[index][i]);

            return arr;
        };



        return {
            vaule: policies,
            makeClassPolicies: function(config) {
                if(config == 0 || config == '0') config = 273;

                var arr = [];
                if( (config & 0x00000001) == 1 ) {
                    arr = makeClass(arr, 0);
                }
                if( (config & 0x000000F0) == 16 ) {
                    arr = makeClass(arr, 1);
                }
                if( (config & 0x00000F00) == 256 ) {
                    arr = makeClass(arr, 2);
                }
                return arr;
            }
        }
    })();
    var policies = {
        // yessign : 금융결제원
        '1.2.410.200005.1.1.1': {
            caName: 'yessignCA Class 1',
            //usage: '범용(개인)'
            usage: policiesLang(0)
        },
        '1.2.410.200005.1.1.5': {
            caName: 'yessignCA-Test Class 1',
            //usage: '범용(법인)'
            usage: policiesLang(1)
        },
        '1.2.410.200005.1.1.4': {
            caName: 'yessignCA Class 1',
            //usage: '은행/보험(개인)'
            usage: policiesLang(2)
        },
        '1.2.410.200005.1.1.2': {
            caName: 'yessignCA Class 1',
            //usage: '은행/보험(법인)'
            usage: policiesLang(3)
        },
        '1.2.410.200005.1.1.6.2': {
            caName: 'yessignCA Class 1',
            //usage: '신용카드용'
            usage: policiesLang(4)
        },
        '1.2.410.200005.1.1.6.8': {
            caName: 'yessignCA Class 1',
            //usage: '전자세금용'
            usage: policiesLang(5)
        },

        // SignKorea : 코스콤 : 한국증권전산
        '1.2.410.200004.5.1.1.5': {
            caName: 'SignKorea CA2',
            //usage: '범용(개인)'
            usage: policiesLang(0)
        },
        '1.2.410.200004.5.1.1.7': {
            caName: 'SignKorea CA2',
            //usage: '범용(법인)'
            usage: policiesLang(1)
        },
        '1.2.410.200004.5.1.1.10': {
            caName: 'SignKorea CA2',
            //usage: '증권(법인)'
            usage: policiesLang(12)
        },
        '1.2.410.200004.5.1.1.9': {
            caName: 'SignKorea CA2',
            //usage: '증권(개인)'
            usage: policiesLang(13)
        },
        '1.2.410.200004.5.1.1.9.2': {
            caName: 'SignKorea CA2',
            //usage: '카드(개인)'
            usage: policiesLang(14)
        },
        '1.2.410.200005.1.1.12.902': {
            caName: 'SignKorea CA2',
            //usage: '전자세금용'
            usage: policiesLang(5)
        },

        // CrossCert : 한국전자인증
        '1.2.410.200004.5.4.1.1': {
            caName: 'CrossCertCA2',
            //usage: '범용(개인)'
            usage: policiesLang(0)
        },
        '1.2.410.200004.5.4.1.2': {
            caName: 'CrossCertTestCA2',
            //usage: '범용(범용)'
            usage: policiesLang(1)
        },
        '1.2.410.200004.5.4.1.101': {
            caName: 'CrossCertCA2',
            //usage: '은행/보험'
            usage: policiesLang(8)
        },
        '1.2.410.200004.5.4.1.103': {
            caName: 'CrossCertCA2',
            //usage: '신용카드용'
            usage: policiesLang(4)
        },
        '1.2.410.200004.5.4.2.80': {
            caName: 'CrossCertCA2',
            //usage: '전자세금용'
            usage: policiesLang(5)
        },

        // signGATE : 한국정보인증
        '1.2.410.200004.5.2.1.2': {
            caName: 'signGATE CA4',
            //usage: '범용(개인)'
            usage: policiesLang(0)
        },
        '1.2.410.200004.5.2.1.1': {
            caName: 'signGATE FTCACA4',
            //usage: '1등급(법인)'
            usage: policiesLang(10)
        },
        '1.2.410.200004.5.2.1.7.1': {
            caName: 'signGATE CA4',
            //usage: '은행/보험'
            usage: policiesLang(8)
        },
        '1.2.410.200004.5.2.1.7.3': {
            caName: 'signGATE CA4',
            //usage: '신용카드용'
            usage: policiesLang(4)
        },
        '1.2.410.200004.5.2.1.6.369': {
            caName: 'signGATE CA4',
            //usage: '테스트'
            usage: policiesLang(6)
        },

        // TradeSign : 한국무역정보통신
        '1.2.410.200012.1.1.1': {
            caName: 'TradeSignCA2',
            //usage: '범용(개인)'
            usage: policiesLang(0)
        },
        '1.2.410.200012.1.1.3': {
            caName: 'TradeSignCA2011Test2',
            //usage: '범용(범인)'
            usage: policiesLang(1)
        },
        '1.2.410.200012.1.1.101': {
            caName: 'TradeSignCA2',
            //usage: '은행/보험'
            usage: policiesLang(8)
        },
        '1.2.410.200012.1.1.105': {
            caName: 'TradeSignCA2',
            //usage: '신용카드용'
            usage: policiesLang(4)
        },
        '1.2.410.200012.5.19.1.1': {
            caName: 'TradeSignCA2',
            //usage: '전자세금용'
            usage: policiesLang(5)
        }
    };

    if (vest) {
        vest.util = vest.util || (vest.util = {});
        vest.extend(vest.util, {
            'policies': policies,
            'classPolicies': classPolicies
        });
    }
})(window, (function () {
    'use strict';
    var vest = undefined;
    if (window.opener) {
        if (!(window.opener.vest == undefined || window.opener.vest == "")) return window.opener.vest;
        //} else if (window.parent.vestSign !== 'undefined') {
    } else if (window.parent.vest) {
        if (!(window.parent.vest == undefined || window.parent.vest == "")) return window.parent.vest;
    }
    else return vest;
})());
