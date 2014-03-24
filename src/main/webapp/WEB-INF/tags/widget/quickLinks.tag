<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ attribute name="user" required="true" type="fr.bitcoinerie.domain.MyUser" %>

<div class="panel panel-default">
    <div class="panel-heading">Quick links</div>
    <div class="panel-body">
        <ul>
            <li><a href="/index">Accueil</a></li>
            <li><a href="/index">Nouvelle Transaction</a></li>
            <li><a href="/index">Historique</a></li>
            <li><a href="/profile/${user.id_user}">Profil</a></li>
        </ul>
    </div>
</div>