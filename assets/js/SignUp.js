
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

function signUp(this){
	axios.get
}