package sn.groupeisi.jeeappli.servlets.Unit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;
import sn.groupeisi.jeeappli.dao.UnitDAO;
import sn.groupeisi.jeeappli.database.HibernateUtil;
import sn.groupeisi.jeeappli.entiies.Unit;

import java.io.IOException;
import java.util.List;

@WebServlet("/allUnits")
public class AllUnits extends HttpServlet {

    private UnitDAO unitDAO;

    @Override
    public void init() throws ServletException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        unitDAO = new UnitDAO(sessionFactory);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        UnitDAO unitDAO = new UnitDAO(sessionFactory);

        // Récupération des options pour l'adresse depuis la base de données
        List<String> addressOptions = unitDAO.getDistinctAddresses();

        request.setAttribute("addressOptions", addressOptions);

        // Récupération des unités filtrées
        String minPriceStr = request.getParameter("minPrice");
        String maxPriceStr = request.getParameter("maxPrice");
        String address = request.getParameter("address");
        String numberOfRoomsStr = request.getParameter("numberOfRooms");

        Double minPrice = minPriceStr != null && !minPriceStr.isEmpty() ? Double.parseDouble(minPriceStr) : null;
        Double maxPrice = maxPriceStr != null && !maxPriceStr.isEmpty() ? Double.parseDouble(maxPriceStr) : null;
        Integer numberOfRooms = numberOfRoomsStr != null && !numberOfRoomsStr.isEmpty() ? Integer.parseInt(numberOfRoomsStr) : null;

        List<Unit> units = unitDAO.getFilteredUnits(minPrice, maxPrice, address, numberOfRooms);
        request.setAttribute("units", units);

        request.getRequestDispatcher("unit/allUnits.jsp").forward(request, response);
    }

}
