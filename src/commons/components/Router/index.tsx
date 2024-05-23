import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import MainPage from '../../../pages/main';
import Layout from '../Layout';
import Login from '../../../pages/login';

const RouterComponent = () => {
	return (
		<Router>
			<Routes>
				<Route
					path="/"
					element={
						<Layout>
							<MainPage />
						</Layout>
					}
				/>
				<Route path="login" element={<Login />} />
			</Routes>
		</Router>
	);
};

export default RouterComponent;
