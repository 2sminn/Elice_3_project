import * as S from './style';
import logo from '/assets/images/logo.svg';
import settingImg from './assets/setting.svg';
import logout from './assets/logout.svg';
import { Link } from 'react-router-dom';
import usePopup from '../../hooks/usePopup';
import AddBillCountPopup from './components/AddBillCountPopup';
import BillPopup from '../popups/BillPopup';
import PrimaryButton from '../buttons/PrimaryButton';
import { MENU_ARR } from '../../constants/routeList';

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
					<Link to="/">
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
					<PrimaryButton text="충전하기" isFill onClick={() => openPopup(<AddBillCountPopup />)} />
					<PrimaryButton text="청구서 발급" onClick={() => openPopup(<BillPopup />)} />
				</S.PointBox>
				<S.MenuContainer>
					<S.Menu>
						{MENU_ARR.map((route) => (
							<li key={route.id}>
								<Link to={route.link}>{route.title}</Link>
							</li>
						))}
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
