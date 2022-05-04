import java.sql.ResultSet;
import java.sql.SQLException;

public class TestaApp {

    TestaApp() throws SQLException {
       run();
    }

    public void run() throws SQLException {
        DBManager.openMySQLConnection();
        if(DBManager.connection != null && DBManager.statement!= null){
            System.out.print("connessione avvenuta\n\n");
        }

        DBManager.statement.execute("drop table if exists users;");
        DBManager.statement.execute("create table users(\n" + "username varchar(25) PRIMARY KEY,\n" + "pw varchar(25)\n" + ");");
        DBManager.statement.execute("insert into users values('Ayoub','password')");
        ResultSet rs = DBManager.statement.executeQuery("select * from users");
        /*stampo la tabella, c'è solo Ayoub , passowrd*/
        while(rs.next()){
            System.out.println(String.format("username= %s , pw= %s", rs.getString("username"),rs.getString("pw")));
        }

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
