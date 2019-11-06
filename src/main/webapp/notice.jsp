<%@page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>发件箱</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/moment.min.js"></script>
<script type="application/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
<script type="application/javascript">
	$(function(){
		$.ajax({
			type:"GET",
			url:"${pageContext.request.contextPath}/notice/jsonList",
			success:function(entity){
			    if(entity.map.statusCode == 200) {
                    $(entity.map.page.list).each(function(index,item){
                        var tr =" <tr name='appendtr' class='addtr' align='center' bgcolor='#FFFFFF' onMouseMove='javascript:this.bgColor='#FCFDEE';'onMouseOut='javascript:this.bgColor='#FFFFFF';' height='22' > "
                            +"<td><input name='id' type='checkbox' id='id' value='"+ item.nid +"' class='np'></td>"
                            +"<td>"+(index+1)+"</td>"
                            +"<td>"+item.ntitle+"</td>";
                        if(item.remark.length > 8){
                            tr+="<td align='center'>"+item.remark.substring(0,8)+"......</td>";
                        }else{
                            tr+="<td align='center'>"+item.remark+"</td>";
                        }
                        tr+="<td>"+moment(item.ndate).format('YYYY-MM-DD')+"</td>"
                            +"<td><a href='customer-edit.jsp'>删除</a> |"
                            +"<a href='customer-edit.jsp'>编辑</a> | "
                            +"<a href='customer-look.jsp'>查看详情</a></td> </tr>";
                        $("#tr-third").before(tr);
                    });
                }
                page(entity);
			}
		});
	});
	function changePage(href) {
        $.ajax({
            type:'GET',
            url:href,
            success:function(entity){
                if(entity.map.statusCode == 200) {
                    //进行数据展示时，清除页面原有的tr标签
                    $("tr[name=appendtr]").remove();
                    $("#div-dh").remove();

                    $(entity.map.page.list).each(function(index,item){
                        var tr =" <tr name='appendtr' class='addtr' align='center' bgcolor='#FFFFFF' onMouseMove='javascript:this.bgColor='#FCFDEE';'onMouseOut='javascript:this.bgColor='#FFFFFF';' height='22' > <td>"
                            +"<input name='id' type='checkbox' id='id' value='"+ item.nid +"' class='np'></td>"
                            +"<td>"+(index+1)+"</td>"
                            +"<td>"+item.ntitle+"</td>";
                        if(item.remark.length >8 ){
                            tr+="<td align='center'>"+item.remark.substring(0,8)+"......</td>";
                        }else{
                            tr+="<td align='center'>"+item.remark+"</td>";
                        }
                        tr+="<td>"+moment(item.ndate).format('YYYY-MM-DD')+"</td>"
                            +"<td><a href='customer-edit.jsp'>删除</a> |"
                            +"<a href='customer-edit.jsp'>编辑</a> | "
                            +"<a href='customer-look.jsp'>查看详情</a></td> </tr>";
                        $("#tr-third").before(tr);
                    });
                }
                page(entity);
            }
        });
        return false;
    }

    function page(entity){
        var div = "<div id='div-dh'></div>";
        var firstPage = "<a onclick='changePage(this.name)' href='javascript:void(0)' name='"+entity.map.requestURI+"?pageNum=1" + entity.map.queryStr+"'>首页</a>&nbsp;&nbsp;&nbsp;";
        var prePage = "<a onclick='changePage(this.name)' href='javascript:void(0)' name='"+entity.map.requestURI+"?pageNum="+(entity.map.page.pageNum-1) + entity.map.queryStr+"'>上一页</a>&nbsp;&nbsp;&nbsp;";
        var nextPage = "<a onclick='changePage(this.name)' href='javascript:void(0)' name='"+entity.map.requestURI+"?pageNum="+(entity.map.page.pageNum+1)+ entity.map.queryStr+"'>下一页</a>&nbsp;&nbsp;&nbsp;";
        var endPage = "<a onclick='changePage(this.name)' href='javascript:void(0)' name='"+entity.map.requestURI+"?pageNum="+(entity.map.page.pages) + entity.map.queryStr+"'>尾页</a>&nbsp;&nbsp;&nbsp;";
        var pages = "";
        $(entity.map.page.navigatepageNums).each(function (index,item) {
            pages = pages + "<a onclick='changePage(this.name)' href='javascript:void(0)' name='"+entity.map.requestURI+"?pageNum="+ item + entity.map.queryStr +"'>"+ item +"</a>&nbsp;页&nbsp;&nbsp;&nbsp;";
        })
        $(div).append(firstPage).append(prePage).append(pages).append(nextPage).append(endPage).appendTo($("#td-page"));
	}
</script>

</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>
<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:信息箱>>通知公告
 </td>
	  <td>
		  <input type='button' class="coolbg np" onClick="location='notice-send.jsp';" value='发布新通告' />
	  </td>
 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->

<!--  内容列表   -->
<form name="form2">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;发件箱&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22" id="tr2">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">标题</td>
	<td width="10%">内容</td>
	<td width="8%">发送时间</td>
	<td width="8%">操作</td>
</tr>

<tr id="tr-third" bgcolor="#FAFAF1">
<td height="28" colspan="12">
	&nbsp;
	<a href="" class="coolbg">全选</a>
	<a href="" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="" class="coolbg">&nbsp;删除&nbsp;</a>
	<a href="" class="coolbg">&nbsp;导出Excel&nbsp;</a>
</td>
</tr>

<tr align="right" bgcolor="#EEF4EA">
	<td id="td-page" height="36" colspan="12" align="center">

	</td>
</tr>
</table>

</form>

</body>
</html>