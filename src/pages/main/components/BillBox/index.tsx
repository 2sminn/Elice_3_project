import PrimaryButton from '../../../../components/buttons/PrimaryButton';
import { formatNumber } from '../../../../utils/formatNumber';
import * as S from './style';

interface BillBoxProps {
	title: string;
	content1: string;
	content2: string;
	price: number;
	count: number;
}

const BillBox = ({ title, content1, content2, price, count }: BillBoxProps) => {
	return (
		<S.Container>
			<S.TitleBox>
				<S.BillTitle>{title}</S.BillTitle>
			</S.TitleBox>
			<S.BillContentContainer>
				<S.BillContentBox>
					<h5>{content1}</h5>
					<p>{formatNumber(count)}건</p>
				</S.BillContentBox>
				<S.BillContentBox>
					<h5>{content2}</h5>
					<p>{formatNumber(price)}원</p>
				</S.BillContentBox>
			</S.BillContentContainer>
			<S.BillDetailLinkBtn to="/">
				<PrimaryButton text="자세히 보러 가기" fill textSize="15px" />
			</S.BillDetailLinkBtn>
		</S.Container>
	);
};

export default BillBox;
