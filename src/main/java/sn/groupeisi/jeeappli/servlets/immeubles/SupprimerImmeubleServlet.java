package sn.groupeisi.jeeappli.servlets.immeubles;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;
import sn.groupeisi.jeeappli.dao.ImmeubleDAO;
import sn.groupeisi.jeeappli.database.HibernateUtil;

import java.io.IOException;

@WebServlet("/supprimerImmeuble")
public class SupprimerImmeubleServlet extends HttpServlet {

    private ImmeubleDAO immeubleDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        immeubleDAO = new ImmeubleDAO(sessionFactory);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String immeubleIdStr = request.getParameter("immeubleId");

        if (immeubleIdStr != null) {
            try {
                int immeubleId = Integer.parseInt(immeubleIdStr);
                immeubleDAO.supprimerImmeuble(immeubleId);
                response.sendRedirect(request.getContextPath() + "/list");
            } catch (NumberFormatException e) {
                response.getWriter().write("ID de l'immeuble invalide.");
            }
        } else {
            response.getWriter().write("ID de l'immeuble non fourni.");
        }
    }
}
