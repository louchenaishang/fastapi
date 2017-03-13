<%--
  Created by IntelliJ IDEA.
  User: louchen
  Date: 16/8/22
  Time: 下午9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>api</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/index.css" rel="stylesheet">
</head>

<body>

<nav class="navbar navbar-dark navbar-fixed-top bg-inverse" style="height: 55px;">
    <button type="button" class="navbar-toggler visible-xs" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="#">API Server</a>
    <%--<form class="navbar-form pull-right">--%>
        <%--<input type="text" class="form-control" placeholder="Search...">--%>
    <%--</form>--%>
    <br>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12 col-md-12 main">
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>PATH</th>
                        <th>MediaType</th>
                        <th>参数</th>
                        <th>描述信息</th>
                        <th>返回</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" varStatus="i" var="item" >
                        <tr>
                            <td>${item.path}</td>
                            <td>Consume:${item.consumeMediaTypes}<br/>Produce:${item.produceMediaTypes}</td>
                            <td>
                            <c:forEach items="${item.queryParamDescriptors}" varStatus="i" var="x" >
                                Query&lt;${x.remark}&gt;:${x.paramName}
                            </c:forEach>
                            <c:forEach items="${item.pathParamDescriptors}" varStatus="i" var="x" >
                                Path&lt;${x.remark}&gt;:${x.pathName}
                            </c:forEach>
                            </td>
                            <td>${item.description}</td>
                            <td>
                                <a href="javascript:showDetail('${item.produceMeta}');">
                                    ${item.produceMeta}
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="myModalLabel"></h4>
            </div>
            <div class="modal-body">

                <div class="container-fluid">
                    <div class="row">
                        <div class="col-sm-12 col-md-12 main">
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th>属性</th>
                                        <th>类型</th>
                                        <th>名称</th>
                                        <th>长度</th>
                                        <th>not null</th>
                                        <th>描述</th>
                                    </tr>
                                    </thead>
                                    <tbody id="modal-tbody">

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</button>
            </div>
        </div>
    </div>
</div>

<input id="className" value="">

<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/commons-frame.js"></script>

<script>
    $(function(){
        $('#className').hide();
    });

    function showDetail(className){
        var url = '<%=basePath%>api/restClass?className='+className;
        var data = {};
        LogUtil.deBuglog(url);
        AjaxUtil.getSync(url,data,function(json){
            LogUtil.deBuglog(json);
            $('#myModalLabel').html(className);
            var html = '';
            for (var i=0;i<json.length;i++){
                var j = json[i];
                html+='<tr>';
                html+='<td>'+j.className+'</td>';
                html+='<td>'+j.classType+'</td>';
                html+='<td>'+j.name+'</td>';
                html+='<td>'+j.len+'</td>';
                html+='<td>'+j.notnull+'</td>';
                html+='<td>'+j.remark+'</td>';
                html+='</tr>';
            }

            $('#modal-tbody').html(html);
            $('#myModal').modal('show');
        });
    }
</script>
</body>
</html>
