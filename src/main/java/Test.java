
import com.epam.university_admissions.dao.*;
import com.epam.university_admissions.entity.Entrant;
import com.epam.university_admissions.entity.Faculty;
import com.epam.university_admissions.entity.User;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
//        ResourceBundle resourceBundle = ResourceBundle.getBundle("resources\\database");
//        System.out.println(resourceBundle.getString("driver"));
//        System.out.println(ConnectionPool.getInstance().toString());
//        System.out.println(ConnectionPool.getInstance().toString());
//        System.out.println(ConnectionPool.getInstance().toString());
//        System.out.println(ConnectionPool.getInstance().toString());
//        Dao<Faculty> facultyDao = new FacultyDAO();
//        Dao<User> userDao = new UserDAO();
//        EntrantDAO entrantDAO = new EntrantDAO();
//        User user = userDao.find(2);
//        Entrant entrant = entrantDAO.findEntrantByUserId(user);
////        user=entrant;
//        System.out.println(entrant);
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

        System.out.println(isDateValid("2019-05-31"));
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String str = myFormat.format(new Date());
        System.out.println("Entered date has not arrived yet!");
    }

    public static boolean isDateValid(String date) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        myFormat.setLenient(false);
        try {
            myFormat.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
//    public static boolean checkToCurrentDate(String date){
//        Pattern pattern = Pattern.compile("[\\d]{4}");
//        Matcher matcher = pattern.matcher(date);
//        while(matcher.find()){
//            System.out.println(matcher.group());
//        }
//    }



}
