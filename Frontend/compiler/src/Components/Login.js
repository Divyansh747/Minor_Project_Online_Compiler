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
            hasLoginFailed: false,
            showSuccessMessage: false
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

    loginClicked() {
         AuthenticationService
             .executeJwtAuthenticationService(this.state.email, this.state.password)
             .then((response) => {
                 AuthenticationService.registerSuccessfulLoginForJwt(this.state.email, response.data.token)
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
                    Email: <Input type="text" name="email" value={this.state.email} onChange={this.handleChange} />
                    <br />
                    Password: <Input type="password" name="password" value={this.state.password} onChange={this.handleChange} />
                    <br />
                    <Button className="btn btn-success" onClick={this.loginClicked}>Login</Button>
                </div>
                <br />
                <br />
            </div>
        )
    }
}

export default Login