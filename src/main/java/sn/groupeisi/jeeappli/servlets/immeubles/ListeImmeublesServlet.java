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
import java.util.List;

@WebServlet("/list")
public class ListeImmeublesServlet extends HttpServlet {

    private ImmeubleDAO immeubleDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        immeubleDAO = new ImmeubleDAO(sessionFactory);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");

        if (utilisateur == null) {
            // Redirection vers la page de connexion si l'utilisateur n'est pas connecté
            response.sendRedirect(request.getContextPath() + "/connexion");
            return;
        }
        List<Immeuble> immeubles = immeubleDAO.obtenirImmeublesPourUtilisateur(utilisateur);
        request.setAttribute("immeubles", immeubles);
        request.getRequestDispatcher("/immeuble/index.jsp").forward(request, response);
    }
}