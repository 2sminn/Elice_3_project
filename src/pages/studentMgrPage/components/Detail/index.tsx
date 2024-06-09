import React, { useState, useEffect } from 'react';
import {
	PopupContainer,
	Header,
	Title,
	CloseButton,
	DetailList,
	DetailItem,
	ButtonContainer,
	EditButton,
	Input,
	SaveButton,
} from './style';
import useStudentStore from '../../../../stores/useStudentStore';

interface StudentDetailPopupProps {
	student: {
		id: string;
		studentName: string;
		birthDate: string;
		contact: string;
		email: string;
		schoolName: string;
		grade: string;
		classes: string[];
		paymentInfo: {
			outstanding: number;
			upcoming: number;
		};
		phoneNumber: string;
	};
	onClose: () => void;
}

const StudentDetailPopup: React.FC<StudentDetailPopupProps> = ({ student, onClose }) => {
	const [isEditing, setIsEditing] = useState(false);
	const [editedStudent, setEditedStudent] = useState(student);
	const { updateStudent } = useStudentStore();

	useEffect(() => {
		setEditedStudent(student);
	}, [student]);

	const handleEditClick = () => {
		setIsEditing(true);
	};

	const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		const { name, value } = e.target;
		setEditedStudent((prev) => ({
			...prev,
			[name]: value,
		}));
	};

	const handleSaveClick = async () => {
		console.log('저장된 데이터:', editedStudent);
		try {
			await updateStudent(editedStudent.id, editedStudent);
			alert('원생 정보가 업데이트 되었습니다.');
		} catch (error) {
			alert('원생 정보 업데이트에 실패했습니다.');
		}
		setIsEditing(false);
	};

	return (
		<PopupContainer>
			<Header>
				<Title>원생정보</Title>
				<CloseButton onClick={onClose}>×</CloseButton>
			</Header>
			<DetailList>
				<DetailItem>
					<strong>이름:</strong>
					{isEditing ? (
						<Input name="studentName" value={editedStudent.studentName} onChange={handleInputChange} />
					) : (
						student.studentName
					)}
				</DetailItem>
				<DetailItem>
					<strong>생년월일:</strong>
					{isEditing ? (
						<Input name="birthDate" value={editedStudent.birthDate} onChange={handleInputChange} />
					) : (
						student.birthDate
					)}
				</DetailItem>
				<DetailItem>
					<strong>연락처:</strong>
					{isEditing ? (
						<Input name="contact" value={editedStudent.contact} onChange={handleInputChange} />
					) : (
						student.contact
					)}
				</DetailItem>
				<DetailItem>
					<strong>이메일:</strong>
					{isEditing ? <Input name="email" value={editedStudent.email} onChange={handleInputChange} /> : student.email}
				</DetailItem>
				<DetailItem>
					<strong>학교:</strong>
					{isEditing ? (
						<Input name="schoolName" value={editedStudent.schoolName} onChange={handleInputChange} />
					) : (
						student.schoolName
					)}
				</DetailItem>
				<DetailItem>
					<strong>학년:</strong>
					{isEditing ? <Input name="grade" value={editedStudent.grade} onChange={handleInputChange} /> : student.grade}
				</DetailItem>
				<DetailItem>
					<strong>수강반:</strong>
					{isEditing ? (
						<Input
							name="classes"
							value={editedStudent.classes.join(', ')}
							onChange={(e) =>
								setEditedStudent((prev) => ({
									...prev,
									classes: e.target.value.split(',').map((cls) => cls.trim()),
								}))
							}
						/>
					) : (
						student.classes.join(', ')
					)}
				</DetailItem>
				<DetailItem>
					<strong>미납:</strong> {student.paymentInfo.outstanding} 건
				</DetailItem>
				<DetailItem>
					<strong>납부예정:</strong> {student.paymentInfo.upcoming} 건
				</DetailItem>
			</DetailList>
			<ButtonContainer>
				{isEditing ? (
					<SaveButton onClick={handleSaveClick}>저장하기</SaveButton>
				) : (
					<EditButton onClick={handleEditClick}>수정하기</EditButton>
				)}
			</ButtonContainer>
		</PopupContainer>
	);
};

export default StudentDetailPopup;
