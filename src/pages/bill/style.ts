import styled from 'styled-components';

export const Wrap = styled.div`
	width: 100%;
	height: 100vh;
	overflow-y: auto;
	background-color: #eee;
`;

export const Container = styled.div`
	width: 640px;
	margin: 0 auto;
	padding: 32px 20px;
`;

export const TitleContainer = styled.div`
	width: 100%;
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 24px;

	img {
		width: 150px;
	}
`;

export const BillTitle = styled.h2`
	font-size: 30px;
	font-weight: 900;
	color: #000;
	line-height: 1.2;
`;

export const BillBox = styled.div`
	width: 100%;
	border-radius: 12px;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.1);
	background-color: #fff;
	padding: 16px;
`;

export const BoxTitle = styled.h3`
	font-size: ${({ theme }) => theme.textSize.medium};
	font-weight: 700;
	color: #000;
`;

export const InfoBox = styled.div`
	width: 100%;
	display: flex;
	flex-direction: column;
	margin-bottom: 20px;
`;

export const InfoContainer = styled.div`
	width: 100%;
	padding: 16px 0;
	display: flex;
	justify-content: space-between;
	align-items: center;

	&:not(:last-child) {
		border-bottom: 1px solid #eee;
	}

	h4 {
		font-size: 13px;
		font-weight: 400;
		color: #000;
	}

	p {
		font-size: 15px;
		font-weight: 800;
		color: #000;
	}
`;

export const BillPrice = styled.div`
	font-size: 27px;
	font-weight: 900;
	color: #000;
	padding: 0 2px;
	background-image: linear-gradient(180deg, transparent 52%, #00ff9a 0);
	background-size: 100% 97%;
	background-repeat: no-repeat;
	vertical-align: middle;
`;
