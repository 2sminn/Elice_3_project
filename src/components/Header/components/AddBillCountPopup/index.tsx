import { useState } from 'react';
import usePopup from '../../../../hooks/usePopup';
import { formatNumber } from '../../../../utils/formatNumber';
import * as S from './style';
import PrimaryButton from '../../../buttons/PrimaryButton';

const EduDatas = [
	{
		id: 1,
		edu: 20,
		price: 10000,
	},
	{
		id: 2,
		edu: 50,
		price: 25000,
	},
	{
		id: 3,
		edu: 100,
		price: 45000,
	},
	{
		id: 4,
		edu: 300,
		price: 120000,
	},
	{
		id: 5,
		edu: 500,
		price: 200000,
	},
	{
		id: 6,
		edu: 1000,
		price: 380000,
	},
];

interface eduDataType {
	id: number;
	edu: number;
	price: number;
}

const AddBillCountPopup = () => {
	const { closePopup } = usePopup();

	const [eduData, setEduData] = useState<eduDataType | null>(null);

	const handleClickBtn = (edu: eduDataType) => () => {
		setEduData(edu);
	};

	return (
		<S.Container>
			<S.Title>청구 잔여수량 충전</S.Title>
			<S.SubTitleBox>
				<S.SubTitle>
					<span>현재보유수량</span>
					<p>100건</p>
				</S.SubTitle>
				<S.SubTitle>
					<span>충전 후 잔여수량</span>
					<p className="active">{eduData ? 100 + eduData?.edu : 100}건</p>
				</S.SubTitle>
			</S.SubTitleBox>
			<S.AddBillBox>
				{EduDatas.map((edu) => (
					<S.AddCountBtn onClick={handleClickBtn(edu)} key={edu.id} $isActive={edu.id === eduData?.id}>
						<span>{formatNumber(edu.edu)}건</span>
						{formatNumber(edu.price)}원
					</S.AddCountBtn>
				))}
			</S.AddBillBox>
			<S.BtnContainer>
				<PrimaryButton text="충전" width="49%" isFill />
				<PrimaryButton text="취소" width="49%" onClick={closePopup} />
			</S.BtnContainer>
		</S.Container>
	);
};

export default AddBillCountPopup;
