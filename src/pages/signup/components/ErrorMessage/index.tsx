import styled from 'styled-components';

interface ErrorMessageProps {
	message?: string;
}

const ErrorText = styled.div`
	color: red;
	font-size: 12px;
	margin-top: 4px; /* Space between input and error message */
`;

const ErrorMessage = ({ message }: ErrorMessageProps) => {
	return <ErrorText>{message}</ErrorText>;
};

export default ErrorMessage;
