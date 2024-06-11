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

export const PopupTitle = styled.h3`
	font-size: ${({ theme }) => theme.textSize.large};
	font-weight: 600;
	margin-bottom: 20px;
	color: #333;
`;

export const PopupForm = styled.form`
	display: flex;
	flex-direction: column;
`;

export const PopupInput = styled.input`
	margin-bottom: 15px;
	padding: 10px;
	font-size: 16px;
	border: 1px solid #ccc;
	border-radius: ${({ theme }) => theme.radius.small};
	flex: 1;
`;

export const PopupSelect = styled.select`
	margin-bottom: 15px;
	padding: 10px;
	font-size: 16px;
	border: 1px solid #ccc;
	border-radius: ${({ theme }) => theme.radius.small};
	flex: 1;
`;

export const PopupButtonContainer = styled.div`
	display: flex;
	justify-content: flex-end;
	gap: 10px;
	margin-top: 20px; /* 간격 추가 */
`;

export const PopupButton = styled.button`
	padding: 10px;
	font-size: 16px;
	background-color: ${({ theme }) => theme.colors.primary};
	color: white;
	border: none;
	border-radius: ${({ theme }) => theme.radius.small};
	cursor: pointer;
	margin-top: 10px; /* 간격 추가 */

	&:hover {
		background-color: ${({ theme }) => theme.colors.accent};
	}
`;

export const ScheduleContainer = styled.div`
	display: flex;
	align-items: center;
	margin-bottom: 15px; /* 각 일정 필드 그룹 사이 간격 추가 */
	gap: 10px; /* 필드와 버튼 사이 간격 추가 */
`;

export const ScheduleInputGroup = styled.div`
	display: flex;
	align-items: center;
	gap: 10px; /* 필드와 버튼 사이 간격 추가 */
	flex: 1;
`;

export const ScheduleRemoveButton = styled(PopupButton)`
	flex-shrink: 0; /* 버튼의 크기를 고정 */
`;
