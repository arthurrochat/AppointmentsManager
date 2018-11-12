const host = 'http://127.0.0.1:7200'

function FactoryXMLHttpRequest() {

	if(window.XMLHttpRequest) {
		return new XMLHttpRequest();// Opera 8.0+, Firefox, Chrome, Safari
	}
	else if (window.XDomainRequest) {
		return new XDomainRequest(); // Antigo Safari
	}
	else if(window.ActiveXObject) {
		var msxmls = new Array(// Internet Explorer
		'Msxml2.XMLHTTP',
		'Microsoft.XMLHTTP',
		'Msxml3.XMLHTTP',
		'Msxml2.XMLHTTP.7.0',
		'Msxml2.XMLHTTP.6.0',
		'Msxml2.XMLHTTP.5.0',
		'Msxml2.XMLHTTP.4.0',
		'Msxml2.XMLHTTP.3.0');
		for (var i = 0; i < msxmls.length; i++) {
			try {
				return new ActiveXObject(msxmls[i]);
			} catch (e) {
			}
		}
	} else throw new Error("Could not instantiate XMLHttpRequest");
}

function schedule(){
	var xmlhttp = new FactoryXMLHttpRequest();
	let data = `cpf=${document.getElementById("cpf").value}&date=${document.getElementById("dataConsulta").value}`;
	xmlhttp.open('post', `${host}/Agendar`, true);
	xmlhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');				
	
	xmlhttp.onload = function () {
		if (xmlhttp.readyState == 4) {
			let jsonObj = JSON.parse(xmlhttp.responseText);
			
			if(jsonObj.status == 0){
				document.getElementById("teste").innerHTML = "Sucesso ao marcar consulta";
			}
			else{
				document.getElementById("teste").innerHTML = "Falha ao marcar consulta";
			}
		}
	};
	
	xmlhttp.send(data);
}