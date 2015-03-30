package cn.com.wei.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCTools {
   private Connection conn=null;
   private Statement stmt=null;
   String url="jdbc:mysql://localhost:3306/supermarket";
   String user="root";
   String psw="root";
   
   //连接数据库
  private Connection getConnection(){
	  try{
		  Class.forName("com.mysql.jdbc.Driver");
		  conn=DriverManager.getConnection(url,user,psw);
	  }catch(Exception e){
		  e.printStackTrace();
		  conn=null;
	  }
	  return conn;
  }
  
  //更新方法
  public int update(String sql){
	 
	  try{
		  stmt=this.getConnection().createStatement();
		 return stmt.executeUpdate(sql);
	  }catch(Exception e){
		 // e.printStackTrace();
		  return -1;
	  }
	  
  }
  
  //查询方法
  public ResultSet query(String sql){
	  try{
		  stmt=this.getConnection().createStatement();
		  return stmt.executeQuery(sql);
	  }catch(Exception e){
		  //e.printStackTrace();
		  return null;
	  }
	  
  }
  
  //关闭资源
  public void close(ResultSet rs){
	  if(rs!=null){
		  try{
			   rs.close();
		  }catch(Exception e){
			  e.printStackTrace();
			  System.out.println("关闭rs出错");
		  }
		 
	  }
	  
	  if(conn!=null){
		  try{
			  conn.close();
		  }catch(Exception e){
			  e.printStackTrace();
			  System.out.println("关闭conn出错");
		  }
		  
	  }
	  
	  if(stmt!=null){
		  try{
			 stmt.close(); 
		  }catch(Exception e){
			  e.printStackTrace();
			  System.out.println("关闭stmt出错");
		  }
	  }
  }
  
}
