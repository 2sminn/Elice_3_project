import React, { FC } from 'react';
import styled from 'styled-components';

interface ErrorMessageProps {
	message?: string;
}

const ErrorText = styled.div`
	color: red;
	font-size: 12px;
	margin-top: 4px; /* Space between input and error message */
`;

const ErrorMessage: FC<ErrorMessageProps> = ({ message }) => {
	return <ErrorText>{message}</ErrorText>;
};

export default ErrorMessage;
