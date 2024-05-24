import * as S from './style';
import logo from './assets/logo.jpeg';
import settingImg from './assets/setting.svg';
import logout from './assets/logout.svg';
import { Link } from 'react-router-dom';
import usePopup from '../../hooks/usePopup';
import AddBillCountPopup from './components/AddBillCountPopup';

const Header = () => {
	const { openPopup } = usePopup();

	return (
		<S.Container>
			<S.HeaderTop>
				<S.LogoBox>
					<img src={logo} alt="" />
				</S.LogoBox>
				<S.AcademyInfoBox>
					<h2>에듀학원</h2>
					<Link to="/123">
						<img src={settingImg} alt="" />
					</Link>
				</S.AcademyInfoBox>
				<S.PointBox>
					<S.PointContainer>
						<p>잔여수량</p>
						<p>
							<span>1,000</span> 건
						</p>
					</S.PointContainer>
					<S.AddPointBtn onClick={() => openPopup(<AddBillCountPopup />)}>충전하기</S.AddPointBtn>
				</S.PointBox>
				<S.MenuContainer>
					<S.Menu>
						<li>
							<Link to="/">학원비 청구</Link>
						</li>
						<li>
							<Link to="/">원생관리</Link>
						</li>
						<li>
							<Link to="/">매출관리</Link>
						</li>
						<li>
							<Link to="/">정산</Link>
						</li>
					</S.Menu>
				</S.MenuContainer>
			</S.HeaderTop>
			<S.LogoutBtn>
				<img src={logout} alt="로그아웃 버튼" />
			</S.LogoutBtn>
		</S.Container>
	);
};

export default Header;
