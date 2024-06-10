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
import usePopup from '../../hooks/usePopup';
import StudentRegistrationPopup from './components/Registration';
import StudentDetailPopup from './components/Detail';
import useStudentStore from '../../stores/useStudentStore';
import { StudentType } from './api';

const StudentMgrPage = () => {
	const { students, fetchStudents, createStudent, updateStudent, deleteStudent } = useStudentStore();
	const [searchTerm, setSearchTerm] = useState<string>('');
	const [filters, setFilters] = useState<Partial<StudentType>>({
		name: '',
		school: '',
		grade: '',
		group: '',
		class: '',
		teacher: '',
	});
	const [selectAll, setSelectAll] = useState<boolean>(false);
	const { openPopup, closePopup } = usePopup();

	useEffect(() => {
		fetchStudents();
	}, [fetchStudents]);

	const handleSearchChange = (e: React.ChangeEvent<HTMLInputElement>) => setSearchTerm(e.target.value);

	const handleFilterChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
		const { name, value } = e.target;
		setFilters((prevFilters) => ({
			...prevFilters,
			[name]: value,
		}));
	};

	const filterStudents = (students: StudentType[], filters: Partial<StudentType>) => {
		return students.filter((student) => {
			return Object.keys(filters).every((key) => {
				const value = student[key as keyof StudentType];
				return typeof value === 'string' && value.includes(filters[key as keyof Partial<StudentType>]);
			});
		});
	};

	const handleSearchSubmit = (e: React.FormEvent<HTMLFormElement>) => {
		e.preventDefault();
		// setStudents(filterStudents(studentsData, { name: searchTerm, ...filters }));
	};

	const handleFilterSubmit = (e: React.FormEvent<HTMLFormElement>) => {
		e.preventDefault();
		// setStudents(filterStudents(studentsData, filters));
	};

	const handleDeleteClick = () => {
		const selectedStudents = students.filter((student) => student.selected);
		if (selectedStudents.length === 0) {
			alert('삭제할 원생을 선택하세요.');
			return;
		}
		selectedStudents.forEach((student) => deleteStudent(student.id));
		alert('선택된 원생이 삭제되었습니다.');
	};

	const handleAddNewClick = () => {
		openPopup(<StudentRegistrationPopup onClose={handleNewStudentClose} />);
	};

	const handleNewStudentClose = () => {
		closePopup();
		fetchStudents(); // 학생 목록을 새로 불러오기
	};

	const handleSelectAllChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		const { checked } = e.target;
		setSelectAll(checked);
		students.forEach((student) => updateStudent(student.id, { selected: checked }));
	};

	const handleSelectChange = (index: number) => (e: React.ChangeEvent<HTMLInputElement>) => {
		const { checked } = e.target;
		const student = students[index];
		updateStudent(student.id, { selected: checked });
	};

	const handleStudentNameClick = (student: StudentType) => {
		openPopup(
			<StudentDetailPopup
				student={{
					name: student.name,
					birthDate: '2014-01-01',
					contact: student.contact,
					email: 'abc@gmail.com',
					school: student.school,
					grade: student.grade,
					classes: ['초등 5반', '단과수학반'],
					paymentInfo: {
						outstanding: 0,
						upcoming: 1,
					},
				}}
				onClose={closePopup}
			/>,
		);
	};

	return (
		<Container>
			<Title>원생관리</Title>

			<SearchSection>
				<SearchTitle>통합검색</SearchTitle>
				<SearchForm onSubmit={handleSearchSubmit}>
					<InputGroup>
						<Select>
							<option>원생명</option>
						</Select>
						<Input placeholder="검색어 입력" value={searchTerm} onChange={handleSearchChange} />
						<Button type="submit">검색</Button>
					</InputGroup>
				</SearchForm>
			</SearchSection>

			<SearchSection>
				<SearchTitle>조건검색</SearchTitle>
				<SearchForm onSubmit={handleFilterSubmit}>
					<InputGroup>
						<Input name="name" placeholder="원생명 입력" value={filters.name} onChange={handleFilterChange} />
						<Input name="school" placeholder="학교명 입력" value={filters.school} onChange={handleFilterChange} />
						<Input name="grade" placeholder="학년명 입력" value={filters.grade} onChange={handleFilterChange} />
					</InputGroup>
					<InputGroup>
						<Select name="group" value={filters.group} onChange={handleFilterChange}>
							<option value="">그룹선택</option>
							<option value="그룹1">그룹1</option>
							<option value="그룹2">그룹2</option>
						</Select>
						<Select name="class" value={filters.class} onChange={handleFilterChange}>
							<option value="">반선택</option>
							<option value="초등 5반">초등 5반</option>
							<option value="초등 6반">초등 6반</option>
						</Select>
						<Select name="teacher" value={filters.teacher} onChange={handleFilterChange}>
							<option value="">주담임선택</option>
							<option value="선생님1">선생님1</option>
							<option value="선생님2">선생님2</option>
						</Select>
					</InputGroup>
					<Button type="submit">검색</Button>
				</SearchForm>
			</SearchSection>

			<ButtonGroup>
				<Button onClick={handleDeleteClick}>삭제</Button>
				<Button onClick={handleAddNewClick}>신규원생등록</Button>
			</ButtonGroup>

			<StudentTable
				students={students}
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
	onSelectChange: (index: number) => (e: React.ChangeEvent<HTMLInputElement>) => void;
	onStudentNameClick: (student: StudentType) => void;
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
					<Th>학교명</Th>
					<Th>학년명</Th>
					<Th>그룹</Th>
					<Th>수강반 정보</Th>
					<Th>주담임</Th>
					<Th>수납정보</Th>
					<Th>연락처</Th>
				</tr>
			</thead>
			<tbody>
				{students.map((student, index) => (
					<tr key={index}>
						<Td>
							<input type="checkbox" checked={student.selected || false} onChange={onSelectChange(index)} />
						</Td>
						<Td onClick={() => onStudentNameClick(student)} style={{ cursor: 'pointer', color: '#007bff' }}>
							{student.name}
						</Td>
						<Td>{student.school}</Td>
						<Td>{student.grade}</Td>
						<Td>{student.group}</Td>
						<Td>{student.class}</Td>
						<Td>{student.teacher}</Td>
						<Td>{student.paymentInfo}</Td>
						<Td>{student.contact}</Td>
					</tr>
				))}
			</tbody>
		</Table>
	);
};

export default StudentMgrPage;
