//*公告页面js
//*作者：sc
//*时间：2015年09月15日

/*鼠标悬停出发事件 Create by sc 2014.07.09*/
(function ($) {
    $.fn.hoverDelay = function (options) {
        var defaults = {
            hoverDuring: 300,
            outDuring: 300,
            hoverEvent: function () {
                $.noop();
            },
            outEvent: function () {
                $.noop();
            }
        };
        var sets = $.extend(defaults, options || {});
        var hoverTimer, outTimer;
        return $(this).each(function () {
            $(this).hover(function () {
                clearTimeout(outTimer);
                hoverTimer = setTimeout(sets.hoverEvent, sets.hoverDuring);
            }, function () {
                clearTimeout(hoverTimer);
                outTimer = setTimeout(sets.outEvent, sets.outDuring);
            });
        });
    }
})(jQuery);
/*鼠标悬停出发事件*/

//获取鼠标屏幕位置
function getScrollLeft() {
    var d = document;
    return window.pageXOffset || d.documentElement.scrollLeft || d.body.scrollLeft;
};
function getScrollTop() {
    var d = document;
    return window.pageYOffset || d.documentElement.scrollTop || d.body.scrollTop;
};
var xy = { x: 0, y: 0 };
$(document).mousemove(function (e) {
    e = window.event || e;
    xy.x = e.clientX;
    xy.y = e.clientY;
});
function getMousePosition() {
    return {
        x: getScrollLeft() + xy.x,
        y: getScrollTop() + xy.y
    };
};
var ggid = "noticeId";
var curPage = "1"; var pageSize = "10";
var donum = 0;
var ram = 1000000;

$(document).ready(function () {
    $(".imgTx").hover(function () {
        var yhbh = $(this).attr("yhbh");
        //            alert(aaa);
        $("#hf_mp").val(yhbh);
    }, function () {
        $("#hf_mp").val("");
    });
    //悬停效果提示窗口
    $(".imgTx").hoverDelay({
        hoverDuring: 300,
        outDuring: 0,
        hoverEvent: function () {
            //alert("划入并悬停！");
            var yhbh = $("#hf_mp").val();

            $.ajax({
                type: "GET",
                url: "../personManage/find.do?id="+yhbh,
                contentType: "application/json",
                dataType: "json",
                success: function (data) {
                    if (data.userEname != "") {
                        var _div = $("<div style=\"width: 100%; height: 100%; background-color:#fff;\" />");

                        var _div_left = $("<div style=\"width: 80px; height: 100%; float: left; text-align:center;\" />");
                        _div_left.append("<img src=\"/EasyUiCompoment/noticeModel/img/pic000.jpg\"  alt=\"" + data.userCname + "\" style=\"width: 70px; margin:15px auto auto 15px; border: 1px solid #000;\" />");

                        var _div_right = $("<div style=\"width: 200px; height: auto; float: right; background-color:#fff; padding-left:0px;margin-top:10px;\" />");
                        _div_right.append("<h1 style=\"font-size: 14px; text-align: left; padding-left: 5px;\">" + data.userCname + "</h1>");
                        _div_right.append("<p><font style=\" font-weight:bold\">所在部门：</font>" + data.personUnitname + "</p>");
                        _div_right.append("<p><font style=\" font-weight:bold\">职    务：</font>" + data.duty + "</p>");
                        _div_right.append("<p><font style=\" font-weight:bold\">办公电话：</font>" + data.officephone + "</p>");
                        _div_left.appendTo(_div);
                        _div_right.appendTo(_div);
                        $("#div_mp").empty();
                        _div.appendTo($("#div_mp"));
                    }

                },
                error: function (err) {
                    alert("操作失败！原因：" + err);
                }

            });
            var pos = getMousePosition();
            //alert("x:" + pos.x + ", y:" + pos.y);
            if ($(window).height() - pos.y + getScrollTop() > 240) {  //鼠标距离页面底部170像素以上
                $('#div_mp').window('move', {
                    left: pos.x + 14,
                    top: pos.y
                });
            }
            else { //鼠标距离页面底部170像素以下
                $('#div_mp').window('move', {
                    left: pos.x + 14,
                    top: pos.y - 215
                });
            }
            $('#div_mp').window('open');
        },
        outEvent: function () {
            //alert("划出！");
            $('#div_mp').window('close');
            $("#div_mp").empty();
        }
    });

    GetInfoNum(); //获取公告浏览数,评论数,回执数
    loadFyZ(); //加载分页
    loadGGPL(); //加载评论数据

    //添加分页控件
    $("#demo1").click(function () {
        curPage = $(this).find(".jPag-current").text();
        loadGGPL();
    });

    //发布评论
    $(".btn_submit").click(function () {
        var plContent = $(".gg_pinglun_fabu_text").val();
        if (plContent != "") {
            $.ajax({
                type: "POST",
                url: "../myRoomManager/save1.do?content="+plContent+"&noticeid="+infoId,
               /* data: { "content":plContent, "noticeid":infoId },*/
                contentType: "application/json",
                dataType: "json",
                success: function (data) {
                 
                        $(".gg_pinglun_fabu_text").val("");
                        GetInfoNum(); //获取公告浏览数,评论数,回执数
                        loadFyZ(); //加载分页
                        loadGGPL(); //加载评论数据
               
                },
                error: function (err) {
                    alert("操作失败！原因：" + err);
                }
            });
        }
        else {
            alert("请输入评论内容，再发布评论！");
        }
    });
    //滑动到评论处
    $(".a_GoPinglun").click(function () {
        var $elem = $('.gg_page');
        $('html, body').animate({ scrollTop: $elem.height() }, 500);
    })


    //获取公告浏览数,评论数,回执数
    function GetInfoNum() {
            $.ajax({
                type: "GET",
                url: "../myRoomManager/findNumByInfoId.do?id="+infoId+"&numb="+numb,
                contentType: "application/json",
                dataType: "json",
                success: function (data) {
                    $(".nums_yd").text(data.numLiulan);
                    $(".nums_pl").text(data.numPinglun);
                },
                error: function (err) {
                    alert("操作失败！原因：" + err);
                }
            });
        
    }
    //加载公告评论json数据
    function loadGGPL() {
        $.ajax({
            type: "GET",
            url: "../myRoomManager/findMessageByInfoId.do?id="+infoId+"&rows=10&page="+curPage,
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                //loadPLHF("B05743B8-5300-44A1-9281-66B131EFC346");
                var _ul = $("<ul />");
                _ul.addClass("pinglun_ul");
                $(data).each(function (index, json) {
                    var _li = $("<li />");
                    _li.addClass("pinglun_li");
                    _li.append("<input id='hf_pinglun' class='hf_pinglun' type='hidden' value='" + json.id + "' />");
                    var _div_pinglun_left = $("<div />");
                    _div_pinglun_left.addClass("pinglun_left");
                   if(json.usersex ==0){
                	   _div_pinglun_left.append("<img src='/EasyUiCompoment/noticeModel/img/pic000.jpg' yhbh='" + json.name + "' class='imgTx' style='cursor:pointer' /><br />");   
                   }else if(json.usersex ==1){
                	   _div_pinglun_left.append("<img src='/EasyUiCompoment/noticeModel/img/pic0001.jpg' yhbh='" + json.name + "' class='imgTx' style='cursor:pointer' /><br />");  
                   }
                    _div_pinglun_left.append("<span style='color: #915833;'>" + json.name + "</span>");
                    _div_pinglun_left.appendTo(_li);

                    var _div_pinglun_right = $("<div />");
                    _div_pinglun_right.addClass("pinglun_right");
                    var _h1 = $("<h1 />");
                    if (cur_ryxxid != json.createid) {
                        _h1.append("<span><a href='javascript:void(0)' class='a_huifu'>回复</a></span><font style='font-weight: bold'>" + ((curPage - 1) * pageSize + index + 1) + "楼</font>&nbsp;&nbsp;评论时间:" + json.createtime);
                    }
                    else {
                        _h1.append("<span><a href='javascript:void(0)' class='a_huifu'>回复</a>&nbsp;<a href='javascript:void(0)' class='a_pinglun_del'>删除</a></span><font style='font-weight: bold'>" + ((curPage - 1) * pageSize + index + 1) + "楼</font>&nbsp;&nbsp;评论时间:" + json.createtime);
                    }
                    var _div_pinglun_content = $("<div />");
                    _div_pinglun_content.addClass("pinglun_content");
                    _div_pinglun_content.append(json.content);
                    var _div_pinglun_huifu = $("<div />");
                    _div_pinglun_huifu.addClass("pinglun_huifu");

                    loadPLHF(json.id, _div_pinglun_huifu, index);

                    var _div_pinglun_huifu_fabu = $("<div />");
                    _div_pinglun_huifu_fabu.addClass("pinglun_huifu_fabu");
                    _div_pinglun_huifu_fabu.css("display", "none");
                    _div_pinglun_huifu_fabu.append("<textarea class='pinglun_huifu_text' id='Textarea1' rows='3' cols=''></textarea>");
                    var _div_div = $("<div />");
                    _div_div.append("<input id='pinglun_huifu_fb' class='btn_huifu_submit' type='button' value='发表' />");
                    _div_div.append("<input id='pinglun_huifu_qx' class='btn_huifu_qx' type='button' value='取消' />");
                    _div_div.appendTo(_div_pinglun_huifu_fabu);
                    _h1.appendTo(_div_pinglun_right);
                    _div_pinglun_content.appendTo(_div_pinglun_right);
                    _div_pinglun_huifu.appendTo(_div_pinglun_right);
                    _div_pinglun_huifu_fabu.appendTo(_div_pinglun_right);
                    _div_pinglun_right.appendTo(_li);
                    $(_li).appendTo(_ul);
                });
                $(".div_pinglun_ul").empty();
                _ul.appendTo($(".div_pinglun_ul"));

                //显示评论回复输入界面
                $(".a_huifu").click(function () {
                    //$(this).parent().parent().parent().find(".pinglun_huifu_fabu").css("display", "");
                    $(this).parent().parent().parent().find(".pinglun_huifu_fabu").show(500);
                });

                //删除评论
                $(".a_pinglun_del").click(function () {
                    if (confirm("确定要删除数据吗")) {
                        var delid = $(this).parent().parent().parent().parent().find($(".hf_pinglun")).val();
                        DelOnePl(delid);
                    }
                });

                //提交评论回复
                $(".btn_huifu_submit").click(function () {
                    var hfContent = $(this).parent().parent().find($(".pinglun_huifu_text")).val();
                    var plid = $(this).parent().parent().parent().parent().find($(".hf_pinglun")).val();
                    //alert(hfContent + "|" + plid);
                    if (hfContent != "") {
                        var obj = $(this).parent().parent().parent().find($(".pinglun_huifu"));
                        AddOneHf(hfContent, plid, obj);

                        $(this).parent().parent().find($(".pinglun_huifu_text")).val(""); //清空输入框
                        $(this).parent().parent().hide(500); //隐藏回复提交区域
                    }
                    else {
                        alert("请输入回复内容，再发布评论回复！");
                    }
                });
                //取消评论回复（隐藏评论回复界面）
                $(".btn_huifu_qx").click(function () {
                    //$(this).parent().parent().css("display", "none");
                    $(this).parent().parent().find($(".pinglun_huifu_text")).val("");
                    $(this).parent().parent().hide(500);
                });

            },
            error: function (err) {
                alert("操作失败！原因：" + err);
            }
        });
    }
    
    //加载评论的回复json数据
    function loadPLHF(plid, o, i) {
        $.ajax({
            type: "GET",
            url: "../myRoomManager/findPinlunHuifuByid.do?id="+plid,
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                //alert(dataHF.d);
                var _ul = $("<ul />");
                $(data).each(function (index, json) {
                    var _li = $("<li />");
                    _li.append("<input id='hf_huifu' class='hf_huifu' type='hidden' value='" + json.id + "' />");
                    if(json.usersex ==0){
                    	 _li.append("<img  src='/EasyUiCompoment/noticeModel/img/pic000.jpg' yhbh='" + json.createid + "' class='imgTx' style='cursor:pointer' />");
                    }else if(json.usersex ==1){
                    	 _li.append("<img  src='/EasyUiCompoment/noticeModel/img/pic0001.jpg' yhbh='" + json.createid + "' class='imgTx' style='cursor:pointer' />"); 
                    }
                    var _div = $("<div />");
                    if ( cur_ryxxid != json.createid) {
                        _div.append("<font color='#915833'>" + json.name + "</font>" + json.content);
                    }
                    else {
                        _div.append("<font color='#915833'>" + json.name + "</font>" + json.content + "&nbsp;<a href='javascript:void(0)' class='a_huifu_del" + i + "'>删除</a>");
                    }

                    _div.appendTo(_li);
                    _li.appendTo(_ul);



                });
                o.empty();
                _ul.appendTo(o);
                //删除回复
                $(".a_huifu_del" + i).click(function () {
                    if (confirm("确定要删除数据吗")) {
                        //alert("删除回复！");
                        var hfid = $(this).parent().parent().find($(".hf_huifu")).val();
                        //alert(hfid);
                        var plid = $(this).parent().parent().parent().parent().parent().parent().find($(".hf_pinglun")).val();
                        var obj = $(this).parent().parent().parent().parent();
                        //alert(plid);
                        DelOneHf(hfid, plid, obj);

                    }
                });
            },
            error: function (err) {
                alert("操作失败！原因：" + err);
            }
        });
    }
    //删除一条回复
    function DelOneHf(hfid, plid, obj) {
        $.ajax({
            type: "GET",
            url: "../myRoomManager/delPinlunHuifuByid.do?id="+hfid,
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                if (data == "1") {
                    //loadGGPL(); //加载评论数据
                    ram += 1;
                    //alert(ram);
                    loadPLHF(plid, obj, ram); //重新加载该评论的回复
                }
            },
            error: function (err) {
                alert("操作失败！原因：" + err);
            }
        });
    }
    //发布一条评论回复
    function AddOneHf(hfContent, plid, obj) {
        $.ajax({
           type: "POST",
           url: "../myRoomManager/AddOneHf.do?hfContent="+hfContent+"&plid="+plid,
          /*  data: "{'hfContent':'" + hfContent + "','plid':'" + plid + "','ryid':''}",*/
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                if (data.d == "1") {
                    //loadGGPL(); //加载评论数据
                    ram += 1;
                    //alert(ram);
                    loadPLHF(plid, obj, ram); //重新加载该评论的回复
                }
            },
            error: function (err) {
                alert("操作失败！原因：" + err);
            }
        });
    }
    //删除一条评论
    function DelOnePl(plid) {
        $.ajax({
        	type: "GET",
            url: "../myRoomManager/delPinlunHuifuByid.do?id="+plid,
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                if (data > 0) {
                    GetInfoNum(); //获取公告浏览数,评论数,回执数
                    loadFyZ(); //加载分页
                    loadGGPL(); //加载评论数据
                }
            },
            error: function (err) {
                alert("操作失败！原因：" + err);
            }
        });
    }
    //加载分页
    function loadFyZ() {
        $.ajax({
            type: "GET",
            url: "../myRoomManager/findMessageNumByInfoId.do?id="+infoId,
            data: null,
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                var pageNums = parseInt(data);
                var disPage = 5;
                if (pageNums > 0 && pageNums < 5) {
                    disPage = pageNums;
                }
                if (pageNums != 0) {
                    loadFY(pageNums, 1, disPage)
                }
            },
            error: function (err) {
                alert("操作失败！原因：" + err);
            }
        });
    }
    function loadFY(cou, sta, dis) {
        $("#demo1").paginate({
            count: cou,
            start: sta,
            display: dis,
            border: true,
            border_color: '#fff',
            text_color: '#fff',
            background_color: 'black',
            border_hover_color: '#ccc',
            text_hover_color: '#000',
            background_hover_color: '#fff',
            images: false,
            mouse: 'press'
        });
    }
});