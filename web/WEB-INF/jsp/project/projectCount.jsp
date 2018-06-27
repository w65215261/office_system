<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<style>
  #wid-id-1{
    border-width: 0px 0px 0px 0px;
  }
  #wid-id-2{
    border-width: 0px 0px ;
  }
  #wid-id-3{
    border-width: 0px 0px;
    height: 255px;
    background-color: #F7F7F7;
  }
  .noSolid{
    border-width: 0px 0px 0px 0px;
  }


</style>
<c:if test="${taskCountSize==0}">
  <div class="row" style="width: 99%;margin: 0 auto;height: 250px;">
    <span class="col-sm-12" style="font-size: 30px;">任务总数：<i style="color: red">0</i></span>
  </div>
</c:if>
<c:if test="${taskCountSize!=0}">
<div class="row">
  <section  class="widget-grid" style="border:1px solid #dcdcdc;width: 97.8%;margin:0 auto;">
    <article class="col-xs-12 col-sm-12" style="text-align: center">
      <span class="col-xs-12 col-sm-10">

        <h6 id="count"></h6>
      </span>
      <span class="col-xs-12 col-sm-2">

      </span>
    </article>
    <div class="row" style="margin:0 auto;">
      <article class="col-xs-12 col-sm-2">
      </article>
      <article class="col-xs-12 col-sm-6">
        <div class="jarviswidget"  id="wid-id-1" data-widget-colorbutton="false" data-widget-fullscreenbutton="false" data-widget-editbutton="false" data-widget-sortable="false">
          <!-- widget div-->
          <div id="wid-id-2">
            <!-- widget edit box -->
            <div class="jarviswidget-editbox">
              <!-- This area used as dropdown edit box -->
              <input class="form-control" type="text">
            </div>
            <!-- end widget edit box -->

            <!-- widget content -->
            <div class="widget-body no-padding" style="text-align: center">
              <!-- this is what the user will see -->
              <canvas id="doughnutChart" height="200px" ></canvas>
            </div>
            <!-- end widget content -->
          </div>
          <!-- end widget div -->
        </div>
      </article>
      <article class="col-xs-12 col-sm-2">
      </article>
      <article class="col-xs-12 col-sm-2 no-padding">
        <div id="wid-id-3">
          <br><br><br>
          <span class="col-sm-12">任务总数：<i>${taskCountSize}</i></span>
          <span class="col-sm-12">&nbsp;</span>
          <span class="col-sm-12">&nbsp;&nbsp;&nbsp;&nbsp;未完成：<i>${unfinishedSize}</i></span>
          <span class="col-sm-12">&nbsp;</span>
          <span class="col-sm-12">&nbsp;&nbsp;&nbsp;&nbsp;已完成：<i>${completeSize}</i></span>
        </div>
      </article>
    </div>
  </section>
  <section class="widget-grid" style="border:1px solid #dcdcdc;width: 97.8%;margin:0 auto;">
    <div style="width: 97.4%;margin:0 auto;">
      <table class="table table-bordered table-striped" style="border-width: 0px 0px 0px 0px;">
        <caption style="text-align:center;font-weight:bold"><font color="black" size="3px">按负责人统计任务进展状况</font></caption>
        <tr>
          <td align="left" width="10%" style="border-width: 0px 0px 0px 0px;background-color: white">
          </td>
          <td align="center" style="border-width: 0px 0px 0px 0px;background-color: white">
          </td>
          <td align="right" width="30%" style="border-width: 0px 0px 0px 0px;background-color: white">
          </td>
          <td align="right" width="30%" style="border-width: 0px 0px 0px 0px;background-color: white">
            <span style="color: #57879B"><i class="fa  fa-stop"></i>已完成 &nbsp; </span>
            <span>柱状图后的数字:（完成数/总数）</span>
          </td>
        </tr>
        <c:forEach items="${taskCounts}" var="counts">
        <tr>
          <td align="left" width="10%" style="border-width: 0px 0px 0px 0px;background-color: white">${counts.responsiblePersonName}
          </td>
          <td align="center" style="border-width: 0px 0px 0px 0px;background-color: white">
            <div class="progress" style="height: 10px;margin-top: 5px;">
              <div class="progress-bar" role="progressbar" id="${counts.responsiblePersonOid}" aria-valuenow="60" aria-valuemin="0" aria-valuemax="50" style="height: 50px">
              </div>
            </div>
          </td>
          <td align="left" width="30%" style="border-width: 0px 0px 0px 0px;background-color: white">
            (${counts.completeCount}/${counts.responsibleCount})
          </td>
          <td align="right" width="30%" style="border-width: 0px 0px 0px 0px;background-color: white">
          </td>
        </tr>
        </c:forEach>
      </table>
    </div>
  </section>
  <br>
  <section class="widget-grid" style="border:1px solid #dcdcdc;width: 97.8%;margin:0 auto;">
    <div style="width: 100%;margin:0 auto;">
      <table class="table table-bordered table-striped">
        <tr style="background-color: mediumpurple;color: white">
          <td>
            <span>人员</span>
          </td>
          <td align="left">
            <span>负责总数</span>
          </td>
          <td align="left">
            <span>已完成</span>
          </td>
          <td align="left">
            <span>完成率</span>
          </td>
          <td align="left">
            <span>评分</span>
          </td>
        </tr>
        <c:forEach items="${taskCounts}" var="counts2">
        <tr>
          <td align="left" valign="middle">
            <span>${counts2.responsiblePersonName}</span>
          </td>
          <td align="left">
            <span>${counts2.responsibleCount}</span>
          </td>
          <td align="left">
            <span>${counts2.completeCount}</span>
          </td>
          <td align="left">
            <div class="progress" style="height: 10px;">
              <div class="progress-bar" role="progressbar" id="${counts2.responsiblePersonOid}2" aria-valuenow="60" aria-valuemin="0" aria-valuemax="50">
              </div>
            </div>
          </td>
          <td align="left">
            <span></span>
          </td>
        </tr>
      </c:forEach>
      </table>
    </div>
  </section>
</div>

<script type="text/javascript">

pageSetUp();

var  myNewChart;


var chartFunction = function() {

var doughnutOptions = {
segmentStrokeWidth : 2,
//Number - The percentage of the chart that we cut out of the middle
percentageInnerCutout : 50, // This is 0 for Pie charts
//Number - Amount of animation steps
animationSteps : 100,
//String - Animation easing effect
animationEasing : "easeOutBounce",
//Boolean - Whether we animate the rotation of the Doughnut
animateRotate : true,
//Boolean - Whether we animate scaling the Doughnut from the centre
animateScale : false
//Boolean - Re-draw chart on page resize
//responsive: true
};
  var completeSize = "${completeSize}";

  var unfinishedSize = "${unfinishedSize}";
  var taskCountSize = "${taskCountSize}";
  var completePercent = Math.round(completeSize*100/taskCountSize);
  var unfinishedPercent = Math.round(unfinishedSize*100/taskCountSize);
var doughnutData = [
{
value: completePercent,
color:"#58C583",
label: "已完成"
},
{
value: unfinishedPercent,
color: "#FF7777",
label: "未完成"
}
];

// render chart
var ctx = document.getElementById("doughnutChart").getContext("2d");
myNewChart = new Chart(ctx).Doughnut(doughnutData, doughnutOptions);
};
loadScript("/smartAdmin/js/plugin/chartjs/chart.min.js", chartFunction);
var pagedestroy = function(){

  //destroy all charts
  myNewChart.destroy();
  myNewChart=null;
};
<c:forEach items="${taskCounts}" var="count">
  var countId = "#"+"${count.responsiblePersonOid}";
  var countId2 = "#"+"${count.responsiblePersonOid}"+"2";
  var completeCount = ${count.completeCount};
  var responsibleCount = ${count.responsibleCount};
  var  percent = completeCount/responsibleCount*100+"%";
  $(countId).css({'width':percent});
  $(countId2).css({'width':percent});
</c:forEach>
var completeSize = "${completeSize}";
var taskCountSize = "${taskCountSize}";
var completePercent =  Math.round(completeSize*100/taskCountSize);
var countNumber = completePercent+"%";
$('#count').html("任务进展情况总览（完成率 "+countNumber+" ）&nbsp;&nbsp;&nbsp;&nbsp;");


</script>
</c:if>