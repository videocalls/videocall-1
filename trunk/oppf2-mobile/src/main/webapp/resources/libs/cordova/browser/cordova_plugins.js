cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
                  {
                      "file": "plugins/cordova-plugin-app/App.js",
                      "id": "cordova-plugin-app.App",
                      "pluginId": "cordova-plugin-app",
                      "clobbers": [
                           "KOSCOM"
                      ]
                  },
                  {
                      "file": "plugins/cordova-plugin-app/src/OPPF2.js",
                      "id": "cordova-plugin-app.OPPF2",
                      "pluginId": "cordova-plugin-app",
                      "runs": true
                  },
                  {
                      "file": "plugins/cordova-plugin-app/src/AppProxy.js",
                      "id": "cordova-plugin-app.AppProxy",
                      "pluginId": "cordova-plugin-app",
                      "runs": true
                  },
                  {
                      "file": "plugins/cordova-plugin-datacache/DataCache.js",
                      "id": "cordova-plugin-datacache.DataCache",
                      "pluginId": "cordova-plugin-datacache",
                      "clobbers": [
                          "KOSCOM.datacache"
                      ]
                  },
                  {
                      "file": "plugins/cordova-plugin-datacache/src/DataCacheManager.js",
                      "id": "cordova-plugin-datacache.DataCacheManager",
                      "pluginId": "cordova-plugin-datacache",
                      "runs": true
                  },
                  {
                      "file": "plugins/cordova-plugin-datacache/src/DataCacheProxy.js",
                      "id": "cordova-plugin-datacache.DataCacheProxy",
                      "pluginId": "cordova-plugin-datacache",
                      "runs": true
                  },
                  {
                      "file": "plugins/cordova-plugin-restful/src/APIServiceLoader.js",
                      "id": "cordova-plugin-restful.APIServiceLoader",
                      "pluginId": "cordova-plugin-restful",
                      "runs": true
                  },
                  {
                      "file": "plugins/cordova-plugin-restful/src/APIServiceConnector.js",
                      "id": "cordova-plugin-restful.APIServiceConnector",
                      "pluginId": "cordova-plugin-restful",
                      "runs": true
                  },
                  {
                      "file": "plugins/cordova-plugin-restful/src/APIDataFieldMapping.js",
                      "id": "cordova-plugin-restful.APIDataFieldMapping",
                      "pluginId": "cordova-plugin-restful",
                      "runs": true
                  },
                  {
                      "file": "plugins/cordova-plugin-restful/src/HTTPRequest.js",
                      "id": "cordova-plugin-restful.HTTPRequest",
                      "pluginId": "cordova-plugin-restful",
                      "runs": true
                  },
                  {
                      "file": "plugins/cordova-plugin-restful/src/APIConnectorProxy.js",
                      "id": "cordova-plugin-restful.APIConnectorProxy",
                      "pluginId": "cordova-plugin-restful",
                      "runs": true
                  },
                  {
                      "file": "plugins/cordova-plugin-restful/APIConnector.js",
                      "id": "cordova-plugin-restful.APIConnector",
                      "pluginId": "cordova-plugin-restful",
                      "clobbers": [
                              "KOSCOM.api"
                      ]
                  },
                  {
                      "file": "plugins/cordova-plugin-securepad/SecurePad.js",
                      "id": "cordova-plugin-securepad.SecurePad",
                      "pluginId": "cordova-plugin-securepad",
                      "clobbers": [
                             "KOSCOM.securepad"
                       ]
                  },
                  {
                      "file": "plugins/cordova-plugin-securepad/src/SecurePadProxy.js",
                      "id": "cordova-plugin-securepad.SecurePadProxy",
                      "pluginId": "cordova-plugin-securepad",
                      "runs": true
                  },
                  {
                      "id": "cordova-plugin-certificate.Certificate",
                      "file": "plugins/cordova-plugin-certificate/Certificate.js",
                      "pluginId": "cordova-plugin-certificate",
                      "clobbers": [
                          "KOSCOM.certificate"
                      ]
                  },
                  {
                      "file": "plugins/cordova-plugin-certificate/src/CertificateProxy.js",
                      "id": "cordova-plugin-certificate.CertificateProxy",
                      "pluginId": "cordova-plugin-certificate",
                      "runs": true
                  }
                  
];
module.exports.metadata = 
// TOP OF METADATA
{
    "cordova-plugin-whitelist": "1.3.2",
}
// BOTTOM OF METADATA
});
