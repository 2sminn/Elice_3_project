import * as S from './style';
import logo from '/assets/images/logo.svg';
import settingImg from './assets/setting.svg';
import logout from './assets/logout.svg';
import { Link, useNavigate } from 'react-router-dom';
import usePopup from '../../hooks/usePopup';
import AddBillCountPopup from './components/AddBillCountPopup';
import BillPopup from '../popups/BillPopup';
import PrimaryButton from '../buttons/PrimaryButton';
import { MENU_ARR } from '../../constants/routeList';
import { useTokenStore } from '../../stores/tokenStore';
import { successAlert } from '../../utils/alert';
import { useGetUserQuery } from './hooks/useGetUserQuery';

const Header = () => {
	const { data: userInfo } = useGetUserQuery();
	console.log(userInfo);

	const { openPopup } = usePopup();
	const { clearTokens } = useTokenStore();
	const navigate = useNavigate();

	const handleLogout = () => {
		clearTokens();
		navigate('/login');
		successAlert('로그아웃 되었습니다.');
	};

	return (
		<S.Container>
			<S.HeaderTop>
				<S.LogoBox>
					<img src={logo} alt="" />
				</S.LogoBox>
				<S.AcademyInfoBox>
					<h2>{userInfo?.academyName}</h2>
					<Link to="/edit-profile">
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
			<S.LogoutBtn onClick={handleLogout}>
				<img src={logout} alt="로그아웃 버튼" />
			</S.LogoutBtn>
		</S.Container>
	);
};

export default Header;
