<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<%@ attribute name="transaction" required="true" type="fr.bitcoinerie.domain.Transaction.MyTransaction" %>

<span class="lead"><a href="/edit/${transaction.id_transaction}">Transaction ${transaction.id_transaction}: ${fn:escapeXml(transaction.emetteur)},${fn:escapeXml(transaction.recepteur)}</a></span>
<div>
    <p><fmt:formatDate value="${transaction.date_temps}" pattern="dd MMM yyyy kk:mm:ss.SSS"/></p>
    <span class="lead">Emetteur : ${transaction.emetteur}</span>
    <code>Recepteur : ${transaction.recepteur}</code>
    <p>Montant : ${transaction.montant}</p>
</div>
<br>