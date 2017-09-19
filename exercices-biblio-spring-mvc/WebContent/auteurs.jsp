<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>          
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Auteurs</title>
</head>
<body>

<h1>Gestion de bibliotheque</h1>

<c:set var="auteurs" value="${requestScope['listeAuteurs']}" />

<c:if test="${fn:length(auteurs) > 1 }" >
	<h2>Liste des auteurs</h2>
	<c:forEach var="a" items="${auteurs}">
		<li>
		<form action="${pageContext.request.contextPath}/spring/delAuteur" method="post">
			<c:out value="${a.auteur.prenom}"/> 
			<c:out value="${a.auteur.nom}"/> 
			<input type="hidden"  name="delvalue" value="${a.auteur.id}"/>
			<c:if test="${!a.livresEcrits}" >
				<input type="submit" value="delete"/>
			</c:if>	
		</form>		
		</li>

	</c:forEach>
</c:if>	

<h2>Ajout d'un nouvel auteur</h2>

<form action="${pageContext.request.contextPath}/spring/addAuteur" method="post">
	prenom: <input type="text" name="prenom"/>
	<br/><br/>
	nom: <input type="text" name="nom"/>
	<br/><br/>
	<input type="submit" value="ajouter"/>
</form>

<p><a href="${pageContext.request.contextPath}/spring/index">index</a></p>

</body>
</html>