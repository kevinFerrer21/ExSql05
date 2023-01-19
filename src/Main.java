import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Connection connection = null;
        Statement statement;
        ResultSet resultSet;

        List<Student> italianStudent = new ArrayList<>();
        List<Student> germanStudent = new ArrayList<>();

        try {
            String DB_URL = "jdbc:mysql://localhost:3306/newdb";
            String USER = "developer";
            String PASSWORD = "passwordhere";

            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            statement = connection.createStatement();

            String queryItaly = "CREATE VIEW italian_students AS SElECT first_name,last_name FROM newdb.student WHERE (`country` = 'italy')";
            String queryGermany = "CREATE VIEW german_students AS SElECT first_name,last_name FROM newdb.student WHERE (`country` = 'germany')";

            statement.executeUpdate(queryGermany);
            statement.executeUpdate(queryItaly);
            resultSet = statement.executeQuery("SELECT * FROM italian_students");

            while (resultSet.next()) {
                String name = resultSet.getNString("first_name");
                String surname = resultSet.getString("last_name");
                italianStudent.add(new Student(name, surname));


            }
            for (Student x : italianStudent
            ) {
                System.out.println("Student from Italy : " + x.getName() + " " + x.getSurname());
            }

            resultSet = statement.executeQuery("SELECT * FROM german_students");

            while (resultSet.next()) {
                String name = resultSet.getString("first_name");
                String surname = resultSet.getString("last_name");
                germanStudent.add(new Student(name, surname));
            }


            for (Student y : germanStudent
            ) {
                System.out.println("Student from Germany : " + y.getName() + " " + y.getSurname());
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}


