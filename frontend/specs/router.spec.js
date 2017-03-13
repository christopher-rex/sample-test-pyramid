describe('Router', () => {
	const handlers = {
		login: {
			init: jasmine.createSpy('init'),
			cleanup: jasmine.createSpy('cleanup')
		}
	}

	let outputElement, templateElement

	beforeEach(() => {
		outputElement = document.createElement('div')
		outputElement.id = 'output'
		document.body.appendChild(outputElement)

		templateElement = document.createElement('div')
		templateElement.id = 'login-template'
		document.body.appendChild(templateElement)
	})

	afterEach(() => {
		document.body.removeChild(outputElement)
		document.body.removeChild(templateElement)
	})

	it('initialises the current route', () => {
		router.init(handlers)

		expect(handlers.login.init).toHaveBeenCalled()
	})

	it('cleans up the previous route', () => {
		const route = router.init(handlers)
		route()

		expect(handlers.login.cleanup).toHaveBeenCalled()
	})
})
