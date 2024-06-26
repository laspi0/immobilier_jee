<%@ page import="sn.groupeisi.jeeappli.entiies.Property" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/user/headerProperty.jsp" %>

<div class="row">
    <div class="col-md-12 my-4">
        <h2 class="h4 mb-1">Listes des propriétés</h2>
        <p class="mb-3">detailed information</p>
        <div class="card shadow">
            <div class="card-body">
                <!-- table -->
                <table class="table table-hover table-borderless border-v">
                    <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nom</th>
                        <th>Adresse</th>
                        <th>Description</th>
                        <th>Équipements</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<Property> properties = (List<Property>) request.getAttribute("properties");
                        if (properties != null && !properties.isEmpty()) {
                            for (Property property : properties) {
                    %>
                    <tr class="accordion-toggle collapsed" id="c-<%= property.getId() %>" data-toggle="collapse" data-parent="#c-<%= property.getId() %>" href="#collap-<%= property.getId() %>">
                        <td><%= property.getId() %></td>
                        <td><%= property.getName() %></td>
                        <td><%= property.getAddress() %></td>
                        <td><%= property.getDescription() %></td>
                        <td>
                            <ul>
                                <%
                                    List<String> equipments = property.getEquipments();
                                    if (equipments != null && !equipments.isEmpty()) {
                                        for (String equipment : equipments) {
                                %>
                                <li><%= equipment %></li>
                                <%
                                        }
                                    }
                                %>
                            </ul>
                        </td>
                        <td>
                            <form action="${pageContext.request.contextPath}/deleteProperty" method="post" onsubmit="return confirm('Êtes-vous sûr de vouloir supprimer cette propriété ?');">
                                <input type="hidden" name="propertyId" value="<%= property.getId() %>">
                                <button class="btn btn-sm btn-danger m-2" type="submit">Supprimer</button>
                            </form>
                            <form action="${pageContext.request.contextPath}/editProperty" method="get">
                                <input type="hidden" name="propertyId" value="<%= property.getId() %>">
                                <button class="btn-sm btn btn-primary" type="submit">Modifier</button>
                            </form>
                        </td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="6">Aucune propriété trouvée</td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div> <!-- end section -->

<%@ include file="/user/footer.jsp" %>
