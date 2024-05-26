import { ButtonHTMLAttributes } from 'react';
import { StyledButton } from './style';

interface ButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {
	text: string;
	textColor?: string;
	width?: string;
	height?: string;
	borderRadius?: string;
	fill?: boolean;
}

const PrimaryButton = ({ text, textColor, width, height, borderRadius, fill, ...props }: ButtonProps) => {
	return (
		<StyledButton
			textColor={textColor}
			width={width}
			height={height}
			borderRadius={borderRadius}
			fill={fill}
			{...props}
		>
			{text}
		</StyledButton>
	);
};

export default PrimaryButton;
