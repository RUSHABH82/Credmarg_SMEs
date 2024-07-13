import {useEffect, useState} from 'react';
import {getMessages} from '../../util/api/api.js';
import MailDetailComponent from './MailDetail.jsx';
import styled from 'styled-components';
import Spinner from '../spinner/Spinner.jsx';
import {HeaderContainer, SectionHeader} from "../StyledComponents.js";

export const MailboxContainer = styled.div`
    margin-bottom: 20px;
    padding: 20px;
    background-color: white;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
`;

const Mailbox = () => {
    const [mails, setMails] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const response = getMessages();
        response.then(data => setMails(data))
        response.catch(response=>console.error(response))
        response.finally(()=>setLoading(false))

    }, []);

    return (<MailboxContainer>
        <HeaderContainer>
            <SectionHeader>Mail Box</SectionHeader>
        </HeaderContainer>
        {loading ? (<Spinner/>) : (mails.length === 0 ? (<p>No mails to display</p>) : (<div>
            {mails.map((mail, index) => (<MailDetailComponent mail={mail} key={index}/>))}
        </div>))}
    </MailboxContainer>);
};

export default Mailbox;
