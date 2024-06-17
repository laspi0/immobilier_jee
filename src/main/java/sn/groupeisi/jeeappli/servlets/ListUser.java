package sn.groupeisi.jeeappli.servlets;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.SessionFactory;
import sn.groupeisi.jeeappli.dao.UtilisateurDAO;
import sn.groupeisi.jeeappli.database.HibernateUtil;
import sn.groupeisi.jeeappli.entiies.Utilisateur;

import java.io.IOException;
import java.util.List;

@WebServlet("/listuser")
public class ListUser extends HttpServlet {
    private UtilisateurDAO utilisateurDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        utilisateurDAO = new UtilisateurDAO(sessionFactory);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Utilisateur> utilisateurs = utilisateurDAO.getAllNonAdminUsers();
        for (Utilisateur utilisateur : utilisateurs) {
            System.out.println("Utilisateur: " + utilisateur.getNom() + " " + utilisateur.getPrenom());
        }
        HttpSession session = request.getSession();
        session.setAttribute("utilisateurs", utilisateurs);
        request.getRequestDispatcher("user/listUser.jsp").forward(request, response);
    }
}