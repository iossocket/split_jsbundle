var fs = require('fs');
var readline = require('readline');

function readFileToArr(fReadName, callback) {
  var fRead = fs.createReadStream(fReadName);
  var objReadline = readline.createInterface({
      input:fRead
  });
  var arr = new Array();
  objReadline.on('line',function (line) {
      arr.push(line);
  });
  objReadline.on('close',function () {
      callback(arr);
  });
}

var argvs = process.argv.splice(2);
var commonFile = argvs[0]; // common.jsbundle
var businessFile = argvs[1]; // business.jsbundle
var diffOut = argvs[2]; // diff.jsbundle
readFileToArr(commonFile, function (c_data) {
  var diff = [];
  var commonArrs = c_data;
  readFileToArr(businessFile, function (b_data) {
    var businessArrs = b_data;
    for (let i = 0; i < businessArrs.length; i++) {
      if (commonArrs.indexOf(businessArrs[i]) === -1) {
        diff.push(businessArrs[i]);
      }
    }
    var newContent = diff.join('\n');
    fs.writeFileSync(diffOut, newContent);
  });
});
