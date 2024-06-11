import { useState } from 'react';
import usePopup from '../../../../hooks/usePopup';
import { formatNumber } from '../../../../utils/formatNumber';
import * as S from './style';
import PrimaryButton from '../../../buttons/PrimaryButton';
import { errorAlert } from '../../../../utils/alert';
import { useAddPointMutation } from '../../hooks/useAddPointMutation';
import { useGetUserQuery } from '../../hooks/useGetUserQuery';

const EduDatas = [
	{
		id: 1,
		edu: 20,
		price: 100,
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

declare global {
	interface Window {
		IMP: any;
	}
}

const IMP_CODE = import.meta.env.VITE_IMP_CODE;

const AddBillCountPopup = () => {
	const { closePopup } = usePopup();

	const [eduData, setEduData] = useState<eduDataType | null>(null);

	const { mutate: addPointMutate } = useAddPointMutation();
	const { data: userInfo } = useGetUserQuery();

	const requestPay = () => {
		const { IMP } = window;
		IMP.init(IMP_CODE); // 'your_imp_uid'를 실제 가맹점 식별코드로 변경

		IMP.request_pay(
			{
				pg: 'html5_inicis', // PG사
				pay_method: 'card', // 결제수단
				merchant_uid: `merchant_${new Date().getTime()}`, // 주문번호
				name: '학원비 결제', // 주문명
				amount: eduData && eduData.price, // 금액
				buyer_email: userInfo?.academyEmail,
				buyer_name: userInfo?.username,
				buyer_tel: userInfo?.phoneNumber,
				buyer_addr: userInfo?.address,
				buyer_postcode: userInfo?.zipCode,
			},
			(rsp: any) => {
				if (rsp.success) {
					console.log(rsp);
					const data = {
						user_id: String(userInfo?.userId),
						point: String(eduData?.edu),
						imp_uid: rsp.imp_uid,
					};

					addPointMutate(data);
					// 결제 성공 시 로직 처리
				} else {
					errorAlert(`결제에 실패하였습니다.${rsp.error_msg}`);
					// 결제 실패 시 로직 처리
					console.log(rsp);
				}
			},
		);
	};

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
				<PrimaryButton text="충전" width="49%" isFill onClick={requestPay} />
				<PrimaryButton text="취소" width="49%" onClick={closePopup} />
			</S.BtnContainer>
		</S.Container>
	);
};

export default AddBillCountPopup;
