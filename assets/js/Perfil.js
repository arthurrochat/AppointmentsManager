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

function AlterarSenha(){
	var xmlhttp = new FactoryXMLHttpRequest();
	let cpf = sessionStorage.getItem('YourHealthLogin');
	let senha = document.getElementById("senha").value;
	let novasenha = document.getElementById("novasenha").value
	
	let data = "cpf="+cpf+"&senha="+senha+"&novasenha="+novasenha;
	
	xmlhttp.open('post', `${host}/AlterarSenha`, true);
	xmlhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	
	xmlhttp.onload = function () {
		if (xmlhttp.readyState == 4) {
			let jsonObj = JSON.parse(xmlhttp.responseText);
			
			if(jsonObj.status == "0")
				document.getElementById("resposta").innerHTML = "Senha alterada com sucesso";
			else
				document.getElementById("resposta").innerHTML = "Senha invalida";
		}
	}
	
	xmlhttp.send(data);
}

function PerfilSignDown(){
	AutoLogar();
	
	var xmlhttp = new FactoryXMLHttpRequest();
	let cpf = sessionStorage.getItem('YourHealthLogin');
	
	let data = "cpf="+cpf;
	
	xmlhttp.open('post', `${host}/CarregaUsuario`, true);
	xmlhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	
	xmlhttp.onload = function () {
		document.getElementById("nome").innerHTML = xmlhttp.readyState;
		if (xmlhttp.readyState == 4) {
			let jsonObj = JSON.parse(xmlhttp.responseText);
			
			document.getElementById("nome").innerHTML = jsonObj.nome;
			document.getElementById("cpf").innerHTML = jsonObj.cpf;
			document.getElementById("dataNascimento").innerHTML = jsonObj.dataNascimento;
			document.getElementById("telefone").innerHTML = jsonObj.telefone;
			document.getElementById("email").innerHTML = jsonObj.email;
			if(jsonObj.sexo == 1)
				document.getElementById("sexo").innerHTML = "Masculino";
			else
				document.getElementById("sexo").innerHTML = "Feminino";
			
			if(jsonObj.tipo == 1){
				document.getElementById("tipo").innerHTML = "Paciente";
				document.getElementById("id01").innerHTML = "Plano:";
				document.getElementById("info01").innerHTML = jsonObj.plano;
				document.getElementById("id02").innerHTML = "Tipo do Plano:";
				document.getElementById("info02").innerHTML = jsonObj.tipoPlano;
				document.getElementById("id03").innerHTML = "Codigo do Plano:";
				document.getElementById("info03").innerHTML = jsonObj.codigoPlano;
				document.getElementById("id04").innerHTML = "Validade:";
				document.getElementById("info04").innerHTML = jsonObj.validade;
			}	
			else{
				document.getElementById("tipo").innerHTML = "Funcionário";
				document.getElementById("id01").innerHTML = "Profissao:";
				document.getElementById("info01").innerHTML = jsonObj.profissao;
				document.getElementById("id02").innerHTML = "Carteira de Trabalho:";
				document.getElementById("info02").innerHTML = jsonObj.carteiraTrabalho;
				document.getElementById("id03").innerHTML = "Salario:";
				document.getElementById("info03").innerHTML = jsonObj.salario;
				document.getElementById("id04").innerHTML = "Data de Admissao:";
				document.getElementById("info04").innerHTML = jsonObj.dataAdmissao;
			}
			
		}
	};
	
	xmlhttp.send(data);
}