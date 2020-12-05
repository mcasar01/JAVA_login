import java.sql.*;

public class Validate {
    public static boolean checkUser(String email,char [] pass) 
    {
    	String passw = String.valueOf(pass);
        boolean st =false;
        try {

            //loading drivers for mysql
            Class.forName("org.sqlite.JDBC");
        	
        	String ruta = "C:\\Users\\mcps_\\eclipse-workspace\\Java_Login_DPS\\src\\DPS_Login.db";
            //creating connection with the database
            Connection con = DriverManager.getConnection("jdbc:sqlite:"+ ruta);
            PreparedStatement ps = con.prepareStatement("select * from login where email=? and pass=?");
            ps.setString(1, email);
            ps.setString(2, passw);
            ResultSet rs =ps.executeQuery();
            st = rs.next();
            passw =null;

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return st;
        
    }   
}