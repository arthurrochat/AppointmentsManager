/*const host = 'http://127.0.0.1:7200'

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
}*/

function Init(){
	window.location = "assets/containers/Login.html";
}

function AutoLogar() {
	var xmlhttp = new FactoryXMLHttpRequest();
	let login = sessionStorage.getItem('YourHealthLogin');
	let senha = sessionStorage.getItem('YourHealthSenha');
	
	let data = "login="+login+"&senha="+senha;
	xmlhttp.open('post', `${host}/testaLogin`, true);
	xmlhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	
	xmlhttp.onload = function () {
		if (xmlhttp.readyState == 4) {
			let jsonObj = JSON.parse(xmlhttp.responseText);
			
			if(jsonObj.status != 0)
				window.location = "Login.html";
			else
				document.getElementById("usuario").innerHTML = jsonObj.nome;
		}
	};
	
	xmlhttp.send(data);
}

function Sair(){
	sessionStorage.setItem("YourHealthLogin", "");
	sessionStorage.setItem("YourHealthSenha", "");
	window.location = "Login.html";
}