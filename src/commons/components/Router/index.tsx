import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import MainPage from '../../../pages/main';

const RouterComponent = () => {
	return (
		<Router>
			<Routes>
				<Route path="/" element={<MainPage />} />
			</Routes>
		</Router>
	);
};

export default RouterComponent;
