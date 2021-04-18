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
            <Link className="list-group-item list-group-item-action" to="/contact">Contact</Link>
            <Link className="list-group-item list-group-item-action" to="/aboutus">About US</Link>
            {!isUserLoggedIn && <Link className="list-group-item list-group-item-action" to="/login">Login / Logout</Link> }
            {isUserLoggedIn && <Link className="list-group-item list-group-item-action" to="/logout" onClick={AuthenticationService.logout}>Login / Logout</Link> }
            <Link className="list-group-item list-group-item-action" to="/signup">Sign Up</Link>
        </ListGroup>
    )
}

export default Menus