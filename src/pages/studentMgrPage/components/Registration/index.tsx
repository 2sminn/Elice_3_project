import React, { useState } from 'react';
import { PopupContainer, PopupTitle, PopupForm, PopupInput, PopupButton, PopupButtonContainer } from './style';
import useStudentStore from '../../../../stores/useStudentStore';

interface StudentRegistrationPopupProps {
	onClose: () => void;
}

const StudentRegistrationPopup: React.FC<StudentRegistrationPopupProps> = ({ onClose }) => {
	const { createStudent } = useStudentStore();
	const [student, setStudent] = useState({
		name: '',
		birthday: '',
		contact: '',
		email: '',
		school: '',
		grade: '',
		courseCode: '',
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
				<PopupInput name="name" placeholder="원생명" value={student.name} onChange={handleChange} />
				<PopupInput name="birthday" placeholder="생년월일" value={student.birthday} onChange={handleChange} />
				<PopupInput name="contact" placeholder="연락처" value={student.contact} onChange={handleChange} />
				<PopupInput name="email" placeholder="이메일" value={student.email} onChange={handleChange} />
				<PopupInput name="school" placeholder="학교" value={student.school} onChange={handleChange} />
				<PopupInput name="grade" placeholder="학년" value={student.grade} onChange={handleChange} />
				<PopupInput name="courseCode" placeholder="수강강의코드" value={student.courseCode} onChange={handleChange} />
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
