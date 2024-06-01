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
