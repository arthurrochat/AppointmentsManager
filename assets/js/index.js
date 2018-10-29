const host = 'http://127.0.0.1:7200'

function signin(){
	let type = document.getElementById('userType').value
	axios.get(host)
		.then(() => {
			console.log(leu)
		})
		.catch((err) => {

		})
}