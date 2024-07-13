import styled from 'styled-components';


const ButtonWrapper = styled.button`
    padding: 10px 15px;
    border: none;
    border-radius: 5px;
    background-color: ${({isloading}) => isloading ? '#ccc' : '#007bff'};
    color: ${({isloading}) => isloading ? '#999' : 'white'};
    cursor: ${({isloading}) => isloading ? 'not-allowed' : 'pointer'};
    display: flex;
    justify-content: center;
    align-items: center;
    position: relative;

    &:hover {
        background-color: #0056b3;
    }

    align-self: flex-start;
    margin-left: auto;

`;

const Spinner = styled.div`
    width: 20px;
    height: 20px;
    border: 2px solid transparent;
    border-top-color: #333;
    border-radius: 50%;
    position: absolute;
    animation: spin 1s ease-in-out infinite;
    -webkit-animation: spin 1s ease-in-out infinite;
    @keyframes spin {
        to {
            -webkit-transform: rotate(360deg);
        }
    }
    @-webkit-keyframes spin {
        to {
            -webkit-transform: rotate(360deg);
        }
    }
`;

const Button = ({isloading, children, ...otherProps}) => (
    <ButtonWrapper isloading ={isloading}{...otherProps}>
        {isloading && <Spinner/>}
        {children}
    </ButtonWrapper>);

export default Button;
