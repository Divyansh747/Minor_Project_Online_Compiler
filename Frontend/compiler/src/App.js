import React from 'react'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import Menus from './Components/Menus';
import { Container, Row, Col } from "reactstrap";
import Home from "./Components/Home"
import Header from './Components/Header';
import Ide from './Components/Ide'
import Compiler from './Components/Compiler'
import Login from './Components/Login'
import Logout from './Components/Logout'
import AuthenticatedRoute from './AuthenticatedRoute'
import Signup from './Components/Signup'

function App() {
  return (
    <div>
      <Router>
        <Container>
          <Header />
          <Row>
            <Col md={3}>
              <Menus />
            </Col>
            <Col md={9}>
            <Switch>
            <Route path="/" component={Home} exact />
            <Route path="/login" component={Login} exact />
            <Route path="/signup" component={Signup} exact />
            <Route path="/home" component={Home} exact />
            <AuthenticatedRoute path="/logout" component={Logout} exact />
            <AuthenticatedRoute path="/ide" component={Ide} exact />
            <AuthenticatedRoute path="/compiler" component={Compiler} exact />
            </Switch>
            </Col>
          </Row>
        </Container>
      </Router>
    </div>
  );
}

export default App;
