import { ListGroup } from 'reactstrap';
import { Link } from 'react-router-dom';
import AuthenticationService from '../Service/AuthenticationService';

const Menus = () => {
    const isUserLoggedIn = AuthenticationService.isUserLoggedIn();
    return (
        <ListGroup>
            <Link className="list-group-item list-group-item-action" to="/home">Home</Link> 
            <Link className="list-group-item list-group-item-action" to="/Ide">IDE</Link> 
            <Link className="list-group-item list-group-item-action" to="/compiler">Compiler</Link> 
            <Link className="list-group-item list-group-item-action" to="#">Contact</Link>
            <Link className="list-group-item list-group-item-action" to="#">About US</Link>
            {!isUserLoggedIn && <Link className="list-group-item list-group-item-action" to="/login">Login</Link> }
            {isUserLoggedIn && <Link className="list-group-item list-group-item-action" to="/logout" onClick={AuthenticationService.logout}>Logout</Link> }
            <Link className="list-group-item list-group-item-action" to="#">Singup</Link>
        </ListGroup>
    )
}

export default Menus