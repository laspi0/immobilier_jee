<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/user/header.jsp" %>

<div class="row">
    <div class="col-md-12 my-4">
        <h2 class="h4 mb-1">Listes des utilisateurs</h2>
        <p class="mb-3">detailed information</p>
        <div class="card shadow">
            <div class="card-body">
                <!-- table -->
                <table class="table table-hover table-borderless border-v">
                    <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nom</th>
                        <th>Prénom</th>
                        <th>Email</th>
                        <th>Rôle</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                    </thead>

                </table>
            </div>
        </div>
    </div>
</div> <!-- end section -->

<%@ include file="/user/footer.jsp" %>
