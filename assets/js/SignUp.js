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

function changeContainer(elm) {
	const container = document.getElementById('container')
	container.innerHTML = ''
	if(elm.value){
		elm.value === '2' ? appendEmployeeForm(container) : appendPacientForm(container)
	}
}

function appendEmployeeForm(container){
	let profissao = elementBuilder('input', {
		class: 'form-control',
		type: 'text',
		placeholder: 'Profissão',
		id: 'profissao'
	})
	let carteiraTrabalho = elementBuilder('input', {
		class: 'form-control',
		type: 'text',
		placeholder: 'Carteira de Trabalho',
		id: 'carteiraTrabalho'
	})
	let salario = elementBuilder('input', {
		class: 'form-control',
		type: 'text',
		placeholder: 'Salário',
		id: 'salario'
	})
	let dataAdmissao = elementBuilder('input', {
		class: 'form-control',
		type: 'date',
		id: 'dataAdmissao'
	})
	container.append(profissao)
	container.append(carteiraTrabalho)
	container.append(salario)
	container.append("Data de Admissão:")
	container.append(dataAdmissao)	
}

function appendPacientForm(container){
	let plano = elementBuilder('input', {
		class: 'form-control',
		type: 'text',
		placeholder: 'Plano',
		id: 'plano'
	})
	let tipoPlano = elementBuilder('input', {
		class: 'form-control',
		type: 'text',
		placeholder: 'Tipo de Plano',
		id: 'tipoPlano'
	})
	let codigoPlano = elementBuilder('input', {
		class: 'form-control',
		type: 'text',
		placeholder: 'Código de Plano',
		id: 'codigoPlano'
	})
	let validade = elementBuilder('input', {
		class: 'form-control',
		type: 'date',
		id: 'validade'
	})
	container.append(plano)
	container.append(tipoPlano)
	container.append(codigoPlano)
	container.append("Data de Validade:")
	container.append(validade)

}

function elementBuilder(elm, attrs){
	let newElm = document.createElement(elm)
	Object.keys(attrs).forEach((key) => {
		newElm.setAttribute(key, attrs[key])
	})
	return newElm
}

function signUp(){
	/*if (!$('#form-cadastro')[0].checkValidity()){
		document.getElementById("mensagem").innerHTML = "Preencha o formulário corretamente.";
		return;
	}*/
	var xmlhttp = new FactoryXMLHttpRequest();
	let data = buildRow();
	
	xmlhttp.open('post', `${host}/adicionarUsuario`, true);
	xmlhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	
	xmlhttp.onload = function () {
		if (xmlhttp.readyState == 4) {
			let jsonObj = JSON.parse(xmlhttp.responseText);
			//document.getElementById("mensagem").innerHTML = "Cadastro realisado com sucesso";
			window.location = "SignUp.html";
		}
	};
	
	xmlhttp.send(data);
}

function buildRow(){
	let tipo = document.getElementById("tipo").value
	if(tipo){
		let row = {
			cpf: document.getElementById("cpf").value,
			nome: document.getElementById("nome").value,
			sexo: document.getElementById("sexo").value,
			dataNascimento: document.getElementById("dataNascimento").value,
			telefone: document.getElementById("telefone").value,
			email: document.getElementById("email").value,
			tipo: tipo
		}
		
		if(tipo === '1'){
			row['plano'] = document.getElementById('plano').value
			row['tipoPlano'] = document.getElementById('tipoPlano').value
			row['codigoPlano'] = document.getElementById('codigoPlano').value
			row['validade'] = document.getElementById('validade').value
		} else if(tipo === '2'){
			row['profissao'] = document.getElementById('profissao').value
			row['carteiraTrabalho'] = document.getElementById('carteiraTrabalho').value
			row['salario'] = document.getElementById('salario').value
			row['dataAdmissao'] = document.getElementById('dataAdmissao').value
		}
		
		return format(row)		
	}
	document.alert("Escolha um tipo");
}

function format(row){
	let data = ''
	Object.keys(row).forEach(key => data = `${data}${key}=${row[key]}&`)
	return data.slice(0, -1)
}