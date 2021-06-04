import React, { Component } from 'react'
import AuthenticationService from '../Service/AuthenticationService';
import { Button, Input, Jumbotron} from 'reactstrap'
import { Link } from 'react-router-dom';

class Login extends Component {

    constructor(props) {
        super(props)

        this.state = {
            email: '',
            password: '',
            emailError: '',
            passwordError: '',
            hasLoginFailed: false,
            showSuccessMessage: false,
            }

        this.handleChange = this.handleChange.bind(this)
        this.loginClicked = this.loginClicked.bind(this)
    }

    handleChange(event) {
        this.setState(
            {
                [event.target.name]: event.target.value
            }
        )
    }

    validate = () => {
        let emailError = "";
        let passwordError = "";
         
        if (!this.state.email.includes('@')) {
            emailError = 'invalid email';
        }

        if (!this.state.password) {
            passwordError = 'Password cannot be empty, or Please recheck you password';
        }
        if(emailError) {
            this.setState({emailError})
            return false;
        }

        if (emailError || passwordError) {
            this.setState({ emailError, passwordError });
            return false;
        }

        return true;
    }

    loginClicked() {
        const isValid = this.validate();
        if (!isValid) {
            return false;    
        }
        
         AuthenticationService
             .executeJwtAuthenticationService(this.state.email, this.state.password)
             .then((response) => {
                 console.log("response: "+response.data)
                 AuthenticationService.registerSuccessfulLoginForJwt(this.state.email, response.data)
                 this.props.history.push(`/home`)
             }).catch(() => {
                 this.setState({ showSuccessMessage: false })
                 this.setState({ hasLoginFailed: true })
             })

    }

    render() {
        return (
            <div>            
                <Jumbotron>
                    <h1 className="text-center">Login</h1>
                    <hr className="my-2" />
                    <p className="lead text-center">
                    <br />
                    <Link className="btn btn-primary" to="/signup">Register Here</Link>
                    </p>
                </Jumbotron>
                <div className="container">
                    {this.state.hasLoginFailed && <div className="alert alert-danger">Invalid Credentials. Please enter valid E-mail/Password</div>}
                    {this.state.showSuccessMessage && <div>Login Sucessful</div>}
                    Email: <Input 
                        type="text" 
                        name="email" 
                        value={this.state.email} 
                        onChange={this.handleChange} 
                    />
                    <div style={{ fontSize: 12, color: "red" }}> {this.state.emailError} </div>
                    <br />
                    Password: <Input 
                        type="password" 
                        name="password" 
                        value={this.state.password} 
                        onChange={this.handleChange} 
                    />
                    <div style={{ fontSize: 12, color: "red" }}> {this.state.passwordError} </div>
                    <br />
                    <Button 
                        className="btn btn-success" onClick={this.loginClicked}>Login</Button>
                </div>
                <br />
                <br />
            </div>
        )
    }
}

export default Login