import  { useEffect } from 'react';
import { Jumbotron } from "reactstrap";
import { Card, Badge, CardHeader, CardFooter, CardBody, CardTitle, CardText } from 'reactstrap';

const Aboutus = () => {
    useEffect(() => {
        document.title = "About Us"
    }, [])

    return(
        <div>
            <Jumbotron>
            <h1 className="text-center">About Us</h1>
            <hr className="my-2" />
            <p className="lead text-center">
            <p>This project is developed under Minor Project category for University B.Tech Computer Science 6th Semester curriculum.</p>
            </p>
            </Jumbotron>
        <CardBody>
      <Card>
        <CardHeader tag="h3">Team Member</CardHeader>
        <CardBody>
          <CardTitle tag="h5">Divyansh Rahangdale</CardTitle>
          <CardText>B.Tech Computer Science Student.</CardText>
          <a class="btn btn-danger btn-sm mr-2" href="mailto:divyansh.1999.dr@gmail.com" target="_newtab">GMail</a>
          <a class="btn btn-primary btn-sm mr-2" href="https://www.linkedin.com/in/divyansh-rahangdale-27600311b/" target="_newtab">Linkedin</a>
          <a class="btn btn-dark btn-sm" href="https://github.com/Divyansh747" target="_newtab">Github</a>
          </CardBody>
        <CardFooter>
        <Badge color="danger" className="mr-2" pill>Backend</Badge>
        <Badge color="warning" className="mr-2" pill>Frontend</Badge>
        <Badge color="success" className="mr-2" pill>Database</Badge>
        <Badge color="info" className="mr-2" pill>Deployment & Infra</Badge>
        <Badge color="dark" className="mr-2" pill>Docker</Badge>
        </CardFooter>
      </Card>
      <br/>
      
      <Card>
        <CardHeader tag="h3">Team Member</CardHeader>
        <CardBody>
          <CardTitle tag="h5">Harshal Choudhary</CardTitle>
          <CardText>B.Tech Computer Science Student.</CardText>
          <a class="btn btn-danger btn-sm mr-2" href="mailto:6261harshal@gmail.com" target="_newtab">GMail</a>
          <a class="btn btn-primary btn-sm mr-2" href="https://www.linkedin.com/in/harshal-choudhary-615968170" target="_newtab">Linkedin</a>
          <a class="btn btn-dark btn-sm" href="#" target="_newtab">Github</a>
          </CardBody>
        <CardFooter>
        <Badge color="warning" className="mr-2" pill>Frontend</Badge>
        <Badge color="success" className="mr-2" pill>Documentation & Presentation</Badge>
        
        </CardFooter>
      </Card>
      </CardBody>
    </div>
    )
}

export default Aboutus