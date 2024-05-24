import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Layout from '../Layout';
import MainPage from '../../pages/main';
import Login from '../../pages/login';

const RouterComponent = () => {
	return (
		<Router>
			<Routes>
				<Route element={<Layout />}>
					{/* 레이아웃이 들어가는 페이지 */}
					<Route path="/" element={<MainPage />} />
				</Route>
				<Route path="login" element={<Login />} />
			</Routes>
		</Router>
	);
};

export default RouterComponent;
