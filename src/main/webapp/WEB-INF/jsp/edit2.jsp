
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags/widget" prefix="widget" %>


<!DOCTYPE html>
<html>
<head>
    <title>Bitcoinerie</title>

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
    <widget:navbar />

    <div class="row">

        <legend>Create a MyTransaction</legend>

        <div class="col-lg-7 col-lg-offset-1">

            <form:form cssClass="form-horizontal" commandName="myTransaction" action="/edit" method="post">
                <div class="form-group">
                    <label for="emetteur" class="col-lg-2 control-label">Emetteur</label>
                    <div class="col-lg-8">

                        <!--form:input cssClass="form-control" type="text" path="emetteur" id="emetteur" placeholder="Enter emitter's name"/ -->
                        <input type="text" class="form-control" path="emetteur" id="emetteur" placeholder="Enter emitter's name">
                    </div>
                </div>

                <div class="form-group">
                    <label for="recepteur" class="col-lg-2 control-label">Recepteur</label>
                    <div class="col-lg-8">
                        <input type="text" class="form-control" id="recepteur" placeholder="Enter recipient's name">
                    </div>
                </div>
                <div class="form-group">
                    <label for="montant" class="col-lg-2 control-label">Montant</label>
                    <div class="col-lg-8">
                        <input type="number" class="form-control" id="montant" placeholder="Enter amount of transaction">
                    </div>
                </div>

                <button type="submit" class="btn btn-default">Submit</button>
            </form:form>


        </div>

        <div class="col-lg-3">
            <widget:quickLinks/>

            <widget:tagCloud/>

        </div>

    </div>
</div>
<script src="../../js/jquery.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
</body>
</html>