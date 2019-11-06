<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>创建任务</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	function changeAnalysis() {
        //var projects = $ {projects};
        var pid = $("#sel_pro").val();
		if(pid != null && pid != 0) {
			$("#sel_ana").val(pid);
		}
    }
    function commit() {
        $("select[disabled]").each(function() {
            if (parseInt($(this).val()) != -1) {
                $(this).attr("disabled", false);
            }
        });
        $("#form2").submit();
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
    当前位置:任务管理>>创建任务
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form name="form2" id="form2" action="${pageContext.request.contextPath}/task/saveInfo" method="post">
	<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
	<tr bgcolor="#E7E7E7">
		<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;创建任务&nbsp;</td>
	</tr>
	<tr >
		<td align="right" bgcolor="#FAFAF1" height="22">参考位置：</td>
		<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
			<select id="sel_pro" onchange="changeAnalysis()" style="width: 140px; text-align: center;">
				<option value=0>请选择项目</option>
				<c:forEach items="${projects}" var="project">
					<option value=${project.pid}>${project.pname}</option>
				</c:forEach>
			</select>-
			<select id="sel_ana" style="width: 140px; text-align: center;" disabled>
				<option value=0>请选择分析</option>
				<c:forEach items="${projects}" var="project">
					<c:if test="${project.analysis != null && project.analysis != ''}">
						<option value="${project.pid}">${project.analysis.title}</option>
					</c:if>
					<c:if test="${project.analysis == null || project.analysis == ''}">
						<option value="${project.pid}">暂无项目分析</option>
					</c:if>
				</c:forEach>
			</select>-
			<%--<c:forEach items="${projects}" var="project">--%>
				<select id="sel_mod_${project.pid}" style="width: 140px; text-align: center;">
					<option value="${project.analysis.module.id}">${project.analysis.module.modname}</option>
				</select>-
			<%--</c:forEach>--%>
			<select id="sel_fun" style="width: 140px; text-align: center;">
				<option value=0>请选择功能</option>
				<option value=1>添加帐户功能</option>
			</select>
		</td>
	</tr>
	<tr >
		<td align="right" bgcolor="#FAFAF1" height="22">任务标题：</td>
		<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><input/></td>
	</tr>
	<tr >
		<td align="right" bgcolor="#FAFAF1" height="22">开始时间：</td>
		<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><input/></td>
	</tr>
	<tr >
		<td align="right" bgcolor="#FAFAF1" height="22">结束时间：</td>
		<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><input/></td>
	</tr>
	<tr >
		<td align="right" bgcolor="#FAFAF1" height="22">执行者：</td>
		<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><select><option value=1>张含馨--初级程序员</option><option value=1>张&nbsp;&nbsp;伟--中级程序员</option><option value=1>孙传杰--项目经理</option></select></td>
	</tr>
	<tr >
		<td align="right" bgcolor="#FAFAF1" height="22">优先级：</td>
		<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><select><option>高</option><option>中</option><option>低</option><option>暂缓</option></select></td>
	</tr>

	<tr >
		<td align="right" bgcolor="#FAFAF1" >详细说明：</td>
		<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
			<textarea rows=10 cols=130></textarea>
		</td>
	</tr>

	<tr bgcolor="#FAFAF1">
	<td height="28" colspan=4 align=center>
		&nbsp;
		<a href="javascript:commit()" class="coolbg">保存</a>
	</td>
	</tr>
	</table>
</form>

</body>
</html>