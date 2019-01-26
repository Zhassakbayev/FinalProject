
import com.epam.university_admissions.dao.*;
import com.epam.university_admissions.entity.Entrant;
import com.epam.university_admissions.entity.FacultiesReport;
import com.epam.university_admissions.entity.Faculty;
import com.epam.university_admissions.entity.User;

import java.util.ResourceBundle;

public class Test {
    public static void main(String[] args) {
//        ResourceBundle resourceBundle = ResourceBundle.getBundle("resources\\database");
//        System.out.println(resourceBundle.getString("driver"));
//        System.out.println(ConnectionPool.getInstance().toString());
//        System.out.println(ConnectionPool.getInstance().toString());
//        System.out.println(ConnectionPool.getInstance().toString());
//        System.out.println(ConnectionPool.getInstance().toString());
//        Dao<Faculty> facultyDao = new FacultyDAO();
        Dao<User> userDao = new UserDAO();
        User user = ((UserDAO) userDao).findUserByEmailPassword("akbolzhasakbaev@gmail.com","1234asd");
        System.out.println(user);
//        Entrant entrant = new Entrant();
//        entrant.setIin("010821351631");
//        entrant.setCity("Oral");
//        entrant.setDistrict("selo Akzhayik");
//        entrant.setSchoolName("Enbek mektebi");
//        entrant.setUserId(11);
//        entrant.setBlockedStatus(true);
//        for (Entrant entrant : entrantDAO.findAll()){
//            System.out.println(entrant);
//        }
//        for (Faculty faculty : facultyDao.findAll()){
//            System.out.println(faculty);
//        }

    }
}
