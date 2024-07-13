import EmployeeForm from './EmployeeForm';
import { HeaderContainer, Section, SectionHeader} from "../StyledComponents.js";
import EmployeeList from "./EmployeeList.jsx";
import {useState} from "react";
import Button from "../button/Button.jsx";

const EmployeeCard = () => {

    const [isFormVisible, setIsFormVisible] = useState(false);
    const toggleFormVisibility = () => {
        setIsFormVisible((prevState) => !prevState);
    };
    return (<Section>
            <HeaderContainer>
                <SectionHeader>Employees</SectionHeader>
                <Button onClick={toggleFormVisibility}>
                    {isFormVisible ? 'List Employee' : 'Add Employee'}
                </Button>
            </HeaderContainer>
            {isFormVisible ? <EmployeeForm toggleFormVisibility={toggleFormVisibility}/> : <EmployeeList/>}
        </Section>);
};

export default EmployeeCard;
