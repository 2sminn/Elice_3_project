import { Link } from 'react-router-dom';
import styled from 'styled-components';

export const Container = styled.div`
	width: 48.5%;
	padding: 20px 15px;
	background-color: #fff;
	box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.2);
	border-radius: 10px;
`;

export const TitleBox = styled.div`
	width: 100%;
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 30px;
`;

export const BillTitle = styled.h3`
	font-size: 18px;
	font-weight: 700;
	color: #000;
`;

export const BillContentContainer = styled.div`
	display: flex;
	gap: 5%;
	align-items: center;
	margin-bottom: 20px;
`;

export const BillContentBox = styled.div`
	display: flex;
	flex-direction: column;
	gap: 8px;

	h5 {
		font-size: 17px;
		font-weight: 500;
		color: #000;
	}

	p {
		font-size: 22px;
		font-weight: 800;
		color: #000;
	}
`;

export const BillDetailLinkBtn = styled(Link)`
	display: block;
	width: 150px;

	&:hover {
		opacity: 0.85;
		transition: 0.3s;
	}
`;
