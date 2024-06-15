package sn.groupeisi.jeeappli.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;
import sn.groupeisi.jeeappli.dao.UtilisateurDAO;
import sn.groupeisi.jeeappli.database.HibernateUtil;
import sn.groupeisi.jeeappli.entiies.Utilisateur;

import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;

// ...

@WebServlet("/inscription")
public class InscriptionServlet extends HttpServlet {

    private UtilisateurDAO utilisateurDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        utilisateurDAO = new UtilisateurDAO(sessionFactory);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("user/inscription.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupération des paramètres du formulaire
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");
        String role = request.getParameter("role");

        // Hashage du mot de passe
        String motDePasseHash = BCrypt.hashpw(motDePasse, BCrypt.gensalt());

        // Création d'un nouvel utilisateur avec le mot de passe hashé
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(nom);
        utilisateur.setPrenom(prenom);
        utilisateur.setEmail(email);
        utilisateur.setMotDePasse(motDePasseHash);
        utilisateur.setRole(role);

        // Inscription de l'utilisateur
        boolean inscriptionReussie = utilisateurDAO.inscrire(utilisateur);

        if (inscriptionReussie) {
            // Redirection vers une page de succès d'inscription
            response.sendRedirect("inscription_succes.jsp");
        } else {
            // Gestion de l'erreur d'inscription
            response.sendRedirect("inscription.jsp?erreur=1");
        }
    }
}
