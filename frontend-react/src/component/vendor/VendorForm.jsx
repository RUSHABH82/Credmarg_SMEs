import {useState} from 'react';
import {addVendor} from "../../util/api/api.js";
import {Form, Input, InputGroup, Label} from "../StyledComponents.js";
import Button from "../button/Button.jsx";

const INITIAL_VENDOR = {
    name: "",
    email: "",
    upi: ""
}

const VendorForm = ({toggleFormVisibility}) => {
    const [vendor, setVendor] = useState(INITIAL_VENDOR);

    const handleSubmit = (e) => {
        e.preventDefault();
        const response = addVendor(vendor);
        response.then(() => toggleFormVisibility());
        response.catch(err => console.log(err));
    };

    return (
        <Form onSubmit={handleSubmit}>
            <InputGroup>
                <Label>Name</Label>
                <Input
                    type="text"
                    value={vendor.name}
                    onChange={({target}) => setVendor(prevState => ({...prevState, ['name']: target.value}))}
                    required
                />
            </InputGroup>
            <InputGroup>
                <Label>Email</Label>
                <Input
                    type="email"
                    value={vendor.email}
                    onChange={({target}) => setVendor(prevState => ({...prevState, ['email']: target.value}))}
                    required
                />
            </InputGroup>
            <InputGroup>
                <Label>UPI</Label>
                <Input
                    type="text"
                    value={vendor.upi}
                    onChange={({target}) => setVendor(prevState => ({...prevState, ['upi']: target.value}))}
                    required
                />
            </InputGroup>
            <Button type="submit">Add Vendor</Button>
        </Form>
    );
};

export default VendorForm;