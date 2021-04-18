import axios from 'axios'

const URL = 'http://localhost:8080'

export const USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'

class AuthenticationService {

    executeJwtAuthenticationService(email, password) {
        console.log(email);
        return axios
        .post(`${URL}/authenticate`, {
            email,
            password
        }).catch(
            console.log("LOGIN FAILED")
        )
    }

    userRegistrationService(email, username, password) {
        return axios.post(`${URL}/userRegistration`, {
            email,
            username,
            password
        }).catch(
            console.log("SIGNUP FAILED")
        ) 
    }

    registerSuccessfulLoginForJwt(email, token) {
        sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, email)
        localStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, email)
        console.log("token: "+token)
        this.setupAxiosInterceptors(this.createJWTToken(token))
    }

    createJWTToken(token) {
        console.log('Bearer ' + token)
        return 'Bearer ' + token
    }

    logout() {
        sessionStorage.removeItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
        localStorage.removeItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
    }

    isUserLoggedIn() {
        let user = localStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
        if (user === null) return false
        return true
    }

    getLoggedInUserName() {
        let user = localStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
        if (user === null) return ''
        return user
    }

    setupAxiosInterceptors(token) {
        console.log(this.isUserLoggedIn())
        console.log("interceptors"+token)
        axios.interceptors.request.use(
            (config) => {
                console.log("config")
                if (this.isUserLoggedIn()) {
                    console.log("YES")
                    config.headers['Authorization'] = token
                    console.log(token)
                }
                return config
            }
        )
    }
}

export default new AuthenticationService()
