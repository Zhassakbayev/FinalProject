
import com.epam.university_admissions.dao.ConnectionPool;
import com.epam.university_admissions.dao.Dao;
import com.epam.university_admissions.dao.DaoConnection;
import com.epam.university_admissions.dao.EntrantDAO;
import com.epam.university_admissions.entity.Entrant;

import java.util.ResourceBundle;

public class Test {
    public static void main(String[] args) {
//        ResourceBundle resourceBundle = ResourceBundle.getBundle("resources\\database");
//        System.out.println(resourceBundle.getString("driver"));
//        System.out.println(ConnectionPool.getInstance().toString());
//        System.out.println(ConnectionPool.getInstance().toString());
//        System.out.println(ConnectionPool.getInstance().toString());
//        System.out.println(ConnectionPool.getInstance().toString());
        Dao<Entrant> entrantDAO =new EntrantDAO();
//        Entrant entrant = new Entrant();
//        entrant.setIin("010821351631");
//        entrant.setCity("Oral");
//        entrant.setDistrict("selo Akzhayik");
//        entrant.setSchoolName("Enbek mektebi");
//        entrant.setUserId(11);
//        entrant.setBlockedStatus(true);
        for (Entrant entrant : entrantDAO.findAll()){
            System.out.println(entrant);
        }
    }
}
