import java.sql.SQLException;

public class TestaApp {

    TestaApp() throws SQLException {
       run();
    }

    public void run() throws SQLException {
        DBManager.openMySQLConnection();
        if(DBManager.connection != null && DBManager.statement!= null){
            System.out.print("connessione avvenuta");
        }

        DBManager.statement.execute("insert into users values('Ayoub','password')");
        DBManager.closeMySQLConnecion();
    }

    public static void main(String[] args) {
       try {
           new TestaApp();
       }catch (SQLException e){
           e.printStackTrace();
       }
    }
}
