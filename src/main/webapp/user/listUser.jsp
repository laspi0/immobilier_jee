<%@ page import="sn.groupeisi.jeeappli.entiies.Utilisateur" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>



    <table  class="table table-hover">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Prénom</th>
            <th>Email</th>
            <th>Rôle</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Utilisateur> utilisateurs = (List<Utilisateur>) session.getAttribute("utilisateurs");
            if (utilisateurs != null) {
                for (Utilisateur utilisateur : utilisateurs) {
        %>
        <tr>
            <td><%= utilisateur.getId() %></td>
            <td><%= utilisateur.getNom() %></td>
            <td><%= utilisateur.getPrenom() %></td>
            <td><%= utilisateur.getEmail() %></td>
            <td><%= utilisateur.getRole() %></td>
            <td>
                <form action="${pageContext.request.contextPath}/toggleUserStatus" method="post" style="display:inline;">
                    <input type="hidden" name="userId" value="<%= utilisateur.getId() %>">
                    <% if ("actif".equals(utilisateur.getStatus())) { %>
                    <button class="btn btn-sm btn-danger" type="submit">Désactiver</button>
                    <% } else { %>
                    <button class="btn btn-sm btn-success" type="submit">Activer</button>
                    <% } %>
                </form>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="6">Aucun utilisateur trouvé</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

<%@ include file="footer.jsp" %>

