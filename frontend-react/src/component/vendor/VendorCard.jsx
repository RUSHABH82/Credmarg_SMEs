import VendorForm from './VendorForm';
import { HeaderContainer, Section, SectionHeader} from "../StyledComponents.js";
import VendorList from "./VendorList.jsx";
import {useState} from "react";
import Button from "../button/Button.jsx";

const VendorCard = () => {

    const [isFormVisible, setIsFormVisible] = useState(false);
    const toggleFormVisibility = () => {
        setIsFormVisible((prevState) => !prevState);
    };
    return (<Section>
        <HeaderContainer>
            <SectionHeader>Vendors</SectionHeader>
            <Button onClick={toggleFormVisibility}>
                {isFormVisible ? 'List Vendor' : 'Add Vendor'}
            </Button>
        </HeaderContainer>
        {isFormVisible ? <VendorForm toggleFormVisibility={toggleFormVisibility}/> : <VendorList/>}
    </Section>);
};

export default VendorCard;
