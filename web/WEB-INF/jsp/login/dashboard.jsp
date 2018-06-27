<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- Bread crumb is created dynamically -->
<link href="/smartAdmin/css/shouyangshi.css" rel="stylesheet" type="text/css" />

<div class="rowo" >


    <!-- end col -->

    <!-- right side of the page with the sparkline graphs -->
    <!-- col -->
    <div class="col-12">
        <dl>
            <dt><img src="/smartAdmin/img/shouye/tou.png" width="62" height="62"/></dt>
            <dd>
                <div class="_yingdenglu">

                    <span>${user}</span>
                    <ul>
                        <li>
                            <a href="#/message/show.do">
                                <b>未读邮箱</b>
                                <i>（${noReadBoxCount}）</i>


                            </a>
                        </li>
                        <li>
                            <a href="#/message/show.do">
                                <b>收件箱</b>
                                <i>（${inBoxCount}）</i>

                            </a>
                        </li>

                    </ul>


                </div>


            </dd>


        </dl>


        <!-- sparks -->

        <!-- end sparks -->

        <!-- star tianqi -->
        <div class="_tianqis" id="tianqis">
            <%--<iframe name="weather_inc" src="http://i.tianqi.com/index.php?c=code&id=2&num=2" width="330" height="70" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>--%>
            <iframe name="weather_inc" src="http://i.tianqi.com/index.php?c=code&id=8"  width="225" height="80" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
            <%--<ul>--%>
                <%--<li><a href="javascript:void(0);" >城市：<b><span  id="city" style="border: hidden"></span></b></a></li>--%>
                <%--<li><a href="javascript:void(0);"><span type="text" id="week"> </span>&nbsp;&nbsp;&nbsp;&nbsp; </a></li>--%>
                <%--<li><a href="javascript:void(0);" >今日: <span id="wendu"></span></a></li>--%>
                <%--<li><a href="javascript:void(0);" >明日: <span id="wendu1"></span></a></li>--%>


            <%--</ul>--%>


        </div>
        <!-- end tianqi -->

        <div class="clear"></div>

    </div>
    <!-- end col -->


    <div class="_two">
        <!-- star _two -->
        <!--star_xinwen-->

        <div class="_xinwen">
            <div class="_neirong">
                <ul>
                    <li>公</li>
                    <li>告</li>
                    <li>栏</li>



                </ul>


            </div>

            <div class="_xiangqing" style="min-height: 171px;">

                    <c:forEach items="${newsList}" var="nl">
                        <a href="javascript:void(0);" onclick="new_window('${nl.id}')">
                        <dl>
                            <dt>${nl.title}</dt>
                            <dd><fmt:formatDate pattern="yyyy-MM-dd" value="${nl.rptDate}"/></dd>
                        </dl>
                        </a>
                    </c:forEach>


            </div>


        </div>
        <!--end _xinwen-->

        <div class="_xinwen">
            <div class="_neirong">
                <ul>
                    <li>日</li>
                    <li>程</li>



                </ul>


            </div>

            <div class="_xiangqing" style="min-height: 171px;">
                <c:forEach items="${programmeList}" var="pl">
                    <a href="javascript:void(0);" onclick="pro_window('${pl.oid}')">
                        <dl>
                            <dt>${pl.title}</dt>
                            <dd><fmt:formatDate pattern="yyyy-MM-dd" value="${pl.startTime}"/></dd>
                        </dl>
                    </a>
                </c:forEach>

            </div>
        <%--<div class="_richeng">--%>
            <%--<div class="_neirong">--%>
                <%--<ul>--%>
                    <%--<li>日</li>--%>
                    <%--<li>程</li>--%>


                    <%--<span><a href="#">详情</a></span>--%>


                <%--</ul>--%>
            <%--</div>--%>
            <%--<div class="_neirongt">--%>
            <%--</div>--%>
        <%--</div>--%>
<%--<span class="_neirongt">--%>
<%--<textarea name="textarea" cols="" rows="" style=" width:500px--%>
        <%--; height:150px">--%>
                <%--<c:forEach items="${programmeList}" var="pl">--%>
                    <%--<a href="#">--%>
                        <%--<dl>--%>
                            <%--<dt>习近平用数字编织的民生保障网</dt>--%>
                            <%--<dd>2016-1-14</dd>--%>
                        <%--</dl>--%>
                    <%--</a>--%>
                <%--</c:forEach>--%>

       <%--</textarea>--%>
<%--</span>--%>

        <!--     <div class="_xiangqing">
              <a href="#">
               <dl>
                 <dt>习近平用数字编织的民生保障网</dt>
                 <dd>2016-1-14</dd>


               </dl>
              </a>


                    <a href="#">
               <dl>
                 <dt>习近平用数字编织的民生保障网</dt>
                 <dd>2016-1-14</dd>


               </dl>
                    </a>
                    <a href="#">
                    <dl>
                      <dt>习近平用数字编织的民生保障网</dt>
                      <dd>2016-1-14</dd>


                    </dl>
                    </a>

                    <a href="#">
               <dl>
                 <dt>习近平用数字编织的民生保障网</dt>

                 <dd>2016-1-14</dd>


               </dl>
              </a>-->


    </div>
</div>
<!--<div class="tab-pane" id="hr2" style="overflow:hidden">

<ul class="nav nav-tabs">
<li class="active">
<a href="#iss1" data-toggle="tab">计划</a>
</li>
<li>
<a href="#iss2" data-toggle="tab">总结</a>
</li>
<li>
<a href="#iss3" data-toggle="tab">建议</a>
</li>
</ul>
<div class="tab-content padding-10">
<div class="tab-pane active" id="iss1">
<p>
节目名称：新闻大求真节目简介：湖南卫视中国第一家传言求证中心《新闻大求真》2012年7月4日起每周三至周五18:00播出。传言到底哪个是真哪个是假？最顶尖的实验室，最权威的专家，告诉你最可信的答案。播出时间：每周三到周五18点左右。
</p>
</div>
<div class="tab-pane fade" id="iss2">
<p>
【特别报道】
　　新年伊始，中国元首外交将再度扬帆起航。1月19日至23日，中国国家主席习近平将对沙特、埃及和伊朗进行国事访问。新年首访“花落”中东，可见中国领导人对这一地区的高度重视。此访将有助于加强中国与中东地区的战略合作，让世界在中东热点问题上听到“中国声音”，对于我国在新的一年深耕中东“朋友圈”、完善全方位外交布局具有领航作用。
</p>
</div>
<div class="tab-pane fade" id="iss3">
<p>
1月16日，MIC男团成员赵泳鑫的经纪人刘奕辰以“艺人的善良不是让你放肆的资本”为题发布长微博，称疯狂女粉丝非法入侵赵泳鑫家，被家中监控摄像拍到，警察入屋抓人发现该女赤身裸体的躺在艺人家中的浴缸里洗澡。
</p>
</div>
</div>


</div>
<!-- end widget content -->


<div class="clear"></div>


<div class="jarviswidget well" id="wid-id-3" data-widget-colorbutton="false" data-widget-editbutton="false"
     data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false"
     data-widget-custombutton="false" data-widget-sortable="false" >


    <div class="widget-body">


        <ul id="myTab1" class="nav nav-tabs bordered">
            <li class="active">
                <a href="#s1" data-toggle="tab">管理类</a>

            </li>
            <li>
                <a href="#s2" data-toggle="tab">工时类 </a>
            </li>
            <li>
                <a href="#s3" data-toggle="tab">信息类 </a>
            </li>
            <li>
                <a href="#s4" data-toggle="tab">请假类 </a>
            </li>

        </ul>
        </li>

        </ul>
        <p>&nbsp;</p>

        <div id="myTabContent1" class="tab-content padding-10" style=" border:0px ; ">
            <div class="tab-pane fade in active" id="s1">



                <div class="co-sm-1">
                    <a href="/authority/backIndexShow.do"><img style="height: 60px" src="/smartAdmin/img/logo/houtaiguanli.png"
                                                               alt="image with rounded corners"
                                                               class="img-responsive                                          img-rounded"/></a>
                    <ul class="list-inline">
                        <li>后台管理
                        </li>
                    </ul>
                </div>

                <div class="co-sm-1">

                    <a href="#/project/show.do"> <img style="height: 60px" src="/smartAdmin/img/logo/xiangmuguanli.png"
                                                      alt="image with rounded corners"
                                                      class="img-responsive img-rounded"> </a>
                    <ul class="list-inline">
                        <li>项目管理
                        </li>
                    </ul>
                </div>
                <div class="co-sm-1">

                    <a href="#/plan/showPlanDate.do"> <img style="height:60px" src="/smartAdmin/img/logo/jihuaguanli.png"
                                                           alt="image with rounded corners"
                                                           class="img-responsive img-rounded"> </a>
                    <ul class="list-inline">
                        <li>计划管理
                        </li>
                    </ul>
                </div>

            </div>
            <div class="tab-pane fade" id="s2">

                <div class="co-sm-1">

                    <a href="#/workHour/show.do"> <img style="height: 60px" src="/smartAdmin/img/logo/gongshibaosong.png"
                                                       alt="image with rounded                                      corners"
                                                       class="img-responsive img-rounded"></a>
                    <ul class="list-inline">
                        <li>工时报送
                        </li>
                    </ul>
                </div>
                <div class="co-sm-1">

                    <a href="#/workHour/showSearch.do"> <img style="height:60px" src="/smartAdmin/img/logo/gongshibaosongchaxun.png"
                                                             alt="image with rounded corners"
                                                             class="img-responsive img-rounded"></a>
                    <ul class="list-inline">
                        <li>工时查询
                        </li>
                    </ul>
                </div>

            </div>
            <div class="tab-pane fade" id="s3">


                <div class="co-sm-1">

                    <a href="/authority/backIndexShow.do"> <img style="height: 60px" src="/smartAdmin/img/logo/pingtaixinxi.png"
                                                                alt="image with rounded corners"
                                                                class="img-responsive                                          img-rounded"></a>
                    <ul class="list-inline">
                        <li>即时通讯
                        </li>
                    </ul>
                </div>


                <div class="co-sm-1">

                    <a href="#/message/show.do"> <img style="height: 60px" src="/smartAdmin/img/logo/zhanneixin.png"
                                                      alt="image with rounded corners"
                                                      class="img-responsive img-rounded"></a>
                    <ul class="list-inline">
                        <li>站内信
                        </li>
                    </ul>
                </div>

            </div>
            <div class="tab-pane fade" id="s4">

                <div class="co-sm-1">

                    <a href="#/leave/leaveAsk.do"> <img style="height:60px" src="/smartAdmin/img/logo/qingjiashenqing.png"
                                                        alt="image with rounded corners"
                                                        class="img-responsive                                         img-rounded"></a>
                    <ul class="list-inline">
                        <li>请假申请
                        </li>
                    </ul>
                </div>

            </div>

        </div>

    </div>
</div>


</div>
<!--end two-->


</div>
<!-- end row -->

<!--
The ID "widget-grid" will start to initialize all widgets below
You do not need to use widgets if you dont want to. Simply remove
the <section></section> and you can use wells or panels instead
-->

<!-- widget grid -->
<section id="widget-grid" class="">

    <!-- row -->
    <div class="row">


        <!-- end row -->

        <!-- row -->


        <!-- end row -->

    </div>

</section>
<!-- end widget grid -->

<!-- end widget grid -->

<script type="text/javascript">

    /* DO NOT REMOVE : GLOBAL FUNCTIONS!
     *
     * pageSetUp(); WILL CALL THE FOLLOWING FUNCTIONS
     *
     * // activate tooltips
     * $("[rel=tooltip]").tooltip();
     *
     * // activate popovers
     * $("[rel=popover]").popover();
     *
     * // activate popovers with hover states
     * $("[rel=popover-hover]").popover({ trigger: "hover" });
     *
     * // activate inline charts
     * runAllCharts();
     *
     * // setup widgets
     * setup_widgets_desktop();
     *
     * // run form elements
     * runAllForms();
     *
     ********************************
     *
     * pageSetUp() is needed whenever you load a page.
     * It initializes and checks for all basic elements of the page
     * and makes rendering easier.
     *
     */

    pageSetUp();

    /*
     * ALL PAGE RELATED SCRIPTS CAN GO BELOW HERE
     * eg alert("my home function");
     *
     * var pagefunction = function() {
     *   ...
     * }
     * loadScript("js/plugin/_PLUGIN_NAME_.js", pagefunction);
     *
     * TO LOAD A SCRIPT:
     * var pagefunction = function (){
     *  loadScript(".../plugin.js", run_after_loaded);
     * }
     *
     * OR you can load chain scripts by doing
     *
     * loadScript(".../plugin.js", function(){
     * 	 loadScript("../plugin.js", function(){
     * 	   ...
     *   })
     * });
     */

    // pagefunction

    var pagefunction = function () {
        //java后台获取天气预报
//        $.getScript('http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js',function(){
//            alert(remote_ip_info.country+"国家");//国家
//            alert(remote_ip_info.province+"省份");//省份
//            alert(remote_ip_info.city+"城市");//城市
//
//        $.ajax({
//            type: 'GET',
//            url: '/signInfo/showWeather.do?cityName='+remote_ip_info.city,
//            dataType: 'JSON',
//            success: function(data) {
//                console.log("data1"+data);
//                console.log("data4"+data.forecast.img_title1)
//                $("#city").html(data.forecast.city);
//                $("#week").html(data.forecast.date_y);
//                $("#wendu").html(data.forecast.img_title1+data.forecast.temp1);
//                $("#wendu1").html(data.forecast.img_title2+data.forecast.temp2);
//                    if(data.forecast.img_title1.contains("多云")){
//                    $("#tianqi").attr("style","background:url('/smartAdmin/img/shouye/tianqi33.png') no-repeat; padding-left:80px;  overflow:hidden; float:right; height:100px");
//                }else if ("晴"==data.forecast.img_title1){
//                        $("#tianqi").attr("style","background:url('/smartAdmin/img/shouye/tianqi6.png') no-repeat; padding-left:80px;  overflow:hidden; float:right; height:100px");
//                    }else  if(data.forecast.img_title1.contains("雨")){
//                        $("#tianqi").attr("style","background:url('/smartAdmin/img/shouye/tianqi5.png') no-repeat; padding-left:80px;  overflow:hidden; float:right; height:100px");
//                    }else  if(data.forecast.img_title1.contains("雪")){
//                        $("#tianqi").attr("style","background:url('/smartAdmin/img/shouye/tianqi3.png') no-repeat; padding-left:80px;  overflow:hidden; float:right; height:100px");
//                    }else{
//                        $("#tianqi").attr("style","background:url('/smartAdmin/img/shouye/tianqi2.png') no-repeat; padding-left:80px;  overflow:hidden; float:right; height:100px");
//                    }
//            }
//        });
//        });
        // clears the variable if left blank
    };

    // end pagefunction

    // destroy generated instances
    // pagedestroy is called automatically before loading a new page
    // only usable in AJAX version!

    var pagedestroy = function () {

        /*
         Example below:

         $("#calednar").fullCalendar( 'destroy' );
         if (debugState){
         root.console.log("✔ Calendar destroyed");
         }

         For common instances, such as Jarviswidgets, Google maps, and Datatables, are automatically destroyed through the app.js loadURL mechanic

         */
    }

    // end destroy

    // run pagefunction
    pagefunction();

    function new_window(id){
         window.showModalDialog("/newsInfo/findNews.do?id="+id+"",window, "dialogHeight:550px;dialogWidth:720px;status=no;center:yes;dialogLeft:300px;dialogTop:200px;scroll:yes;resizable:no");
    }
    function pro_window(id){
        window.showModalDialog("/programmeManagement/queryProgra.do?oid="+id+"",window, "dialogHeight:550px;dialogWidth:720px;status=no;center:yes;dialogLeft:300px;dialogTop:200px;scroll:yes;resizable:no");
    }
//    function checkWeather(){
//        loadURL("/signInfo/showWeather.do", $('#inbox-content > .table-wrap'));
//    }
</script>
<style type="text/css">
    #tianqis {float:right; height:100px  }
</style>