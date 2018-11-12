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

function login(){
	var xmlhttp = new FactoryXMLHttpRequest();
	let data = `login=${document.getElementById("cpf").value}&senha=${document.getElementById("password").value}`;
	xmlhttp.open('post', `${host}/testaLogin`, true);
	xmlhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');				
	
	xmlhttp.onload = function () {
		if (xmlhttp.readyState == 4) {
			let jsonObj = JSON.parse(xmlhttp.responseText);
			
			if(jsonObj.status == 0){
				document.getElementById("teste").innerHTML = jsonObj.nome;
				sessionStorage.setItem("YourHealthLogin", document.getElementById("cpf").value);
				sessionStorage.setItem("YourHealthSenha", document.getElementById("password").value);
				window.location = "Perfil.html";
			}
			else if(jsonObj.status == 1)
				document.getElementById("teste").innerHTML = "Senha invalida";
			else
				document.getElementById("teste").innerHTML = "Login não cadastrado";
		}
	};
	
	xmlhttp.send(data);
}