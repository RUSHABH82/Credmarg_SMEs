import {useEffect, useState} from 'react';
import {getVendors, sendPaymentMailToVendor} from "../../util/api/api.js";
import {EmployeeVendorDetails, EmployeeVendorInfo, List, ListItem} from "../StyledComponents.js";
import Button from "../button/Button.jsx";

const INITIAL_SEND_MAIL_FILTER = {
    sentTo: [], subjects: []
}

const VendorList = () => {
    const [vendors, setVendors] = useState([]);
    const [isMailSending, setIsMailSending] = useState(false);

    useEffect(() => {
        getVendors().then(data => setVendors(data));
    }, []);

    const handleSendMail = () => {
        if (!isMailSending) {
            const response = sendPaymentMailToVendor(INITIAL_SEND_MAIL_FILTER);
            setIsMailSending(true);
            response.finally(() => setIsMailSending(false))
            response.catch(reason => console.error(reason))
        }
    }

    return (<>
        {vendors.length > 0 ? (<>
            <Button isloading={isMailSending} onClick={handleSendMail}>Send MAil</Button>
            <List>
                {vendors.map(vendor => (<ListItem key={vendor.id}>
                    <EmployeeVendorDetails>
                        <EmployeeVendorInfo>
                            <p>{vendor.id}</p>
                            <p>{vendor.name}</p>
                            <p>{vendor.email}</p>
                            <p>{vendor.upi}</p>
                        </EmployeeVendorInfo>
                    </EmployeeVendorDetails>
                </ListItem>))}
            </List></>) : <p>No vendors to Display</p>}
    </>);
};

export default VendorList;
