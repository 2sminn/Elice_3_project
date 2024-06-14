import { MouseEventHandler, ReactNode } from 'react';
import { Container } from './style';

interface BackdropProps {
	children: ReactNode | null;
	isView: boolean;
	handleClick: MouseEventHandler<HTMLDivElement>;
}

const Backdrop = ({ children, isView }: BackdropProps) => {
	return (
		<Container initial={{ opacity: 0 }} animate={{ opacity: isView ? 1 : 0 }} transition={{ duration: 0.5 }}>
			{children}
		</Container>
	);
};

export default Backdrop;
