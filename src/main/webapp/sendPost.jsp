<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>发送帖子</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/static/js/utf8-jsp/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/static/js/utf8-jsp/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/static/js/utf8-jsp/lang/zh-cn/zh-cn.js"></script>
    <script type="application/javascript">
        var ue = UE.getEditor('editor');
        var forumtitle = $("#forumtitle").val();
        var forumcontent = $("#forumcontent").val();
        function commit() {
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/post/saveInfo",
                data:{"forumtitle":forumtitle,"forumcontent":forumcontent},
                success:function (entity) {
                    if (entity == 200) {
                        window.location.href = "${pageContext.request.contextPath}/main.jsp";
                    }else {
                        alert(entity.message);
                    }
                }
            });
        }
    </script>
    <style type="text/css">
        div{
            width:100%;
        }
    </style>
</head>
<body>
    <form id="form1" href="javascript:void(0)" action="#" method="post">
        标题：<input type="text" id="forumtitle" name="forumtitle" placeholder="帖子标题"><br /><br />
        内容: <script id="editor" name="forumcontent" type="text/plain" style="width:1024px;height:500px;"></script><br /><br />
    </form>
    <a href="javascript:commit()" class="coolbg">发帖</a>
    <a href="javascript:history.go(-1)" class="coolbg">返回</a>
</body>
</html>
