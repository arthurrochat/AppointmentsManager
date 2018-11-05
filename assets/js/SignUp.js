const host = 'http://127.0.0.1:7200'

function changeContainer(elm) {
	const container = document.getElementById('container')
	container.innerHTML = ''
	if(elm.value){
		elm.value === 'employee' ? appendEmployeeForm(container) : appendPacientForm(container)

		let expirationDate = elementBuilder('input', {
			class: 'form-control',
			placeholder: 'Data de Validade',
			id: 'expirationDate'
		})		
		container.append(expirationDate)
	}
		
}

function appendEmployeeForm(container){
	let career = elementBuilder('input', {
		class: 'form-control',
		placeholder: 'Profissão',
		id: 'career'
	})
	let workPermit = elementBuilder('input', {
		class: 'form-control',
		placeholder: 'Carteira de Trabalho',
		id: 'workPermit'
	})
	let salary = elementBuilder('input', {
		class: 'form-control',
		placeholder: 'Salário',
		id: 'salary'
	})
	container.append(career)
	container.append(workPermit)
	container.append(salary)	
}

function appendPacientForm(container){
	let plan = elementBuilder('input', {
		class: 'form-control',
		placeholder: 'Plano',
		id: 'plan'
	})
	let planType = elementBuilder('input', {
		class: 'form-control',
		placeholder: 'Tipo de Plano',
		id: 'planType'
	})
	let planCode = elementBuilder('input', {
		class: 'form-control',
		placeholder: 'Código de Plano',
		id: 'planCode'
	})
	container.append(plan)
	container.append(planType)
	container.append(planCode)

}

function elementBuilder(elm, attrs){
	let newElm = document.createElement(elm)
	Object.keys(attrs).forEach((key) => {
		newElm.setAttribute(key, attrs[key])
	})
	return newElm
}

function signUp(){
	let data = buildRow()
	data && axios.post(`${host}/signUp`, data)
}

function buildRow(){
	let type = document.getElementById("userType").value
	if(type){
		let row = {
			cpf: document.getElementById("CPF").value,
			name: document.getElementById("name").value,
			password: document.getElementById("password").value,
			birth: document.getElementById("dateOfBirth").value,
			expirationDate: document.getElementById("expirationDate").value,
			type: type
		}
		if(type === 'employee'){
			row['career'] = document.getElementById('career').value
			row['workPermit'] = document.getElementById('workPermit').value
			row['salary'] = document.getElementById('salary').value
		} else if(type === 'pacient'){
			row['plan'] = document.getElementById('plan').value
			row['planType'] = document.getElementById('planType').value
			row['planCode'] = document.getElementById('planCode').value
		}

		return format(row)		
	}
	document.alert("Escolha um tipo")
}

function format(row){
	let data = ''
	Object.keys(row).forEach(key => data = `${data}${key}=${row[key]}&`)
	return data.slice(0, -1)
}