import styled from 'styled-components';

export const Container = styled.div`
	width: 700px;
	height: 80vh;
	overflow-y: auto;
	padding: 30px;
	background-color: #fff;
	border-radius: 6px;
`;

export const Title = styled.h3`
	font-size: ${({ theme }) => theme.textSize.large};
	font-weight: 600;
	color: #000;
	margin-bottom: 20px;
	display: flex;
	justify-content: space-between;
	align-items: center;
`;

export const BillForm = styled.form`
	width: 100%;
`;

export const UserSearchBox = styled.div`
	width: 100%;
	position: relative;
`;

export const SearchBtn = styled.button`
	position: absolute;
	top: 50%;
	right: 10px;
	transform: translateY(-50%);
	display: flex;
	justify-content: center;
	align-items: center;
`;

export const UserListBox = styled.div`
	width: 100%;
	height: 150px;
	border: 1px solid #ccc;
	border-radius: ${({ theme }) => theme.radius.small};
	margin-top: 10px;
	padding: 10px;
	overflow-y: auto;
`;

export const UserList = styled.ul`
	display: flex;
	flex-direction: column;
	gap: 10px;

	li {
		display: flex;
		justify-content: space-between;
		align-items: center;
		padding: 7px 0;
		&:not(:last-child) {
			border-bottom: 1px solid #eee;
		}
	}
`;

export const UserInfo = styled.span`
	font-size: ${({ theme }) => theme.textSize.little};
	font-weight: 400;
	color: #000;

	span {
		font-weight: 500;
		color: ${({ theme }) => theme.colors.primary};
	}
`;

export const UserInfoContainer = styled.div`
	width: 100%;
	display: flex;
	flex-direction: column;
	gap: 10px;
	margin-top: 15px;
`;

export const InputContainer = styled.div`
	display: flex;
	flex-direction: column;
	gap: 5px;

	label {
		font-size: ${({ theme }) => theme.textSize.little};
		font-weight: 500;
		color: #000;
	}
`;

export const BillSubTitle = styled.div`
	font-size: ${({ theme }) => theme.textSize.large};
	font-weight: 600;
	color: #333;
	margin: 15px 0;
	padding: 5px 0;
	border-bottom: 1px solid #aaa;
`;

export const MessageContainer = styled.div`
	margin-bottom: 20px;
`;

export const BillBtnContainer = styled.div`
	display: flex;
	justify-content: space-between;
	align-items: center;
`;
