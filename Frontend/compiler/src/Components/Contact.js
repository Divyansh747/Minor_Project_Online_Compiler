import { Link } from 'react-router-dom';
import  { useEffect } from 'react';
import { Jumbotron } from "reactstrap";
import { Toast, ToastBody, ToastHeader, Badge } from 'reactstrap';

const Contacts = () => {
    useEffect(() => {
        document.title = "Contact Us"
    }, [])

    return(
        <div>
            <Jumbotron>
            <h1 className="text-center">Contact Us</h1>
            <hr className="my-2" />
            <p className="lead text-center">
            <p>Feel free to get in touch with us.</p>
            </p>
            <div className="p-3 my-2 rounded">
            <center>
            
        <Toast>
          <ToastHeader>
          <Badge color="primary" pill>Email ID</Badge>
          </ToastHeader>
          <ToastBody>
            <b>Email us on below ID</b>
            <br/><hr/>
            <Link>divyansh.1999.dr@gmail.com</Link>
            <br/><br/>
            <Link>6261harshal@gmail.com</Link>
          </ToastBody>
        </Toast>
        </center>
      </div>
            </Jumbotron>
            
        </div>
    )
}

export default Contacts