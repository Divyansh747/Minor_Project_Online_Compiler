import React from 'react'
import {Form, FormGroup, Input, Container, Button} from 'reactstrap'
import axios from 'axios'
import api from '../api/api.js'
import { Jumbotron } from 'reactstrap';

class Compiler extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            codeFile: null,
            inputFile: null,
            output: [],
            requiredOutput: [],
            statusCode: []
        }
        this.handleForm = this.handleForm.bind(this)
        this.onChangeFirst = this.onChangeFirst.bind(this)
        this.onChangeSecond = this.onChangeSecond.bind(this)
        
        this.postDataToServer = this.postDataToServer.bind(this)
    }

    componentDidMount() {
        document.title = "Compiler"
        console.log("title set")
    }

    onChangeFirst(e) {
        this.setState({
            codeFile: e.target.files[0], 
        })
    }

    onChangeSecond(e) {
        this.setState({
            inputFile: e.target.files[0],
        })
    }


    handleForm(e) {
        e.preventDefault()
        this.postDataToServer(
            this.state.codeFile, 
            this.state.inputFile
            ).then((response) => {
                console.log(response)
            } )
        
    }

    postDataToServer(codeFile, inputFile, timeLimit, storageLimit) {
        const language = document.getElementById("language").value
        const formData = new FormData()
        formData.append('codeFile',codeFile)
        formData.append('inputFile', inputFile)
        const config = { headers: { 'Content-Type': 'multipart/form-data' } };

        return axios.post(`${api}/${language}`, formData, config).then(
            (response) =>  {
                console.log(response)
                console.log("compiler api called")
                this.setState({
                    output: response.data.output,
                    statusCode: response.data.statuscode
                })
            },(error) => {
                console.log(error)
                console.log("error")
            }
        )
    }

render() {
    return(
        <div>
            <Jumbotron>
            <h1 className="text-center">Compiler</h1>
            </Jumbotron>
            <Form onSubmit={this.handleForm}>
            <FormGroup>
                <label>Select Programming Language</label>
                <br/>
                <Input type="select" id="language" onChange={this.onChange}>
                    <option>c</option>
                    <option>cpp</option>
                    <option>java</option>
                    <option>python</option>
                </Input>
            </FormGroup>

            <FormGroup>
                <label>Upload Code File</label>
                <Input type="file" onChange={this.onChangeFirst}></Input>
            </FormGroup>

       
            <FormGroup>
                <label>Upload Input File</label>
                <Input type="file" onChange={this.onChangeSecond}></Input>
            </FormGroup>

            <Container className="text-center">
                    <Button type="submit" color="success">Compile</Button>
            </Container>
            
            </Form>
            <br/>
            <Jumbotron>
                <h3>Output:</h3>
                { 
                    <p>{this.state.output}</p> 
                
                }
                <br/>
                <h3>Status Code:</h3>
                { 
                    <p>{this.state.statusCode}</p>    
                }
            </Jumbotron>

        </div>
    )
}
}

export default Compiler