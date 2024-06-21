import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./components/Commons/Header";
import Footer from "./components/Commons/Footer";
import Home from "./components/Outline/Home";
import 'bootstrap/dist/css/bootstrap.min.css';
import Register from "./components/User/Register";
import Login from "./components/User/Login";
import OutlineDetails from "./components/Outline/OutlineDetails";
import { useReducer } from "react";
import MyUserReducer from "./configs/Reducers";
import cookie from "react-cookies";
import { MyDispatchContext, MyUserContext } from "./configs/Contexts";

const App = () => {
  const [user, dispatch] = useReducer(MyUserReducer, cookie.load("user") || null);
  return (
      <BrowserRouter>
        <MyUserContext.Provider value={user}>
          <MyDispatchContext.Provider value={dispatch}>
            <Header />
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/outlines/:outlineId" element={<OutlineDetails />} />
              <Route path="/register" element={<Register />} />
              <Route path="/login" element={<Login />} />
            </Routes>
          </MyDispatchContext.Provider>
        </MyUserContext.Provider>
        {/* <Footer /> */}
      </BrowserRouter>
    );
}

export default App;



// return (
//   <BrowserRouter>
//     <MyUserContext.Provider value={user}>
//         <MyDispatchContext.Provider value={dispatch}>
//           <Header />
//           <Routes>
//             <Route path="/" element={<Home />} />
//             <Route path="/outlines/:outlineId" element={<OutlineDetails />} />
//             <Route path="/register" element={<Register />} />
//             <Route path="/login" element={<Login />} />
//           </Routes>
//         </MyDispatchContext.Provider>
//     </MyUserContext.Provider>
//     <Footer />
//   </BrowserRouter>
// );