import { FaSpinner } from 'react-icons/fa';
import styled from 'styled-components';

export const Container = styled.div`
	flex: 1;
	padding: 20px;
`;

export const PageTitle = styled.h2`
	font-size: ${({ theme }) => theme.textSize.large};
	font-weight: 700;
	color: #333;
	margin-bottom: 30px;
`;

interface TableTitleListProps {
	width: string;
	$isTitle?: boolean;
}

export const TableList = styled.li<TableTitleListProps>`
	width: ${({ width }) => width};
	font-size: ${({ theme }) => theme.textSize.little};
	font-weight: ${({ $isTitle }) => ($isTitle ? '700' : '500')};
	color: #000;
`;

export const TableTitleBox = styled.ul`
	width: 100%;
	display: flex;
	text-align: center;
	margin-bottom: 20px;
`;

export const TableContentContainer = styled.div`
	display: flex;
	flex-direction: column;
`;

export const TableContentBox = styled.ul`
	width: 100%;
	display: flex;
	align-items: center;
	padding: 20px 0;
	text-align: center;

	&:not(:last-child) {
		border-bottom: 1px solid #ddd;
	}
`;

export const LoadingSpiner = styled(FaSpinner)`
	@keyframes spin {
		0% {
			transform: rotate(0deg);
		}
		100% {
			transform: rotate(360deg);
		}
	}
	animation: spin 1s linear infinite;
	font-size: 18px;
	display: block;
	margin: 0 auto;
`;

export const WarningMessage = styled.p`
	font-size: 18px;
	font-weight: 500;
	color: #000;
	text-align: center;
`;
