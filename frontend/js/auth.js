const auth = {
	login(user) {
		user && user.auth_token &&
			localStorage.setItem('auth_token', user.auth_token)
	},

	logout() {
		localStorage.removeItem('auth_token')
	},

	isLoggedIn() {
		return !!localStorage.getItem('auth_token')
	}
}
