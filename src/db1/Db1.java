/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 ******** I can add players to my chess database. I have to figure how to display them, and also make use of the autonumber for the indexed ID
 */
package db1;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Scott
 */
public class Db1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // TODO code application logic here
        //Class.forName("com.mysql.jdbc.Driver");
        //Connection con = getConnection(jdbc.mysql)
        int nID, nSchool;
        String sFirst, sLast;
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "chess";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "***";
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
        while (rs.next()) {

            nID = rs.getInt("ID");
            sFirst = rs.getString("FirstName");
            sLast = rs.getString("LastName");
            nSchool = rs.getInt("nSchool");
            System.out.println(nID + " " + sFirst + " " + sLast + " " + nSchool);

        }

         //the block below allows me to add 2 records to the table.
        //it works great - but I would like the ID (the first field) to be added by way of autoIncrement - not hard-coded.
        String sql = "INSERT INTO players " + "VALUES (7,'Scott', 'Jimi', 18)";
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Db1.class.getName()).log(Level.SEVERE, null, ex);
        }
        sql = "INSERT INTO players "
                + "VALUES ( 8,'Christine', 'Hector', 25)";
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Db1.class.getName()).log(Level.SEVERE, null, ex);
        }

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
