<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CoolMeeting会议管理系统</title>
    <link rel="stylesheet" href="styles/common.css"/>
    <style type="text/css">
        #divoperator input[type="button"] {
            margin: 10px 0;
        }
    </style>
    <script type="application/javascript">
    </script>
</head>
<body onload="body_load()">
<jsp:include page="top.jsp"/>
<div class="page-body">
    <jsp:include page="leftMenu.jsp"/>

    <div class="page-content">
        <div class="content-nav">
            会议预定 > 修改会议预定
        </div>
        <form action="/meeting/updateoptinfo" method="post">
            <fieldset>
                <legend>会议信息</legend>
                <table class="formtable">
                    <c:if test="${error!=null}">
                        <tr>
                            <td colspan="2">${error}</td>
                        </tr>
                    </c:if>
                    <tr>
                        <td>会议编码：</td>
                        <td>${mt.meetingid}</td>
                    </tr>
                    <tr>
                        <input type="hidden" name="mid" value="${mt.meetingid}"/>
                        <td>会议名称：</td>
                        <td>${mt.meetingname}</td>
                    </tr>
                    <tr>
                        <td>预计参加人数：</td>
                        <td>${mt.numberofparticipants}</td>
                    </tr>
                    <tr>
                        <td>预计开始时间：</td>
                        <td>${mt.starttime}</td>
                    </tr>
                    <tr>
                        <td>预计结束时间：</td>
                        <td>${mt.endtime}</td>
                    </tr>
                    <tr>
                        <td>会议说明：</td>
                        <td>
                            <textarea id="description" rows="5" readonly>${mt.description}</textarea>
                        </td>
                    </tr>
                    <c:if test="${error==null}">
                    <tr>
                        <td>参会人员需要填写的信息：</td>
                    </tr>
                    <c:forEach items="${oplists}" var="list">
                        <tr>
                            <td >${list.option}:<input type="hidden" name="optionid" value="${list.optionid}"/></td>
                            <td>
                                <input name="optioninfo" type="text" id="${list.optionid}" maxlength="20"/>
                            </td>
                        </tr>
                    </c:forEach>
                    </c:if>
                    <c:if test="${error!=null}">
                    <tr>
                        <td>参会人员：</td>
                        <td>
                            <table class="listtable">
                                <tr class="listheader">
                                    <th>姓名</th>
                                    <th>联系电话</th>
                                    <th>电子邮件</th>
                                    <th>身份证号</th>
                                    <th>性别</th>
                                    <th>所在地</th>
                                    <th>是否需要房间</th>
                                </tr>
                                <c:forEach items="${emps}" var="emp" varStatus="loop">
                                    <tr>
                                        <td>${emp.employeename}</td>
                                        <td>${emp.phone}</td>
                                        <td>${emp.email}</td>
                                        <td>${opinfolists[loop.count-1].IDnumber}</td>
                                        <td>${opinfolists[loop.count-1].gender}</td>
                                        <td>${opinfolists[loop.count-1].location}</td>
                                        <td>${opinfolists[loop.count-1].roomrequired}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>
                    </tr>
                    </c:if>
                    <tr>
                    <tr>
                        <td class="command" colspan="2">
                            <c:if test="${type=='cancel'}">
                                <input type="button" class="clickbutton" value="撤销会议"
                                       onclick="window.location.href='/meeting/cancelmeeting?mid=${mt.meetingid}';"/>
                            </c:if>
                            <c:if test="${error==null}">
                                <input type="submit" class="clickbutton" value="上传信息" />
                            </c:if>
                            <input type="button" class="clickbutton" value="返回" onclick="window.history.back();"/>
                        </td>
                    </tr>
                    </tr>
                </table>
            </fieldset>
        </form>
    </div>
</div>
<div class="page-footer">
    <hr/>
    更多问题，欢迎联系<a href="mailto:webmaster@eeg.com">管理员</a>
    <img src="images/footer.png" alt="CoolMeeting"/>
</div>
</body>
</html>
