import { useEffect } from 'react';
import { Jumbotron } from 'reactstrap';
import { Link } from 'react-router-dom'

const Home = () => {
    useEffect(() => {
        document.title = "Home"
    }, [])

    return(
        <Jumbotron className="text-center">
            <h1 className="display-4">Online Compiler</h1>
            <p>Online Compiler with Integrated Development Environment</p>
            <hr />
            <Link className="btn btn-primary mr-4" to="/login">Login Here</Link>
            <Link className="btn btn-primary ml-4" to="/signup">Register Here</Link>
        </Jumbotron>
    )

}

export default Home