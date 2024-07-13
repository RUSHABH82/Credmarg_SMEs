import styled, { keyframes } from 'styled-components';

const spin = keyframes`
    to {
        transform: rotate(360deg);
    }
`;

const SpinnerWrapper = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
`;

const SpinnerCircle = styled.div`
  width: 40px;
  height: 40px;
  border: 4px solid rgba(0, 0, 0, 0.1);
  border-left-color: #007bff;
  border-radius: 50%;
  animation: ${spin} 1s infinite linear;
`;

const Spinner = () => (
    <SpinnerWrapper>
        <SpinnerCircle />
    </SpinnerWrapper>
);

export default Spinner;
