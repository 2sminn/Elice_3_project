import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Layout from '../Layout';
import { LAYOUT_ROUTE_ARR, ROUTE_ARR } from '../../constants/routeList';
import PublicRoute from './PubliceRoute';
import NotFound from './NotFound';
import BillPage from '../../pages/bill';
import BillCompletePage from '../../pages/bill/complete';

const RouterComponent = () => {
	return (
		<Router>
			<Routes>
				<Route element={<Layout />}>
					{/* 레이아웃이 들어가는 페이지 */}
					{LAYOUT_ROUTE_ARR.map((route) => (
						<Route key={route.id} path={route.path} element={route.element} />
					))}
				</Route>
				<Route element={<PublicRoute />}>
					{ROUTE_ARR.map((route) => (
						<Route key={route.id} path={route.path} element={route.element} />
					))}
				</Route>
				<Route path="/bill/:id" element={<BillPage />} />
				<Route path="/bill/success" element={<BillCompletePage isSuccess />} />
				<Route path="/bill/error" element={<BillCompletePage />} />
				<Route path="*" element={<NotFound />} />
			</Routes>
		</Router>
	);
};

export default RouterComponent;
