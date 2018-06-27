<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<style>
    .treeview .list-group-item {
        cursor: pointer;
    }

    .treeview span.indent {
        margin-left: 10px;
        margin-right: 10px;
    }

    .treeview span.icon {
        width: 12px;
        margin-right: 5px;
    }

    .treeview .node-disabled {
        color: silver;
        cursor: not-allowed;
    }

    .treeview .glyphicon {
        color: #1582F1;
    }
</style>
<div class="widget-body" style=" height: auto ">
    <hr class="simple">
    <ul id="myTab1" class="nav nav-tabs bordered">
        <li class="active">
            <a href="#s1" data-toggle="tab" onclick="showSend()"><span style="display: block;width: 100px;text-align: center">发日志</span></a>
        </li>
        <li>
            <a href="#s2" onclick="showSendWork()"  data-toggle="tab"><span style="display: block;width: 100px;text-align: center">我发出的</span></a>
        </li>
        <li>
            <a href="#s3" onclick="showReceivedWork()"  data-toggle="tab"><span style="display: block;width: 100px;text-align: center">我收到的</span></a>
        </li>
        <li>
            <a href="#s4" onclick="showLogWork()"  data-toggle="tab"><span style="display: block;width: 100px;text-align: center">日志报表</span></a>
        </li>
    </ul>
    <div style=" min-height:500px "id="myTabContent" class="tab-content padding-10">
        <div class="tab-pane fade in active" id="s1">

        </div>
        <div class="tab-pane fade" id="s2">
            2
        </div>
        <div class="tab-pane fade" id="s3" style="min-height: 700px">
            3
        </div>
        <div class="tab-pane fade" id="s4">
            4
        </div>
    </div>
</div>
<script type="text/javascript">

    pageSetUp();

    var pageFunction = function () {
        loadURL("/summarizeReport/sendReportShow.do",$('#s1'));
    }
    function showSend(){
        loadURL("/summarizeReport/sendReportShow.do",$('#s1'));
    }
    function showSendWork(){
        loadURL("/summarizeReport/showSendWork.do",$('#s2'));
    }
    function showReceivedWork(){
        loadURL("/summarizeReport/showReceivedWork.do",$('#s3'));
    }
    function showLogWork(){
        loadURL("/summarizeReport/searchReports.do",$('#s4'));
    }
    loadScript("/smartAdmin/js/plugin/summernote/summernote.min.js", function(){
        loadScript("/smartAdmin/js/plugin/jquery-nestable/jquery.nestable.min.js", pageFunction)
    });
</script>
<style>
    #myTabContent{
        clear: both;
    }

</style>