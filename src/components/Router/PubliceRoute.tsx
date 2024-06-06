import { Navigate, Outlet } from 'react-router-dom';
import { useTokenStore } from '../../stores/tokenStore';

const PublicRoute = () => {
	const { accessToken } = useTokenStore();

	return accessToken ? <Navigate to="/" /> : <Outlet />;
};

export default PublicRoute;
