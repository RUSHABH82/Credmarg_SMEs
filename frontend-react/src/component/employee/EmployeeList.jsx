import {useEffect, useState} from "react";
import {getEmployees} from "../../util/api/api.js";
import {EmployeeVendorDetails, EmployeeVendorInfo, List, ListItem} from "../StyledComponents.js";


const EmployeeList = () => {
    const [employees, setEmployees] = useState([]);

    useEffect(() => {
        getEmployees().then(data => setEmployees(data));
    }, []);

    return (<>
        {employees.length > 0 ? <List>
            {employees.map(employee => (<ListItem key={employee.id}>
                <EmployeeVendorDetails>
                    <EmployeeVendorInfo>
                        <p>{employee.id}</p>
                        <p>{`${employee.name} (${employee.designation})`}</p>
                        <p>{employee.email}</p>
                        <p> {`Rs. ${employee.ctc}`}</p>
                    </EmployeeVendorInfo>
                </EmployeeVendorDetails>
            </ListItem>))}
        </List> : <p>No Employee to Display</p>}
    </>);
};

export default EmployeeList;
