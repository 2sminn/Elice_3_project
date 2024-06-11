// pages/lectureMgrPage/LectureMgrPage.tsx
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
import useLectureStore from '../../stores/useLectureStore';
import { LectureType } from './api';
import usePopup from '../../hooks/usePopup';
import LectureRegistrationPopup from './components/Registration';
import LectureDetailPopup from './components/Detail'; // 강의 상세 팝업 컴포넌트 추가

const LectureMgrPage = () => {
	const { lectures, filteredLectures, fetchLectures, deleteLecture, searchLectures, selectLecture, fetchLecture } =
		useLectureStore();
	const [searchTerm, setSearchTerm] = useState<string>('');
	const [searchField, setSearchField] = useState<keyof LectureType>('lectureName');
	const [selectAll, setSelectAll] = useState<boolean>(false);
	const { openPopup, closePopup } = usePopup();

	useEffect(() => {
		fetchLectures();
	}, [fetchLectures]);

	const handleSearchChange = (e: React.ChangeEvent<HTMLInputElement>) => setSearchTerm(e.target.value);

	const handleSearchFieldChange = (e: React.ChangeEvent<HTMLSelectElement>) =>
		setSearchField(e.target.value as keyof LectureType);

	const handleSearchSubmit = (e: React.FormEvent<HTMLFormElement>) => {
		e.preventDefault();
		searchLectures(searchTerm, searchField);
	};

	const handleDeleteClick = async () => {
		const selectedLectures = lectures.filter((lecture) => lecture.selected);
		if (selectedLectures.length === 0) {
			alert('삭제할 강의를 선택하세요.');
			return;
		}
		await Promise.all(selectedLectures.map((lecture) => deleteLecture(lecture.id)));
		alert('선택된 강의가 삭제되었습니다.');
	};

	const handleAddNewClick = () => {
		openPopup(<LectureRegistrationPopup onClose={closePopup} onSuccess={fetchLectures} />);
	};

	const handleSelectAllChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		const { checked } = e.target;
		setSelectAll(checked);
		lectures.forEach((lecture) => selectLecture(lecture.id));
	};

	const handleSelectChange = (lectureId: number) => () => {
		selectLecture(lectureId);
	};

	const handleLectureNameClick = async (lectureId: number) => {
		const lecture = await fetchLecture(lectureId);
		if (lecture) {
			openPopup(<LectureDetailPopup lecture={lecture} onClose={closePopup} />);
		}
	};

	return (
		<Container>
			<Title>강의관리</Title>

			<SearchSection>
				<SearchTitle>통합검색</SearchTitle>
				<SearchForm onSubmit={handleSearchSubmit}>
					<InputGroup>
						<Select value={searchField} onChange={handleSearchFieldChange}>
							<option value="lectureName">강의명</option>
							<option value="lectureStatus">반상태</option>
							<option value="teacherName">담임명</option>
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
				lectures={filteredLectures}
				selectAll={selectAll}
				onSelectAllChange={handleSelectAllChange}
				onSelectChange={handleSelectChange}
				onLectureNameClick={handleLectureNameClick}
			/>
		</Container>
	);
};

interface LectureTableProps {
	lectures: LectureType[];
	selectAll: boolean;
	onSelectAllChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
	onSelectChange: (lectureId: number) => (e: React.ChangeEvent<HTMLInputElement>) => void;
	onLectureNameClick: (lectureId: number) => void;
}

const LectureTable: React.FC<LectureTableProps> = ({
	lectures,
	selectAll,
	onSelectAllChange,
	onSelectChange,
	onLectureNameClick,
}) => {
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
				{lectures.map((lecture) => (
					<tr key={lecture.id}>
						<Td>
							<input type="checkbox" checked={lecture.selected || false} onChange={onSelectChange(lecture.id)} />
						</Td>
						<Td onClick={() => onLectureNameClick(lecture.id)} style={{ cursor: 'pointer', color: '#007bff' }}>
							{lecture.lectureName}
						</Td>
						<Td>{lecture.teacherName}</Td>
						<Td>{lecture.price.toLocaleString()}원</Td>
						<Td>{lecture.lectureStatus}</Td>
					</tr>
				))}
			</tbody>
		</Table>
	);
};

export default LectureMgrPage;
