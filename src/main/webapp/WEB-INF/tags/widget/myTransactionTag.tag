<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<%@ attribute name="transaction" required="true" type="fr.bitcoinerie.domain.MyTransaction" %>

<span class="lead"><a href="/edit/${transaction.id_transaction}">Transaction ${transaction.id_transaction}: ${fn:escapeXml(transaction.emetteur.prenom)},${fn:escapeXml(transaction.recepteur.prenom)}</a></span>
<div>
    <p><fmt:formatDate value="${transaction.date_temps}" pattern="dd MMM yyyy kk:mm:ss.SSS"/></p>
    <span class="lead">Emetteur : ${transaction.emetteur.prenom}</span>
    <code>Recepteur : ${transaction.recepteur.nom}</code>
    <p>Montant : ${transaction.montant}</p>

</div>
<br>