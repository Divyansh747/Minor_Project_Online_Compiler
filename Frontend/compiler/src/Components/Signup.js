import React, { Component } from 'react'
import { Button, Input, Jumbotron} from 'reactstrap'
import AuthenticationService from '../Service/AuthenticationService';
import { Link } from 'react-router-dom';

class Signup extends Component {

    constructor(props) {
        super(props)

        this.state = {
            username: '',
            email: '',
            password: '',
            usernameError: '',
            emailError: '',
            passwordError: '',
            hasLoginFailed: false,
            showSuccessMessage: false
        }

        this.handleChange = this.handleChange.bind(this)
        this.signupClicked = this.signupClicked.bind(this)
    }

    handleChange(event) {
        this.setState(
            {
                [event.target.name]: event.target.value
            }
        )
    }

    validate = () => {
        let usernameError= "";
        let emailError = "";
        let passwordError = "";
         
        if (!this.state.username) {
            usernameError= 'Username cannot be empty'
        }
        if (!this.state.email.includes('@')) {
            emailError = 'invalid email';
        }

        if (!this.state.password) {
            passwordError = 'Password cannot be empty';
        }
        if(emailError) {
            this.setState({emailError})
            return false;
        }

        if (emailError || usernameError || passwordError) {
            this.setState({ emailError, usernameError, passwordError });
            return false;
        }

        return true;
    }

    signupClicked() {
        const isValid = this.validate();
        if (!isValid) {
            return false;    
        }
        AuthenticationService
        .userRegistrationService(this.state.email, this.state.username, this.state.password)
        .then(() => {
            this.setState({ showSuccessMessage: true })
            this.setState({ hasLoginFailed: false })
        })
        .catch(() => {
            this.setState({ showSuccessMessage: false })
            this.setState({ hasLoginFailed: true })
        })
    }

    render() {
        return(
            <div>            
            <Jumbotron>
                <h1 className="text-center">Sign Up</h1>
                <hr className="my-2" />
                <p className="lead text-center">
                <br />
                <Link className="btn btn-primary" to="/login">Login Here</Link>
                </p>
            </Jumbotron>
            <div className="container">
                {this.state.hasLoginFailed && <div className="alert alert-danger">Email already registered!</div>}
                {this.state.showSuccessMessage && <div className="alert alert-success">Sucessfully Registered</div>}
                Username: <Input 
                    type="text" 
                    name="username" 
                    value={this.state.username} 
                    onChange={this.handleChange} 
                />
                <div style={{ fontSize: 12, color: "red" }}> {this.state.usernameError} </div>
                <br />
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
                <Button className="btn btn-success" onClick={this.signupClicked}>Signup</Button>
            </div>
            <br />
            <br />
        </div>
        )
    }
}

export default Signup