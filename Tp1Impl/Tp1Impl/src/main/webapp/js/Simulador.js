$(document).ready(function () {
  
    $("#grabarDiv").hide();
    $("#simularDiv").show();
    
    sessionStorage.setItem('simuladorLibre', true);


    $("#pestanaSimular").click(function () {
        $("#grabarDiv").hide();
        $("#simularDiv").show();
    });

    $("#pestanaGrabar").click(function () {
        $("#simularDiv").hide();
        $("#grabarDiv").show();
    });
    
    $("#agregarRig").on("click",function () { 
		$("#tablaRigs").append(	
    	'<tr><td class="textRight">Rig:</td>' 
		+'<td class="textRight"> <label for="pexcava">Poder excavaci&oacute;n d&iacute;a</label> </td> <td class="textLeft"> <input size="1" type="text" class="pExcavaRig" id="pexcava" /></td>'
		+'<td class="textRight"> <label for="cAlquilerDia">Costo alquiler d&iacute;a</label> </td> <td class="textLeft"> <input size="1" type="text" id="cAlquilerDia" class="cAlquilerRig"/></td>'
		+'<td class="textRight"> <label for="minDiasPag">M&iacute;nima cantidad dias pagar</label> </td> <td class="textLeft"> <input size="1" type="text" id="minDiasPag" class="minDiasRig"/></td>'
		+'<td class="textRight"> <label for="consumCombDia">Consumo combustible d&iacute;a</label> </td> <td class="textLeft"> <input size="1" type="text" id="consumCombDia" class="consumRig"/></td>'
		+'<td class="textRight"> </td>'
	+'</tr>');
    });
    
    $("#agregarPlanta").on("click",function () { 
		$("#tablaModelosPlantas").append(	
				'<tr>'
				+ '<td class="textRight"> <label for="modelPlant">Modelo Planta</label> </td> <td class="textLeft"> <input size="1" type="text" id="modelPlant" class="ClassmodelPlant"/></td>'
				+ '<td class="textRight"> <label for="diasConstPlant">D&iacute;as construcci&oacute;n</label> </td> <td class="textLeft"> <input size="1" type="text" id="diasConstPlant" class="ClassdiasConstPlant"/></td>'
				+ '<td class="textRight"> <label for="costoConstPlant">Costo construci&oacute;n</label> </td> <td class="textLeft"> <input size="1" type="text" id="costoConstPlant" class="ClasscostoConstPlant"/></td>'
				+ '<td class="textRight"> <label for="poderProces">Poder procesamiento d&iacute;a</label> </td> <td class="textLeft"> <input size="1" type="text" id="poderProces" class="ClasscantPlant"/></td>'
				+ '<td class="textRight"> <label for="cantPlant">Cantidad inicial</label> </td> <td class="textLeft"> <input size="1" type="text" id="poderProces" class="ClasspoderProces"/></td>'
				+ '<td class="textRight"></td>'
			 +'</tr>');
    });
    
    $("#agregarTanque").on("click",function () { 
		$("#modelTanq").append(	
			
			'	<tr>'
			+'	<td class="textRight"> Modelo Tanque </td> <td class="textLeft"> <input size="1" type="text" id="modelPlant" class="ClassmodelTanq"/></td>'
			+'	<td class="textRight"> D&iacute;as construcci&oacute;n </td> <td class="textLeft"> <input size="1" type="text" id="diasConstTanq" class="ClassdiasConstTanq"/></td>'
			+'	<td class="textRight"> Costo construci&oacute;n </td> <td class="textLeft"> <input size="1" type="text" id="costoConstTanq" class="ClasscostoConstTanq"/></td>'
			+'	<td class="textRight"> Volumen total </td> <td class="textLeft"> <input size="1" type="text" id="poderProces" class="ClassvolTot"/></td>'
			+'	<td class="textRight"> Cantidad inicial </td> <td class="textLeft"> <input size="1" type="text" id="cantTanq" class="ClasscantTanq"/></td>'
			+'	<td class="textRight"> Tipo tanque </td> <td class="textLeft"> <select id="tipoTanq" class="ClasstipoTanq">'
			+'			<option value="agua">Agua</option>'
			+'			<option value="gas">Gas</option>'
			+'	</select></td>'
			+'<td class="textRight" ></td>'
			+'</tr>')
    });
    
    $("#agregarParcela").on("click",function () { 
		$("#tablaParcelas").append(	
			
				'<tr>'
				+'<td class="textRight">Parcela :</td>'
				+'<td class="textRight"> <label for="profundidadTotal">Profundidad total</label> </td> <td class="textLeft"> <input size="1" type="text" id="profundidadTotal" class="ClassprofundidadTotal"/></td>'
				+'<td class="textRight"> <label for="presionInicial">Presi&oacute;n inicial</label> </td> <td class="textLeft"> <input size="1" type="text" id="presionInicial" class="ClasspresionInicial"/></td>'
				+'<td class="textRight"> <label for="tipoTerreno">Tipo terreno</label> </td> <td class="textLeft"> <select id="tipoTerreno" class="ClasstipoTerreno">'
				+'		<option value="arcilloso">ARCILLOSO</option>'
				+'		<option value="normal">NORMAL</option>'
				+'		<option value="rocoso">ROCOSO</option>'
				+'</select></td>'
				+'<td class="textRight"></td>'
			+'</tr>')
    });
    
    
     

    $("#simularBtn").click(function () {    	
    	if(sessionStorage.getItem('simuladorLibre') == "false"){
    		alert("El simulador se encuentra ocupado. Intente nuevamente mas tarde.")
    	}else{
	    	sessionStorage.setItem('simuladorLibre', false);
	    	
	        var alpha1 = $("#alpha1").val();
	        var alpha2 = $("#alpha2").val();
	        var cantPozos = $("#cantPozos").val();
	        var cantMaxRigs = $("#cantMaxRigs").val();
	        var volMaxReiny = $("#volMaxReiny").val();
	        var presCrit = $("#presCrit").val();
	        var dilCrit = $("#dilCrit").val();
	        var diasMaxSim = $("#diasMaxSim").val();
	        
	        var pPetr = $("#pPetr").val();
	        var pGas = $("#pGas").val();
	        var pAgua = $("#pAgua").val();
	        var pcomb = $("#pcomb").val();
	        
	        var pExcavaRigResult = [];   
	        var cAlquilerRigResult  = [];     
	        var minDiasRigResult  = [];
	        var consumRigResult  = [];
	        
	        var pExcavaRigDat = $(".pExcavaRig")
	        var cAlquilerRigDat = $(".cAlquilerRig")
	        var minDiasRigDat = $(".minDiasRig")
	        var consumRigDat = $(".consumRig")
	        
	        var cantRigs = pExcavaRigDat.length;
	        for(var ind =0; ind < cantRigs;ind++){
	        	pExcavaRigResult[ind] = pExcavaRigDat[ind].value;
	        	cAlquilerRigResult[ind] = cAlquilerRigDat[ind].value;
	        	minDiasRigResult[ind] = minDiasRigDat[ind].value;
	        	consumRigResult[ind] = consumRigDat[ind].value;
	        }
	        
	        var ClassmodelPlantResult = [];   
	        var ClassdiasConstPlantResult  = [];     
	        var ClasscostoConstPlantResult  = [];
	        var ClasscantPlantResult  = [];
	        var ClasspoderProcesResult  = [];
	        
	        var ClassmodelPlantDat = $(".ClassmodelPlant")
	        var ClassdiasConstPlantDat = $(".ClassdiasConstPlant")
	        var ClasscostoConstPlantDat = $(".ClasscostoConstPlant")
	        var ClasscantPlantDat = $(".ClasscantPlant")
	        var ClasspoderProcesDat = $(".ClasspoderProces")
 
	        var cantPlantas = ClassmodelPlantDat.length;
	        for(var ind =0; ind < cantPlantas;ind++){
	        	ClassmodelPlantResult[ind] = ClassmodelPlantDat[ind].value;
	        	ClassdiasConstPlantResult[ind] = ClassdiasConstPlantDat[ind].value;
	        	ClasscostoConstPlantResult[ind] = ClasscostoConstPlantDat[ind].value;
	        	ClasscantPlantResult[ind] = ClasscantPlantDat[ind].value;
	        	ClasspoderProcesResult[ind] = ClasspoderProcesDat[ind].value;
	        }
	        
	        var ClassmodelTanqResult = [];   
	        var ClassdiasConstTanqResult  = [];     
	        var ClasscostoConstTanqResult  = [];
	        var ClassvolTotResult  = [];
	        var ClasscantTanqResult  = [];
	        var ClasstipoTanqResult  = [];
	        
	        var ClassmodelTanqDat = $(".ClassmodelTanq")
	        var ClassdiasConstTanqDat = $(".ClassdiasConstTanq")
	        var ClasscostoConstTanqDat = $(".ClasscostoConstTanq")
	        var ClassvolTotDat = $(".ClassvolTot")
	        var ClasscantTanqDat = $(".ClasscantTanq")
	        var ClasstipoTanqDat = $(".ClasstipoTanq")
 
	        var cantTanq = ClassmodelTanqDat.length;
	        for(var ind =0; ind < cantTanq;ind++){
	        	ClassmodelTanqResult[ind] = ClassmodelTanqDat[ind].value;
	        	ClassdiasConstTanqResult[ind] = ClassdiasConstTanqDat[ind].value;
	        	ClasscostoConstTanqResult[ind] = ClasscostoConstTanqDat[ind].value;
	        	ClassvolTotResult[ind] = ClassvolTotDat[ind].value;
	        	ClasscantTanqResult[ind] = ClasscantTanqDat[ind].value;
	        	ClasstipoTanqResult[ind] = ClasstipoTanqDat[ind].value;
	        }
	        
	        var constrPlant = $("#constrPlant").val();
	        var periodoPlant = $("#periodoPlant").val();
	        
	        var constrTanq =  $("#constrTanq").val();
	        var periodoTanq = $("#periodoTanq").val();
	        
	        var constrPozos = $("#constrPozos").val();
	        var periodoPozo = $("#periodoPozo").val();
	        
	        
	        var elecParc = $("#elecParc").val();
	        var eleRig = $("#eleRig").val();
	        
	        var extrPozo = $("#extrPozo").val();
	        
	        var eleFinali = $("#eleFinali").val();
	        
	        var eleVentGas = $("#eleVentGas").val();
	        var periodoGas = $("#periodoGas").val();
	        
	        var critReiny = $("#critReiny").val();
	        
	        
	        
	        
	        
	        $.ajax({
	            type: 'POST',
	            url: 'home/Simular',
	//     dataType: 'json',
	            data : {alpha1: alpha1, alpha2: alpha2, cantPozos: cantPozos, cantMaxRigs: cantMaxRigs, volMaxReiny: volMaxReiny, presCrit: presCrit, dilCrit:dilCrit,diasMaxSim : diasMaxSim, pExcavaRigResult : pExcavaRigResult,cAlquilerRigResult : cAlquilerRigResult, minDiasRigResult : minDiasRigResult, consumRigResult : consumRigResult,
	            	ClassmodelPlantResult:ClassmodelPlantResult,ClassdiasConstPlantResult:ClassdiasConstPlantResult,ClasscostoConstPlantResult:ClasscostoConstPlantResult,ClasscantPlantResult:ClasscantPlantResult,ClasspoderProcesResult:ClasspoderProcesResult,
	            	ClassmodelTanqResult: ClassmodelTanqResult,ClassdiasConstTanqResult :ClassdiasConstTanqResult,ClasscostoConstTanqResult:ClasscostoConstTanqResult,ClassvolTotResult:ClassvolTotResult,ClasscantTanqResult:ClasscantTanqResult,ClasstipoTanqResult:ClasstipoTanqResult,
	            	pPetr:pPetr,pGas:pGas,pAgua:pAgua,pcomb:pcomb,
	            	constrPlant:constrPlant,periodoPlant:periodoPlant,constrTanq:constrTanq,periodoTanq:periodoTanq,
	            	constrPozos:constrPozos,periodoPozo:periodoPozo,elecParc:elecParc,eleRig:eleRig,extrPozo:extrPozo,
	            	eleFinali:eleFinali,eleVentGas:eleVentGas,periodoGas:periodoGas,critReiny:critReiny
	            	},
	            async: true,
	            success: function (response) {
	                console.log(response);
	                alert("La ganancia total resultante fue de " + response.numResp);
	            	function download(filename, text) {
	            		  var element = document.createElement('a');
	            		  element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(text));
	            		  element.setAttribute('download', filename);
	            		  element.style.display = 'none';
	            		  document.body.appendChild(element);
	            		  element.click();
	            		  document.body.removeChild(element);
	            	}
	
	            	download("archivo.txt",response.stringResp);
	            	sessionStorage.setItem('simuladorLibre', true);  	
	            },
	            error: function (data) {
	                console.log('Ajax error!');
	                sessionStorage.setItem('simuladorLibre', true);
	            }
	        });
        
    	}
    });
    
    $("#ingresarValoresBtn").click(function () {    	
    	if(sessionStorage.getItem('almacenandorLibre') == "false"){
    		alert("El simulador se encuentra ocupado. Intente nuevamente mas tarde.")
    	}else{
	    	sessionStorage.setItem('almacenandorLibre', false);
	    	

	        var volTot = $("#volTot").val();
	        var porcPetr = $("#porcPetr").val();
	        var porcGas = $("#porcGas").val();
	        var porcAgua = $("#porcAgua").val();
	        
	        var profundidadTotalResult = [];   
	        var presionInicialResult  = [];     
	        var tipoTerrenoResult  = [];
	        
	        var profundidadTotalDat = $(".ClassprofundidadTotal")
	        var presionInicialDat = $(".ClasspresionInicial")
	        var tipoTerrenoDat = $(".ClasstipoTerreno")
	        
	        var cantParcelas = profundidadTotalDat.length;
	        for(var ind =0; ind < cantParcelas;ind++){
	        	profundidadTotalResult[ind] = profundidadTotalDat[ind].value;
	        	presionInicialResult[ind] = presionInicialDat[ind].value;
	        	tipoTerrenoResult[ind] = tipoTerrenoDat[ind].value;
	        }

//	              
	        $.ajax({
	            type: 'POST',
	            url: 'home/Grabar',
	//     dataType: 'json',
	            data : {volTot : volTot, porcPetr : porcPetr, porcGas : porcGas, porcAgua : porcAgua,profundidadTotalResult:profundidadTotalResult, presionInicialResult:presionInicialResult,tipoTerrenoResult:tipoTerrenoResult},
	            async: true,
	            success: function (response) {
	                console.log(response);
	                alert("Los datos se guardaron correctamente.")
	            	sessionStorage.setItem('almacenandorLibre', true);  	
	            },
	            error: function (data) {
	                console.log('Ajax error!');
	                alert("Los datos no se guardaron correctamente.")
	                sessionStorage.setItem('almacenandorLibre', true);
	            }
	        });
        
    	}
    });
    
    
    
    
    

});