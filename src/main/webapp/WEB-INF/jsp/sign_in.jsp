<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags/widget" prefix="widget" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Connexion</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">

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

        <legend>Connexion</legend>

        <div class="col-lg-7 col-lg-offset-1">

            <form:form cssClass="form-horizontal" commandName="user" action="/sign_in" method="post">
                <form:hidden path="Prenom" />
                <form:hidden path="Nom" />
                <form:hidden path="Email" />
                <div class="form-group">
                    <label for="login" class="col-lg-2 control-label">Login</label>
                    <div class="col-lg-6">
                        <c:set var="error1"><form:errors path="login"><span style="color:#B94A48" class="help-block">Le login doit être compris entre 5 et 16 caractères.</span></form:errors></c:set>
                        <form:input cssClass="form-control" path="login" id="login" placeholder="Login" />
                            ${error1}
                    </div>
                </div>
                <div class="form-group">
                    <label for="mot_de_passe" class="col-lg-2 control-label">Mot de passe</label>
                    <div class="col-lg-6">
                        <c:set var="error2"><form:errors path="mot_de_passe"><span style="color:#B94A48" class="help-block">Le mot de passe doit être compris entre 8 et 16 caractères.</span></form:errors></c:set>
                        <form:input cssClass="form-control" path="mot_de_passe" id="mot_de_passe" placeholder="Mot de passe" />
                            ${error2}
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                        <button type="submit" class="btn btn-default">Submit</button>
                    </div>
                </div>
            </form:form>

            <p>Vous n'êtes pas membre ? <a href="/new_user">Inscrivez-vous !</a></p>

        </div>

    </div>
</div>
<script src="../../js/jquery.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
</body>
</html>
