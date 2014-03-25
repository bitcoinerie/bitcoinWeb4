<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags/widget" prefix="widget" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
<html>
<head>
    <title>Home</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="/css/myStyle.css" rel="stylesheet" media="screen">

    <style type="text/css">
        body {
            margin: 5px 0 50px 0;
        }
    </style>
</head>
<body>
<div class="container">
    <c:if test="${not empty flashMessage}">
        <div class="alert alert-success">${flashMessage}</div>
    </c:if>
    <div class="row">
        <div class="col-lg-7 col-lg-offset-1">
            <legend>Mon compte</legend>
            <widget:myAccount user="${user}" />

            <legend>Nouvelle transaction</legend>
            <form action="/searchUser" class="navbar-form navbar-center " role="search">
                <input type="hidden" name="id_user" value="${user.id_user}">
                <div class="form-group ">
                    <input name="prenom_dest" class="form-control" placeholder="Prénom du destinataire">
                </div>

                <div class="form-group ">
                    <input name="nom_dest"  class="form-control" placeholder="Nom du destinataire">
                </div>

                <button type="submit" class="btn">Search</button>
            </form>
            <c:if test="${not empty flashMessage2}">
                <div class="alert alert-success">${flashMessage2}</div>
            </c:if>
            <c:if test="${not empty user2}">
                <div class="alert alert-success">Utilisateur trouvé : ${user2.prenom} ${user2.nom}</div>

                <form action="/newTrans" class="navbar-form navbar-center " role="search">
                    <input type="hidden" name="id_user1" value="${user.id_user}">
                    <input type="hidden" name="id_user2" value="${user2.id_user}">
                    <div class="form-group ">
                        <input name="montant"  class="form-control" placeholder="Montant">
                    </div>

                    <button type="submit" class="btn">Submit</button>
                </form>
            </c:if>

            <legend>Mes transactions</legend>
            <c:if test="${empty transactions}">
                <div class="alert alert-info">Pas de transactions pour l'instant.</div>
            </c:if>
            <c:forEach var="transaction" items="${transactions}">
                <widget:myTransactionTag transaction="${transaction}" />
            </c:forEach>

            <div>
                <p>October 2013 - March 2014</p>
                <p>Virtual Monetary System Project - Centrale Marseille</p>
            </div>
        </div>

        <div class="col-lg-3">
            <widget:quickLinks user="${user}"/>

        </div>
    </div>
</div>
<script src="../../js/jquery.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
</body>
</html>