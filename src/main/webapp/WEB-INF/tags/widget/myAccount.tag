<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<%@ attribute name="user" required="true" type="fr.bitcoinerie.domain.MyUser" %>

<div class="container">
    <div>
            <span class="lead">
                <div class="col-lg-8">Prenom :  ${user.prenom}</div>
                <div class="col-lg-8">Nom : ${user.nom} </div>
                <div class="col-lg-8">Montant  : ${user.montant_compte}  </div>
                <%--<div class="col-lg-8">Réputation  : ${user.reputation}  </div>--%>


            </span>
    </div>

</div>