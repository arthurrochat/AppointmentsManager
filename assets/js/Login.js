const host = 'http://127.0.0.1:7200'

function login(){
	let data = `cpf=${document.getElementById("CPF").value}&password=${document.getElementById("password").value}`
	axios.post(`${host}/login`, data).then((response)=> {
		console.log('batatata')
	})
}