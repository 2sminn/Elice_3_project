import GlobalStyle from '../../styles/GlobalStyles';
import Header from '../Header';
import { Outlet } from 'react-router-dom';

const Layout = () => {
	return (
		<>
			<GlobalStyle />
			<div className="wrap">
				<div className="container">
					<Header />
					<div id="contents">
						<Outlet />
					</div>
				</div>
			</div>
		</>
	);
};

export default Layout;
