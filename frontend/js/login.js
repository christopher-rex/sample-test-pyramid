const login = (_ => {
	const listeners = {
		handleSubmit(event) {
			event.preventDefault()

			document.querySelectorAll('.validation-message').forEach(element =>
				element.innerText = '')

			const [email, password] =
				[].map.call(document.querySelectorAll('input'), node => node.value)

			fetch('http://localhost:4567/login', {
				method: 'post',
				body: JSON.stringify({email, password})
			}).then(response => {
				if (response.status === 200) {
					notify('success', 'Logged in successfully.')
					return response.json()
				}

				else if (response.status === 401) {
					notify('error', 'You are not authorized.')
					return new Promise(resolve => resolve())
				}

				else notify('error', 'A surprising error occurred.')
				return new Promise(resolve => resolve())
			}, _ => {
				notify('error', 'Unable to connect to the server.')
			}).then(user => {
				if (user) {
					auth.login(user)

					if (user.email_verified !== 'true') {
						notify('warning', 'Please confirm your email')
					}

					location.hash = 'coupon'
				}
			})
		},

		handleInputChange(event) {
			const isValid = event.target.checkValidity()
			if (isValid) {
				event.target.parentNode.querySelector('.validation-message').innerText = ''
			}
		},

		handleInvalid(event) {
			event.preventDefault()

			event.target.parentNode.querySelector('.validation-message').innerText =
				event.target.validationMessage
		}
	}

	return {
		init() {
			document.querySelector('#login-form').addEventListener('submit',
				listeners.handleSubmit)

			document.querySelectorAll('input').forEach(element => {
				element.addEventListener('change', listeners.handleInputChange)
			})

			document.addEventListener('invalid', listeners.handleInvalid, true)
		},

		cleanup() {
			document.querySelector('#login-form').removeEventListener('submit',
				listeners.handleSubmit)

			document.querySelectorAll('input').forEach(element => {
				element.removeEventListener('change', listeners.handleInputChange)
			})

			document.removeEventListener('invalid', listeners.handleInvalid, true)
		}
	}
})()
