
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.UserDetails;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegistrationDao {
    private static PreparedStatement ps,ps1,ps2,ps3,ps4,ps5;
    static{
    try{
     ps=DBConnection.getConnection().prepareStatement("select * from user_details where adhar_no=?");
     ps1=DBConnection.getConnection().prepareStatement("Insert into user_details values(?,?,?,?,?,?,?,?,?)");
     ps2=DBConnection.getConnection().prepareStatement("select * from user_details where user_type='Voter'");
     ps3=DBConnection.getConnection().prepareStatement("delete from user_details where adhar_no=?");
     ps4=DBConnection.getConnection().prepareStatement("select adhar_no from user_details where user_type='Voter'");
     ps5=DBConnection.getConnection().prepareStatement("select * from user_details where adhar_no=? and user_type='Voter'");
     
    }
    catch(SQLException ex){
     ex.printStackTrace();
 }
}
    public static boolean searchUser(String userid)throws SQLException
    {
       ps.setString(1,userid);
       return ps.executeQuery().next();
    }
    public static boolean registerUser(UserDetails user)throws SQLException
    {
      ps1.setString(1,user.getUserid());
      ps1.setString(2,user.getPassword());
      ps1.setString(3,user.getUsername());
      ps1.setString(4,user.getAddress());
      ps1.setString(5,user.getCity());
      ps1.setString(6,user.getEmail());
      ps1.setLong(7,user.getMobile());
      ps1.setString(8,"Voter");
      ps1.setString(9,user.getGender());
     
      return ps1.executeUpdate()!=0;
    }
    public static ArrayList<String>getAllUserId()throws SQLException
    {
      ResultSet rs=ps4.executeQuery();
      ArrayList<String>list=new ArrayList<>();
      while(rs.next()){
          list.add(rs.getString(1));
          
      }
      return list;
    }
    
    public static UserDetails showUserDetails(String uid)throws SQLException{
        //ArrayList<UserPojo>list=new ArrayList();
        ps5.setString(1, uid);
        ResultSet rs=ps5.executeQuery();
        UserDetails user=new UserDetails();
        if(rs.next()){
            
            user.setUserid(rs.getString(1));
            user.setPassword(rs.getString(2));
            user.setUsername(rs.getString(3));
            user.setAddress(rs.getString(4));
            user.setCity(rs.getString(5));
            user.setEmail(rs.getString(6));
            user.setMobile(rs.getLong(7));
            
        }
        return user;
    }
 public static ArrayList<UserDetails> showUsers()throws SQLException{
        ArrayList<UserDetails>list=new ArrayList();
       
        ResultSet rs=ps2.executeQuery();
       
        while(rs.next()){
             UserDetails user=new UserDetails();
            user.setUserid(rs.getString(1));
            user.setPassword(rs.getString(2));
            user.setUsername(rs.getString(3));
            user.setAddress(rs.getString(4));
            user.setCity(rs.getString(5));
            user.setEmail(rs.getString(6));
            user.setMobile(rs.getLong(7));
            list.add(user);
        }
        return list;
    }
 public static boolean deleteUser(String uid)throws SQLException
 {
     ps3.setString(1,uid);
     return ps3.executeUpdate()==1;
 }
}