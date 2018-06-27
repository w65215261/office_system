<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="inbox-content" class="inbox-body no-content-padding">

    <div class="inbox-side-bar">


        <nav id="inboxmenu">
            <ul class="inbox-menu-sm">
                <li>
                    <a href="#" onclick="projectList()" ><i class="fa fa-lg fa-fw fa-bar-chart-o"></i> <span class="menu-item-parent">我负责的</span></a>
                    <ul>
                        <c:forEach items="${projectList1}" var="d" varStatus="vs">
                               <a href="#" onclick=projectDetail("${d.id}") >${d.projectName}</a>
                        </c:forEach>
                    </ul>
                </li>
                <li>
                    <a href="#" onclick="projectPersonRelaList()"><i class="fa fa-lg fa-fw fa-bar-chart-o" style="font-size: 20px"></i> <span class="menu-item-parent">我参与的</span></a>
                    <ul>

                        <c:forEach items="${personProjectList}" var="ds">
                            <li>
                                <a href="#"  onclick=projectDetail("${ds.oid}") >${ds.project.projectName}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
            </ul>

        </nav>

    </div>


    <div class="table-wrap   animated fast fadeInRight">
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

        //初始化本Ajax页面的 nav组件，实现折叠与展开，参考代码app.min.js
        topmenu || $("#inboxmenu ul").jarvismenu({
            "accordion": menu_accordion || !0,
            "speed": menu_speed || !0,
            "closedSign": '<em class="fa fa-plus-circle"></em>',
            "openedSign": '<em class="fa fa-minus-circle"></em>'
        })
        /*
         * LOAD INBOX MESSAGES
         */
        loadInbox();
        function loadInbox() {
            //loadURL("/smartAdmin/ajax/email/email.html", $('#inbox-content > .table-wrap'))
            loadURL("/project/showList.do", $('#inbox-content > .table-wrap'))
        }

        /*
         * Buttons (compose mail and inbox load)
         */
        $(".inbox-load").click(function() {
            loadInbox();
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

    function projectDetail(projectId){

        loadURL("/project/findProjectByOid.do?projectId="+projectId, $('#inbox-content > .table-wrap'));
    }


    function projectList(){

        loadURL("/project/showList.do", $('#inbox-content > .table-wrap'));
    }

    $("#addProject").click(function() {
        loadURL("/project/addProject.do", $('#inbox-content > .table-wrap'));
    });
    function projectPersonRelaList(){
        loadURL("/project/showRelaList.do", $('#inbox-content > .table-wrap'));
    }
</script>



