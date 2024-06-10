import React, { useState } from 'react';
import {
	Container,
	Title,
	SearchSection,
	SearchTitle,
	SearchForm,
	InputGroup,
	Input,
	Select,
	Button,
	ButtonGroup,
	Table,
	Th,
	Td,
} from './style';

const lecturesData = [
	{
		name: '국어',
		teacher: '선생님1',
		fee: '200,000원',
		status: '종강',
		selected: false,
	},
	{
		name: '수학',
		teacher: '선생님2',
		fee: '150,000원',
		status: '수강',
		selected: false,
	},
];

const LectureMgrPage = () => {
	const [searchTerm, setSearchTerm] = useState('');
	const [searchField, setSearchField] = useState('name');
	const [selectAll, setSelectAll] = useState(false);
	const [lectures, setLectures] = useState(lecturesData);

	const handleSearchChange = (e) => setSearchTerm(e.target.value);

	const handleSearchFieldChange = (e) => setSearchField(e.target.value);

	const filterLectures = (lectures, searchTerm, searchField) => {
		return lectures.filter((lecture) => lecture[searchField].includes(searchTerm));
	};

	const handleSearchSubmit = (e) => {
		e.preventDefault();
		setLectures(filterLectures(lecturesData, searchTerm, searchField));
	};

	const handleDeleteClick = () => console.log('Delete Clicked');
	const handleAddNewClick = () => console.log('Add New Lecture Clicked');

	const handleSelectAllChange = (e) => {
		const { checked } = e.target;
		setSelectAll(checked);
		setLectures((prevLectures) => prevLectures.map((lecture) => ({ ...lecture, selected: checked })));
	};

	const handleSelectChange = (index) => (e) => {
		const { checked } = e.target;
		setLectures((prevLectures) => {
			const newLectures = [...prevLectures];
			newLectures[index].selected = checked;
			if (!checked) {
				setSelectAll(false);
			} else if (newLectures.every((lecture) => lecture.selected)) {
				setSelectAll(true);
			}
			return newLectures;
		});
	};

	return (
		<Container>
			<Title>강의관리</Title>

			<SearchSection>
				<SearchTitle>통합검색</SearchTitle>
				<SearchForm onSubmit={handleSearchSubmit}>
					<InputGroup>
						<Select value={searchField} onChange={handleSearchFieldChange}>
							<option value="name">강의명</option>
							<option value="status">반상태</option>
							<option value="teacher">담임명</option>
						</Select>
						<Input placeholder="검색어 입력" value={searchTerm} onChange={handleSearchChange} />
						<Button type="submit">검색</Button>
					</InputGroup>
				</SearchForm>
			</SearchSection>

			<ButtonGroup>
				<Button onClick={handleDeleteClick}>삭제</Button>
				<Button onClick={handleAddNewClick}>신규강의등록</Button>
			</ButtonGroup>

			<LectureTable
				lectures={lectures}
				selectAll={selectAll}
				onSelectAllChange={handleSelectAllChange}
				onSelectChange={handleSelectChange}
			/>
		</Container>
	);
};

const LectureTable = ({ lectures, selectAll, onSelectAllChange, onSelectChange }) => {
	return (
		<Table>
			<thead>
				<tr>
					<Th>
						<input type="checkbox" checked={selectAll} onChange={onSelectAllChange} />
					</Th>
					<Th>강의명</Th>
					<Th>담임</Th>
					<Th>수강료</Th>
					<Th>상태</Th>
				</tr>
			</thead>
			<tbody>
				{lectures.map((lecture, index) => (
					<tr key={index}>
						<Td>
							<input type="checkbox" checked={lecture.selected} onChange={onSelectChange(index)} />
						</Td>
						<Td>{lecture.name}</Td>
						<Td>{lecture.teacher}</Td>
						<Td>{lecture.fee}</Td>
						<Td>{lecture.status}</Td>
					</tr>
				))}
			</tbody>
		</Table>
	);
};

export default LectureMgrPage;
