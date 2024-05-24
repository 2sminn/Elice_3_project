import styled from 'styled-components';

export const Container = styled.div`
	width: 700px;
	padding: 30px;
	background-color: #fff;
	border-radius: 6px;
`;

export const Title = styled.h3`
	font-size: 20px;
	font-weight: 600;
	color: #000;
	margin-bottom: 20px;
`;

export const SubTitleBox = styled.div`
	display: flex;
	align-items: center;
	gap: 50px;
	margin-bottom: 20px;
`;

export const SubTitle = styled.h4`
	display: flex;
	gap: 10px;
	align-items: center;
	p {
		font-size: 20px;
		font-weight: 400;
		color: #000;
	}

	span {
		display: inline-block;
		padding: 6px 10px;
		background-color: #000;
		font-size: 14px;
		font-weight: 500;
		color: #fff;
		border-radius: 100px;
	}
`;

export const AddBillBox = styled.div`
	display: flex;
	gap: 15px 2%;
	flex-wrap: wrap;
	margin-bottom: 25px;
`;

type AddCountBtnType = {
	isActive: boolean;
};

export const AddCountBtn = styled.button<AddCountBtnType>`
	display: block;
	width: 32%;
	height: 65px;
	padding: 5px 0;
	border: ${({ isActive }) => (isActive ? '2px solid green' : '1px solid #000')};
	font-size: 18px;
	font-weight: 700;
	color: #000;
	border-radius: 10px;

	span {
		font-size: 16px;
		font-weight: 400;
		color: #333;
		display: block;
	}
`;

export const BtnContainer = styled.div`
	width: 100%;
	display: flex;
	justify-content: space-between;
	align-items: center;
`;
