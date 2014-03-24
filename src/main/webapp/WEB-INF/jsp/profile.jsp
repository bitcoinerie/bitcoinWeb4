<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags/widget" prefix="widget" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
<html>
<head>
    <title>Mon profil</title>

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
    <div class="row">
        <div class="col-lg-7 col-lg-offset-1">
            <legend>Mon compte</legend>
            <widget:myAccount user="${user}" />
            <form:form cssClass="form-horizontal" commandName="user" action="/sign_in" method="post">
                <form:hidden path="Prenom" />
                <form:hidden path="Nom" />
                <div class="form-group">
                    <label for="email" class="col-lg-2 control-label">Email</label>
                    <div class="col-lg-6">
                        <c:set var="error1"><form:errors path="email"><span style="color:#B94A48" class="help-block">Email non conforme.</span></form:errors></c:set>
                        <form:input cssClass="form-control" path="email" id="email" placeholder="Email" />
                            ${error1}
                    </div>
                </div>
                <div class="form-group">
                    <label for="login" class="col-lg-2 control-label">Login</label>
                    <div class="col-lg-6">
                        <c:set var="error2"><form:errors path="login"><span style="color:#B94A48" class="help-block">Le login doit être compris entre 5 et 16 caractères.</span></form:errors></c:set>
                        <form:input cssClass="form-control" path="login" id="login" placeholder="Login" />
                        ${error2}
                    </div>
                </div>
                <div class="form-group">
                    <label for="mot_de_passe" class="col-lg-2 control-label">Mot de passe</label>
                    <div class="col-lg-6">
                        <c:set var="error3"><form:errors path="mot_de_passe"><span style="color:#B94A48" class="help-block">Le mot de passe doit être compris entre 8 et 16 caractères.</span></form:errors></c:set>
                        <form:input cssClass="form-control" path="mot_de_passe" id="mot_de_passe" placeholder="Mot de passe" />
                        ${error3}
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                        <button type="submit" class="btn btn-default">Submit</button>
                    </div>
                </div>
            </form:form>
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