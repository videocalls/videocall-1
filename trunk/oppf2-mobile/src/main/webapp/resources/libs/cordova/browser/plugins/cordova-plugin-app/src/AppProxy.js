cordova.define("cordova-plugin-app.AppProxy", function(require, exports, module) {
               
               
               
    module.exports= {}
               
    require("cordova/exec/proxy").add("App",module.exports);
               
});
