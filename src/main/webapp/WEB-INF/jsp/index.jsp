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
    <link href="/css/myStyle.css" rel="stylesheet" media="screen">

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
        <div class="col-lg-7 col-lg-offset-1">
            <legend>All Transaction</legend>
            <br>

            <c:forEach var="transaction" items="${transactions}">
                <widget:myTransactionTag transaction="${transaction}" />
            </c:forEach>

            <widget:myTransactionTag transaction="${transactionById}" />

            <c:forEach var="transaction" items="${transactionsByQuery}">
                <widget:myTransactionTag transaction="${transaction}" />
            </c:forEach>

            <div>
                <p>October 2013 - March 2014</p>
                <span class="lead">Virtual Monetary System Project</span>
                <code>Bitcoin</code>
                <code>Linden</code>
                <code>SEL</code>
                <p>Virtual Monetary System Project - Centrale Marseille</p>
            </div>


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