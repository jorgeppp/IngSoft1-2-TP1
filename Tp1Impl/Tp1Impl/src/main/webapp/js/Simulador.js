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

    $("#simularBtn").click(function () {    	
    	if(sessionStorage.getItem('simuladorLibre') == "false"){
    		alert("El simulador se encuentra ocupado. Intente nuevamente mas tarde.")
    	}else{
	    	sessionStorage.setItem('simuladorLibre', false);
	    	
//	        var nombre;
//	        var apellido;
//	        var razonSocial;
//	        var tipo;
//	        var numero;
//	        var producto;
//	        var subproducto;
//	        var nrocuenta;
//	        var fechaDesde;
//	        var fechaHasta;
//	        var sucursal;
//	        var estado;
//	        var nroSolicitud;
//	        var evidencia;
//	        var tipoDocumental;
//	        var subtipoDocumental;
//	
//	        nombre = $("#nom").val();
//	        apellido = $("#ape").val();
//	        razonSocial = $("#razonSoc").val();
//	        tipo = $("#tipo").val();
//	        numero = $("#numero").val();
//	        producto = $("#producto").val();
//	        subproducto = $("#subproducto").val();
//	        nrocuenta = $("#nrocuenta").val();
//	        fechaDesde = $("#fechaDesde").val();
//	        fechaHasta = $("#fechaHasta").val();
//	        sucursal = $("#sucursal").val();
//	        estado = $("#estado").val();
//	        nroSolicitud = $("#nroSolicitud").val();
//	        evidencia = $("#evidencia").val();
//	        tipoDocumental = $("#tipoDocumental").val();
//	        subtipoDocumental = $("#subtipoDocumental").val();
//	              
	        $.ajax({
	            type: 'POST',
	            url: 'home/Simular',
	//     dataType: 'json',
	//          data: {pestana: pestana, nombre: nombre, apellido: apellido, razonSocial: razonSocial, tipo: tipo, numero: numero, producto: producto, subproducto: subproducto, nrocuenta: nrocuenta, fechaDesde: fechaDesde, fechaHasta: fechaHasta, sucursal: sucursal, estado: estado, nroSolicitud: nroSolicitud, evidencia: evidencia, tipoDocumental: tipoDocumental, subtipoDocumental: subtipoDocumental},
	            data : {},
	            async: true,
	            success: function (response) {
	                console.log(response);
	                alert(response.numResp)
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
	    	
//	        var nombre;
//	        var apellido;
//	        var razonSocial;
//	        var tipo;
//	        var numero;
//	        var producto;
//	        var subproducto;
//	        var nrocuenta;
//	        var fechaDesde;
//	        var fechaHasta;
//	        var sucursal;
//	        var estado;
//	        var nroSolicitud;
//	        var evidencia;
//	        var tipoDocumental;
//	        var subtipoDocumental;
//	
//	        nombre = $("#nom").val();
//	        apellido = $("#ape").val();
//	        razonSocial = $("#razonSoc").val();
//	        tipo = $("#tipo").val();
//	        numero = $("#numero").val();
	        producto = $("#producto").val();
//	        subproducto = $("#subproducto").val();
	        nrocuenta = $("#nrocuenta").val();
//	        fechaDesde = $("#fechaDesde").val();
//	        fechaHasta = $("#fechaHasta").val();
//	        sucursal = $("#sucursal").val();
//	        estado = $("#estado").val();
//	        nroSolicitud = $("#nroSolicitud").val();
//	        evidencia = $("#evidencia").val();
//	        tipoDocumental = $("#tipoDocumental").val();
//	        subtipoDocumental = $("#subtipoDocumental").val();
//	              
	        $.ajax({
	            type: 'POST',
	            url: 'home/Grabar',
	//     dataType: 'json',
	//          data: {pestana: pestana, nombre: nombre, apellido: apellido, razonSocial: razonSocial, tipo: tipo, numero: numero, producto: producto, subproducto: subproducto, nrocuenta: nrocuenta, fechaDesde: fechaDesde, fechaHasta: fechaHasta, sucursal: sucursal, estado: estado, nroSolicitud: nroSolicitud, evidencia: evidencia, tipoDocumental: tipoDocumental, subtipoDocumental: subtipoDocumental},
	            data : {producto : producto, nrocuenta : nrocuenta},
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