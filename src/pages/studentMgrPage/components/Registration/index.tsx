import React, { useState } from 'react';
import { PopupContainer, PopupTitle, PopupForm, PopupInput, PopupButton, PopupButtonContainer } from './style';
import useStudentStore from '../../../../stores/useStudentStore';

interface StudentRegistrationPopupProps {
	onClose: () => void;
}

const StudentRegistrationPopup: React.FC<StudentRegistrationPopupProps> = ({ onClose }) => {
	const { createStudent } = useStudentStore();
	const [student, setStudent] = useState({
		studentName: '',
		birthDate: '',
		contact: '',
		email: '',
		schoolName: '',
		grade: '',
		class: '',
		phoneNumber: '',
	});

	const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		const { name, value } = e.target;
		setStudent((prevStudent) => ({
			...prevStudent,
			[name]: value,
		}));
	};

	const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
		e.preventDefault();
		try {
			await createStudent(student);
			alert('새 원생이 등록되었습니다.');
			onClose();
		} catch (error) {
			alert('원생 등록에 실패했습니다. 다시 시도해주세요.');
		}
	};

	return (
		<PopupContainer>
			<PopupTitle>신규 원생 등록</PopupTitle>
			<PopupForm onSubmit={handleSubmit}>
				<PopupInput name="studentName" placeholder="원생명" value={student.studentName} onChange={handleChange} />
				<PopupInput name="birthDate" placeholder="생년월일" value={student.birthDate} onChange={handleChange} />
				<PopupInput name="contact" placeholder="연락처" value={student.contact} onChange={handleChange} />
				<PopupInput name="email" placeholder="이메일" value={student.email} onChange={handleChange} />
				<PopupInput name="schoolName" placeholder="학교" value={student.schoolName} onChange={handleChange} />
				<PopupInput name="grade" placeholder="학년" value={student.grade} onChange={handleChange} />
				<PopupInput name="class" placeholder="수강반" value={student.class} onChange={handleChange} />
				<PopupInput name="phoneNumber" placeholder="전화번호" value={student.phoneNumber} onChange={handleChange} />
				<PopupButtonContainer>
					<PopupButton type="submit">등록</PopupButton>
					<PopupButton type="button" onClick={onClose}>
						취소
					</PopupButton>
				</PopupButtonContainer>
			</PopupForm>
		</PopupContainer>
	);
};

export default StudentRegistrationPopup;
