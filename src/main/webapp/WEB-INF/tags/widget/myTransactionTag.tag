<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<%@ attribute name="transaction" required="true" type="fr.bitcoinerie.domain.MyTransaction" %>

<span class="lead"><a href="/transaction/${transaction.id_transaction}">Transaction ${transaction.id_transaction} : ${fn:escapeXml(transaction.emetteur.prenom)} , ${fn:escapeXml(transaction.recepteur.prenom)}</a></span>
<div class="container">
    <p><fmt:formatDate value="${transaction.date_temps}" pattern="dd MMM yyyy kk:mm:ss.SSS"/></p>

        <div class="emetteur">
            <span class="lead">
                <div class="col-lg-8">Emetteur :  ${transaction.emetteur.prenom} ${transaction.emetteur.nom} </div>
                <div class="col-lg-8">montant courant: ${transaction.emetteur.montant_compte} </div>
                <div class="col-lg-8">réputation  : ${transaction.emetteur.reputation}           </div>

            </span>
        </div>

        <div class="recepteur">
            <code>
                <div class="col-lg-8">Recepteur : ${transaction.recepteur.prenom} ${transaction.recepteur.nom} </div>
                <div class="col-lg-8">montant courant: ${transaction.recepteur.montant_compte} </div>
                <div class="col-lg-8">réputation  : ${transaction.recepteur.reputation}  </div>
            </code>
        </div>

</div>
<p>Montant de la transaction: ${transaction.montant}</p>
<br>
