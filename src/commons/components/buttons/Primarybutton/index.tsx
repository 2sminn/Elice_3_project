import { FC, ReactNode, MouseEvent } from 'react';
import { StyledButton } from './style';

type ButtonProps = {
	text: string;
	backcolor: string;
	textcolor: string;
	onMouseEnter?: (e: MouseEvent<HTMLButtonElement>) => void;
	onMouseLeave?: (e: MouseEvent<HTMLButtonElement>) => void;
	onClick?: (e: MouseEvent<HTMLButtonElement>) => void;
	type: 'button' | 'submit';
	icon?: ReactNode;
	width?: string;
	height?: string;
	disabled?: boolean;
	borderradius?: string;
};

const Button: FC<ButtonProps> = ({
	text,
	backcolor,
	textcolor,
	onMouseEnter,
	onMouseLeave,
	onClick,
	type,
	icon,
	width,
	height,
	disabled,
	borderradius,
}) => {
	return (
		<StyledButton
			backcolor={backcolor}
			textcolor={textcolor}
			onClick={onClick}
			onMouseEnter={onMouseEnter}
			onMouseLeave={onMouseLeave}
			type={type}
			hasicon={icon ? 'true' : 'false'}
			width={width}
			height={height}
			disabled={disabled}
			borderradius={borderradius}
		>
			{icon && <span className="icon-container">{icon}</span>}
			{text}
		</StyledButton>
	);
};

export default Button;
