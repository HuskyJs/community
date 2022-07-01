//package tk.quanjia.community.mysql;
//
//import org.springframework.beans.factory.annotation.Value;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.Properties;
//
//public class jdbc {
//    @Value("${spring.datasource.url}")
//    private String url;
//    @Value("${spring.datasource.username}")
//    private String username;
//    @Value("${spring.datasource.password}")
//    private String password;
//    @Value("${spring.datasource.driver_class_name}")
//    private String driver_class_name;
//
//
//    public static Connection connect05() throws SQLException, ClassNotFoundException, IOException {
//        Properties properties = new Properties();
//        properties.load(new FileInputStream("src\\mysql.properties"));
//        String user = properties.getProperty(username);
//        String password = properties.getProperty("password");
//        String driver = properties.getProperty("driver");
//        String url = properties.getProperty("url");
//        Connection connection = DriverManager.getConnection(url, user, password);
//        System.out.println("方式5" + connection);
//        return connection;
//    }
//}
