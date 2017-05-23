package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import simulacion.CriteriosVentaGas;
import simulacion.EleccionVentaGasDias;
import simulacion.EleccionVentaGasPorcentaje;
import simulacion.Simulador;

/**
 * Hello world!
 *
 */
@RestController
public class AppController 
{
    @RequestMapping(value = "/Simular", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> simularController(@RequestParam("criterioVentaGas") String criterioVentaGas, @RequestParam("parametroVentaGas") String parametroVentaGas){
    	
    	String respString= "primeraLinea\r\nsegundaLinea\r\n";
    	
    	respString +="ddddd";	
    	
    	CriteriosVentaGas critVentGas;
    	if(criterioVentaGas == "A"){
    		critVentGas = new EleccionVentaGasPorcentaje(Double.parseDouble(parametroVentaGas));
    	}else if(criterioVentaGas == "B"){
    		critVentGas = new EleccionVentaGasDias(Integer.parseInt(parametroVentaGas));
    	}
    	
//    	Simulador simul = new Simulador( double alpha1,double alpha2, );
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
		HttpHeaders hh = new HttpHeaders();	
		hh.set("Cache-control", "no-cache");
		hh.set("Expires", "0");
		hh.set("Content-Type", "application/json");
    	
		ResponseDto resp = new ResponseDto(respString,87.55);
		
		System.out.println("hollla");
		
		
		
		
        return new ResponseEntity<ResponseDto>(resp,hh,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/Grabar", method = RequestMethod.POST)
    public void grabarController(@RequestParam("producto") String producto,@RequestParam("nrocuenta") String nrocuenta) throws SQLException{
		
		System.out.println("valoresTraidos: " + producto + " " + nrocuenta );
		
		
		Connection conn = null;
		PreparedStatement sentenciaSQLPrepared = null;
		Statement sentenciaSQLStatement = null;
		PreparedStatement sentenciaSQL=null;
		ResultSet resultado = null;
		
		ResultSet resultado2 = null;

		
		Statement stmt = null;
		
		Statement stmtDelete = null;

		try {
			System.out.println("hh");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@//localhost:1521/XE";
			conn = DriverManager.getConnection(url, "SYSTEM", "hoy");
			
			conn.setAutoCommit(true); 
			
			DatabaseMetaData dbmd=conn.getMetaData();  
			 
			System.out.println("Database Product Name: "+dbmd.getDatabaseProductName());  
			System.out.println("Database Product Version: "+dbmd.getDatabaseProductVersion()); 
			System.out.println("Driver Name: "+dbmd.getDriverName());  
			System.out.println("Driver Version: "+dbmd.getDriverVersion());			
			
			System.out.println("UserName: "+dbmd.getUserName());
			
		      //STEP 4: Execute a query
		      System.out.println("Creating table in given database...");
		      stmt = conn.createStatement();
		      
//		      String sql = "CREATE TABLE REGISTRATION " +
//		                   "(id INTEGER not NULL, " +
//		                   " first VARCHAR(255), " + 
//		                   " last VARCHAR(255), " + 
//		                   " age INTEGER, " + 
//		                   " PRIMARY KEY ( id ))"; 
//
//		      stmt.executeUpdate(sql);
//		      System.out.println("Created table in given database...");
		      
			  String sql = "INSERT INTO Registration " +
		                   "VALUES (100, 'Zara', 'Ali', 18)";
		      stmt.executeUpdate(sql);
		      sql = "INSERT INTO Registration " +
		                   "VALUES (101, 'Mahnaz', 'Fatma', 25)";
		      stmt.executeUpdate(sql);
		      sql = "INSERT INTO Registration " +
		                   "VALUES (102, 'Zaid', 'Khan', 30)";
		      stmt.executeUpdate(sql);
		      sql = "INSERT INTO Registration " +
		                   "VALUES(103, 'Sumit', 'Mittal', 28)";
		      stmt.executeUpdate(sql);
		      System.out.println("Inserted records into the table...");
		      
		      
		      
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
			
    }	
    
}
