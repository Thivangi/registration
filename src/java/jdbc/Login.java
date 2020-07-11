/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Login extends HttpServlet {
public Login(){
    super();
}
   
     
    


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      try{
           String name=request.getParameter("user");
           String password=request.getParameter("password");
           
          
           String sql="select * from registration where name=? password=?";
           Class.forName("com.mysql.jdbc.Driver");
           try {
               Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/portal","root","");
               PreparedStatement ps=conn.prepareStatement(sql);
               ps.setString(1,name);
               ps.setString(2, password);
             ResultSet rs=ps.executeQuery();
               PrintWriter out=response.getWriter();
             
             while(rs.next()){
                 name=rs.getString(name);
                 password=rs.getString(password);
             }
             
             
              if(name.equals("name") && password.equals("password"))
        {
            out.println("Authentication Successful");
        }
        else
        {
            out.println("Authentication Failed");
        }
              
               
               
           } catch (SQLException ex) {
               Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
           }
           
       }
       
           
               
           catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
