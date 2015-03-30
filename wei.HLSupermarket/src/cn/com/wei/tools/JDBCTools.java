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
   
   //�������ݿ�
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
  
  //���·���
  public int update(String sql){
	 
	  try{
		  stmt=this.getConnection().createStatement();
		 return stmt.executeUpdate(sql);
	  }catch(Exception e){
		 // e.printStackTrace();
		  return -1;
	  }
	  
  }
  
  //��ѯ����
  public ResultSet query(String sql){
	  try{
		  stmt=this.getConnection().createStatement();
		  return stmt.executeQuery(sql);
	  }catch(Exception e){
		  //e.printStackTrace();
		  return null;
	  }
	  
  }
  
  //�ر���Դ
  public void close(ResultSet rs){
	  if(rs!=null){
		  try{
			   rs.close();
		  }catch(Exception e){
			  e.printStackTrace();
			  System.out.println("�ر�rs����");
		  }
		 
	  }
	  
	  if(conn!=null){
		  try{
			  conn.close();
		  }catch(Exception e){
			  e.printStackTrace();
			  System.out.println("�ر�conn����");
		  }
		  
	  }
	  
	  if(stmt!=null){
		  try{
			 stmt.close(); 
		  }catch(Exception e){
			  e.printStackTrace();
			  System.out.println("�ر�stmt����");
		  }
	  }
  }
  
}
