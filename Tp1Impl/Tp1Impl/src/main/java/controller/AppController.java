package controller;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import plantaseparadoras.PlantaSeparadora;
import simulacion.CriteriosConstruccionPlantas;
import simulacion.CriteriosConstruccionTanques;
import simulacion.CriteriosVentaGas;
import simulacion.EleccionPlantaPeriodo;
import simulacion.EleccionTanquePeriodo;
import simulacion.EleccionVentaGasDias;
import simulacion.EleccionVentaGasPorcentaje;
import simulacion.EstadoFinancieroYacimiento;
import simulacion.Parcela;
import simulacion.Rig;
import simulacion.Simulador;
import tanques.TanqueAgua;
import tanques.TanqueGas;

/**
 * Hello world!
 *
 */
@RestController
public class AppController 
{
    @RequestMapping(value = "/Simular", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> simularController(@RequestParam("alpha1") String alpha1, @RequestParam("alpha2") String alpha2, @RequestParam("cantPozos") String cantPozos,
    													@RequestParam("cantMaxRigs") String cantMaxRigs, @RequestParam("volMaxReiny") String volMaxReiny, @RequestParam("presCrit") String presCrit, @RequestParam("dilCrit") String dilCrit,
    													@RequestParam("diasMaxSim") String diasMaxSim , @RequestParam(value = "pExcavaRigResult[]") String[] pExcavaRigResult,
    													@RequestParam(value = "cAlquilerRigResult[]") String[] cAlquilerRigResult,@RequestParam(value = "minDiasRigResult[]") String[] minDiasRigResult, @RequestParam(value = "consumRigResult[]") String[] consumRigResult,
    													@RequestParam(value = "ClassmodelPlantResult[]") String[] ClassmodelPlantResult,@RequestParam(value = "ClassdiasConstPlantResult[]") String[] ClassdiasConstPlantResult,@RequestParam(value = "ClasscostoConstPlantResult[]") String[] ClasscostoConstPlantResult,
    													@RequestParam(value = "ClasscantPlantResult[]") String[] ClasscantPlantResult,@RequestParam(value = "ClasspoderProcesResult[]") String[] ClasspoderProcesResult,
    													@RequestParam(value = "ClassmodelTanqResult[]") String[] ClassmodelTanqResult,@RequestParam(value = "ClassdiasConstTanqResult[]") String[] ClassdiasConstTanqResult,@RequestParam(value = "ClasscostoConstTanqResult[]") String[] ClasscostoConstTanqResult,@RequestParam(value = "ClassvolTotResult[]") String[] ClassvolTotResult,@RequestParam(value = "ClasscantTanqResult[]") String[] ClasscantTanqResult,@RequestParam(value = "ClasstipoTanqResult[]") String[] ClasstipoTanqResult,
    													@RequestParam("constrPlant") String criterioConstrPlant,@RequestParam("periodoPlant") String periodoPlant,@RequestParam("constrTanq") String constrTanq,@RequestParam("periodoTanq") String periodoTanq,
    													@RequestParam("constrPozos") String constrPozos,@RequestParam("elecParc") String elecParc,
    													@RequestParam("eleRig") String eleRig,@RequestParam("extrPozo") String extrPozo,
    													@RequestParam("eleVentGas") String eleVentGas,@RequestParam("eleFinali") String eleFinali,@RequestParam("critReiny") String critReiny,
    													@RequestParam("pPetr") String pPetr,@RequestParam("pGas") String pGas,@RequestParam("pAgua") String pAgua,@RequestParam("pcomb") String pcomb) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
      	
    	Connection conn = null;
		Statement stmt = null;
		double volTot;
		double porcPetr;
		double porcAgua;
		double porcGas;
		List<Parcela> parelasYacim = new ArrayList<Parcela>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@//localhost:1521/XE";
			conn = DriverManager.getConnection(url, "SYSTEM", "hoy");
			
			conn.setAutoCommit(true); 
			
			DatabaseMetaData dbmd=conn.getMetaData();  
			
		      //STEP 4: Execute a query
//		      System.out.println("Creating table in given database...");
		      stmt = conn.createStatement();
		      
		      String sql = "SELECT * FROM RESERVORIO";
		      ResultSet rs = stmt.executeQuery(sql);
		      rs.next();
		      volTot = rs.getFloat(2);
		      porcPetr = rs.getFloat(3);
		      porcAgua = rs.getFloat(4);
		      porcGas = rs.getFloat(5);
		      rs.close();
		      
		      String sqlParc = "SELECT * FROM PARCELAS";
		      ResultSet rsParc = stmt.executeQuery(sqlParc);
		      //STEP 5: Extract data from result set
		      while(rsParc.next()){
		         //Retrieve by column name
//		         int id  = rsParc.getInt(1);
		         double profundidadTotal = rsParc.getFloat(2);
		         double presionInicial = rsParc.getFloat(3);
		         String tipoTerreno = rsParc.getString(3);

		         //Display values
		         System.out.println("ID: " + profundidadTotal);
		         System.out.println(", Age: " + presionInicial);
		         System.out.println(", First: " + tipoTerreno);
		      }
		      rsParc.close();
		      
		      
		      
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

    	String respString= "primeraLinea\r\nsegundaLinea\r\n";
    	
    	respString +="ddddd";
    	
    	double alpha1d = Double.parseDouble(alpha1);
    	double alpha2d = Double.parseDouble(alpha2);
    	int cantPozosi = Integer.parseInt(cantPozos);
    	int cantMaxRigsi = Integer.parseInt(cantMaxRigs);
    	double volMaxReinyd = Double.parseDouble(volMaxReiny);
    	double presCritd = Double.parseDouble(presCrit);
    	double dilCritd = Double.parseDouble(dilCrit);
    	int diasMaxSimi = Integer.parseInt(diasMaxSim);
    	
    	EstadoFinancieroYacimiento estadFinan = new EstadoFinancieroYacimiento();
    	EstadoFinancieroYacimiento.setCostoCombustibleRigs(Double.parseDouble(pcomb));
    	EstadoFinancieroYacimiento.setPrecioCompraAgua(Double.parseDouble(pAgua));
    	EstadoFinancieroYacimiento.setPrecioVentaGas(Double.parseDouble(pGas));
    	EstadoFinancieroYacimiento.setPrecioVentaPetroleo(Double.parseDouble(pPetr));
    	
    	List<Rig> listaRigsDisponibles = new ArrayList<Rig>();
    	
    	for(int i =0;i<pExcavaRigResult.length;i++){
    		Rig rigAgregar = new Rig(Double.parseDouble(pExcavaRigResult[i]),Double.parseDouble(cAlquilerRigResult[i]), Integer.parseInt(minDiasRigResult[i]),Double.parseDouble(consumRigResult[i]));
    		listaRigsDisponibles.add(rigAgregar);
    	}
    	
    	List<PlantaSeparadora> listaPlantasDisponibles = new ArrayList<PlantaSeparadora>();
    	List<PlantaSeparadora> listaPlantasModelos = new ArrayList<PlantaSeparadora>();
    	
    	for(int i=0;i<ClassmodelPlantResult.length;i++){
    		PlantaSeparadora modeloPlantSep = new PlantaSeparadora(ClassmodelPlantResult[i],Integer.parseInt(ClassdiasConstPlantResult[i]),Double.parseDouble(ClasscostoConstPlantResult[i]),Double.parseDouble(ClasspoderProcesResult[i]) );
    		listaPlantasModelos.add(modeloPlantSep);
    		for(int j= 0;j<Integer.parseInt(ClasscantPlantResult[i]);j++){		
    			Class plantaClass = Class.forName("PlantaSeparadora");  
    			Method cloneMethod = plantaClass.getDeclaredMethod("clone");	
//  			PlantaSeparadora plantSep = (PlantaSeparadora) modeloPlantSep.clone();
    			cloneMethod.setAccessible(true);
    			PlantaSeparadora plantSepCopia = (PlantaSeparadora) cloneMethod.invoke(modeloPlantSep);
    			listaPlantasDisponibles.add(plantSepCopia);
    		}
    	}
    	
    	List<TanqueAgua> listaTanqAguaDisponibles = new ArrayList<TanqueAgua>();
    	List<TanqueGas> listaTanqGasDisponibles = new ArrayList<TanqueGas>();
    	
    	List<TanqueAgua> listaTanqueAguaModelos = new ArrayList<TanqueAgua>();
    	List<TanqueGas> listaTanqueGasModelos = new ArrayList<TanqueGas>();
    	
    	for(int i=0;i<ClassmodelTanqResult.length;i++){
    		if(ClasstipoTanqResult[i] == "agua"){
    			TanqueAgua modeloTanq = new TanqueAgua(ClassmodelTanqResult[i],Integer.parseInt(ClassdiasConstTanqResult[i]),Double.parseDouble(ClasscostoConstTanqResult[i]),Double.parseDouble(ClassvolTotResult[i]) );
    			listaTanqueAguaModelos.add(modeloTanq);
    			
    			
    			
    		}
    		//    		listaPlantasModelos.add(modeloPlantSep);
//    		for(int j= 0;j<Integer.parseInt(ClasscantPlantResult[i]);j++){		
//    			Class plantaClass = Class.forName("PlantaSeparadora");  
//    			Method cloneMethod = plantaClass.getDeclaredMethod("clone");	
////  			PlantaSeparadora plantSep = (PlantaSeparadora) modeloPlantSep.clone();
//    			cloneMethod.setAccessible(true);
//    			PlantaSeparadora plantSepCopia = (PlantaSeparadora) cloneMethod.invoke(modeloPlantSep);
//    			listaPlantasDisponibles.add(plantSepCopia);
//    		}
    	}
    	
    	
    	
//    	ClassmodelTanqResult[ind] = ClassmodelTanqDat[ind].value;
//    	ClassdiasConstTanqResult[ind] = ClassdiasConstTanqDat[ind].value;
//    	ClasscostoConstTanqResult[ind] = ClasscostoConstTanqDat[ind].value;
//    	ClassvolTotResult[ind] = ClassvolTotDat[ind].value;
//    	ClasstipoTanqResult[ind] = ClasstipoTanqDat[ind].valu
//    	
//    	
//    	    	ClasscantTanqResult[ind] = ClasscantTanqDat[ind].value;

    			
    			
    	CriteriosConstruccionPlantas criterioPlant = new EleccionPlantaPeriodo(Integer.parseInt(periodoPlant));
    	CriteriosConstruccionTanques criterioTanq = new EleccionTanquePeriodo(Integer.parseInt(periodoTanq));
    	
    	
//    	
//    	CriteriosVentaGas critVentGas;
//    	if(criterioVentaGas == "A"){
//    		critVentGas = new EleccionVentaGasPorcentaje(Double.parseDouble(parametroVentaGas));
//    	}else if(criterioVentaGas == "B"){
//    		critVentGas = new EleccionVentaGasDias(Integer.parseInt(parametroVentaGas));
//    	}
    	
//    	Simulador simul = new Simulador( double alpha1,double alpha2, );
    	
    	
		HttpHeaders hh = new HttpHeaders();	
		hh.set("Cache-control", "no-cache");
		hh.set("Expires", "0");
		hh.set("Content-Type", "application/json");
    	
		ResponseDto resp = new ResponseDto(respString,87.55);
        return new ResponseEntity<ResponseDto>(resp,hh,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/Grabar", method = RequestMethod.POST)
    public void grabarController(@RequestParam("volTot") String volTot,@RequestParam("porcPetr") String porcPetr,@RequestParam("porcGas") String porcGas,@RequestParam("porcAgua") String porcAgua,
    		@RequestParam(value = "profundidadTotalResult[]") String[] profundidadTotalResult,@RequestParam(value = "presionInicialResult[]") String[] presionInicialResult,@RequestParam(value = "tipoTerrenoResult[]") String[] tipoTerrenoResult ) throws SQLException{
		
		System.out.println("valoresTraidos: " + volTot  );
		
		
		Connection conn = null;
		Statement stmt = null;
		

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
//		      System.out.println("Creating table in given database...");
		      stmt = conn.createStatement();
		      
//		      String sql2 = "CREATE TABLE PARCELAS " +
//              "(id INTEGER not NULL, " +
//              " profundidadTotal FLOAT, " + 
//              " presionInicial FLOAT, " + 
//              " tipoTerreno VARCHAR(255), " + 
//              " PRIMARY KEY ( id ))"; 
//
//			 stmt.executeUpdate(sql2);
//			 System.out.println("Created table in given database...");
		      
		      
		      //		      stmt.executeUpdate("DELETE * FROM PARCELAS");

//		      String sql = "CREATE TABLE RESERVORIO " +
//		                   "(id INTEGER not NULL, " +
//		                   " volumen FLOAT, " + 
//		                   " porcPetroleo FLOAT, " + 
//		                   " porcGas FLOAT, " + 
//		                   " porcAgua FLOAT, " + 
//		                   " PRIMARY KEY ( id ))"; 
//
//		      stmt.executeUpdate(sql);
//		      System.out.println("Created table in given database...");
//		      

		      
		      stmt.executeUpdate("TRUNCATE TABLE PARCELAS");//vacio las parcelas viejas

		      for(int i=0;i<profundidadTotalResult.length;i++){//inserto las parcelas nuevas
			      String sqlParc = "INSERT INTO PARCELAS " +
                  "VALUES ( + " + i + ", " + profundidadTotalResult[i] +  ","+ presionInicialResult[i] + ", '" +tipoTerrenoResult[i] + "')";
			      stmt.executeUpdate(sqlParc);
		      }
//		      String sqll = "INSERT INTO PARCELAS " +
//		                   "VALUES (1, 80.55, 80.44, 'arcilloso')";
//		      stmt.executeUpdate(sqll);
//		      System.out.println("Inserted records into the table...");
		      
		      System.out.println(volTot + " " +porcPetr + " " +porcGas + " " + porcAgua);
		      String sql = "UPDATE RESERVORIO " +
	                   "SET volumen =" + volTot + "WHERE id=1";
		      stmt.executeUpdate(sql);
		      
		      sql = "UPDATE RESERVORIO " +
	                   "SET porcPetroleo =" + porcPetr + "WHERE id=1";
		      stmt.executeUpdate(sql);
		      
		      sql = "UPDATE RESERVORIO " +
	                   "SET porcGas =" + porcGas + "WHERE id=1";
		      stmt.executeUpdate(sql);
		      
		      sql = "UPDATE RESERVORIO " +
	                   "SET porcAgua =" + porcAgua + "WHERE id=1";
		      stmt.executeUpdate(sql);
		      
		      
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
			
    }	
    
}
