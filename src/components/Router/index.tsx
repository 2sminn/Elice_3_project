import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Layout from '../Layout';
import { LAYOUT_ROUTE_ARR, ROUTE_ARR } from '../../constants/routeList';

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
				{ROUTE_ARR.map((route) => (
					<Route key={route.id} path={route.path} element={route.element} />
				))}
			</Routes>
		</Router>
	);
};

export default RouterComponent;
