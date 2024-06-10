import { Navigate, Outlet } from 'react-router-dom';
import { getAccessToken } from '../../stores/tokenStore';

const PublicRoute = () => {
	const accessToken = getAccessToken();

	return accessToken ? <Navigate to="/" /> : <Outlet />;
};

export default PublicRoute;
