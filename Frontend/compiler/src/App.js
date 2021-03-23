import React from 'react'
import {BrowserRouter as Router, Route} from 'react-router-dom';
import Menus from './Components/Menus';
import { Container, Row, Col } from "reactstrap";
import Home from "./Components/Home"
import Header from './Components/Header';
import Ide from './Components/Ide'
import Compiler from './Components/Compiler'

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
            <Route path="/" component={Home} exact />
            <Route path="/ide" component={Ide} exact />
            <Route path="/compiler" component={Compiler} exact />
            </Col>
          </Row>
        </Container>
      </Router>
    </div>
  );
}

export default App;
