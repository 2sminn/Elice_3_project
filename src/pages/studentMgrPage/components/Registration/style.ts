import styled from 'styled-components';

export const PopupContainer = styled.div`
	width: 500px;
	background-color: #fff;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	display: flex;
	flex-direction: column;
`;

export const Header = styled.div`
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 20px;
`;

export const Title = styled.h3`
	font-size: ${({ theme }) => theme.textSize.large};
	font-weight: 600;
	color: #333;
`;

export const CloseButton = styled.button`
	background: none;
	border: none;
	font-size: 24px;
	cursor: pointer;

	&:hover {
		color: ${({ theme }) => theme.colors.accent};
	}
`;

export const Form = styled.form`
	display: flex;
	flex-direction: column;
`;

export const Input = styled.input`
	margin-bottom: 10px;
	padding: 10px;
	font-size: 16px;
	border: 1px solid #ccc;
	border-radius: ${({ theme }) => theme.radius.small};
`;

export const ButtonContainer = styled.div`
	display: flex;
	justify-content: flex-end;
	gap: 10px;
`;

export const SaveButton = styled.button`
	padding: 10px;
	font-size: 16px;
	background-color: ${({ theme }) => theme.colors.primary};
	color: white;
	border: none;
	border-radius: ${({ theme }) => theme.radius.small};
	cursor: pointer;

	&:hover {
		background-color: ${({ theme }) => theme.colors.accent};
	}
`;

export const ScheduleContainer = styled.div`
	margin-bottom: 10px;
	padding: 10px;
	border: 1px solid #ddd;
	border-radius: ${({ theme }) => theme.radius.small};
	display: flex;
	flex-direction: column;
`;

export const ScheduleInputGroup = styled.div`
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 5px;

	& > input {
		flex: 1;
		margin-right: 10px;
	}

	& > select {
		flex: 1;
		margin-right: 10px;
	}
`;

export const ScheduleRemoveButton = styled.button`
	background-color: #ff4d4d;
	color: white;
	border: none;
	border-radius: ${({ theme }) => theme.radius.small};
	padding: 5px 10px;
	cursor: pointer;

	&:hover {
		background-color: #ff1a1a;
	}
`;

export const PopupSelect = styled.select`
	margin-bottom: 10px;
	padding: 10px;
	font-size: 16px;
	border: 1px solid #ccc;
	border-radius: ${({ theme }) => theme.radius.small};
`;

export const PopupButton = styled.button`
	padding: 10px;
	font-size: 16px;
	background-color: ${({ theme }) => theme.colors.primary};
	color: white;
	border: none;
	border-radius: ${({ theme }) => theme.radius.small};
	cursor: pointer;

	&:hover {
		background-color: ${({ theme }) => theme.colors.accent};
	}
`;
