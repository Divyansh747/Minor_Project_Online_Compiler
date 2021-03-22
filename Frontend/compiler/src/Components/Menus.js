import { ListGroup } from 'reactstrap';
import { Link } from 'react-router-dom';

const Menus = () => {
    return (
        <ListGroup>
            <Link className="list-group-item list-group-item-action" to="/">Home</Link> 
            <Link className="list-group-item list-group-item-action" to="/Ide">IDE</Link> 
            <Link className="list-group-item list-group-item-action" to="#">Compiler</Link> 
            <Link className="list-group-item list-group-item-action" to="#">Contact</Link>
            <Link className="list-group-item list-group-item-action" to="#">About US</Link>
        </ListGroup>
    )
}

export default Menus