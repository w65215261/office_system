<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="inbox-content" class="inbox-body no-content-padding" style="height: auto;width: auto">
  <div class="inbox-side-bar" >
    <ul class="inbox-menu-sm">
      <li>
        <h2 class="page-title txt-color-blueDark hidden-tablet"><i class="fa fa-fw fa-list-alt"></i> 查询 &nbsp;</h2>
        <ul>
          <select id="year" style="margin-left: 88px;">
            <option value="${year-2}" onclick="refreshMonthCalendar()">${year-2}</option>
            <option value="${year-1}" onclick="refreshMonthCalendar()" >${year-1}</option>
            <option value="${year}" selected="true" onclick="refreshMonthCalendar()">${year}</option>
            <option value="${year+1}" onclick="refreshMonthCalendar()">${year+1}</option>
          </select>
          <li>
            <c:forEach items="${month}" var="m" varStatus="t">
              <a href="javascript:void(0)" id="${t.count}report" onclick=refreshDayCalendar("${m}")>${m}月</a>
            </c:forEach>
          </li>
        </ul>
      </li>
    </ul>
  </div>
  <div class="table-wrap   animated fast fadeInRight">
    LOADING...
  </div>
</div>

<script type="text/javascript">

  pageSetUp();
  var pagefunction = function() {
    loadInbox();
    function loadInbox() {
      var nowTime=new Date();
      var month=nowTime.getMonth()+1;
      for(var i=1;i<=12;i++){
        var s=month+'report';
        var s1=i+'report'
        if(i==month){
          $("#"+s).css("background-color","#F0EFEE");
        }else{
          $("#"+s1).css("background-color","");
        }
      }
      loadURL('/search/load.do', $('#inbox-content > .table-wrap'));
    }
    $(".inbox-load").click(function() {
      loadInbox();
    });
  };
  loadScript("/smartAdmin/js/plugin/delete-table-row/delete-table-row.min.js", pagefunction);

  function refreshDayCalendar(month){
    var year=$("#year").val();
    if(month<10){
      var months=+0+month;
    }else{
         var   months=month;
  }
    var s=year+'-'+months+'-'+'01';
    var flag=$("#flag").val();
    if(flag==1){
      loadURL('/report/refreshCalendarWeek.do?belongsDate='+year+'&month='+month+'&flag='+flag+'', $('#s2'));
    }else{
      loadURL('/search/load.do?date='+s+'', $('#inbox-content > .table-wrap'));

    }
    for(var i=1;i<=12;i++){
      var s=i+'report'
      if(i==month){
        $("#"+s).css("background-color","#F0EFEE");
      }else{
        $("#"+s).css("background-color","");
      }
    }
  }
  function refreshMonthCalendar(){
    var year=$("#year").val();
    var month='01';
    var s=year+'-'+month+'-'+'01';
    var flag=$("#flag").val();
    if(flag==1){
      loadURL('/report/refreshCalendarWeek.do?year='+year+'&month='+month+'&flag='+flag+'', $('#s2'));
    }else{
      loadURL('/search/load.do?date='+s+'', $('#inbox-content > .table-wrap'));
    }

    for(var i=1;i<=12;i++){
      var s=i+'report';
      var s1=1+'report';
      if(i==month){
        $("#"+s1).css("background-color","#F0EFEE");
      }else{
        $("#"+s).css("background-color","");
      }
    }
  }
</script>