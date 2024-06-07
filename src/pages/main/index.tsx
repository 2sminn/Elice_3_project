import { Container } from '../../styles/commonStyle';
import BillBox from './components/BillBox';
import LineChart from './components/SalesLineChart';
import * as S from './style';

const MainPage = () => {
	return (
		<Container>
			<S.Introduce>
				<span>조정택</span>님, 안녕하세요.
			</S.Introduce>
			<S.ContentContainer>
				<S.MiddleContent>
					<S.SalesChartContainer>
						<h4>5월 매출</h4>
						<h5>총 매출 합계</h5>
						<p>120,000,000</p>
						<LineChart />
					</S.SalesChartContainer>
					<S.BillContainer>
						<BillBox title="청구서 발송 내역" content1="발송건" content2="청구금액" count={232} price={256000000} />
						<BillBox title="청구서 수납 내역" content1="수납건" content2="수납금액" count={195} price={214000000} />
					</S.BillContainer>
				</S.MiddleContent>
			</S.ContentContainer>
		</Container>
	);
};

export default MainPage;
