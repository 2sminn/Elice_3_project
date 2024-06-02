import styled from 'styled-components';

interface StyledSelectProps {
	width?: string;
	height?: string;
}

export const StyledSelect = styled.select<StyledSelectProps>`
	width: ${({ width }) => width || '100%'};
	height: ${({ height }) => height || '50px'};
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

	&:disabled {
		background-color: #eee;
	}
`;
