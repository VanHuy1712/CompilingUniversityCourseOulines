import { useEffect, useState } from "react";
import APIs, { endpoints } from "../../configs/APIs";
import { Container, Nav, NavDropdown, Navbar, Spinner } from "react-bootstrap";

const Header = () => {
    const [courses, setCourses] = useState(null);

    const loadCourses = async () => {
        try {
            const res = await APIs.get(endpoints['courses']);
            setCourses(res.data);
        } catch (ex) {
            console.error(ex);
        }
    }

    useEffect(() => {
        loadCourses();
    }, []);

    return (
        <Navbar expand="lg" className="bg-body-tertiary">
            <Container>
                <Navbar.Brand href="#home">React-Bootstrap</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Nav.Link href="#home">Home</Nav.Link>
                        <Nav.Link href="#link">Link</Nav.Link>
                        <NavDropdown title="Dropdown" id="basic-nav-dropdown">

                            {courses==null?<Spinner animation="grow" variant="secondary" /> :<>
                                {courses.map(c => <NavDropdown.Item key={c.id} href="#action/3.1">{c.name}</NavDropdown.Item>)}
                            </>}
                            
                            

                        </NavDropdown>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}

export default Header;