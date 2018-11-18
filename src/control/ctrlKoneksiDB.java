package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ctrlKoneksiDB {
    
    /** Creates a new instance of DBConnection */
    public ctrlKoneksiDB() {  }
    
    public Connection openConnection() {
        Connection connect = null;
        try {
             Class.forName("com.mysql.jdbc.Driver");
             connect = DriverManager.getConnection("jdbc:mysql://localhost/ptpalito_nusantara","root","");
             return connect;
        }catch (SQLException se){	
            System.out.println("No Connection Open ! Error @ : "+se.getMessage());
            return null;
        }catch (Exception ex){	
            System.out.println("Failed Connect To Database ! Error @ : "+ex.getMessage());
            return null;
        }
    }
}
