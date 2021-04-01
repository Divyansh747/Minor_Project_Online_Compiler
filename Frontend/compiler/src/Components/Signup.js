import React, { Component } from 'react'
import { Button, Input, Jumbotron} from 'reactstrap'
import AuthenticationService from '../Service/AuthenticationService';

class Signup extends Component {

    constructor(props) {
        super(props)

        this.state = {
            username: '',
            email: '',
            password: '',
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

    signupClicked() {
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
                <h1 className="text-center">Signup</h1>
                <hr className="my-2" />
                <p className="lead text-center">
                <br />
                Registration Page!
                </p>
            </Jumbotron>
            <div className="container">
                {this.state.hasLoginFailed && <div className="alert alert-danger">Email already registered!</div>}
                {this.state.showSuccessMessage && <div className="alert alert-success">Sucessfully Registered</div>}
                Username: <Input type="text" name="username" value={this.state.username} onChange={this.handleChange} />
                <br />
                Email: <Input type="text" name="email" value={this.state.email} onChange={this.handleChange} />
                <br />
                Password: <Input type="password" name="password" value={this.state.password} onChange={this.handleChange} />
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