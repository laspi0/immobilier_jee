<%@ page import="java.util.List" %>
<%@ page import="sn.groupeisi.jeeappli.entiies.Immeuble" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mes immeubles</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>
<h1>Mes immeubles</h1>
<table>
    <tr>
        <th>Nom</th>
        <th>Adresse</th>
        <th>Description</th>
        <th>Équipements</th>
        <th>Actions</th>
    </tr>
    <%
        List<Immeuble> immeubles = (List<Immeuble>) request.getAttribute("immeubles");
        if (immeubles != null) {
            for (Immeuble immeuble : immeubles) {
    %>
    <tr>
        <td><%= immeuble.getNom() %></td>
        <td><%= immeuble.getAdresse() %></td>
        <td><%= immeuble.getDescription() %></td>
        <td>
            <ul>
                <%
                    List<String> equipements = immeuble.getEquipements();
                    if (equipements != null) {
                        for (String equipement : equipements) {
                            if (equipement != null) {
                %>
                <li><%= equipement %></li>
                <%
                            }
                        }
                    }
                %>
            </ul>
        </td>
        <td>
            <form action="${pageContext.request.contextPath}/supprimerImmeuble" method="post" onsubmit="return confirm('Êtes-vous sûr de vouloir supprimer cet immeuble ?');">
                <input type="hidden" name="immeubleId" value="<%= immeuble.getId() %>">
                <button type="submit">Supprimer</button>
            </form>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
