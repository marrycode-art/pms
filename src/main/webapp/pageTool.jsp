<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <title>分页工具</title>
    <link rel="stylesheet" type="text/css" href="skin/css/base.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
    <script type="text/javascript">
        function jumpToPage() {
            var pageNum = $("#pageNum").val();
            var reg = /^[1-9]*[1-9][0-9]*$/;
            if(reg.test(pageNum)){
                window.location.href = "${requestURI}?pageNum=" + pageNum${queryStr};
            }else {
                alert("非法页数！");
            }
        }
    </script>
</head>
<tr align="right" bgcolor="#EEF4EA">
    <td height="36" colspan="12" align="center">
        <div>
            <a href="${requestURI}?pageNum=1${queryStr}">首页</a>&nbsp;&nbsp;&nbsp;
            <a href="${requestURI}?pageNum=${page.pageNum-1}${queryStr}">上一页</a>&nbsp;&nbsp;&nbsp;
            <c:forEach items="${page.navigatepageNums}" var="pageNum">
                <c:if test="${pageNum == page.pageNum}">
                    <span style="color: red; font-weight: bold;">${pageNum}页</span>&nbsp;&nbsp;&nbsp;
                </c:if>
                <c:if test="${pageNum != page.pageNum}">
                    <a href="${requestURI}?pageNum=${pageNum}${queryStr}">${pageNum}页</a>&nbsp;&nbsp;&nbsp;
                </c:if>
            </c:forEach>
            <a href="${requestURI}?pageNum=${page.pageNum+1}${queryStr}">下一页</a>&nbsp;&nbsp;&nbsp;
            <a href="${requestURI}?pageNum=${page.pages}${queryStr}">尾页</a>&nbsp;&nbsp;&nbsp;
            跳转到&nbsp;<input id="pageNum" size="1px" onblur="jumpToPage()">&nbsp;页
        </div>
    </td>
</tr>