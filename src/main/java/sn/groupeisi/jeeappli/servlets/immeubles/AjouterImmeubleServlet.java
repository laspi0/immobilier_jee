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

@WebServlet("/ajouteImmeuble")
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

        Immeuble immeuble = new Immeuble();
        immeuble.setNom(nom);
        immeuble.setAdresse(adresse);
        immeuble.setDescription(description);
        immeuble.setEquipements(Arrays.asList(equipementsArray));
        immeuble.setUtilisateur(utilisateur);

        boolean ajoutReussi = immeubleDAO.ajouterImmeuble(immeuble);

        if (ajoutReussi) {
            response.sendRedirect(request.getContextPath() + "/confirmation.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/erreur.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/immeuble/ajouteImmeuble.jsp").forward(request, response);
    }
}
