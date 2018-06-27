<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!-- Bread crumb is created dynamically -->
<!-- row -->

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

        <!-- NEW WIDGET START -->
        <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

            <!-- Widget ID (each widget will need unique ID)-->
            <div class="jarviswidget" id="wid-id-10" data-widget-editbutton="false">
                <!-- widget options:
                usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">

                data-widget-colorbutton="false"
                data-widget-editbutton="false"
                data-widget-togglebutton="false"
                data-widget-deletebutton="false"
                data-widget-fullscreenbutton="false"
                data-widget-custombutton="false"
                data-widget-collapsed="true"
                data-widget-sortable="false"

                -->
                <header>
                    <h2><a href="#/groupDiscuss/showNew.do"> 我的小组话题 </a> > <a href="#/groupDiscuss/toList.do?groupInfoOid=${ groupInfo.id} ">${groupInfo.groupName}</a>>新增发言</h2>
                    <div class="widget-toolbar">

                    </div>
                </header>

                <!-- widget div-->
                <div>

                    <!-- widget edit box -->
                    <div class="jarviswidget-editbox">
                        <!-- This area used as dropdown edit box -->

                    </div>
                    <!-- end widget edit box -->

                    <!-- widget content -->
                    <div class="widget-body no-padding">


                        <!-- row -->
                        <div class="row">

                            <div class="col-sm-12">

                                <div class="well">

                                    <table class="table table-striped table-forum">

                                        <tbody>



                                        <!-- Post -->
                                        <tr>
                                            <td class="text-center">

                                            </td>
                                            <td><input type="text" class="form-control input-lg" name="topicTitle" id="topicTitle" placeholder="请输入发言的标题" /></td>
                                        </tr>
                                        <tr>
                                            <td class="text-center" style="width: 12%;">
                                                <div class="push-bit">
                                                    <a href="#ajax/profile.html">
                                                        <img src="/smartAdmin/img/avatars/sunny.png" width="50" alt="avatar" class="online">
                                                    </a>
                                                </div>
                                                <small>473 Posts</small>
                                            </td>
                                            <td>

                                                <div id="forumPost"></div>
                                                <button class="btn btn-primary margin-top-10" onclick=postTopic(${groupInfo.id})>Post</button>
                                                <button class="btn btn-success margin-top-10">Save for later</button>

                                            </td>
                                        </tr>
                                        <!-- end Post -->

                                        </tbody>
                                    </table>



                                </div>
                            </div>

                        </div>








                    </div>
                    <!-- end widget content -->

                </div>
                <!-- end widget div -->

            </div>
            <!-- end widget -->


            <!-- end widget -->


            <!-- end widget -->



        </article>
        <!-- WIDGET END -->

    </div>

    <!-- end row -->

    <!-- end row -->

</section>
<!-- end row -->

<!-- row -->

<div class="row">

    <!-- a blank row to get started -->

</div>

<!-- end row -->

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
     */

    // pagefunction

    var pagefunction = function() {

        $('#forumPost').summernote({
            height : 180,
            focus : false,
            tabsize : 2
        });

    };

    function postTopic(groupId){
        var shtml=$('#forumPost').code();
        var title=$('#topicTitle').val();
        $.ajax({
            type: "POST",
            url: '/groupDiscuss/postTopic.do',
            data:{groupInfoOid:groupId,discussion:shtml,discussTitle:title},
            success: function (data) {

                location.href="#/groupDiscuss/toList.do?groupInfoOid="+groupId;


              //  var	 container = $("#content")ii;
                //loadURL("/groupDiscuss/toList.do?groupId="+groupId, container);

            },
            error: function () {

            }
        });
    }
    // end pagefunction

    // load summernote editor
    loadScript("/smartAdmin/js/plugin/summernote/summernote.min.js", pagefunction);

</script>
