import { useEffect, useState } from "react";
import { Button, Card, Col, Container, Row } from "react-bootstrap";
import MySpinner from "../Commons/MySpinner";
import APIs, { endpoints } from "../../configs/APIs";
import { useNavigate, useSearchParams } from "react-router-dom";

const Home = () => {
    const[outlines, setOutlines] = useState([]);
    const [loading, setLoading] = useState(false);

    const [page, setPage] = useState(1);
    const nav = useNavigate();
    const [q, ] = useSearchParams();

    const loadOutlines = async () => {
        setLoading(true)
        try {
            let url = `${endpoints['outlines']}?page=${page}`;

            let kw = q.get("kw");
            if (kw) {
                setPage(1);
                url = `${url}&kw=${kw}`;
            }

            let courseCredit = q.get('courseCredit')
            if (courseCredit) {
                setPage(1);
                url = `${url}&courseCredit=${courseCredit}`;
            }

            let teacherName = q.get('teacherName')
            if (teacherName) {
                setPage(1);
                url = `${url}&teacherName=${teacherName}`;
            }

            let term = q.get('term')
            if (term) {
                setPage(1);
                url = `${url}&term=${term}`;
            }



            console.info(url)
            const res = await APIs.get(url);

            if (page === 1)
                setOutlines(res.data);
            else 
                setOutlines(current => {
                    return [...current, ...res.data];
                })
        } catch (ex) {
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        loadOutlines();
    }, [q, page]);

    

    const loadMore = () => {
        if (!loading)
            setPage(page + 1);
    }

    return(
        // <h1>Home</h1>
        <>
            
            <Container>
                {loading && <MySpinner />}
                <Row>
                   {outlines.map(o => <>
                        <Col md={3} xs={12} className='p-2'>
                            <Card>
                                {/* <Card.Img variant="top" src={p.image} /> */}
                                <Card.Body>
                                    <Card.Title>{o.course.name}</Card.Title>
                                    <Button variant="primary" className="m-1" onClick={() => nav(`/outlines/${o.id}`)}>Xem chi tiết</Button>
                                    {/* <Button onClick={() => order(p.id, p.name, p.price)} variant="danger" className="m-1">Đặt hàng</Button> */}
                                </Card.Body>
                            </Card>
                        </Col>
                   </>)}
                </Row>
                {loading && page > 1 && <MySpinner />}
                <div className="text-center">
                    <Button variant="info" onClick={loadMore} disabled={loading}>Xem thêm</Button>
                </div>
            </Container>
            

        </>
    );
}

export default Home;

// const [products, setProducts] = useState([]);
//     const [loading, setLoading] = useState(false);
    
//     const [page, setPage] = useState(1);
//     const nav = useNavigate();
//     const [q, ] = useSearchParams();

//     const loadProducts = async () => {
//         setLoading(true)
//         try {
//             let url = `${endpoints['products']}?page=${page}`;

//             let cateId = q.get('cateId')
//             if (cateId) {
//                 setPage(1);
//                 url = `${url}&cateId=${cateId}`;
//             }

//             let kw = q.get("kw");
//             if (kw) {
//                 setPage(1);
//                 url = `${url}&kw=${kw}`;
//             }

//             console.info(url)
//             const res = await APIs.get(url);

//             if (page === 1)
//                 setProducts(res.data);
//             else 
//                 setProducts(current => {
//                     return [...current, ...res.data];
//                 })
//         } catch (ex) {
//             console.error(ex);
//         } finally {
//             setLoading(false);
//         }
//     }

//     useEffect(() => {
//         loadProducts();
//     }, [q, page]);

    

//     const loadMore = () => {
//         if (!loading)
//             setPage(page + 1);
//     }

//     const order = (id, name, price) => {
      
//         let cart = cookie.load("cart")||null;
//         if (cart === null)
//             cart = {};

//         if (id in cart) {
//             // đã có trong giỏ
//             cart[id]["quantity"]++;
//         } else {
//             // chưa có
//             cart[id] = {
//                 "id": id,
//                 "name": name,
//                 "price": price,
//                 "quantity": 1
//             };
//         }

//         cookie.save('cart', cart);
//         console.info(cart);
//     }   

//     return (
//         <>
            
//             <Container>
//                 {loading && <MySpinner />}
//                 <Row>
//                    {products.map(p => <>
//                         <Col md={3} xs={12} className='p-2'>
//                             <Card>
//                                 <Card.Img variant="top" src={p.image} />
//                                 <Card.Body>
//                                     <Card.Title>{p.name}</Card.Title>
//                                     <Card.Text>{p.price} VNĐ</Card.Text>
//                                     <Button variant="primary" className="m-1" onClick={() => nav(`/products/${p.id}`)}>Xem chi tiết</Button>
//                                     <Button onClick={() => order(p.id, p.name, p.price)} variant="danger" className="m-1">Đặt hàng</Button>
//                                 </Card.Body>
//                             </Card>
//                         </Col>
//                    </>)}
//                 </Row>
//                 {loading && page > 1 && <MySpinner />}
//                 <div className="text-center">
//                     <Button variant="info" onClick={loadMore} disabled={loading}>Xem thêm</Button>
//                 </div>
//             </Container>
            

//         </>
//     );