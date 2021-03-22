import { useEffect } from 'react';
import { Jumbotron } from 'reactstrap';

const Home = () => {
    useEffect(() => {
        document.title = "Home"
    }, [])

    return(
        <Jumbotron className="text-center">
            <h1 className="display-4">Online Compiler</h1>
            <p>Online Compiler with Integrated Development Environment</p>
            
        </Jumbotron>
    )

}

export default Home