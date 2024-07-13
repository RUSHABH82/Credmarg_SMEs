import {Route, Routes} from "react-router-dom";
import {Navigation} from "./component/navigation/Navigation.jsx";
import {Home} from "./component/home/home.component.jsx";
import styled from "styled-components";
import EmployeeCard from "./component/employee/EmployeeCard.jsx";
import VendorCard from "./component/vendor/VendorCard.jsx";


const AppContainer = styled.div`
    font-family: Arial, sans-serif;
    padding: 20px;
    background-color: #f0f2f5;
    min-height: 100vh;
`;

const App = () => {


    return (
        <AppContainer>
            <Routes>
                <Route path='/' element={<Navigation/>}>
                    <Route index element={<Home/>}/>
                    <Route path='employees' element={<EmployeeCard/>}/>
                    <Route path='vendors' element={<VendorCard/>}/>
                </Route>
            </Routes>
        </AppContainer>
    );
};

export default App;
