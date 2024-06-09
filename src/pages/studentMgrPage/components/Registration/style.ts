import styled from 'styled-components';

export const Container = styled.div`
	width: 700px;
	height: 80vh;
	overflow-y: auto;
	padding: 30px;
	background-color: #fff;
	border-radius: 6px;
`;

export const Title = styled.h3`
	font-size: ${({ theme }) => theme.textSize.large};
	font-weight: 600;
	color: #000;
	margin-bottom: 20px;
	display: flex;
	justify-content: space-between;
	align-items: center;
`;

export const Form = styled.form`
	width: 100%;
	display: flex;
	flex-direction: column;
`;

export const Input = styled.input`
	margin-bottom: 10px;
	padding: 8px;
	font-size: 16px;
	border: 1px solid #ccc;
	border-radius: ${({ theme }) => theme.radius.small};
`;

export const Button = styled.button`
	padding: 10px;
	font-size: 16px;
	background-color: #007bff;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	display: flex;
	justify-content: center;
	align-items: center;

	&:hover {
		background-color: #0056b3;
	}
`;

export const SearchSection = styled.section`
	margin-bottom: 20px;
`;

export const SearchTitle = styled.h2`
	font-size: ${({ theme }) => theme.textSize.large};
	font-weight: 600;
	color: #000;
	margin-bottom: 10px;
`;

export const SearchForm = styled.form`
	display: flex;
	align-items: center;
`;

export const InputGroup = styled.div`
	display: flex;
	align-items: center;
	margin-right: 10px;
`;

export const Select = styled.select`
	margin-right: 10px;
	padding: 8px;
	font-size: 16px;
	border: 1px solid #ccc;
	border-radius: ${({ theme }) => theme.radius.small};
`;

export const ButtonGroup = styled.div`
	display: flex;
	justify-content: space-between;
	margin-top: 20px;
`;

export const Table = styled.table`
	width: 100%;
	border-collapse: collapse;
`;

export const Th = styled.th`
	border: 1px solid #ddd;
	padding: 8px;
	background-color: #f2f2f2;
`;

export const Td = styled.td`
	border: 1px solid #ddd;
	padding: 8px;
`;

export const PopupContainer = styled.div`
	width: 500px;
	background-color: #fff;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	display: flex;
	flex-direction: column;
`;

export const PopupTitle = styled.h3`
	font-size: ${({ theme }) => theme.textSize.large};
	font-weight: 600;
	margin-bottom: 20px;
	color: #333;
`;

export const PopupForm = styled.form`
	display: flex;
	flex-direction: column;
`;

export const PopupInput = styled.input`
	margin-bottom: 15px;
	padding: 10px;
	font-size: 16px;
	border: 1px solid #ccc;
	border-radius: ${({ theme }) => theme.radius.small};
`;

export const PopupButtonContainer = styled.div`
	display: flex;
	justify-content: flex-end;
	gap: 10px;
`;

export const PopupButton = styled.button`
	padding: 10px;
	font-size: 16px;
	background-color: ${({ theme }) => theme.colors.primary};
	color: white;
	border: none;
	border-radius: ${({ theme }) => theme.radius.small};
	cursor: pointer;

	&:hover {
		background-color: ${({ theme }) => theme.colors.accent};
	}
`;
