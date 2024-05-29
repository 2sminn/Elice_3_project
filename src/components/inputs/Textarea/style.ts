import styled from 'styled-components';

interface StyledTextareaProps {
	width?: string;
	height?: string;
}

export const StyledTextarea = styled.textarea<StyledTextareaProps>`
	width: ${({ width }) => width || '100%'};
	height: ${({ height }) => height || '250px'};
	background-color: white;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 8px;
	resize: none;

	&::placeholder {
		color: #888;
	}

	&:focus {
		border-color: ${({ theme }) => theme.colors.primary};
		outline: none;
	}
`;
