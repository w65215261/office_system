<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<!-- widget content -->
    <div style="width:300px;">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title"><p>选择审批人</p></h4>
        </div>
        <div class="modal-body no-padding">
          <div class="custom-scroll table-responsive" style="height:250px; overflow-y: scroll;">
            <div id="organTree">
            </div>
          </div>
        </div>
      </div>
    </div>

<script type="text/javascript">
  loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-tree.min.js");
  loadScript("/smartAdmin/js/plugin/bootstraptree/bootstrap-treeview.js", renderTree);
  function renderTree() {
    $.ajax({
      type: "GET",
      url: '/organization/queryAlltree.do',
      data: {},
      success: function (data) {
        $('#organTree').treeview({
          data: data,
          onNodeSelected: function (event, data) {
            if(data.icon=="glyphicon glyphicon-tower"){
              return ;
            }else{
              $('#receiverLabel').val(data.text);
              var personId=data.href;
              $('#approvePersonName').val(personId);
              $("#approvePersonId1").popover('hide');
            }
          }
        });
      },
      // 调用出错执行的函数
      error: function () {
      }
    });
  }
</script>