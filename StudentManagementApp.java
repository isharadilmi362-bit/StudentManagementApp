
import java.sql.*;
import java.util.Scanner;

public class StudentManagementApp {
    private static final String URL = "jdbc:mysql://localhost:3306/studentdb";
    private static final String USER = "root";   // change if needed
    private static final String PASSWORD = "";   // change if needed

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private static void addStudent(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter age: ");
        int age = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter course: ");
        String course = sc.nextLine();

        String sql = "INSERT INTO students (name, age, course) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setInt(2, age);
        stmt.setString(3, course);

        int rows = stmt.executeUpdate();
        System.out.println(rows + " student(s) added.");
    }

    private static void viewStudents(Connection conn) throws SQLException {
        String sql = "SELECT * FROM students";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("\n--- Student List ---");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") +
                               ", Name: " + rs.getString("name") +
                               ", Age: " + rs.getInt("age") +
                               ", Course: " + rs.getString("course"));
        }
    }

    private static void updateStudent(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter student ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new name: ");
        String name = sc.nextLine();
        System.out.print("Enter new age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new course: ");
        String course = sc.nextLine();

        String sql = "UPDATE students SET name=?, age=?, course=? WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setInt(2, age);
        stmt.setString(3, course);
        stmt.setInt(4, id);

        int rows = stmt.executeUpdate();
        System.out.println(rows + " student(s) updated.");
    }

    private static void deleteStudent(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter student ID to delete: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM students WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);

        int rows = stmt.executeUpdate();
        System.out.println(rows + " student(s) deleted.");
    }

    public static void main(String[] args) {
        try (Connection conn = connect();
             Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n--- Student Management ---");
                System.out.println("1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Choose option: ");

                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1 -> addStudent(conn, sc);
                    case 2 -> viewStudents(conn);
                    case 3 -> updateStudent(conn, sc);
                    case 4 -> deleteStudent(conn, sc);
                    case 5 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
