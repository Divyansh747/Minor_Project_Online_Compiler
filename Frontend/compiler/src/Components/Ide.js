import  { useEffect } from 'react';
import { FormGroup, Input, Button, Jumbotron } from "reactstrap";
import '../App.css';

var downloadCodeFile = () => {
    const element = document.createElement("a");
    const filename = document.getElementById("filename")
    const language = document.getElementById("language")
    const file = new Blob([document.getElementById('inputCode').value],    
                {type: 'text/plain;charset=utf-8'});
    element.href = URL.createObjectURL(file);
    element.download = filename.value + "." + language.value;
    console.log(filename)
    document.body.appendChild(element);
    element.click();
  }

  var downloadInputFile = () => {
    const element = document.createElement("a");
    const filename = document.getElementById("filename")
    const file = new Blob([document.getElementById('inputInput').value],    
                {type: 'text/plain;charset=utf-8'});
    element.href = URL.createObjectURL(file);
    element.download = filename.value + "Input.txt";
    document.body.appendChild(element);
    element.click();
  }

const Ide = () => {
    useEffect(() => {
        document.title = "IDE"
    }, [])

    return(
        <div>
            <Jumbotron>
            <h1 className="text-center">Online IDE</h1>
            </Jumbotron>
            <FormGroup>
                <label>Select Programming Language</label>
                <br/>
                <Input type="select" id="language">
                    <option>c</option>
                    <option>cpp</option>
                    <option>java</option>
                    <option>py</option>
                </Input>
            </FormGroup>
           
            <FormGroup>
                <label>Enter FileName</label>
                <Input type="text" id="filename" />
            </FormGroup>
            
            <FormGroup>
                <label>Code</label>
                <Input type="textarea" 
                    style={{ 
                        height: 250,
                        backgroundColor: "#066f90",
                        color: "#FFFFFF"
                    }} 
                    id="inputCode" />
            </FormGroup>
            <Button onClick={downloadCodeFile} color="primary">Download</Button>
            <br />
            <br />
            
            <FormGroup>
                <label>Input</label>
                <Input type="textarea"  
                    style={{ 
                        height: 150,
                        backgroundColor: "#066f90",
                        color: "#FFFFFF"
                    }} 
                    id="inputInput" />
            </FormGroup>
            <Button onClick={downloadInputFile} color="primary">Download</Button>
            <br />
            <br />
            
        </div>
    )
}

export default Ide