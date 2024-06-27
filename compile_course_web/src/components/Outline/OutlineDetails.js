import { useContext, useEffect, useState } from "react";
import { Button, Col, Container, Form, Row } from "react-bootstrap";
import { useParams } from "react-router-dom";
import MySpinner from "../Commons/MySpinner";
import APIs, { endpoints } from "../../configs/APIs";
import "./SectionTitle.css";
import { MyUserContext } from "../../configs/Contexts";

const OutlineDetails = () => {

    const [content, setContent] = useState("");
    const user = useContext(MyUserContext)
    const [outline, setOutline] = useState(null);
    const { outlineId } = useParams();
    const [outlineMethod, setOutlineMethod] = useState(null);
    const [totalPercent, setTotalPercent] = useState(0);
    const [comment, setComment] = useState(null);

    const loadOutline = async () => {
        try {
            let res = await APIs.get(endpoints['details'](outlineId));
            let med = await APIs.get(endpoints['outlineMethod']);
            let comments = await APIs.get(endpoints['outline_comment'](outlineId));
            setOutline(res.data);

            // Lọc các phương pháp đánh giá liên quan đến outline hiện tại
            const filteredMethods = med.data.filter(method => method.outline.id === res.data.id);
            setOutlineMethod(filteredMethods);

            // const filterComments = comment.data.filter(comment => comment.outline.id === res.data.id);
            setComment(comments.data);

            console.log(comments.data);

            // Tính tổng phần trăm
            const total = filteredMethods.reduce((acc, method) => acc + method.weight, 0);
            setTotalPercent(total);



        } catch (ex) {
            console.error(ex);
        }

    }

    const postComment = async (e) => {
        e.preventDefault();
        if (!content) return;

        try {
            const commentData = {
                content,
                user: user.id,//{ id: user.id },
                outline: outlineId//{ id: outlineId }
            };
            await APIs.post(endpoints['create-comment'], commentData);
            setContent("");
            loadOutline(); // Reload comments
        } catch (ex) {
            console.error(ex);
        }
    }

    useEffect(() => {
        loadOutline();
        // loadOutlineMethod();
    }, [outlineId]);

    return (
        <Container>
            <h1 className="text-center text-info mt-1">CHI TIẾT ĐỀ CƯƠNG</h1>
            {outline === null ? <MySpinner /> : <>
                <div className="section">
                    <div className="section-title"><strong>I. Thông Tin Tổng Quát</strong></div>
                    <div className="content">
                        <p><strong>Tên Môn Học:</strong> <span>{outline.course.name}</span></p>
                        <p><strong>Phương Thức Giảng Dạy:</strong> <span>{outline.techingMethod}</span></p>
                        <p><strong>Ngôn Ngữ Giảng Dạy:</strong> <span>{outline.language}</span></p>
                        <p><strong>Thuộc Khối Kiến Thức:</strong> <span>{outline.knowledge}</span></p>
                        <p><strong>Số Tín Chỉ:</strong> <span>{outline.credit}</span></p>
                    </div>
                </div>
                <div className="section">
                    <div className="section-title"><strong>II. Thông Tin Về Môn Học</strong></div>
                    <div className="content">
                        <p><strong>Mô Tả Môn Học:</strong><span>{outline.description}</span> </p>
                        <p><strong>Mục Tiêu Môn Học:</strong><span>{outline.objectives}</span></p>
                        <p><strong>Phương Pháp Đánh Giá:</strong></p>
                        <table className="table table-striped">
                            <thead>
                                <tr>
                                    <th>Thành phần đánh giá</th>
                                    <th>Mô tả</th>
                                    <th>Tỷ lệ</th>
                                </tr>
                            </thead>
                            <tbody>
                                {/* {outlineMethod.outline.id===outline.id?<MySpinner/>:<>
                                    <tr>
                                        <td>{outlineMethod.evaluationMethod.name}</td>
                                        <td>{outlineMethod.name}</td>
                                        <td>{outlineMethod.weight}%</td>
                                    </tr>
                                    </>}
                                    <tr class="total">
                                        <td colspan="2"><strong>Tổng cộng:</strong></td>
                                        <td>{totalPercent + outlineMethod.weight} %</td>
                                    </tr> */}
                                {outlineMethod.map(method => (
                                    <tr key={method.id}>
                                        <td>{method.evaluationMethod.name}</td>
                                        <td>{method.name}</td>
                                        <td>{method.weight}%</td>
                                    </tr>
                                ))}
                                <tr className="total">
                                    <td colSpan="2"><strong>Tổng cộng:</strong></td>
                                    <td>{totalPercent}%</td>
                                </tr>
                            </tbody>
                        </table>
                        <p><strong>Quy Định Môn Học:</strong><span>{outline.policy}</span> </p>
                    </div>
                </div>
                <div className="section">
                    <div className="section-title"><strong>Bình luận</strong></div>
                    <div className="content">
                        <table className="table table-striped">
                            <thead>
                                <tr>
                                    <th>Người dùng</th>
                                    <th>Bình luận</th>
                                </tr>
                            </thead>
                            <tbody>

                                <tr>
                                    <td>{comment.user.lastName} {comment.user.firstName}</td>
                                    <td>{comment.content}</td>
                                </tr>

                            </tbody>
                        </table>
                    </div>
                </div>
                <Form className="d-flex" onSubmit={postComment}>
                    <Form.Control type="text" value={content} onChange={e => setContent(e.target.value)} placeholder="Thêm bình luận..." className="mr-sm-2" />
                    <Button type="submit">Thêm</Button>
                </Form>
            </>}
        </Container>
    );
}

export default OutlineDetails;