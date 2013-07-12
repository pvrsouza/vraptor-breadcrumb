<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><decorator:title default="Vraptor Scaffold" /></title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

<decorator:head />
</head>
<body>
	<a href="<c:url value="/new          "/>">NewController.index()</a> <br />
	<a href="<c:url value="/new/levelone "/>">NewController.leveOne()</a> <br />
	<a href="<c:url value="/new/leveltwo "/>">NewController.levelTwo()</a> <br />
	<a href="<c:url value="/             "/>">BreadcrumbTestController.index()</a> <br />
	<a href="<c:url value="/levelone     "/>">BreadcrumbTestController.levelOne()</a> <br />
	<a href="<c:url value="/leveltwo     "/>">BreadcrumbTestController.levelTwo()</a> <br />
	
	<hr />

	<ul class="breadcrumb">
		<c:forEach items="${breadcrumbSession.itens}" var="item"
			varStatus="status">
			<c:choose>
				<c:when
					test="${status.index == fn:length(bd.itens)-1 && status.index!=0}">
					<li id="current">> ${item.nome}</li>
				</c:when>
				<c:otherwise>
					<li>> <a href="<c:url value="${item.uri}"/>">${item.nome}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</ul>
	<decorator:body />
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/javascripts/application.js"></script>
</body>
</html>
