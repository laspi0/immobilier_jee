<%@ page import="sn.groupeisi.jeeappli.entiies.User" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover">
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
                    if (users != null && !users.isEmpty()) {
                        for (User user : users) {
                %>
                <tr>
                    <td><%= user.getId() %></td>
                    <td><%= user.getLastName() %></td>
                    <td><%= user.getFirstName() %></td>
                    <td><%= user.getEmail() %></td>
                    <td><%= user.getRole() %></td>
                    <td>
                        <form action="${pageContext.request.contextPath}/toggleUserStatus" method="post" style="display:inline;">
                            <input type="hidden" name="userId" value="<%= user.getId() %>">
                            <% if ("active".equals(user.getStatus())) { %>
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
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>
