import styled from 'styled-components';

interface StyledButtonProps {
	textColor?: string;
	width?: string;
	height?: string;
	borderRadius?: string;
	isFill?: boolean;
	textSize?: string;
}

export const StyledButton = styled.button.withConfig({
	shouldForwardProp: (prop) => prop !== 'textSize' && prop !== 'isFill',
})<StyledButtonProps>`
	display: flex;
	align-items: center;
	justify-content: center;
	background-color: ${({ isFill, theme }) => (isFill ? theme.colors.primary : 'none')};
	color: ${({ textColor, isFill, theme }) => (isFill ? '#fff' : textColor || theme.colors.primary)};
	border-radius: ${({ borderRadius }) => borderRadius || '6px'};
	padding: 10px;
	width: ${({ width }) => width || '100%'};
	height: ${({ height }) => height || '45px'};
	font-weight: 500;
	cursor: ${(props) => (props.disabled ? 'default' : 'pointer')};
	pointer-events: ${(props) => (props.disabled ? 'none' : 'auto')};
	position: relative;
	overflow: hidden;
	border: ${({ isFill, theme }) => (isFill ? 'none' : `1px solid ${theme.colors.primary}`)};
	font-size: ${({ textSize, theme }) => (textSize ? textSize : theme.textSize.medium)};

	&:hover {
		opacity: 0.85;
		transition: 0.3s;
	}
`;
