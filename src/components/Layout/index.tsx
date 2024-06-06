import { useTokenStore } from '../../stores/tokenStore';
import Header from '../Header';
import { Navigate, Outlet } from 'react-router-dom';

const Layout = () => {
	const { accessToken } = useTokenStore();

	return accessToken ? (
		<>
			<div className="wrap">
				<div className="container">
					<Header />
					<div id="contents">
						<Outlet />
					</div>
				</div>
			</div>
		</>
	) : (
		<Navigate to="/login" />
	);
};

export default Layout;
