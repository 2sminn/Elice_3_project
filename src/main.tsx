import ReactDOM from 'react-dom/client';
import Layout from './commons/components/Layout/index.tsx';
import RouterComponent from './commons/components/Router/index.tsx';

ReactDOM.createRoot(document.getElementById('root')!).render(
	<Layout>
		<RouterComponent />
	</Layout>,
);
