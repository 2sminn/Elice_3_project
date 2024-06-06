import PrimaryButton from '../../components/buttons/PrimaryButton';
import { formatNumber } from '../../utils/formatNumber';
import * as S from './style';

const BillPage = () => {
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
							<S.BillPrice>{formatNumber(10000000)}</S.BillPrice>
						</S.InfoContainer>
					</S.InfoBox>
					<PrimaryButton text="결제하기" isFill />
				</S.BillBox>
			</S.Container>
		</S.Wrap>
	);
};

export default BillPage;
