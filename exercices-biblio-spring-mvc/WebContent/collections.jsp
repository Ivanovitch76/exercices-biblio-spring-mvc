<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Gestion de bibliotheque</h1>

<c:set var="collections" value="${requestScope['listeCollections']}" />

<c:if test="${fn:length(collections) > 1 }" >
	<h2>Liste des collections</h2>
	<c:forEach var="a" items="${collections}">
		<li>
		<form action="${pageContext.request.contextPath}/spring/delColl" method="post">
			<c:out value="${a.collection.nom}"/> 
			<input type="hidden"  name="idColl" value="${a.collection.id}"/>
			<c:if test="${!a.contenu}" >
				<input type="submit" value="delete"/>
			</c:if>	
		</form>		
		</li>

	</c:forEach>
</c:if>	

<h2>Ajout d'une nouvelle collection</h2>

<form action="${pageContext.request.contextPath}/spring/addColl" method="post">
	nom: <input type="text" name="nom"/>
	<br/><br/>
	<input type="submit" value="ajouter"/>
</form>

<p><a href="${pageContext.request.contextPath}/spring/index">index</a></p>

</body>
</html>