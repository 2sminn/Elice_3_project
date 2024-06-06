import styled from 'styled-components';

export const MypageContainer = styled.div`
	width: 100%;
	padding: 20px;
	background-color: #fff;
	border-radius: ${({ theme }) => theme.radius.small};
	margin-bottom: 20px;
`;

export const ProfileContainer = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
	gap: 15px;
`;

export const ProfileImg = styled.div`
	width: 150px;
	height: 150px;
	background-color: #ddd;
	border-radius: 50%;
	background-repeat: no-repeat;
	background-position: center;
	background-size: cover;
`;

export const ProfileName = styled.h3`
	font-size: ${({ theme }) => theme.textSize.large};
	font-weight: 500;
	color: #000;
`;

export const ProfileInfoContainer = styled.div`
	display: flex;
	justify-content: center;
	align-items: center;
	gap: 30px;
	margin-top: 20px;
`;

export const ProfileInfoBox = styled.div`
	display: flex;
	align-items: center;
	gap: 8px;

	h4 {
		font-size: ${({ theme }) => theme.textSize.medium};
		font-weight: 600;
		color: #000;
	}

	p {
		font-size: ${({ theme }) => theme.textSize.medium};
		font-weight: 400;
		color: #000;
	}
`;

export const ProfileBtnContainer = styled.div`
	display: flex;
	justify-content: center;
	margin-top: 30px;
	gap: 10px;
`;
