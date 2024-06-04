import { useNavigate } from 'react-router-dom';
import PrimaryButton from '../../components/buttons/PrimaryButton';
import { Container, PageTitle } from '../../styles/commonStyle';
import * as S from './style';
import { TabBox, TabMenu } from '../storage/style';

const Mypage = () => {
	const navigate = useNavigate();

	const goToProfileEdit = () => {
		navigate('/edit-profile');
	};

	return (
		<Container>
			<PageTitle>마이페이지</PageTitle>
			<S.MypageContainer>
				<S.ProfileContainer>
					<S.ProfileImg></S.ProfileImg>
					<S.ProfileName>에듀학원</S.ProfileName>
				</S.ProfileContainer>
				<S.ProfileInfoContainer>
					<S.ProfileInfoBox>
						<h4>원생 수:</h4>
						<p>10명</p>
					</S.ProfileInfoBox>
					<S.ProfileInfoBox>
						<h4>과목 수:</h4>
						<p>10명</p>
					</S.ProfileInfoBox>
					<S.ProfileInfoBox>
						<h4>정산예정금액:</h4>
						<p>340,000,000원</p>
					</S.ProfileInfoBox>
				</S.ProfileInfoContainer>
				<S.ProfileBtnContainer>
					<PrimaryButton text="정산 요청" width="200px" />
					<PrimaryButton text="회원정보수정" width="200px" onClick={goToProfileEdit} />
				</S.ProfileBtnContainer>
			</S.MypageContainer>
			<TabBox>
				<TabMenu $active={true}>충전내역</TabMenu>
				<TabMenu>사용내역</TabMenu>
			</TabBox>
			<S.MypageContainer></S.MypageContainer>
		</Container>
	);
};

export default Mypage;
