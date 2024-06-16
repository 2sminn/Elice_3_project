import React, { useEffect, useState } from 'react';
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
import useStudentStore from '../../stores/useStudentStore';
import { StudentType } from './api';
import usePopup from '../../hooks/usePopup';
import StudentRegistrationPopup from './components/Registration';
import StudentDetailPopup from './components/Detail';

const StudentMgrPage = () => {
	const { filteredStudents, fetchStudents, deleteStudent, searchStudents, selectStudent, fetchStudent } =
		useStudentStore();
	const [searchTerm, setSearchTerm] = useState<string>('');
	const [searchField, setSearchField] = useState<keyof StudentType>('studentName');
	const [selectAll, setSelectAll] = useState<boolean>(false);
	const { openPopup, closePopup } = usePopup();

	useEffect(() => {
		fetchStudents();
	}, [fetchStudents]);

	const handleSearchChange = (e: React.ChangeEvent<HTMLInputElement>) => setSearchTerm(e.target.value);

	const handleSearchFieldChange = (e: React.ChangeEvent<HTMLSelectElement>) =>
		setSearchField(e.target.value as keyof StudentType);

	const handleSearchSubmit = (e: React.FormEvent<HTMLFormElement>) => {
		e.preventDefault();
		searchStudents(searchTerm, searchField);
	};

	const handleDeleteClick = async () => {
		const selectedStudents = filteredStudents.filter((student) => student.selected);
		if (selectedStudents.length === 0) {
			alert('삭제할 원생을 선택하세요.');
			return;
		}
		await Promise.all(selectedStudents.map((student) => deleteStudent(student.studentId)));
		alert('선택된 원생이 삭제되었습니다.');
		fetchStudents(); // 삭제 후 데이터 갱신
	};

	const handleAddNewClick = () => {
		openPopup(<StudentRegistrationPopup onClose={closePopup} onSuccess={fetchStudents} />);
	};

	const handleSelectAllChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		const { checked } = e.target;
		setSelectAll(checked);
		filteredStudents.forEach((student) => selectStudent(student.studentId));
	};

	const handleSelectChange = (studentId: number) => () => {
		selectStudent(studentId);
	};

	const handleStudentNameClick = async (studentId: number) => {
		const student = await fetchStudent(studentId);
		if (student) {
			openPopup(<StudentDetailPopup student={student} onClose={closePopup} />);
		}
	};

	return (
		<Container>
			<Title>원생관리</Title>

			<SearchSection>
				<SearchTitle>통합검색</SearchTitle>
				<SearchForm onSubmit={handleSearchSubmit}>
					<InputGroup>
						<Select value={searchField as string} onChange={handleSearchFieldChange}>
							<option value="studentName">원생명</option>
							<option value="grade">학년</option>
							<option value="email">이메일</option>
							<option value="phoneNumber">연락처</option>
						</Select>
						<Input placeholder="검색어 입력" value={searchTerm} onChange={handleSearchChange} />
						<Button type="submit">검색</Button>
					</InputGroup>
				</SearchForm>
			</SearchSection>

			<ButtonGroup>
				<Button onClick={handleDeleteClick}>삭제</Button>
				<Button onClick={handleAddNewClick}>신규원생등록</Button>
			</ButtonGroup>

			<StudentTable
				students={filteredStudents}
				selectAll={selectAll}
				onSelectAllChange={handleSelectAllChange}
				onSelectChange={handleSelectChange}
				onStudentNameClick={handleStudentNameClick}
			/>
		</Container>
	);
};

interface StudentTableProps {
	students: StudentType[];
	selectAll: boolean;
	onSelectAllChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
	onSelectChange: (studentId: number) => (e: React.ChangeEvent<HTMLInputElement>) => void;
	onStudentNameClick: (studentId: number) => void;
}

const StudentTable: React.FC<StudentTableProps> = ({
	students,
	selectAll,
	onSelectAllChange,
	onSelectChange,
	onStudentNameClick,
}) => {
	return (
		<Table>
			<thead>
				<tr>
					<Th>
						<input type="checkbox" checked={selectAll} onChange={onSelectAllChange} />
					</Th>
					<Th>원생명</Th>
					<Th>학년</Th>
					<Th>이메일</Th>
					<Th>연락처</Th>
				</tr>
			</thead>
			<tbody>
				{students.map((student) => (
					<tr key={student.studentId}>
						<Td>
							<input type="checkbox" checked={student.selected || false} onChange={onSelectChange(student.studentId)} />
						</Td>
						<Td onClick={() => onStudentNameClick(student.studentId)} style={{ cursor: 'pointer', color: '#007bff' }}>
							{student.studentName || 'N/A'}
						</Td>
						<Td>{student.grade || 'N/A'}</Td>
						<Td>{student.email || 'N/A'}</Td>
						<Td>{student.phoneNumber || 'N/A'}</Td>
					</tr>
				))}
			</tbody>
		</Table>
	);
};

export default StudentMgrPage;
