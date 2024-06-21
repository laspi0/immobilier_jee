<%@ include file="/user/headerProperty.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-md-6 offset-3">
            <div class="card">
                <div class="card-body">
                    <div class="card-title">Ajout d'un immeuble</div>
                    <hr>
                    <form action="${pageContext.request.contextPath}/addProperty" method="post">
                        <div class="form-group">
                            <label for="input-1">Nom</label>
                            <input type="text" name="name" class="form-control" id="input-1" placeholder="Entrer le nom" required>
                        </div>
                        <div class="form-group">
                            <label for="">Adresse</label>
                            <input type="text" name="address" class="form-control" placeholder="Entrer l'addresse">
                        </div>
                        <div class="form-group">
                            <label for="input-3">description</label>
                            <textarea name="description" class="form-control" id="input-3" placeholder="Entrer la description"  required></textarea>
                        </div>
                        <div class="form-group">
                            <label >equipements</label>
                            <div>
                                <input type="checkbox" name="equipments" value="Ascenseur">Ascenseur<br>
                                <input type="checkbox" name="equipments" value="Parking"> Parking<br>
                                <input type="checkbox" name="equipments" value="Piscine"> Piscine<br>
                            </div>
                        </div>

                        <div class="form-group">
                            <button type="submit" class="btn btn-light px-5"><i class="icon-lock"></i> Ajouter</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <%@ include file="/user/footer.jsp" %>
