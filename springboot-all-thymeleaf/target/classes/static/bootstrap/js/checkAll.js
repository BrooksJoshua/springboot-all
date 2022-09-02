

    $(function(){
        $("#tbodyId").on("change",".cBox",doChangeCheckAllState) //说明:采用on方法注册事件,允许on方法内部的元素暂时不存在
        $("thead").on("change","#checkAll",doCheckAll);
        $("#exportBtn").on("click",doExportArchives);
        $("#exportBtn").on("click",doGetCheckedIds);


        function doChangeCheckAllState(){
            //1.获取tbody中所有checkbox对象状态进行与操作的结果
            var flag=true;
            $("#tbodyId input[type=checkbox]")
                .each(function(){
                    flag=flag&&$(this).prop("checked")
                });
            //2.将tbody中checkbox最终与状态的结果影响thead中checkbox的状态.
            $("#checkAll").prop("checked",flag);

        }
        function doCheckAll(){
            //1.获得点击对象(全选的checkbox)的checked属性值
            var flag=$(this).prop("checked");
            //2.让全选对象的状态值影响tbody中checkbox的状态值.
            /*$("#tbodyId input[type=checkbox]")
             .each(function(){
                 $(this).prop("checked",flag);
             }); */
            $("#tbodyId input[type=checkbox]")
                .prop("checked",flag);
        }

        function doGetCheckedIds(){
            //1.定义数组用于存储选中的checkbox的值
            var array="";// var array=[];
            //2.迭代所有tbody中checkbox对象并获取选中的值
            $("#tbodyId input[type=checkbox]")
                .each(function(){
                    if($(this).prop("checked")){
                        array= array.concat(",",$(this).val().toString())
                    }
                });
            //3.返回选中的值.
            return "apiInfoPKs="+array.substr(1,array.length);
        }


        //讲返回的id记录下来传给后端做导出
        function doExportArchives(){
            //1.获得所有选中checkbox的状态值.
            var array=doGetCheckedIds();
            //2.判定是否有值,没有值则给出提示
            if(array=="apiInfoPKs="||array==null){
                alert("请至少选中一个 !");
                return;
            }
            //3.异步提交要导出的资源的id值,并处理响应结果
            var url="archive/export";
            var params=array;
            $.get(url,params,function(data){
                if(data.length > 0 ){
                    var s = params.replace(/exportIds=/,"");
                    s=s.replace(/,/g,'\n');
                    alert("已导出如下Archive列表:\n[\n"+s+"\n]\n");
                    var reUrl=url+"?"+params;
                    window.open(reUrl);
                    window.location.reload();
                }else{
                    console.log("Oops~ 导出失败!");
                }
            });
        }



    });
