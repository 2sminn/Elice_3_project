import styled from 'styled-components';

export const StorageContainer = styled.div`
	width: 100%;
	padding: 20px;
`;

export const TabBox = styled.ul`
	width: 100%;
	border-bottom: ${({ theme }) => `2px solid ${theme.colors.primary}`};
	display: flex;
	gap: 2px;
`;

interface TabMenuProps {
	$active?: boolean;
}

export const TabMenu = styled.li<TabMenuProps>`
	width: 150px;
	border-top-left-radius: ${({ theme }) => theme.radius.small};
	border-top-right-radius: ${({ theme }) => theme.radius.small};
	background-color: ${({ $active, theme }) => ($active ? theme.colors.primary : '#fff')};
	padding: 5px 10px;
	font-size: ${({ theme }) => theme.textSize.little};
	font-weight: ${({ $active }) => ($active ? '700' : '400')};
	color: ${({ $active }) => ($active ? '#fff' : '#000')};
	border-bottom: ${({ $active, theme }) => ($active ? `1px solid ${theme.colors.primary}` : '1px solid #fff')};
	cursor: pointer;

	&:hover {
		background-color: ${({ theme }) => theme.colors.primary};
		color: #fff;
		font-weight: 700;
	}

	&.active {
		background-color: ${({ theme }) => theme.colors.primary};
		color: #fff;
		font-weight: 700;
	}
`;

export const SearchContainer = styled.div`
	width: 100%;
	margin-top: 20px;
	overflow: hidden;
	border-radius: ${({ theme }) => theme.radius.small};
`;

export const SearchTitle = styled.div`
	width: 100%;
	padding: 7px 30px;
	display: flex;
	align-items: center;
	gap: 3px;
	background-color: ${({ theme }) => theme.colors.primary};

	h3 {
		font-size: ${({ theme }) => theme.textSize.medium};
		font-weight: 500;
		color: #fff;
		padding-top: 2px;
	}
`;

export const SearchBox = styled.form`
	width: 100%;
	padding: 30px 10px;
	background-color: #fff;
`;

export const StorageTable = styled.div`
	width: 100%;
	margin-top: 25px;
	padding: 20px;
	background-color: #fff;
	border-radius: ${({ theme }) => theme.radius.small};
`;

export const SelectTopBox = styled.div`
	width: 100%;
	display: flex;
	gap: 15px;

	& > select {
		flex: 1;
	}
	& > input {
		flex: 2;
	}
	margin-bottom: 10px;
`;
