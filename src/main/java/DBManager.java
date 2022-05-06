
import java.sql.*;
import java.util.ArrayList;
import java.util.TimeZone;

/*Chiamando il metodo openMySQLConnection() crea la connessione con il db e direttamente lo statement
 * con il metodo closeMySQLConnection() chiude la connessione e lo statement
 *
 * Esempio:
 * * * * * * * * * * * * * * * * * * * * * * * * **
 * DBManager.openMySQLConnection();               *
 * DBManager.statement.execute("stringa in sql"); *
 * DBManager.closeMySQLConnection();              *
 * * * * * * * * * * * * * * * * * * * * * * * * **
 *
 */

public class DBManager {

    public static final String JDBC_Driver_MySQL = "com.mysql.cj.jdbc.Driver";
    public static final String JDBC_URL_MySQL = "jdbc:mysql://localhost:3306/lospaccialibri?user=Ayoub&password" +
            "=Password&serverTimezone=" + TimeZone.getDefault().getID();

    public static String JDBC_Driver = null;
    public static String JDBC_URL = null;
    static Connection connection;
    static Statement statement;

    public static void setConnection(String Driver, String URL) {
        JDBC_Driver = Driver;
        JDBC_URL = URL;
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            if (JDBC_Driver == null || JDBC_URL == null) {
                throw new IllegalStateException("Illegal request. Call setConnection() before.");
            }
            try {
                Class.forName(JDBC_Driver);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(JDBC_URL);
            showMetadata();
        }
        return connection;
    }

    public static void showMetadata() throws SQLException {
        if (connection == null) {
            throw new IllegalStateException("Illegal request. Connection not established");
        }

    }

    public static void closeMySQLConnecion() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        if(statement != null){
            statement.close();
        }
    }

    public static void openMySQLConnection() throws SQLException {
        DBManager.setConnection(DBManager.JDBC_Driver_MySQL, DBManager.JDBC_URL_MySQL);
        if(statement == null) {
            statement = DBManager.getConnection().createStatement();
        }
    }

    public static void readUserFromDB(ArrayList<User> users) throws SQLException {
        /*la funzione legge dal DB gli utenti e gli aggiunge alla lista passata come parametro*/
        openMySQLConnection();
        ResultSet rs = DBManager.statement.executeQuery("select * from users");
        while(rs.next()){
            String username = String.format("%s",rs.getString("username"));
            String passoword = String.format("%s", rs.getString("pw"));
            users.add(new User(username,passoword));
        }
        closeMySQLConnecion();
    }

    public static void insertUserToDB(User user) throws SQLException {
        /*la funzione prende un User e lo aggiunge al DB*/
        openMySQLConnection();
        String username = user.getUsername();
        String password = user.getPassword();
        String sql = String.format("insert into users values ('%s','%s')",username,password);
        DBManager.statement.execute(sql);
        closeMySQLConnecion();
    }

}
