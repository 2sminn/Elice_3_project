import { useNavigate } from 'react-router-dom';
import PrimaryButton from '../../components/buttons/PrimaryButton';
import {
	Container,
	PageTitle,
	TableContentBox,
	TableContentContainer,
	TableList,
	TableTitleBox,
} from '../../styles/commonStyle';
import * as S from './style';
import { TabBox, TabMenu } from '../storage/style';
import { Fragment, useEffect, useRef } from 'react';
import { useGetChargeHistoryQuery } from './hooks/useGetChargeHistoryQuery';
import { formatNumber } from '../../utils/formatNumber';
import profileImg from './assets/images/profile.jpeg';
import { formatDate } from '../../utils/formatDate';

const Mypage = () => {
	const navigate = useNavigate();

	const goToProfileEdit = () => {
		navigate('/edit-profile');
	};

	const { data: chargeHistoryDatas, fetchNextPage, hasNextPage, isFetching } = useGetChargeHistoryQuery();

	const observerRef = useRef(null);

	useEffect(() => {
		const observerOptions: IntersectionObserverInit = {
			root: null,
			rootMargin: '0px',
			threshold: 0.8,
		};

		const handleIntersection: IntersectionObserverCallback = (entries) => {
			entries.forEach((entry) => {
				if (entry.isIntersecting && hasNextPage && !isFetching) {
					fetchNextPage();
				}
			});
		};

		const observer = new IntersectionObserver(handleIntersection, observerOptions);

		if (observerRef.current) {
			observer.observe(observerRef.current);
		}

		return () => {
			if (observerRef.current) {
				observer.unobserve(observerRef.current);
			}
		};
	}, [fetchNextPage, hasNextPage, isFetching]);

	console.log(chargeHistoryDatas);
	return (
		<Container>
			<PageTitle>마이페이지</PageTitle>
			<S.MypageContainer>
				<S.ProfileContainer>
					<S.ProfileImg style={{ backgroundImage: `url(${profileImg})` }} />
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
			<S.MypageContainer>
				<TableTitleBox>
					<TableList $isTitle width="15%">
						날짜
					</TableList>
					<TableList $isTitle width="20%">
						충전개수
					</TableList>
					<TableList $isTitle width="40%">
						환불가능여부
					</TableList>
					<TableList $isTitle width="20%"></TableList>
				</TableTitleBox>
				<TableContentContainer>
					{chargeHistoryDatas?.pages.map((page, index) => (
						<Fragment key={index}>
							{page?.content?.map((data) => (
								<TableContentBox key={data.id}>
									<TableList width="15%">{formatDate(data.createdAt)}</TableList>
									<TableList width="20%">{`${formatNumber(data.point)}개`}</TableList>
									<TableList width="40%">O</TableList>
									<TableList width="20%">
										<PrimaryButton text="환불신청" width="90%" textSize="13px" isFill />
									</TableList>
								</TableContentBox>
							))}
						</Fragment>
					))}
					{isFetching && <div>Loading...</div>}
					<div ref={observerRef} style={{ height: '10px', background: 'transparent' }} />
				</TableContentContainer>
			</S.MypageContainer>
		</Container>
	);
};

export default Mypage;
