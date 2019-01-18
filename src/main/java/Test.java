
import com.epam.university_admissions.dao.ConnectionPool;
import com.epam.university_admissions.dao.Dao;
import com.epam.university_admissions.dao.DaoConnection;
import com.epam.university_admissions.dao.EntrantDAO;
import com.epam.university_admissions.entity.Entrant;

import java.util.ResourceBundle;

public class Test {
    public static void main(String[] args) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resources\\database");
        System.out.println(resourceBundle.getString("driver"));
        System.out.println(ConnectionPool.getInstance().toString());
        System.out.println(ConnectionPool.getInstance().toString());
        System.out.println(ConnectionPool.getInstance().toString());
        System.out.println(ConnectionPool.getInstance().toString());

        Dao<Entrant> dao = new EntrantDAO();
        DaoConnection daoConnection = new EntrantDAO();
    }
}
