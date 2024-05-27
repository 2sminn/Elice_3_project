import styled from 'styled-components';

export const StyledInput = styled.input`
	width: ${(props) => props.width || '100%'};
	height: 50px;
	background-color: white;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 8px;

	&::placeholder {
		color: #888;
	}

	&:focus {
		border-color: ${({ theme }) => theme.colors.primary};
		outline: none;
	}
`;
