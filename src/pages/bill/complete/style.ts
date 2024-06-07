import styled from 'styled-components';

export const Wrap = styled.div`
	width: 100%;
	height: 100vh;
	overflow-y: auto;
	background-color: #eee;
`;

interface TopWrapProps {
	$isSuccess?: boolean;
}
export const TopWrap = styled.div<TopWrapProps>`
	width: 100%;
	padding: 30px 0 80px;
	background-color: ${({ $isSuccess }) => ($isSuccess ? '#00db85' : '#fb4949')};
`;

export const TopContainer = styled.div`
	width: 640px;
	margin: 0 auto;
`;

export const TopTitle = styled.h2`
	font-size: 30px;
	font-weight: 900;
	color: #fff;
	line-height: 1.2;
`;

export const ContentBox = styled.div`
	width: 100%;
	position: relative;
`;

export const ContentContainer = styled.div`
	width: 640px;
	position: absolute;
	top: -50px;
	left: 50%;
	transform: translateX(-50%);
	border-radius: 12px;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.1);
	background-color: #fff;
	padding: 30px;
`;

export const ContentTitle = styled.h3`
	font-size: 18px;
	font-weight: 800;
	color: #000;
	padding-bottom: 15px;
	border-bottom: 1px solid #eee;
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

		span {
			font-size: 24px;
		}
	}
`;
