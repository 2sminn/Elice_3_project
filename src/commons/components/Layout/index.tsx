import { ReactNode } from 'react';
import GlobalStyle from '../../styles/GlobalStyles';
import ReactQueryProvider from '../ReactQueryProvider';
import { ThemeProvider } from 'styled-components';
import theme from '../../styles/theme';

interface LayoutProps {
	children: ReactNode;
}

const Layout = ({ children }: LayoutProps) => {
	return (
		<ReactQueryProvider>
			<ThemeProvider theme={theme}>
				<GlobalStyle />
				<div className="wrap">
					<div className="container">{children}</div>
				</div>
			</ThemeProvider>
		</ReactQueryProvider>
	);
};

export default Layout;
