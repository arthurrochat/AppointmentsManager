

function signUp(){
	let user = document.getElementById('CPF').value
	let pswrd = document.getElementById('password').value
	let route = `${host}/signUp`
	axios.post(route, {
		user: user,
		password: pswrd 
	})
	.then(() => {
		console.log(leu)
	})
	.catch((err) => {
		console.log('batata')
	})
}

exports.module = signUp