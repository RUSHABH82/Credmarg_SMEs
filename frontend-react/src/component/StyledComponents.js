import styled from 'styled-components';

export const Section = styled.div`
    margin-bottom: 20px;
    padding: 20px;
    background-color: white;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
`;

export const HeaderContainer = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 10px;
`;

export const SectionHeader = styled.h2`
    color: #333;
    border-bottom: 2px solid #007bff;
    padding-bottom: 5px;
    margin-bottom: 15px;
`;

export const List = styled.ul`
    list-style: none;
    padding: 0;
`;

export const ListItem = styled.li`
    margin: 10px 0;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color: #f9f9f9;
`;

export const Form = styled.form`
    display: flex;
    flex-direction: column;
    margin-bottom: 20px;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color: #f9f9f9;
    max-width: 600px; /* Adjusted form size */
    margin: 0 auto; /* Center the form */
`;

export const InputGroup = styled.div`
    display: flex;
    flex-direction: column;
    margin-bottom: 15px;
`;

export const Label = styled.label`
    margin-bottom: 5px;
    color: #333;
    font-weight: bold;
`;

export const Input = styled.input`
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
`;

export const EmployeeVendorDetails = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 3px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color: #f9f9f9;
`;

export const EmployeeVendorInfo = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: center;
    flex-grow: 1;
    margin: 0 20px;
`;
