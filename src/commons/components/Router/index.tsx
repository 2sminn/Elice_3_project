import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import MainPage from '../../../pages/main';
import Layout from '../Layout';
import Login from '../../../pages/login';

const RouterComponent = () => {
	return (
		<Router>
			<Routes>
				레이아웃
				<Route element={<Layout />}>
					<Route path="/" element={<MainPage />} />
				</Route>
				<Route path="login" element={<Login />} />
			</Routes>
		</Router>
	);
};

export default RouterComponent;
