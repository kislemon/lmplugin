/**
 * Created by Lemon on 16/5/31.
 */
var exec = require('cordova/exec');

module.exports = {
  test:function(success, error) {
    exec(success, error,"LMPlugin","test", []);
  },
}
