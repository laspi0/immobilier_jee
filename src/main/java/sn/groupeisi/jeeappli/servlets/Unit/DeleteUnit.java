package sn.groupeisi.jeeappli.servlets.Unit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;
import sn.groupeisi.jeeappli.dao.UnitDAO;
import sn.groupeisi.jeeappli.database.HibernateUtil;

import java.io.IOException;

@WebServlet("/deleteUnit")
public class DeleteUnit extends HttpServlet {

    private UnitDAO unitDAO;

    @Override
    public void init() throws ServletException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        unitDAO = new UnitDAO(sessionFactory);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int unitId = Integer.parseInt(request.getParameter("unitId"));
        unitDAO.deleteUnit(unitId);
        response.sendRedirect(request.getContextPath() + "/listUnits");
    }
}
