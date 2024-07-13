import {addEmployee} from "../../util/api/api.js";
import {useState} from "react";
import { Form, Input, InputGroup, Label} from "../StyledComponents.js";
import Button from "../button/Button.jsx";


const EmployeeForm = ({toggleFormVisibility}) => {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [designation, setDesignation] = useState('');
    const [ctc, setCtc] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        addEmployee({ name, email, designation, ctc }).then(() => {
            setName('');
            setEmail('');
            setDesignation('');
            setCtc('');
            toggleFormVisibility();

        });
    };

    return (
        <Form onSubmit={handleSubmit}>
            <InputGroup>
                <Label>Name</Label>
                <Input
                    type="text"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                    required
                />
            </InputGroup>
            <InputGroup>
                <Label>Email</Label>
                <Input
                    type="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    required
                />
            </InputGroup>
            <InputGroup>
                <Label>Designation</Label>
                <Input
                    type="text"
                    value={designation}
                    onChange={(e) => setDesignation(e.target.value)}
                    required
                />
            </InputGroup>
            <InputGroup>
                <Label>CTC</Label>
                <Input
                    type="number"
                    value={ctc}
                    onChange={(e) => setCtc(e.target.value)}
                    required
                />
            </InputGroup>
            <Button type="submit">Add Employee</Button>
        </Form>
    );
};

export default EmployeeForm;