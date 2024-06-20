package sn.groupeisi.jeeappli.servlets.immeubles;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;
import sn.groupeisi.jeeappli.dao.ImmeubleDAO;
import sn.groupeisi.jeeappli.database.HibernateUtil;
import sn.groupeisi.jeeappli.entiies.Immeuble;

import java.io.IOException;
import java.util.Arrays;

@WebServlet("/modifierImmeuble")
public class ModifierImmeubleServlet extends HttpServlet {

    private ImmeubleDAO immeubleDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        immeubleDAO = new ImmeubleDAO(sessionFactory);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int immeubleId = Integer.parseInt(request.getParameter("immeubleId"));
        Immeuble immeuble = immeubleDAO.obtenirImmeubleAvecEquipements(immeubleId);
        request.setAttribute("immeuble", immeuble);
        request.getRequestDispatcher("/immeuble/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int immeubleId = Integer.parseInt(request.getParameter("immeubleId"));
        String nom = request.getParameter("nom");
        String adresse = request.getParameter("adresse");
        String description = request.getParameter("description");
        String[] equipements = request.getParameterValues("equipements");

        Immeuble immeuble = immeubleDAO.obtenirImmeubleAvecEquipements(immeubleId);
        immeuble.setNom(nom);
        immeuble.setAdresse(adresse);
        immeuble.setDescription(description);
        immeuble.setEquipements(Arrays.asList(equipements));

        immeubleDAO.modifierImmeuble(immeuble);

        response.sendRedirect(request.getContextPath() + "/list");
    }
}
