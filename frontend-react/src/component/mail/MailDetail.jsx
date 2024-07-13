import styled from 'styled-components';

const MailDetailContainer = styled.div`
    padding: 15px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color: #f9f9f9;
    margin-bottom: 10px;
`;

const MailHeader = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 10px;
`;

export const MailSubject = styled.h3`
    color: #333;
    margin-bottom: 5px;
`;

export const MailTimestamp = styled.p`
    color: #888;
`;

export const MailBody = styled.p`
    color: #555;
    line-height: 1.5;
`;

const MailDetail = ({mail}) => {
    return (<>
        <MailDetailContainer>
            <MailHeader>
                <MailSubject>Mail to: {mail.sendTo}</MailSubject>
                <MailTimestamp>{mail.sentAt}</MailTimestamp>
            </MailHeader>
            <MailBody>{mail.body}</MailBody>
        </MailDetailContainer>
    </>);
};
export default MailDetail;