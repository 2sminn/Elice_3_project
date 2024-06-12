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
	PopupSelect,
	PopupButton,
} from './style';
import useStudentStore from '../../../../stores/useStudentStore';
import { StudentType } from '../../api';
import { useForm } from 'react-hook-form';

interface StudentDetailPopupProps {
	student: StudentType;
	onClose: () => void;
}

const StudentDetailPopup: React.FC<StudentDetailPopupProps> = ({ student, onClose }) => {
	const [isEditing, setIsEditing] = useState(false);
	const { updateStudent } = useStudentStore();
	const { register, handleSubmit, setValue } = useForm<StudentType>({
		defaultValues: { ...student },
	});

	useEffect(() => {
		setValue('studentName', student.studentName);
		setValue('email', student.email);
		setValue('phoneNumber', student.phoneNumber);
		setValue('grade', student.grade);
	}, [student, setValue]);

	const handleEditClick = () => {
		setIsEditing(true);
	};

	const onSubmit = async (data: StudentType) => {
		try {
			await updateStudent(data.studentId, data);
			alert('원생 정보가 업데이트 되었습니다.');
			onClose(); // 원생 정보 업데이트 후 팝업창 닫기
		} catch (error) {
			alert('원생 정보 업데이트에 실패했습니다.');
		}
		setIsEditing(false);
	};

	return (
		<PopupContainer>
			<Header>
				<Title>원생 정보</Title>
				<CloseButton onClick={onClose}>×</CloseButton>
			</Header>
			<DetailList as="form" onSubmit={handleSubmit(onSubmit)}>
				<DetailItem>
					<strong>원생명:</strong>
					{isEditing ? <Input {...register('studentName')} /> : student.studentName}
				</DetailItem>
				<DetailItem>
					<strong>이메일:</strong>
					{isEditing ? <Input {...register('email')} /> : student.email}
				</DetailItem>
				<DetailItem>
					<strong>연락처:</strong>
					{isEditing ? <Input {...register('phoneNumber')} /> : student.phoneNumber}
				</DetailItem>
				<DetailItem>
					<strong>학년:</strong>
					{isEditing ? <Input {...register('grade')} /> : student.grade}
				</DetailItem>
				<ButtonContainer>
					{isEditing ? (
						<SaveButton type="submit">저장하기</SaveButton>
					) : (
						<EditButton type="button" onClick={handleEditClick}>
							수정하기
						</EditButton>
					)}
				</ButtonContainer>
			</DetailList>
		</PopupContainer>
	);
};

export default StudentDetailPopup;
