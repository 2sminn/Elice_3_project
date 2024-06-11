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
	const { students, fetchStudents, fetchStudent, deleteStudent, updateStudent, searchStudents, filterStudents } =
		useStudentStore();
	const [selectedStudent, setSelectedStudent] = useState<StudentType | null>(null);
	const [searchTerm, setSearchTerm] = useState<string>('');
	const [filters, setFilters] = useState<Partial<StudentType>>({
		studentName: '',
		schoolName: '',
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

	const handleSearchSubmit = (e: React.FormEvent<HTMLFormElement>) => {
		e.preventDefault();
		searchStudents(searchTerm);
	};

	const handleFilterSubmit = (e: React.FormEvent<HTMLFormElement>) => {
		e.preventDefault();
		filterStudents(filters);
	};

	const handleDeleteClick = async () => {
		const selectedStudents = students.filter((student) => student.selected);
		if (selectedStudents.length === 0) {
			alert('삭제할 원생을 선택하세요.');
			return;
		}
		await Promise.all(selectedStudents.map((student) => deleteStudent(student.id)));
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

	const handleSelectChange = (id: string) => (e: React.ChangeEvent<HTMLInputElement>) => {
		const { checked } = e.target;
		updateStudent(id, { selected: checked });
	};

	const handleStudentNameClick = async (id: string) => {
		if (!id) {
			console.error('No ID provided for student click handler');
			return;
		}
		const student = await fetchStudent(id);
		if (student) {
			setSelectedStudent(student);
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
						<Input
							name="studentName"
							placeholder="원생명 입력"
							value={filters.studentName}
							onChange={handleFilterChange}
						/>
						<Input
							name="schoolName"
							placeholder="학교명 입력"
							value={filters.schoolName}
							onChange={handleFilterChange}
						/>
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
	onSelectChange: (id: string) => (e: React.ChangeEvent<HTMLInputElement>) => void;
	onStudentNameClick: (id: string) => void;
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
				{students.map((student) => (
					<tr key={student.id}>
						<Td>
							<input type="checkbox" checked={student.selected || false} onChange={onSelectChange(student.id)} />
						</Td>
						<Td onClick={() => onStudentNameClick(student.id)} style={{ cursor: 'pointer', color: '#007bff' }}>
							{student.studentName}
						</Td>
						<Td>{student.schoolName}</Td>
						<Td>{student.grade}</Td>
						<Td>{student.group}</Td>
						<Td>{student.class}</Td>
						<Td>{student.teacher}</Td>
						<Td>
							{student.paymentInfo
								? `${student.paymentInfo.outstanding} 건 / ${student.paymentInfo.upcoming} 건`
								: 'N/A'}
						</Td>
						<Td>{student.phoneNumber}</Td>
					</tr>
				))}
			</tbody>
		</Table>
	);
};

export default StudentMgrPage;
