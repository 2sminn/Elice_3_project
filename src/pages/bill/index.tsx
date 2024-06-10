import { useNavigate } from 'react-router-dom';
import PrimaryButton from '../../components/buttons/PrimaryButton';
import { formatNumber } from '../../utils/formatNumber';
import * as S from './style';
import { errorAlert, successAlert } from '../../utils/alert';
import { usePaymentErrorStore, usePaymentSuccessStore } from '../../stores/paymentStore';

declare global {
	interface Window {
		IMP: any;
	}
}

const IMP_CODE = import.meta.env.VITE_IMP_CODE;

const BillPage = () => {
	const navigate = useNavigate();
	const { setSuccessRes } = usePaymentSuccessStore();
	const { setErrorRes } = usePaymentErrorStore();

	const requestPay = () => {
		const { IMP } = window;
		IMP.init(IMP_CODE); // 'your_imp_uid'를 실제 가맹점 식별코드로 변경

		IMP.request_pay(
			{
				pg: 'html5_inicis', // PG사
				pay_method: 'card', // 결제수단
				merchant_uid: `merchant_${new Date().getTime()}`, // 주문번호
				name: '학원비 결제', // 주문명
				amount: 100, // 금액
				buyer_email: 'iamport@siot.do',
				buyer_name: '조정택',
				buyer_tel: '010-1234-5678',
				buyer_addr: '서울특별시 강남구 삼성동',
				buyer_postcode: '123-456',
			},
			(rsp: any) => {
				if (rsp.success) {
					successAlert('결제가 완료되었습니다.');
					// 결제 성공 시 로직 처리
					console.log(rsp);
					setSuccessRes(rsp);
					navigate('/bill/success');
				} else {
					errorAlert(`결제에 실패하였습니다.${rsp.error_msg}`);
					// 결제 실패 시 로직 처리
					console.log(rsp);
					setErrorRes(rsp);
					navigate('/bill/error');
				}
			},
		);
	};

	return (
		<S.Wrap>
			<S.Container>
				<S.TitleContainer>
					<S.BillTitle>
						청구서
						<br /> 납부하기
					</S.BillTitle>
					<img src="/assets/images/logo.svg" alt="" />
				</S.TitleContainer>
				<S.BillBox>
					<S.BoxTitle>청구내역</S.BoxTitle>
					<S.InfoBox>
						<S.InfoContainer>
							<h4>발급처</h4>
							<p>에듀페이</p>
						</S.InfoContainer>
						<S.InfoContainer>
							<h4>발급사유</h4>
							<p>학원비</p>
						</S.InfoContainer>
						<S.InfoContainer>
							<h4>청구금액</h4>
							<S.BillPrice>{formatNumber(10000000)}원</S.BillPrice>
						</S.InfoContainer>
					</S.InfoBox>
					<PrimaryButton text="결제하기" isFill onClick={requestPay} />
				</S.BillBox>
			</S.Container>
		</S.Wrap>
	);
};

export default BillPage;
