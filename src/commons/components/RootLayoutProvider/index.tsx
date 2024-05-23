import { ThemeProvider } from 'styled-components';
import ReactQueryProvider from '../ReactQueryProvider';
import theme from '../../styles/theme';
import RouterComponent from '../Router';

const RootLayoutProvider = () => {
	return (
		<ReactQueryProvider>
			<ThemeProvider theme={theme}>
				<RouterComponent />
			</ThemeProvider>
		</ReactQueryProvider>
	);
};

export default RootLayoutProvider;
