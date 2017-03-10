const notifier = {
	init(notifierElement) {
		return (severity, message) => {
			notifierElement.classList.remove('success', 'error')
			notifierElement.innerText = message
			notifierElement.classList.add(severity)
			notifierElement.classList.remove('hidden')
		}
	}
}
