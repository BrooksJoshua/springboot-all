// 显示后置脚本
function showPostpositionScriptBox(isShow){
    if(isShow==1){
        $("#postpositionScript").show();
    } else {
        $("#postpositionScript").hide();
    }
}

function ValidateSpaceandLength(){
    spaceCheck();
    equalLengthValidate();
    //showTips();
    if(spaceCheck() && equalLengthValidate()){
        $("#validateBtn").html("校验通过");
        $("#validateBtn").css("color","green");
        $("#submitBtn").show();

    }else if(!spaceCheck()){
        $("#untrimmedTips").show();
        $("#validateBtn").html("空格校验不通过");
        $("#validateBtn").css("color","red");
        $("#submitBtn").hide();
    }else if(!equalLengthValidate()){
        $("#schemaTableTips").show();
        $("#validateBtn").html("长度校验不通过");
        $("#validateBtn").css("color","red");
        $("#submitBtn").hide();
    }

}



function spaceCheck(){
    var resourceIdSpaceFlag= ($("#resourceId").val().toString().indexOf(" ")== -1);
    var schemaIdSpaceFlag= ($("#schemaId").val().toString().indexOf(" ") == -1);
    var tableIdSpaceFlag= ($("#tableId").val().toString().indexOf(" ")==-1);

    if( resourceIdSpaceFlag && schemaIdSpaceFlag && tableIdSpaceFlag) {
        $("#untrimmedTips").hide();
        return true;
    }
    $("#untrimmedTips").show();
    return false;

}
function showTips(){
    if(!equalLengthValidate()){
        $(".docTips").show();
    }
}
function equalLengthValidate(){
    if($("#schemaId").val().toString().split(",").length !=$("#tableId").val().toString().split(",").length){
        $("#schemaTableTips").show();
        return false;
    }
    $("#schemaTableTips").hide();
    return true;
}