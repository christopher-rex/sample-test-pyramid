describe('Auth', () => {
	const user = {
		name: 'Sam',
		auth_token: '34579567'
	}

	describe('login', () => {
		it('stores user data in local storage', () => {
			auth.login(user)

			expect(localStorage.getItem('auth_token')).toBe(user.auth_token)
		})
	})

	describe('logout', () => {
		it('removes user data from local storage', () => {
			localStorage.setItem('auth_token', 'something')

			auth.logout()

			expect(localStorage.getItem('auth_token')).toBeFalsy()
		})
	})

	describe('isLoggedIn', () => {
		it('returns true if a user is logged in', () => {
			auth.login(user)

			expect(auth.isLoggedIn()).toBe(true)
		})

		it('returns false if a user is not logged in', () => {
			auth.logout()

			expect(auth.isLoggedIn()).toBe(false)
		})
	})
})
