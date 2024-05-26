import { ButtonHTMLAttributes } from 'react';
import { StyledButton } from './style';

interface ButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {
	text: string;
	textSize?: string;
	textColor?: string;
	width?: string;
	height?: string;
	borderRadius?: string;
	isFill?: boolean;
}

const PrimaryButton = ({ text, textColor, width, height, borderRadius, isFill, textSize, ...props }: ButtonProps) => {
	return (
		<StyledButton
			textColor={textColor}
			width={width}
			height={height}
			borderRadius={borderRadius}
			isFill={isFill}
			textSize={textSize}
			{...props}
		>
			{text}
		</StyledButton>
	);
};

export default PrimaryButton;
