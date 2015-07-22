/*
 Forget autoincrement - many sites don't like it - so I coded for the ID field.
I use "prepared statements in order to put values from variables into a record.
This way, I have to insert a blank record with ???? values, then I can set strings and ints into the appropriate fields.
 */
package db2;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Scott
 */
public class Db2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // TODO code application logic here
        //Class.forName("com.mysql.jdbc.Driver");
        //Connection con = getConnection(jdbc.mysql)
        int nID = 0, nSchool;
        String sFirst, sLast;
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "chess";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "Raiders23";
        String sID;
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null; // rs will get one record from a table. 
        //from http://theopentutorials.com/tutorials/java/jdbc/how-to-retrieve-all-rows-from-mysql-table-using-jdbc/
        String query = "SELECT * FROM players"; // get all the records from the players table

        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, userName, password);
            //conn.close();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }
        // from http://mrbool.com/how-to-connect-with-mysql-database-using-java/25440

        System.out.println("Inserting records into the table...");
        //try {
        //stmt = conn.createStatement();
        stmt = conn.createStatement();

        //} catch (SQLException ex) {
        // Logger.getLogger(Db1.class.getName()).log(Level.SEVERE, null, ex);
        //}
        //try {
        rs = stmt.executeQuery(query);
        //} catch (SQLException ex) {
        //  Logger.getLogger(Db1.class.getName()).log(Level.SEVERE, null, ex);
        //}
        // getting idea from http://theopentutorials.com/tutorials/java/jdbc/how-to-retrieve-all-rows-from-mysql-table-using-jdbc/
        // the above example shows excelent object orientation - I watered it down.
        // loop to the last record - in order to get the last record id:
        while (rs.next()) {

            nID = rs.getInt("ID");
            //sFirst = rs.getString("FirstName");
            //sLast = rs.getString("LastName");
            //nSchool = rs.getInt("nSchool");
            //System.out.println(nID + " " + sFirst + " " + sLast + " " + nSchool);

        }
        //nID = rs.getInt("ID");

         //the block below allows me to add 2 records to the table.
        //it works great - but I would like the ID (the first field) to be added by way of autoIncrement - not hard-coded.
        //INSERT INTO CUSTOMER_DETAILS (NAME, ADD_ID, GENDER, PHONE_NO) VALUES ('James Bond', LAST_INSERT_ID(), 'MALE', 007);
        String sql = "INSERT INTO players "
                + "VALUES (NULL,'Scott', 'Jimi', 18)";
        //nID = SELECT LAST_INSERT_ID();
        //nID = rs.getInt("ID");
        //sql = "INSERT INTO players(ID, FirstName, LastName, nSchool) VALUES (LAST_INSERT_ID()+1,'Scott', 'Jimi', 18)";
        nID++;
        System.out.println(nID);
        sID = String.valueOf(nID);
        // create a blank record.
        //sql = "INSERT INTO players(ID, FirstName, LastName, nSchool)  VALUES (?,?, ?, ?)";
        //sql = "INSERT INTO players VALUES (13,'Scott', 'Jimi', 18)";
        //sql = "INSERT INTO testtable(name, address1, address2,phone, email)VALUES(?,?,?,?,?)"
/*
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Db2.class.getName()).log(Level.SEVERE, null, ex);
        }*/
// from http://zetcode.com/db/mysqljava/
        try {
            //String sql = "INSERT INTO testtable(name, address1, address2,phone, email)VALUES(?,?,?,?,?)"
            sql = "INSERT INTO players(ID, FirstName, LastName, nSchool)  VALUES (?,?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, nID);
            statement.setString(2, "hhhhh");
            statement.setString(3, "G");
            statement.setInt(4, 99);
            statement.executeUpdate();
        } catch (SQLException e) {

        }

        nID++;
        sID = String.valueOf(nID);
        System.out.println(nID);
        //sql = "INSERT INTO players " + "VALUES ( NULL,'Christine', 'Hector', 25)";
        //sql = "INSERT INTO players(ID, FirstName, LastName, nSchool) VALUES (LAST_INSERT_ID()+1,'Christine', 'Hector', 18)";
        //sql = "INSERT INTO players(ID, FirstName, LastName, nSchool) VALUES (nID,'Christine', 'Hector', 18)";
        //sql = "INSERT INTO players VALUES (14,'Christine', 'Hector', 18)";
        /*try {
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Db2.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        try {
            //String sql = "INSERT INTO testtable(name, address1, address2,phone, email)VALUES(?,?,?,?,?)"
            sql = "INSERT INTO players(ID, FirstName, LastName, nSchool)  VALUES (?,?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, nID);
            statement.setString(2, "tytytyt");
            statement.setString(3, "Ethyl");
            statement.setInt(4, 22);
            statement.executeUpdate();
        } catch (SQLException e) {

        }
        //rs.first(); // can't just get he first - must re-query to get latest inserts.
        rs = stmt.executeQuery(query);
        
        while (rs.next()) {

            nID = rs.getInt("ID");
            sFirst = rs.getString("FirstName");
            sLast = rs.getString("LastName");
            nSchool = rs.getInt("nSchool");
            System.out.println(nID + " " + sFirst + " " + sLast + " " + nSchool);

        }

//Read more: http://mrbool.com/how-to-connect-with-mysql-database-using-java/25440#ixzz3g3MdI3bl
            /*
         try {
         // The newInstance() call is a work around for some
         // broken Java implementations
            
         Class.forName("com.mysql.jdbc.Driver").newInstance();
         } catch (Exception ex) {
         // handle the error

         //Read more: http://mrbool.com/how-to-connect-with-mysql-database-using-java/25440#ixzz3g3MdI3bl
         /*
         try {
         // The newInstance() call is a work around for some
         // broken Java implementations
            
         Class.forName("com.mysql.jdbc.Driver").newInstance();
         } catch (Exception ex) {
         // handle the error
         }*/
    }

}
