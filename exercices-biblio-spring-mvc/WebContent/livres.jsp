<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LivresJSP</title>
</head>

<body>

<h1>Gestion de bibliotheque</h1>

<c:set var="livres" value="${requestScope['listeLivres']}" />
<c:set var="collections" value="${requestScope['listeCollections']}" />
<c:set var="auteurs" value="${requestScope['listeAuteurs']}" />
<c:set var="reservations" value="${requestScope['reservations']}" />

<c:if test="${fn:length(reservations) > 1 }" >
	<h2>Liste des livres</h2>
	<c:forEach var="a" items="${reservations}">
		<li>
			<c:out value="${a.livre.titre}"/> chez
			<c:out value="${a.livre.collection.nom}"/>
			<c:if test="${!a.reserve}" >
 				<a href="${pageContext.request.contextPath}/spring/delLivre?livreCode=${a.livre.code}">delete</a> 
 			</c:if>	
		</li>
	</c:forEach>
</c:if>	

<h2>Ajout d'un nouveau livre</h2>

<form action="${pageContext.request.contextPath}/spring/addLivre" method="post">
	titre: <input type="text" name="titre"/>
	<br/>
	n° edition: <input type="text" name="edition"/>
	<br/>
	date de parution: <input type="text" name="parution" placeholder="dd-mm-yyyy"/>
	<br/>
	nombre de pages: <input type="text" name="pages"/>
	<br/>
	collection: <select name="collection">	
			<c:forEach var="c" items="${collections}">
				<option value="${c.id}"><c:out value="${c.nom}"/>	
				</option>					
			</c:forEach>
		  </select>
	<br/>
	auteurs: <select name="auteurs" multiple>	
			<c:forEach var="a" items="${auteurs}">
				<option value="${a.id}">
					<c:out value="${a.prenom}"/> 
					<c:out value="${a.nom}"/>
				</option>			
			</c:forEach>
		  </select>		
	<br/>
	<input type="submit" value="ajouter"/>
</form>

<p><a href="${pageContext.request.contextPath}/spring/index">index</a></p>

</body>
</html>