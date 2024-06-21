<%@ page import="sn.groupeisi.jeeappli.entiies.User" %>
<%@ page import="java.util.List" %>
<%@ page import="sn.groupeisi.jeeappli.entiies.User" %>
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
        List<User> users = (List<User>) session.getAttribute("users");
        if (users != null) {
            for (User user : users) {
    %>
    <tr>
        <td><%= user.getId() %></td>
        <td><%= user.getNom() %></td>
        <td><%= user.getPrenom() %></td>
        <td><%= user.getEmail() %></td>
        <td><%= user.getRole() %></td>
        <td>
            <form action="${pageContext.request.contextPath}/toggleUserStatus" method="post" style="display:inline;">
                <input type="hidden" name="userId" value="<%= user.getId() %>">
                <% if ("actif".equals(user.getStatus())) { %>
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
        <td colspan="6">Aucun user trouvé</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

<%@ include file="footer.jsp" %>

