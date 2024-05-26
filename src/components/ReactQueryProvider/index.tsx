import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { ReactQueryDevtools } from '@tanstack/react-query-devtools';
import { ReactNode, useState } from 'react';

interface ReactQueryProviderProps {
	children: ReactNode;
}

const ReactQueryProvider = ({ children }: ReactQueryProviderProps) => {
	const [queryClient] = useState(new QueryClient());

	return (
		<QueryClientProvider client={queryClient}>
			{children}
			<ReactQueryDevtools />
		</QueryClientProvider>
	);
};

export default ReactQueryProvider;
