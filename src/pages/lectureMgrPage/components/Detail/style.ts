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
	font-size: 20px;
	cursor: pointer;
	color: #000;
`;

export const DetailList = styled.form`
	list-style: none;
	padding: 0;
	margin: 0;
	flex: 1;
	overflow-y: auto;
`;

export const DetailItem = styled.li`
	margin-bottom: 10px;
	font-size: ${({ theme }) => theme.textSize.medium};
	color: ${({ theme }) => theme.colors.text};
`;

export const ButtonContainer = styled.div`
	text-align: center;
	margin-top: 20px;
`;

export const EditButton = styled.button`
	padding: 10px 20px;
	font-size: 16px;
	background-color: ${({ theme }) => theme.colors.primary}; /* primary 색상 적용 */
	color: white;
	border: none;
	border-radius: ${({ theme }) => theme.radius.small};
	cursor: pointer;

	&:hover {
		background-color: ${({ theme }) => theme.colors.accent}; /* accent 색상 적용 */
	}
`;

export const SaveButton = styled.button`
	padding: 10px 20px;
	font-size: 16px;
	background-color: ${({ theme }) => theme.colors.success};
	color: white;
	border: none;
	border-radius: ${({ theme }) => theme.radius.small};
	cursor: pointer;

	&:hover {
		background-color: ${({ theme }) => theme.colors.accent};
	}
`;

export const Input = styled.input`
	margin-bottom: 10px;
	padding: 8px;
	font-size: 16px;
	border: 1px solid #ccc;
	border-radius: ${({ theme }) => theme.radius.small};
	width: 100%;
`;

export const ScheduleContainer = styled.div`
	display: flex;
	align-items: center;
	margin-bottom: 15px;
	gap: 10px;
`;

export const ScheduleInputGroup = styled.div`
	display: flex;
	align-items: center;
	gap: 10px;
	flex: 1;
`;

export const ScheduleRemoveButton = styled.button`
	padding: 10px;
	font-size: 16px;
	background-color: ${({ theme }) => theme.colors.danger};
	color: white;
	border: none;
	border-radius: ${({ theme }) => theme.radius.small};
	cursor: pointer;

	&:hover {
		background-color: ${({ theme }) => theme.colors.accent};
	}
`;

export const PopupSelect = styled.select`
	margin-bottom: 15px;
	padding: 10px;
	font-size: 16px;
	border: 1px solid #ccc;
	border-radius: ${({ theme }) => theme.radius.small};
	flex: 1;
`;

export const ScheduleItem = styled.li`
	margin-bottom: 10px;
	font-size: ${({ theme }) => theme.textSize.medium};
	color: ${({ theme }) => theme.colors.text};
`;

export const PopupButton = styled.button`
	padding: 10px;
	font-size: 16px;
	background-color: ${({ theme }) => theme.colors.success};
	color: white;
	border: none;
	border-radius: ${({ theme }) => theme.radius.small};
	cursor: pointer;

	&:hover {
		background-color: ${({ theme }) => theme.colors.accent};
	}
`;
