describe('Router', () => {
	const handlers = {
		login: {
			init: jasmine.createSpy('init'),
			cleanup: jasmine.createSpy('cleanup')
		}
	}

	const outputElement = document.createElement('div')
	outputElement.id = 'output'

	const templates = {
		'login-template': 'some html'
	}

	it('initialises the current route', () => {
		router.init(handlers, templates, outputElement)

		expect(handlers.login.init).toHaveBeenCalled()
	})

	it('cleans up the previous route', () => {
		const route = router.init(handlers, templates, outputElement)
		route()

		expect(handlers.login.cleanup).toHaveBeenCalled()
	})
})
