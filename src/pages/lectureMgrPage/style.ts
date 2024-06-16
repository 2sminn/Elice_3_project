import styled from 'styled-components';

export const Container = styled.div`
	padding: 20px;
`;

export const Title = styled.h1`
	font-size: ${(props) => props.theme.textSize.large};
	margin-bottom: 20px;
	color: ${(props) => props.theme.colors.text};

	@media (max-width: 768px) {
		font-size: ${(props) => props.theme.textSize.medium};
	}
`;

export const SearchSection = styled.section`
	margin-bottom: 20px;
`;

export const SearchTitle = styled.h2`
	font-size: ${(props) => props.theme.textSize.medium};
	margin-bottom: 10px;
	color: ${(props) => props.theme.colors.text};

	@media (max-width: 768px) {
		font-size: ${(props) => props.theme.textSize.little};
	}
`;

export const SearchForm = styled.form`
	display: flex;
	flex-direction: column;
	gap: 10px;
`;

export const InputGroup = styled.div`
	display: flex;
	gap: 10px;

	@media (max-width: 768px) {
		flex-direction: column;
	}
`;

export const Input = styled.input`
	flex: 1;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: ${(props) => props.theme.radius.small};
	font-size: ${(props) => props.theme.textSize.little};
`;

export const Select = styled.select`
	flex: 1;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: ${(props) => props.theme.radius.small};
	font-size: ${(props) => props.theme.textSize.little};
`;

export const Button = styled.button`
	padding: 10px 20px;
	background-color: ${(props) => props.theme.colors.primary};
	color: ${(props) => props.theme.colors.btnText};
	border: none;
	border-radius: ${(props) => props.theme.radius.small};
	cursor: pointer;
	font-size: ${(props) => props.theme.textSize.little};

	@media (max-width: 768px) {
		width: 100%;
	}
`;

export const ButtonGroup = styled.div`
	display: flex;
	justify-content: flex-end;
	gap: 10px;
	margin-bottom: 20px;

	@media (max-width: 768px) {
		flex-direction: column;
		align-items: stretch;
	}
`;

export const Table = styled.table`
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
	background-color: #fff;

	@media (max-width: 768px) {
		font-size: ${(props) => props.theme.textSize.small};
	}
`;

export const Th = styled.th`
	padding: 10px;
	border: 1px solid #ddd;
	font-size: ${(props) => props.theme.textSize.little};
	text-align: center;

	@media (max-width: 768px) {
		font-size: ${(props) => props.theme.textSize.small};
	}
`;

export const Td = styled.td`
	padding: 10px;
	border: 1px solid #ddd;
	font-size: ${(props) => props.theme.textSize.little};
	text-align: center;

	@media (max-width: 768px) {
		font-size: ${(props) => props.theme.textSize.small};
	}
`;
