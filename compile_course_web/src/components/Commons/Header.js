import { useContext, useEffect, useState } from "react";
import APIs, { endpoints } from "../../configs/APIs";
import { Badge, Button, Col, Container, Form, Image, Nav, NavDropdown, Navbar, Row, Spinner } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import MySpinner from "./MySpinner";
import { MyDispatchContext, MyUserContext } from "../../configs/Contexts";

const Header = () => {
    const [courses, setCourses] = useState(null);
    const [q, setQ] = useState("");
    const [q1, setQ1] = useState("");
    const [q2, setQ2] = useState("");
    const [q3, setQ3] = useState("");
    const nav = useNavigate();
    const user = useContext(MyUserContext);
    const dispatch = useContext(MyDispatchContext);

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

    const search = (e, courseId) => {
        e.preventDefault();
               
        // setCateId(courseId);
        nav(`/?courseId=${courseId}`)
    }
        
    // const submit = (e) => {
    //     e.preventDefault();
    //     nav(`/?kw=${q}`);
    //     nav(`/?courseCredit=${q1}`);
    //     nav(`/?teacherName=${q2}`);
    //     nav(`/?term=${q3}`);
    //     // ?kw=&courseCredit=&teacherName=&term=
        
    // }

    const submit = (e) => {
        e.preventDefault();
        const query = `/?kw=${q}&courseCredit=${q1}&teacherName=${q2}&term=${q3}`;
        nav(query);
    }
             

        return (
        <>
            {courses===null?<MySpinner />:<>
            <Navbar expand="lg" className="bg-body-tertiary">
                <Container>
                    <Navbar.Brand href="#home">Outline Website</Navbar.Brand>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        {/* <Nav.Link href="#home">Trang chủ</Nav.Link> */}
                        <Link className="nav-link" to="/" >Trang chủ</Link>
                        {/* <Nav.Link href="#link">Link</Nav.Link> */}
                        <NavDropdown title="Danh muc" id="basic-nav-dropdown">
                            {courses.map(c => <NavDropdown.Item key={c.id} href="#" onClick={e => search(e, c.id)}>{c.name}</NavDropdown.Item>)}
                        </NavDropdown>

                        {/* {user===null?<>
                            <Link to="/register" className="nav-link text-success">Đăng ký</Link>
                            <Link to="/login" className="nav-link text-info">Đăng nhập</Link>
                        </>:<>
                            <Link to="/" className="nav-link text-success">
                                <Image src={user.avatar} width="40" roundedCircle /> {user.username}
                            </Link>
                            <Link onClick={() => dispatch({"type": "logout"})} className="nav-link">Đăng xuất</Link>
                        </>} */}
                       

                        {/* <Link to="/cart" className="nav-link">&#128722; <Badge bg="danger">0</Badge></Link> */}
                    </Nav>
                    </Navbar.Collapse>
                    {/* <Form inline onSubmit={submit}>
                        <Row>
                            <Col xs="auto">
                                <Form.Control type="text" value={q} onChange={e => setQ(e.target.value)}  placeholder="Tìm tên môn học..." className="mr-sm-2" />
                                <Form.Control type="text" value={q1} onChange={e => setQ1(e.target.value)}  placeholder="Tìm tín chỉ..." className="mr-sm-2" />
                                <Form.Control type="text" value={q2} onChange={e => setQ2(e.target.value)}  placeholder="Tìm giảng viên..." className="mr-sm-2" />
                                <Form.Control type="text" value={q3} onChange={e => setQ3(e.target.value)}  placeholder="Tìm khóa học..." className="mr-sm-2" />
                            </Col>
                            <Col xs="auto">
                                <Button type="submit">Tìm kiếm</Button>
                            </Col>
                        </Row>
                    </Form> */}
                    <Form className="d-flex" onSubmit={submit}>
                            <Form.Control type="text" value={q} onChange={e => setQ(e.target.value)}  placeholder="Tìm tên môn học..." className="mr-sm-2" />
                            <Form.Control type="text" value={q1} onChange={e => setQ1(e.target.value)}  placeholder="Tìm tín chỉ..." className="mr-sm-2" />
                            <Form.Control type="text" value={q2} onChange={e => setQ2(e.target.value)}  placeholder="Tìm giảng viên..." className="mr-sm-2" />
                            <Form.Control type="text" value={q3} onChange={e => setQ3(e.target.value)}  placeholder="Tìm khóa học..." className="mr-sm-2" />
                            <Button type="submit">Tìm kiếm</Button>
                    </Form>
                </Container>
               
            </Navbar></>}
        </>
    );
    
}

export default Header;

// return (
    //     <Navbar expand="lg" className="bg-body-tertiary">
    //         <Container>
    //             <Navbar.Brand href="#home">React-Bootstrap</Navbar.Brand>
    //             <Navbar.Toggle aria-controls="basic-navbar-nav" />
    //             <Navbar.Collapse id="basic-navbar-nav">
    //                 <Nav className="me-auto">
    //                     <Nav.Link href="#home">Home</Nav.Link>
    //                     <Nav.Link href="#link">Link</Nav.Link>
    //                     <NavDropdown title="Dropdown" id="basic-nav-dropdown">

    //                         {courses==null?<Spinner animation="grow" variant="secondary" /> :<>
    //                             {courses.map(c => <NavDropdown.Item key={c.id} href="#action/3.1">{c.name}</NavDropdown.Item>)}
    //                         </>}
                            
                            

    //                     </NavDropdown>
    //                 </Nav>
    //             </Navbar.Collapse>
    //         </Container>
    //     </Navbar>
    // );