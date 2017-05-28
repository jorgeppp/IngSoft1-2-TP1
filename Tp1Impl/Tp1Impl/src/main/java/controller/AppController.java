package controller;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import plantaseparadoras.PlantaSeparadora;
import plantaseparadoras.PlantasFactory;
import simulacion.CriteriosConstruccionPlantas;
import simulacion.CriteriosConstruccionPozos;
import simulacion.CriteriosConstruccionTanques;
import simulacion.CriteriosEleccionParcela;
import simulacion.CriteriosEleccionRig;
import simulacion.CriteriosExtraccionPozos;
import simulacion.CriteriosFinalizacion;
import simulacion.CriteriosReinyeccion;
import simulacion.CriteriosVentaGas;
import simulacion.EleccionExtraccionAzar;
import simulacion.EleccionExtraccionTotal;
import simulacion.EleccionFinSimulacionDias;
import simulacion.EleccionFinSimulacionDilucionCritica;
import simulacion.EleccionMaximaCantidadRigs;
import simulacion.EleccionParcelaMenorProfundidad;
import simulacion.EleccionParcelaTerreno;
import simulacion.EleccionPlantaPeriodo;
import simulacion.EleccionPozoPeriodo;
import simulacion.EleccionReinyeccionAguaMaxima;
import simulacion.EleccionReinyeccionAguaYGasTanques;
import simulacion.EleccionRigAzar;
import simulacion.EleccionRigPoderExcavacion;
import simulacion.EleccionTanquePeriodo;
import simulacion.EleccionVentaGasDias;
import simulacion.EleccionVentaGasPorcentaje;
import simulacion.EquipoIngenieria;
import simulacion.EstadoFinancieroYacimiento;
import simulacion.Parcela;
import simulacion.Pozo;
import simulacion.Reservorio;
import simulacion.Rig;
import simulacion.Simulador;
import simulacion.TipoDeTerreno;
import simulacion.Yacimiento;
import tanques.TanqueAgua;
import tanques.TanqueGas;
import tanques.TanquesFactory;

/**
 * Hello world!
 *
 */
@RestController
public class AppController
{
	private String logGrabar="";
	private String log="";
	
	@RequestMapping(value = "/Simular", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> simularController(@RequestParam("alpha1") String alpha1, @RequestParam("alpha2") String alpha2, @RequestParam("cantPozos") String cantPozos,
    													@RequestParam("cantMaxRigs") String cantMaxRigs, @RequestParam("volMaxReiny") String volMaxReiny, @RequestParam("presCrit") String presCrit, @RequestParam("dilCrit") String dilCrit,
    													@RequestParam("diasMaxSim") String diasMaxSim , @RequestParam(value = "pExcavaRigResult[]") String[] pExcavaRigResult,
    													@RequestParam(value = "cAlquilerRigResult[]") String[] cAlquilerRigResult,@RequestParam(value = "minDiasRigResult[]") String[] minDiasRigResult, @RequestParam(value = "consumRigResult[]") String[] consumRigResult,
    													@RequestParam(value = "ClassmodelPlantResult[]") String[] ClassmodelPlantResult,@RequestParam(value = "ClassdiasConstPlantResult[]") String[] ClassdiasConstPlantResult,@RequestParam(value = "ClasscostoConstPlantResult[]") String[] ClasscostoConstPlantResult,
    													@RequestParam(value = "ClasscantPlantResult[]") String[] ClasscantPlantResult,@RequestParam(value = "ClasspoderProcesResult[]") String[] ClasspoderProcesResult,
    													@RequestParam(value = "ClassmodelTanqResult[]") String[] ClassmodelTanqResult,@RequestParam(value = "ClassdiasConstTanqResult[]") String[] ClassdiasConstTanqResult,@RequestParam(value = "ClasscostoConstTanqResult[]") String[] ClasscostoConstTanqResult,@RequestParam(value = "ClassvolTotResult[]") String[] ClassvolTotResult,@RequestParam(value = "ClasscantTanqResult[]") String[] ClasscantTanqResult,@RequestParam(value = "ClasstipoTanqResult[]") String[] ClasstipoTanqResult,
    													@RequestParam("pPetr") String pPetr,@RequestParam("pGas") String pGas,@RequestParam("pAgua") String pAgua,@RequestParam("pcomb") String pcomb,
    													@RequestParam("constrPlant") String criterioConstrPlant,@RequestParam("periodoPlant") String periodoPlant,@RequestParam("constrTanq") String constrTanq,@RequestParam("periodoTanq") String periodoTanq,
    													@RequestParam("constrPozos") String constrPozos,@RequestParam("periodoPozo") String periodoPozo,@RequestParam("elecParc") String elecParc,
    													@RequestParam("eleRig") String eleRig,@RequestParam("extrPozo") String extrPozo,
    													@RequestParam("eleVentGas") String eleVentGas,@RequestParam("periodoGas") String periodoGas,@RequestParam("eleFinali") String eleFinali,@RequestParam("critReiny") String critReiny
    													){
      	
    	
		log = logGrabar;
		
		
		EstadoFinancieroYacimiento estadFinan = new EstadoFinancieroYacimiento();
    	EstadoFinancieroYacimiento.setCostoCombustibleRigs(Double.parseDouble(pcomb));
    	EstadoFinancieroYacimiento.setPrecioCompraAgua(Double.parseDouble(pAgua));
    	EstadoFinancieroYacimiento.setPrecioVentaGas(Double.parseDouble(pGas));
    	EstadoFinancieroYacimiento.setPrecioVentaPetroleo(Double.parseDouble(pPetr));
    	
		System.out.println("Precio combustible Rigs " + EstadoFinancieroYacimiento.getCostoCombustibleRigs());
		log += "Precio combustible Rigs " + EstadoFinancieroYacimiento.getCostoCombustibleRigs() + "\r\n";
	
		System.out.println("Precio compra agua " + EstadoFinancieroYacimiento.getPrecioCompraAgua());
		log += "Precio compra agua " + EstadoFinancieroYacimiento.getPrecioCompraAgua() + "\r\n";

		System.out.println("Precio venta gas " + EstadoFinancieroYacimiento.getPrecioVentaGas());
		log += "Precio venta gas " + EstadoFinancieroYacimiento.getPrecioVentaGas() + "\r\n";
    	
		System.out.println("Precio venta petróleo " + EstadoFinancieroYacimiento.getPrecioVentaGas());
		log += "Precio venta petróleo " + EstadoFinancieroYacimiento.getPrecioVentaPetroleo() + "\r\n";
    	
    	List<PlantaSeparadora> listaPlantasDisponibles = new ArrayList<PlantaSeparadora>();
    	List<PlantaSeparadora> listaPlantasModelos = new ArrayList<PlantaSeparadora>();
    	
    	for(int i=0;i<ClassmodelPlantResult.length;i++){
    		PlantaSeparadora modeloPlantSep = new PlantaSeparadora(ClassmodelPlantResult[i],Integer.parseInt(ClassdiasConstPlantResult[i]),Double.parseDouble(ClasscostoConstPlantResult[i]),Double.parseDouble(ClasspoderProcesResult[i]) );
    		listaPlantasModelos.add(modeloPlantSep);
    		for(int j= 0;j<Integer.parseInt(ClasscantPlantResult[i]);j++){		
    			PlantaSeparadora plantSepCopia = (PlantaSeparadora) modeloPlantSep.clone();
    			listaPlantasDisponibles.add(plantSepCopia);
    		}
    	}
    	
		System.out.println("Modelo de plantas " + listaPlantasModelos);
		log += "Modelo de plantas " + listaPlantasModelos.toString()+ "\r\n";
		
		
		System.out.println("Plantas iniciales " + listaPlantasDisponibles.toString());
		log += "Plantas iniciales " + listaPlantasDisponibles.toString() + "\r\n";
    	
    	
    	
    	PlantasFactory plantasFactory = new PlantasFactory(listaPlantasModelos);
    	
    	Connection conn = null;
		Statement stmt = null;
		double volTot=0;
		double porcPetr=0;
		double porcAgua=0;
		double porcGas=0;
		List<Parcela> parelasYacim = new ArrayList<Parcela>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@//localhost:1521/XE";
			conn = DriverManager.getConnection(url, "SYSTEM", "hoy");
			
			conn.setAutoCommit(true); 
			
			
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
		      
			  System.out.println("Volumen yacimiento " + volTot);
		      log += "Volumen yacimiento " + volTot + "\r\n";
		      
			  System.out.println("Porcentaje petróleo " + porcPetr);
		      log += "Porcentaje petróleo " + porcPetr + "\r\n";
		      
			  System.out.println("Porcentaje gas " + porcGas);
		      log += "Porcentaje gas " + porcGas + "\r\n";
		      
			  System.out.println("Porcentaje agua " + porcAgua);
		      log += "Porcentaje agua " + porcAgua + "\r\n";
		      
		      
		      
			  
		      String sqlParc = "SELECT * FROM PARCELAS";
		      ResultSet rsParc = stmt.executeQuery(sqlParc);
		      //STEP 5: Extract data from result set
		      while(rsParc.next()){
		         //Retrieve by column name
//		         int id  = rsParc.getInt(1);
		        double profundidadTotal = rsParc.getFloat(2);
		        double presionInicial = rsParc.getFloat(3);
		        String tipoTerreno = rsParc.getString(4);
		        TipoDeTerreno tipoDeTerreno = TipoDeTerreno.ARCILLOSO;
		        switch (tipoTerreno) {
		            case "arcilloso":  tipoDeTerreno = TipoDeTerreno.ARCILLOSO;
		                     break;
		            case "normal":  tipoDeTerreno = TipoDeTerreno.NORMAL;
		                     break;
		            case "rocoso":   tipoDeTerreno = TipoDeTerreno.ROCOSO;
		                     break;
		        }

		        Pozo poz = new Pozo(profundidadTotal,presionInicial,estadFinan);
		         
		        Random aleatorio =  new Random();
		 		PlantaSeparadora plantPozo =  listaPlantasDisponibles.get(aleatorio.nextInt(listaPlantasDisponibles.size()));
		 		Parcela parc =  new Parcela(tipoDeTerreno, poz, plantPozo );
		 		parelasYacim.add(parc);
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


		System.out.println("Parcelas yacimiento " + parelasYacim);
		log += "Parcelas yacimiento " + parelasYacim.toString() + "\r\n";
    	
    	double alpha1d = Double.parseDouble(alpha1);
    	double alpha2d = Double.parseDouble(alpha2);
    	int cantPozosi = Integer.parseInt(cantPozos);
    	int cantMaxRigsi = Integer.parseInt(cantMaxRigs);
    	double volMaxReinyd = Double.parseDouble(volMaxReiny);
    	double presCritd = Double.parseDouble(presCrit);
    	int dilCriti = Integer.parseInt(dilCrit);
    	int diasMaxSimi = Integer.parseInt(diasMaxSim);
    	
		System.out.println("Alpha1 " + alpha1d);
		log += "Alpha1 " + alpha1d + "\r\n";
		
		System.out.println("Alpha2 " + alpha1d);
		log += "Alpha2 " + alpha2d + "\r\n";
		
		System.out.println("Cantidad pozos construir " + cantPozosi);
		log += "Cantidad pozos construir " + cantPozosi + "\r\n";
		
		System.out.println("Volumen maximo reinyectar " + volMaxReinyd);
		log += "Volumen maximo reinyectar " + volMaxReinyd + "\r\n";
		
		System.out.println("Presión crítica " + presCritd);
		log += "Presión crítica " + presCritd + "\r\n";
		
		System.out.println("Dilución crítica " + dilCriti);
		log += "Dilución crítica  " + dilCriti + "\r\n";
		
		System.out.println("Dias máximo simulación " + diasMaxSimi);
		log += "Dias máximo simulación  " + diasMaxSimi + "\r\n";

    	Reservorio reservorio = new Reservorio(volTot, porcPetr, porcGas, porcAgua);
    	
    	List<Rig> listaRigsDisponibles = new ArrayList<Rig>();
    	
    	for(int i =0;i<pExcavaRigResult.length;i++){
    		Rig rigAgregar = new Rig(Double.parseDouble(pExcavaRigResult[i]),Double.parseDouble(cAlquilerRigResult[i]), Integer.parseInt(minDiasRigResult[i]),Double.parseDouble(consumRigResult[i]));
    		listaRigsDisponibles.add(rigAgregar);
    	}
    	
		System.out.println("Lista rigs disponiles " + listaRigsDisponibles);
		log += "Lista rigs disponiles " + listaRigsDisponibles.toString() + "\r\n";
    	
    	List<TanqueAgua> listaTanqAguaDisponibles = new ArrayList<TanqueAgua>();
    	List<TanqueGas> listaTanqGasDisponibles = new ArrayList<TanqueGas>();
    	
    	List<TanqueAgua> listaTanqueAguaModelos = new ArrayList<TanqueAgua>();
    	List<TanqueGas> listaTanqueGasModelos = new ArrayList<TanqueGas>();
    	
    	TanquesFactory tanquesFactory = new TanquesFactory(listaTanqueAguaModelos,listaTanqueGasModelos);
    	
    	for(int i=0;i<ClassmodelTanqResult.length;i++){
    		System.out.println(ClasstipoTanqResult[i] );
    		if(ClasstipoTanqResult[i].equals("agua") ){
    			TanqueAgua modeloTanqAgua = new TanqueAgua(ClassmodelTanqResult[i],Integer.parseInt(ClassdiasConstTanqResult[i]),Double.parseDouble(ClasscostoConstTanqResult[i]),Double.parseDouble(ClassvolTotResult[i]) );
    			listaTanqueAguaModelos.add(modeloTanqAgua);
    			for(int j=0;j<Integer.parseInt(ClasscantTanqResult[i]);j++){;
        			TanqueAgua tanqAguaCopia = (TanqueAgua) modeloTanqAgua.clone();
        			listaTanqAguaDisponibles.add(tanqAguaCopia);
    			}
    		}else{
    			TanqueGas modeloTanqGas = new TanqueGas(ClassmodelTanqResult[i],Integer.parseInt(ClassdiasConstTanqResult[i]),Double.parseDouble(ClasscostoConstTanqResult[i]),Double.parseDouble(ClassvolTotResult[i]),estadFinan );
    			listaTanqueGasModelos.add(modeloTanqGas);
    			for(int j=0;j<Integer.parseInt(ClasscantTanqResult[i]);j++){
        			TanqueGas tanqGasCopia = (TanqueGas) modeloTanqGas.clone();
        			listaTanqGasDisponibles.add(tanqGasCopia);
    			}
    		}
    	}
		System.out.println("Lista modelos tanques agua " + listaTanqueAguaModelos);
		log += "Lista modelas tanques agua " + listaTanqueAguaModelos.toString() + "\r\n";
    	
		System.out.println("Lista modelos tanques gas " + listaTanqueGasModelos);
		log += "Lista modelas tanques gas " + listaTanqueGasModelos.toString() + "\r\n";
    	
		System.out.println("Lista tanques agua disponiles " + listaTanqAguaDisponibles);
		log += "Lista tanques agua disponiles " + listaTanqAguaDisponibles.toString() + "\r\n";
    	
		System.out.println("Lista tanques gas disponiles " + listaTanqGasDisponibles);
		log += "Lista tanques gas disponiles " + listaTanqGasDisponibles.toString() + "\r\n";
		

		
    	CriteriosConstruccionPlantas criteriosConstruccionPlantas = new EleccionPlantaPeriodo(Integer.parseInt(periodoPlant));
    	CriteriosConstruccionTanques criteriosConstruccionTanques = new EleccionTanquePeriodo(Integer.parseInt(periodoTanq));

		System.out.println("Criterio construcción plantas: periodo " + periodoPlant);
		log += "Criterio construcción plantas: periodo " + periodoPlant + "\r\n";
		
		System.out.println("Criterio construcción tanques: periodo " + periodoTanq);
		log += "Criterio construcción tanques: periodo " + periodoTanq + "\r\n";
    	
    	CriteriosVentaGas criteriosVentaGas =null;	
    	if(eleVentGas.equals("porcentajeGas")){//depende del valor de la pantalla
    		criteriosVentaGas = new EleccionVentaGasPorcentaje(Double.parseDouble(periodoGas));	
    		System.out.println("Criterio venta gas: porcentaje " + periodoGas);
    		log += "Criterio venta gas: porcentaje " + periodoGas + "\r\n";
    	}else if(eleVentGas.equals("periodoGas")){
    		criteriosVentaGas = new EleccionVentaGasDias(Integer.parseInt(periodoGas));
    		System.out.println("Criterio venta gas: periodo " + periodoGas);
    		log += "Criterio venta gas: periodo " + periodoGas + "\r\n";
    	}
    	
    	CriteriosConstruccionPozos criteriosConstruccionPozos=null;
    	
    	if(constrPozos.equals("periodPozo")){
    		criteriosConstruccionPozos = new EleccionPozoPeriodo(Integer.parseInt(periodoPozo));
    		System.out.println("Criterio construcción pozo: periodo " + periodoPozo);
    		log += "Criterio construcción pozo: periodoo " + periodoPozo + "\r\n";
    	}else if(constrPozos.equals("maxRigs")){
    		criteriosConstruccionPozos = new EleccionMaximaCantidadRigs();
    		System.out.println("Criterio construcción pozos : Máxima cantdad rigs");
    		log += "Criterio construcción pozos : Máxima cantdad rigs" + "\r\n";
    	}
    	
    	CriteriosEleccionParcela criteriosEleccionParcela=null;
    	if(elecParc.equals("menorProfun")){
    		criteriosEleccionParcela = new EleccionParcelaMenorProfundidad();
    		System.out.println("Criterio elección parcela: menor profundidad");
    		log += "Criterio elección parcela: menor profundidad" + "\r\n";
    	}else if(elecParc.equals("terreno")){
    		criteriosEleccionParcela = new EleccionParcelaTerreno();
    		System.out.println("Criterio elección parcela: mejor tipo terreno");
    		log += "Criterio elección parcela: mejor tipo terreno" + "\r\n";
    	}
    	CriteriosEleccionRig criteriosEleccionRig=null;
    	if(eleRig.equals("alAzar")){
    		criteriosEleccionRig = new EleccionRigAzar();
    		System.out.println("Criterio elección rigs: Al azar");
    		log += "Criterio elección rigs: Al azar" + "\r\n";
    	}else if(eleRig.equals("poderExcava")){
    		criteriosEleccionRig = new EleccionRigPoderExcavacion();
    		System.out.println("Criterio elección rigs: Mayor poder excavación");
    		log += "Criterio elección rigs: Mayor poder excavación" + "\r\n";
    	}
    	
    	CriteriosExtraccionPozos criteriosExtraccionPozos=null;
    	if(extrPozo.equals("azarExtr")){
    		criteriosExtraccionPozos = new EleccionExtraccionAzar();
    		System.out.println("Criterio extracción pozos: Al azar");
    		log += "Criterio extracción pozos: Al azar" + "\r\n";
    	}else if(extrPozo.equals("todasAbiertas")){
    		criteriosExtraccionPozos = new EleccionExtraccionTotal();
    		System.out.println("Criterio extracción pozos: Todas las valvulas abiertas");
    		log += "Criterio extracción pozos: Todas las valvulas abiertas" + "\r\n";
    	}
    	
    	CriteriosFinalizacion criteriosFinalizacion=null;
    	if(eleFinali.equals("cantMax")){
    		criteriosFinalizacion = new EleccionFinSimulacionDias();
    		System.out.println("Criterio finalización: Fin cantidad dias");
    		log += "Criterio finalizacin: Fin cantidad dias" + "\r\n";
    	}else if(eleFinali.equals("dilCrit")){
    		criteriosFinalizacion = new EleccionFinSimulacionDilucionCritica();
    		System.out.println("Criterio finalización: Dilución crítica");
    		log += "Criterio finalización: Dilución crítica" + "\r\n";
    	}
    	
    	CriteriosReinyeccion criteriosReinyeccion=null;
    	if(critReiny.equals("agua")){
    		criteriosReinyeccion = new EleccionReinyeccionAguaMaxima();
    		System.out.println("Criterio reinyección: Agua maxima");
    		log += "Criterio reinyección: Agua maxima" + "\r\n";
    	}else if(critReiny.equals("aguaYgas")){
    		criteriosReinyeccion = new EleccionReinyeccionAguaYGasTanques();
    		System.out.println("Criterio reinyección: Agua y gas de los tanques");
    		log += "Criterio reinyección: Agua y gas de los tanques" + "\r\n";
    	}
    	
    	Yacimiento yacimiento = new Yacimiento(reservorio,parelasYacim,listaPlantasDisponibles,listaTanqAguaDisponibles,listaTanqGasDisponibles);
    	
    	EquipoIngenieria equipoIngenieria = new EquipoIngenieria(criteriosConstruccionPozos,
    			 criteriosConstruccionPlantas,
    			 criteriosConstruccionTanques,  criteriosExtraccionPozos,
    			 criteriosReinyeccion,  criteriosFinalizacion,
    			 criteriosVentaGas,  criteriosEleccionRig,
    			 criteriosEleccionParcela);

    	Simulador simulador = new Simulador( alpha1d, alpha2d, cantPozosi, cantMaxRigsi,
    			volMaxReinyd, presCritd, dilCriti, diasMaxSimi,
    			yacimiento, estadFinan,equipoIngenieria,plantasFactory,tanquesFactory,listaRigsDisponibles);
    	
    	ResponseDto respuestaSimulador = simulador.execute();
    	log += respuestaSimulador.getStringResp();
    	respuestaSimulador.setStringResp(log);
		
    	HttpHeaders hh = new HttpHeaders();	
		hh.set("Cache-control", "no-cache");
		hh.set("Expires", "0");
		hh.set("Content-Type", "application/json");
    	
        return new ResponseEntity<ResponseDto>(respuestaSimulador,hh,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/Grabar", method = RequestMethod.POST)
    public void grabarController(@RequestParam("volTot") String volTot,@RequestParam("porcPetr") String porcPetr,@RequestParam("porcGas") String porcGas,@RequestParam("porcAgua") String porcAgua,
    		@RequestParam(value = "profundidadTotalResult[]") String[] profundidadTotalResult,@RequestParam(value = "presionInicialResult[]") String[] presionInicialResult,@RequestParam(value = "tipoTerrenoResult[]") String[] tipoTerrenoResult ) throws SQLException{
		
    	logGrabar = "";
		System.out.println("Valores ingresados para grabar:");
		logGrabar += "Valores ingresados para grabar: \r\n";
    	
		System.out.println("volumen total: " + volTot  );
		logGrabar += "volumen total: " + volTot + "\r\n";
		
		System.out.println("Porcentaje petróleo: " + porcPetr  );
		logGrabar += "Porcentaje petróleo: " + porcPetr + "\r\n";
		
		System.out.println("Porcentaje gas: " + porcGas  );
		logGrabar += "Porcentaje gas: " + porcGas + "\r\n";
		
		System.out.println("Porcentaje agua: " + porcAgua  );
		logGrabar += "Porcentaje agua: " + porcAgua + "\r\n";
		
		for(int i=0;i<profundidadTotalResult.length;i++){
			System.out.println("Parcela " + i  );
			logGrabar += "Parcela " + i  + "\r\n";
			
			System.out.println("Profundidad total " + profundidadTotalResult[i]  );
			logGrabar += "Profundidad total " + profundidadTotalResult[i] + "\r\n";
			
			System.out.println("Presión inicial " + presionInicialResult[i]  );
			logGrabar += "Presión inicial " + presionInicialResult[i] + "\r\n";
			
			System.out.println("Tipo terreno " + tipoTerrenoResult[i]  );
			logGrabar += "Tipo terreno " + tipoTerrenoResult[i] + "\r\n";
		}
		System.out.println(  );
		System.out.println( logGrabar );
		
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
