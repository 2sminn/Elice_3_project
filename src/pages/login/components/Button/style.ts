import styled from 'styled-components';

type StyledButtonProps = {
	backcolor: string;
	textcolor: string;
	hasicon: string;
	width?: string;
	height?: string;
	disabled?: boolean;
	borderradius?: string;
};

export const StyledButton = styled.button<StyledButtonProps>`
	display: flex;
	align-items: center;
	justify-content: center;
	background-color: ${(props: { backcolor: string }) => props.backcolor};
	color: ${(props: { textcolor: string }) => props.textcolor};
	border: ${(props) => (props.hasicon === 'true' ? 'none' : '1px solid')};
	border-radius: ${(props) => props.borderradius || '8px'};
	padding: 10px;
	width: ${(props) => props.width || '460px'};
	height: ${(props) => props.height || '55px'};
	font-weight: 500;
	cursor: ${(props) => (props.disabled ? 'default' : 'pointer')};
	pointer-events: ${(props) => (props.disabled ? 'none' : 'auto')};
	position: relative;
	overflow: hidden; // 아이콘의 절대 위치가 경계 밖으로 가지 않도록 설정
	margin-top: 10px;

	.icon-container {
		font-size: 20px;
		position: absolute;
		left: 20px;
		display: flex;
		align-items: center;
	}
`;
