import { ReactNode } from 'react';
import GlobalStyle from '../../styles/GlobalStyles';
import ReactQueryProvider from '../ReactQueryProvider';

interface LayoutProps {
	children: ReactNode;
}

const Layout = ({ children }: LayoutProps) => {
	return (
		<ReactQueryProvider>
			<GlobalStyle />
			{children}
		</ReactQueryProvider>
	);
};

export default Layout;
