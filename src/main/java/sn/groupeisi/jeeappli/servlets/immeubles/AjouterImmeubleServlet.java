package sn.groupeisi.jeeappli.servlets.immeubles;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.SessionFactory;
import sn.groupeisi.jeeappli.dao.ImmeubleDAO;
import sn.groupeisi.jeeappli.database.HibernateUtil;
import sn.groupeisi.jeeappli.entiies.Immeuble;
import sn.groupeisi.jeeappli.entiies.Utilisateur;

import java.io.IOException;
import java.util.Arrays;

@WebServlet("/added")
public class AjouterImmeubleServlet extends HttpServlet {

    private ImmeubleDAO immeubleDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        immeubleDAO = new ImmeubleDAO(sessionFactory);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les données du formulaire
        String nom = request.getParameter("nom");
        String adresse = request.getParameter("adresse");
        String description = request.getParameter("description");
        String[] equipementsArray = request.getParameterValues("equipements");

        // Récupérer l'utilisateur connecté depuis la session
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
        if (utilisateur == null) {
            // Redirection vers la page de connexion si l'utilisateur n'est pas connecté
            response.sendRedirect(request.getContextPath() + "/connexion");
            return;
        }

        // Créer un nouvel objet Immeuble
        Immeuble immeuble = new Immeuble();
        immeuble.setNom(nom);
        immeuble.setAdresse(adresse);
        immeuble.setDescription(description);
        immeuble.setEquipements(Arrays.asList(equipementsArray));
        immeuble.setUtilisateur(utilisateur);

        // Appeler la méthode d'ajout d'immeuble du DAO
        boolean ajoutReussi = immeubleDAO.ajouterImmeuble(immeuble);

        if (ajoutReussi) {
            // Rediriger vers une page de confirmation ou autre
            response.sendRedirect(request.getContextPath() + "/confirmation.jsp");
        } else {
            // Gérer l'échec d'ajout d'immeuble (redirection vers une page d'erreur par exemple)
            response.sendRedirect(request.getContextPath() + "/erreur.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/user/added.jsp").forward(request, response);
    }
}