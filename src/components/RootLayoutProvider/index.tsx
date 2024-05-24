import { ThemeProvider } from 'styled-components';
import ReactQueryProvider from '../ReactQueryProvider';
import RouterComponent from '../Router';
import theme from '../../styles/theme';
import PopupContainer from '../popups/PopupContainer';

const RootLayoutProvider = () => {
	return (
		<ReactQueryProvider>
			<ThemeProvider theme={theme}>
				<RouterComponent />
				<PopupContainer />
			</ThemeProvider>
		</ReactQueryProvider>
	);
};

export default RootLayoutProvider;
