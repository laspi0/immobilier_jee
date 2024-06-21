<%@ page import="sn.groupeisi.jeeappli.entiies.User" %>
<%@ page import="java.util.List" %>
<%@ page import="sn.groupeisi.jeeappli.entiies.Property" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/user/headerProperty.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover">
                <tr>
                    <th>Nom</th>
                    <th>Adresse</th>
                    <th>Description</th>
                    <th>Équipements</th>
                    <th>Actions</th>
                </tr>
                <% List<Property> properties = (List<Property>) request.getAttribute("properties");
                    if (properties != null && !properties.isEmpty()) {
                        for (Property property : properties) { %>
                <tr>
                    <td><%= property.getName() %></td>
                    <td><%= property.getAddress() %></td>
                    <td><%= property.getDescription() %></td>
                    <td>
                        <ul>
                            <% List<String> equipments = property.getEquipments();
                                if (equipments != null && !equipments.isEmpty()) {
                                    for (String equipment : equipments) { %>
                            <li><%= equipment %></li>
                            <%    }
                            } %>
                        </ul>
                    </td>
                    <td>
                        <form action="${pageContext.request.contextPath}/deleteProperty" method="post" onsubmit="return confirm('Êtes-vous sûr de vouloir supprimer cette propriété ?');">
                            <input type="hidden" name="propertyId" value="<%= property.getId() %>">
                            <button type="submit">Supprimer</button>
                        </form>
                        <form action="${pageContext.request.contextPath}/editProperty" method="get">
                            <input type="hidden" name="propertyId" value="<%= property.getId() %>">
                            <button type="submit">Modifier</button>
                        </form>
                    </td>
                </tr>
                <%     }
                } else { %>
                <tr>
                    <td colspan="5">Aucune propriété trouvée</td>
                </tr>
                <% } %>
            </table>
        </div>
    </div>
</div>

<%@ include file="/user/footer.jsp" %>
