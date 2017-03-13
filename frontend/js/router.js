const router = {
	init(routeHandlers) {
		let cleanup

		function route(event) {
			const defaultRoute = 'login'
			const currentRoute = location.hash.substring(1) || defaultRoute

			if (cleanup) cleanup()

			const templateNode = document.querySelector(`#${currentRoute}-template`)

			if (!templateNode) return

			document.querySelector('#output').innerHTML = templateNode.innerHTML

			const currentRouteHandler = routeHandlers[currentRoute]
			cleanup = currentRouteHandler && currentRouteHandler.cleanup
			currentRouteHandler && currentRouteHandler.init()
		}

		window.addEventListener('hashchange', route)
		route()

		return route
	}
}
