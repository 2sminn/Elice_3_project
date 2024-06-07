import { usePaymentErrorStore, usePaymentSuccessStore } from '../../../stores/paymentStore';
import { formatNumber } from '../../../utils/formatNumber';
import * as S from './style';

const BillCompletePage = ({ isSuccess }: { isSuccess?: boolean }) => {
	const { successRes } = usePaymentSuccessStore();
	const { errorRes } = usePaymentErrorStore();

	console.log(successRes);
	console.log(errorRes);

	return (
		<S.Wrap>
			<S.TopWrap $isSuccess={isSuccess}>
				<S.TopContainer>
					<S.TopTitle>
						{isSuccess ? (
							<>
								정상적으로 <br />
								납부되었습니다.
							</>
						) : (
							<>
								결제 <br />
								실패하였습니다.
							</>
						)}
					</S.TopTitle>
				</S.TopContainer>
			</S.TopWrap>
			<S.ContentBox>
				<S.ContentContainer>
					<S.ContentTitle>{isSuccess ? '납부내역' : '납부실패'}</S.ContentTitle>
					<S.InfoBox>
						<S.InfoContainer>
							<h4>발급처</h4>
							<p>에듀페이</p>
						</S.InfoContainer>
						<S.InfoContainer>
							<h4>청구내역</h4>
							<p>학원비</p>
						</S.InfoContainer>
						<S.InfoContainer>
							<h4>{isSuccess ? '청구금액(VAT 포함)' : '사유'}</h4>
							<p>{isSuccess ? <span>{formatNumber(100000000)}원</span> : errorRes?.error_msg}</p>
						</S.InfoContainer>
					</S.InfoBox>
				</S.ContentContainer>
			</S.ContentBox>
		</S.Wrap>
	);
};

export default BillCompletePage;
