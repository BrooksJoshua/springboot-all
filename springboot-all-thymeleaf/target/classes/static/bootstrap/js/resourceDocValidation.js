// 显示后置脚本
function showPostpositionScriptBox(isShow){
    if(isShow==1){
        $("#postpositionScript").show();
    } else {
        $("#postpositionScript").hide();
    }
}

function ValidatebothIDandDoc(){
    spaceCheck();
    formatValidate();
    showTips();
    if(spaceCheck() && formatValidate()){
        $("#validateBtn").html("校验通过");
        $("#validateBtn").css("color","green");
        $("#submitBtn").show();

    }else if(!spaceCheck()){
        $("#resourceIdUntrimmedTips").show();
        $("#validateBtn").html("资源ID校验不通过");
        $("#validateBtn").css("color","red");
        $("#submitBtn").hide();
    }else if(!formatValidate()){
        //$("#resourceIdUntrimmedTips").show();
        $("#validateBtn").html("文档格式校验不通过");
        $("#validateBtn").css("color","red");
        $("#submitBtn").hide();
    }

}



function spaceCheck(){
    if($("#resourceID").val().toString().startsWith(" ") || $("#resourceID").val().toString().endsWith(" ")){
        $("#resourceIdUntrimmedTips").show();
        return false;
    }
    $("#resourceIdUntrimmedTips").hide();
    return true;
}
function showTips(){
    if(!formatValidate()){
        $(".docTips").show();
    }
}
function formatValidate(){

    if($("#docFormatContent").val().toString().indexOf("1.请求参数")==-1){
        $("#DocRelatedErrTips").html("请求参数有误 !");
        $("#DocRelatedErrTips").show();
        return false;
    }else if($("#docFormatContent").val().toString().indexOf("2.返回字段")==-1){
        $("#DocRelatedErrTips").html("返回字段有误 !");
        $("#DocRelatedErrTips").show();
        return false;
    }else if(($("#docFormatContent").val().toString().indexOf("|序号|字段|描述|类型|长度|必填|缺省值|备注|")==-1)||($("#docFormatContent").val().toString().indexOf("|------ |------ |------ |------ |------ |------ |------ |------ |")==-1)){
        $("#DocRelatedErrTips").html("表头格式有误 !");
        $("#DocRelatedErrTips").show();
        return false;
    }
    $("#DocRelatedErrTips").hide();
    $(".docTips").hide();
    return true;
}