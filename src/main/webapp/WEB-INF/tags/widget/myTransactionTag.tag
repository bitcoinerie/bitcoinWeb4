<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<%@ attribute name="transaction" required="true" type="fr.bitcoinerie.domain.MyTransaction" %>

<span class="lead"><a href="/transactionHistoric/${transaction.id_transaction}">Transaction ${transaction.id_transaction} </a></span>
<div class="container">
    <p>Date et Heure : <fmt:formatDate value="${transaction.date_temps}" pattern="dd MMM yyyy kk:mm:ss.SSS"/></p>
    <p>Montant : ${transaction.montant}</p>
        <div class="emetteur">
            <span class="lead">
                <div class="col-lg-8">Emetteur :  ${fn:escapeXml(transaction.emetteur.prenom)} ${fn:escapeXml(transaction.emetteur.nom)} </div>
                <div class="col-lg-8">montant courant: ${transaction.emetteur.montant_compte} </div>
                <div class="col-lg-8">réputation  : ${transaction.emetteur.reputation}           </div>

            </span>
        </div>

        <div class="recepteur">
            <code>
                <div class="col-lg-8">Recepteur : ${fn:escapeXml(transaction.recepteur.prenom)} ${fn:escapeXml(transaction.recepteur.nom)} </div>
                <div class="col-lg-8">montant courant: ${transaction.recepteur.montant_compte} </div>
                <div class="col-lg-8">réputation  : ${transaction.recepteur.reputation}  </div>
            </code>
        </div>

</div>

<br>
