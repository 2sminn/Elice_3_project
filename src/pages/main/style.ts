import styled from 'styled-components';

export const ContentContainer = styled.div`
	width: 100%;
	display: flex;
	justify-content: space-between;
`;

export const Introduce = styled.p`
	font-size: 20px;
	font-weight: 500;
	color: #000;
	margin-bottom: 30px;

	span {
		font-weight: 700;
	}
`;

export const MiddleContent = styled.div`
	width: 100%;
	min-width: 800px;
	display: flex;
	flex-wrap: wrap;
	gap: 20px;
`;

export const SalesChartContainer = styled.div`
	width: 100%;
	background-color: #fff;
	padding: 20px 15px;
	border-radius: 10px;
	box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.2);

	h4 {
		font-size: 18px;
		font-weight: 700;
		color: #000;
		margin-bottom: 30px;
	}

	h5 {
		font-size: 16px;
		font-weight: 500;
		color: #000;
		margin-bottom: 8px;
	}

	p {
		font-size: 24px;
		font-weight: 800;
		color: #000;
	}
`;

export const BillContainer = styled.div`
	width: 100%;
	display: flex;
	justify-content: space-between;
`;
