const router = {
	init(routeHandlers, templates, outputElement) {
		let cleanup

		function route(event) {
			const defaultRoute = 'login'
			const currentRoute = location.hash.substring(1) || defaultRoute

			if (cleanup) cleanup()

			const templateHtml = templates[`${currentRoute}-template`]

			if (!templateHtml) return

			outputElement.innerHTML = templateHtml

			const currentRouteHandler = routeHandlers[currentRoute]
			cleanup = currentRouteHandler && currentRouteHandler.cleanup
			currentRouteHandler && currentRouteHandler.init()
		}

		window.addEventListener('hashchange', route)
		route()

		return route
	}
}
