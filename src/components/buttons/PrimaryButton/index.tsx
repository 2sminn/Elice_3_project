import { ButtonHTMLAttributes } from 'react';
import { StyledButton } from './style';

interface ButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {
	text: string;
	textSize?: string;
	textColor?: string;
	width?: string;
	height?: string;
	borderRadius?: string;
	fill?: boolean;
}

const PrimaryButton = ({ text, textColor, width, height, borderRadius, fill, textSize, ...props }: ButtonProps) => {
	return (
		<StyledButton
			textColor={textColor}
			width={width}
			height={height}
			borderRadius={borderRadius}
			fill={fill}
			textSize={textSize}
			{...props}
		>
			{text}
		</StyledButton>
	);
};

export default PrimaryButton;
