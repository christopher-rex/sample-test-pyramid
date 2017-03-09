function route() {
	const defaultRoute = 'login'
	const currentRoute = location.hash.substring(1) || defaultRoute

	const templateNode = document.querySelector(`#${currentRoute}-template`)

	if (!templateNode) return

	document.querySelector('#output').innerHTML = templateNode.innerHTML
	init[currentRoute]()
	// TODO cleanup event listeners
}

const init = {
	login() {
		document.querySelector('#login-form').addEventListener('submit', event => {
			event.preventDefault()

			document.querySelectorAll('.validation-message').forEach(element =>
				element.innerText = '')

			const [email, password] =
				[].map.call(document.querySelectorAll('input'), node => node.value)

			fetch('http://localhost:4567/login', {
				method: 'post',
				body: JSON.stringify({email, password})
			}).then(response => {
				if (response.status === 200) notify('success', 'Logged in successfully.')
				else if (response.status === 401) notify('error', 'You are not authorized.')
				else notify('error', 'A surprising error occurred.')
			})
		})

		document.querySelectorAll('input').forEach(element => {
			element.addEventListener('change', event => {
				const isValid = event.target.checkValidity()
				if (isValid) {
					event.target.parentNode.querySelector('.validation-message').innerText = ''
				}
			})
		})

		document.addEventListener('invalid', event => {
			event.preventDefault()

			event.target.parentNode.querySelector('.validation-message').innerText =
				event.target.validationMessage
		}, true)
	}
}

function notify(severity, message) {
	const notice = document.querySelector('#notice')
	notice.classList.remove('success', 'error')
	notice.innerText = message
	notice.classList.add(severity)
	notice.classList.remove('hidden')
}

window.addEventListener('hashchange', route)
route()
