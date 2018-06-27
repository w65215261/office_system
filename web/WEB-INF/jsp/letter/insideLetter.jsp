<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>






<div class="inbox-nav-bar no-content-padding">

    <h1 class="page-title txt-color-blueDark hidden-tablet"><i class="fa fa-fw fa-inbox"></i> 站内信 &nbsp;
    </h1>
    <input type="hidden" id="showFlag" value="1">

</div>



<div id="inbox-content" class="inbox-body no-content-padding">

    <div class="inbox-side-bar">

        <a href="javascript:void(0);" id="compose-mail" class="btn btn-primary btn-block"> <strong>写信</strong> </a>


        <ul class="inbox-menu-lg">
            <li id="loadLi" class="active">
                <a class="inbox-load"  id="box-receive" href="javascript:void(0);"> 收件箱</a>
            </li>
            <li id="sendLi">
                <a  clas="inbox-send" id="box-send"  href="javascript:void(0);">发件箱</a>
            </li>

            <li id="trashLi">
                <a   class="inbox-garbage"  id="box-garbage" href="javascript:void(0);">垃圾箱</a>
            </li>
        </ul>

    </div>



    <div class="table-wrap custom-scroll animated fast fadeInRight">
        <!-- ajax will fill this area -->
        LOADING...

    </div>


</div>

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

    // PAGE RELATED SCRIPTS

    // pagefunction

    var pagefunction = function() {

        // fix table height
        tableHeightSize();

        $(window).resize(function() {
            tableHeightSize()
        })
        function tableHeightSize() {



        }

        /*
         * 查询收件箱
         */
        loadInbox();
        function loadInbox() {
            loadURL("/message/queryInbox.do", $('#inbox-content > .table-wrap'))
        }

        /*
         * Buttons (点击收件按钮查询)
         */
        $("#box-receive").click(function() {
            loadInbox();
            $("#trashLi").removeClass("active");
            $("#sendLi").removeClass("active");
            $("#loadLi").addClass("active");
            $("#showFlag").val("1");

        });



       /*
         * Buttons (点击发件按钮查询)
         */
        $("#box-send").click(function() {
            $("#loadLi").removeClass("active");
            $("#trashLi").removeClass("active");
            $("#sendLi").addClass("active");
            loadURL("/message/queryOutbox.do", $('#inbox-content > .table-wrap'));
            $("#showFlag").val("2");
        });




        $("#box-garbage").click(function() {
            $("#loadLi").removeClass("active");
            $("#sendLi").removeClass("active");
            $("#trashLi").addClass("active");
            loadURL("/message/queryGarbage.do", $('#inbox-content > .table-wrap'));
            $("#showFlag").val("3");
        });



        // compose email
        $("#compose-mail").click(function() {
            loadURL("/message/composeEmail.do", $('#inbox-content > .table-wrap'));
        });

    };

    // end pagefunction





    // destroy generated instances
    // pagedestroy is called automatically before loading a new page
    // only usable in AJAX version!

    /*var pagedestroy = function(){

     // destroy summernote
     if (".summernote") {
     $(".summernote").summernote( 'destroy' );
     }

     // clear misc. click events
     //$(".inbox-load").off();

     //loadInbox = undefined;
     //tableHeightSize = undefined;

     // debug msg
     if (debugState){
     root.console.log("✔ Summernote editor destroyed");
     }

     }*/

    // end destroy

    // load delete row plugin and run pagefunction

    loadScript("/smartAdmin/js/plugin/delete-table-row/delete-table-row.min.js", pagefunction);




</script>
