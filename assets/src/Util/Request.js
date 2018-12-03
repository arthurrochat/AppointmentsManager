const baseUrl = 'http://127.0.0.1:8000/'

const Request = (url, data, onSuccess, onFail) => {
	let xmlHttp = new XMLHttpRequest()

	xmlHttp.open('post', `${baseUrl}${url}`, true)
	xmlHttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded')
	xmlHttp.onload = () => {
		if(xmlHttp.readyState === 4) {
			let response = JSON.parse(xmlHttp.responseText)

			if(response.status == "0") {
				onSuccess(response)
			} else {
				onFail(response)
			}
		}
	}

	xmlHttp.send(data)
}
export default Request