package sn.groupeisi.jeeappli.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;
import sn.groupeisi.jeeappli.dao.UtilisateurDAO;
import sn.groupeisi.jeeappli.database.HibernateUtil;
import sn.groupeisi.jeeappli.entiies.Utilisateur;

import java.io.IOException;

@WebServlet("/connexion")
public class ConnexionServlet extends HttpServlet {

    private UtilisateurDAO utilisateurDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        utilisateurDAO = new UtilisateurDAO(sessionFactory);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("user/connexion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Récupération des paramètres du formulaire
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");

        // Validation de l'utilisateur
        Utilisateur utilisateur = utilisateurDAO.getByEmail(email);

        if (utilisateur != null && BCrypt.checkpw(motDePasse, utilisateur.getMotDePasse())) {
            // Authentification réussie
            HttpSession session = request.getSession(true);
            session.setAttribute("utilisateur", utilisateur);

            // Redirection en fonction du rôle de l'utilisateur
            String role = utilisateur.getRole();
            if ("admin".equals(role)) {
                response.sendRedirect("user/admin.jsp");
            } else if ("locataire".equals(role)) {
                response.sendRedirect("locataire/accueil_locataire.jsp");
            } else if ("proprietaire".equals(role)) {
                response.sendRedirect("proprietaire/accueil_proprietaire.jsp");
            } else {
                // Gestion des rôles non définis
                response.sendRedirect("connexion.jsp?erreur=2");
            }
        } else {
            // Échec de l'authentification
            response.sendRedirect("connexion.jsp?erreur=1");
        }
    }
}

