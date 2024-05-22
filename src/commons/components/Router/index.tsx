import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import MainPage from '../../../pages/main';
import Layout from '../Layout';

const RouterComponent = () => {
	return (
		<Router>
			<Layout>
				<Routes>
					<Route path="/" element={<MainPage />} />
				</Routes>
			</Layout>
		</Router>
	);
};

export default RouterComponent;
