import React from 'react';
import styled from 'styled-components';

const Container = styled.div`
	padding: 20px;
`;

const Title = styled.h1`
	font-size: ${(props) => props.theme.textSize.large};
	margin-bottom: 20px;
	color: ${(props) => props.theme.colors.text};

	@media (max-width: 768px) {
		font-size: ${(props) => props.theme.textSize.medium};
	}
`;

const SearchSection = styled.section`
	margin-bottom: 20px;
`;

const SearchTitle = styled.h2`
	font-size: ${(props) => props.theme.textSize.medium};
	margin-bottom: 10px;
	color: ${(props) => props.theme.colors.text};

	@media (max-width: 768px) {
		font-size: ${(props) => props.theme.textSize.little};
	}
`;

const SearchForm = styled.form`
	display: flex;
	flex-direction: column;
	gap: 10px;
`;

const InputGroup = styled.div`
	display: flex;
	gap: 10px;

	@media (max-width: 768px) {
		flex-direction: column;
	}
`;

const Input = styled.input`
	flex: 1;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: ${(props) => props.theme.radius.small};
	font-size: ${(props) => props.theme.textSize.little};
`;

const Select = styled.select`
	flex: 1;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: ${(props) => props.theme.radius.small};
	font-size: ${(props) => props.theme.textSize.little};
`;

const Button = styled.button`
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

const ButtonGroup = styled.div`
	display: flex;
	justify-content: flex-end;
	gap: 10px;
	margin-bottom: 20px;

	@media (max-width: 768px) {
		flex-direction: column;
		align-items: stretch;
	}
`;

const Table = styled.table`
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
	background-color: #fff;

	@media (max-width: 768px) {
		font-size: ${(props) => props.theme.textSize.small};
	}
`;

const Th = styled.th`
	padding: 10px;
	border: 1px solid #ddd;
	font-size: ${(props) => props.theme.textSize.little};
	text-align: center;

	@media (max-width: 768px) {
		font-size: ${(props) => props.theme.textSize.small};
	}
`;

const Td = styled.td`
	padding: 10px;
	border: 1px solid #ddd;
	font-size: ${(props) => props.theme.textSize.little};
	text-align: center;

	@media (max-width: 768px) {
		font-size: ${(props) => props.theme.textSize.small};
	}
`;

const StudentMgrPage = () => {
	return (
		<Container>
			<Title>원생관리</Title>

			<SearchSection>
				<SearchTitle>통합검색</SearchTitle>
				<SearchForm>
					<InputGroup>
						<Select>
							<option>원생명</option>
							{/* Other options */}
						</Select>
						<Input placeholder="검색어 입력" />
						<Button type="submit">검색</Button>
					</InputGroup>
				</SearchForm>
			</SearchSection>

			<SearchSection>
				<SearchTitle>조건검색</SearchTitle>
				<SearchForm>
					<InputGroup>
						<Input placeholder="원생명 입력" />
						<Input placeholder="학교명 입력" />
						<Input placeholder="학년명 입력" />
					</InputGroup>
					<InputGroup>
						<Select>
							<option>그룹선택</option>
							{/* Other options */}
						</Select>
						<Select>
							<option>반선택</option>
							{/* Other options */}
						</Select>
						<Select>
							<option>주담임선택</option>
							{/* Other options */}
						</Select>
					</InputGroup>
					<Button type="submit">검색</Button>
				</SearchForm>
			</SearchSection>

			<ButtonGroup>
				<Button>청구서발송</Button>
				<Button>삭제</Button>
				<Button>신규원생등록</Button>
			</ButtonGroup>

			<Table>
				<thead>
					<tr>
						<Th>
							<input type="checkbox" />
						</Th>
						<Th>원생명</Th>
						<Th>수강반 정보</Th>
						<Th>수납정보</Th>
						<Th>연락처</Th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<Td>
							<input type="checkbox" />
						</Td>
						<Td>홍길동</Td>
						<Td>초등부 &gt; 초등 5반</Td>
						<Td>미납 0건/예정 1건</Td>
						<Td>010-0000-0000</Td>
					</tr>
					{/* Other rows */}
				</tbody>
			</Table>
		</Container>
	);
};

export default StudentMgrPage;
