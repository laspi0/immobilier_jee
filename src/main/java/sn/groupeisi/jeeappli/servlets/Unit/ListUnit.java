package sn.groupeisi.jeeappli.servlets.Unit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.SessionFactory;
import sn.groupeisi.jeeappli.dao.UnitDAO;
import sn.groupeisi.jeeappli.database.HibernateUtil;
import sn.groupeisi.jeeappli.entiies.Unit;

import java.io.IOException;
import java.util.List;

@WebServlet("/listUnits")
public class ListUnit extends HttpServlet {

    private UnitDAO unitDAO;

    @Override
    public void init() throws ServletException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        unitDAO = new UnitDAO(sessionFactory);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        List<Unit> units = unitDAO.getUnitsByUserId(userId);
        request.setAttribute("units", units);
        request.getRequestDispatcher("/unit/listUnit.jsp").forward(request, response);
    }
}
