
import com.epam.university_admissions.dao.ConnectionPool;

import java.util.ResourceBundle;

public class Test {
    public static void main(String[] args) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resources\\database");
        System.out.println(resourceBundle.getString("driver"));
        System.out.println(ConnectionPool.getInstance().toString());
        System.out.println(ConnectionPool.getInstance().toString());
        System.out.println(ConnectionPool.getInstance().toString());
        System.out.println(ConnectionPool.getInstance().toString());
    }
}
