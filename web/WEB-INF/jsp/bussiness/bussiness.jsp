<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script src="/sweetalert-master/dist/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="/sweetalert-master/dist/sweetalert.css">
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
<!-- widget grid -->
<section id="widget-grid" class="">



	<!-- row -->
	<div class="row">
		<!-- Widget ID (each widget will need unique ID)-->
		<article class="col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false"
				 data-widget-togglebutton="false"
				 data-widget-deletebutton="false" data-widget-fullscreenbutton="false"
				 data-widget-colorbutton="false"  data-widget-custombutton="true" data-widget-sortable="false">
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
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
					<h2 id="leaveReply">公司激活</h2>
					<div class="widget-toolbar">
						<input id="permitButton" class="btn  btn-primary btn-xs " type="button" value="激活">
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
						<table id="dt_basic" class="table table-striped table-bordered table-hover" width="100%">
							<thead>
							<tr>
								<th data-hide="phone"> 公司名称</th>
								<th data-class="phone">管理员中文名称</th>
								<th data-hide="phone"> 管理员英文名称</th>
								<th data-hide="phone"> 注册时间</th>
								<th data-hide="phone"> 激活时间</th>
								<th data-hide="phone"> 状态</th>
							</tr>
							</thead>
							<tbody>

							</tbody>
						</table>

					</div>
					<!-- end widget content -->

				</div>
				<!-- end widget div -->

			</div>
			<!-- end widget -->
		</article>
		<!-- WIDGET END -->
	</div>
	<!-- end row -->
</section>
<!-- end widget grid -->

<script type="text/javascript">

	pageSetUp();




	var pagefunction = function() {
		var table = $('#dt_basic').DataTable({
			// "oLanguage": oLanguages,

			"dom": "<'dt-toolbar'<'col-xs-12 col-sm-6'>>" +
			"t" +
			"<'dt-toolbar-footer'<'col-sm-4	  hidden-xs'i><'col-xs-4 col-sm-3'l><'col-xs-6 col-sm-5'p>>",
			"bFilter": true,
			"aLengthMenu": [[10, 20, 50, 100], [10, 20, 50, 100]],
			"aaSorting": [[0, "asc"]],
			"bAutoWidth": true,
			"bLengthChange": true,
			"iDisplayLength": 10,
			"bServerSide": true,
			"bStateSave": false,
			"sAjaxSource": "/organizations/queryBussiness.do",
			"oLanguage": {                          //汉化
				"sLengthMenu": "每页 _MENU_ 条记录",
				"sZeroRecords": "没有检索到数据",
				"sInfo": "第 _START_ 到第 _END_ 条数据,共有 _TOTAL_ 条",
				"sInfoEmpty": "<span class='text-danger'>没有数据</span>",
				"sProcessing": "正在加载数据...",
				"oPaginate": {
					"sFirst": "首页",
					"sPrevious": "前页",
					"sNext": "后页",
					"sLast": "尾页"
				}
			},
			"columnDefs": [

				{
					"targets": 0,
					"data": "orgCname",
					"sWidth": "10%"
				},
				{
					"targets": 1,
					"data": "userCname",
					"sWidth": "10%"
				},
				{
					"targets":2,
					"data": "userEname",
					"sWidth": "10%"
				},
				{
					"targets": 3,
					"data": "createDate",
					"sWidth": "10%"
				},
				{
					"targets": 4,
					"data": "activationTime",
					"sWidth": "10%"
				},
				{
					"targets": 5,
					"data": "delFlag",
					"sWidth": "10%",
					"render": function (odata) {
						if (odata == 0) {
							return "已激活";
						} else if (odata == 2) {
							return "未激活";
						}
					}
				}
			]
		});
		$('#dt_basic tbody').on( 'click', 'tr', function () {
			$(this).toggleClass('selected');
			//console.log($("#dt_basic").find(".selected"));
			//alert(table.rows('.selected').data())
			//alert(table.row(0).data());
		} );
		$('#permitButton').click( function () {
			// console.log( table.rows('.selected').row(0).data().id );
			// table.row('.selected').remove().draw( false );
			if (table.rows('.selected').data().length == 0) {
				swal("", "至少选择一条数据", "warning");
				return;
			}
			var ids='';
			for(var i=0;i<table.rows('.selected').data().length;i++){
				//console.log(table.rows('.selected').row(i).data().id);
				ids+=table.rows('.selected').data()[i].id+",";
			}
			$.ajax({
				type:"GET",
				url:'/organizations/updateCompany.do',
				data : {
					ids : ids
				},
				success:function(data){
					if(data == "success"){
						$('#dt_basic').DataTable().ajax.reload();
					}
				},
				// 调用出错执行的函数
				error: function(){
				}
			});
		});
	}

	loadScript("/smartAdmin/js/plugin/datatables/jquery.dataTables.min.js", function(){
		loadScript("/smartAdmin/js/plugin/datatables/dataTables.colVis.min.js", function(){
			loadScript("/smartAdmin/js/plugin/datatables/dataTables.tableTools.min.js", function(){
				loadScript("/smartAdmin/js/plugin/datatables/dataTables.bootstrap.min.js", function(){
					loadScript("/smartAdmin/js/plugin/datatable-responsive/datatables.responsive.min.js", pagefunction)
				});
			});
		});
	});

</script>

<style>
	table.dataTable tbody tr.selected {
		background-color: #b0bed9;
	}

</style>


