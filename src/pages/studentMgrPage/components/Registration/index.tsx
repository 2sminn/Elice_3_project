import React, { useState, useEffect } from 'react';
import { useForm, useFieldArray } from 'react-hook-form';
import {
	PopupContainer,
	Header,
	Title,
	CloseButton,
	Form,
	Input,
	ButtonContainer,
	SaveButton,
	ScheduleContainer,
	ScheduleInputGroup,
	ScheduleRemoveButton,
	PopupSelect,
	PopupButton,
} from './style';
import { createStudent } from '../../api'; // 올바른 경로로 수정
import { fetchLectures } from '../../../lectureMgrPage/api'; // 강의 정보를 불러오는 API
import { StudentType, LectureType } from '../../api';

const StudentRegistrationPopup = ({ onClose, onSuccess }: { onClose: () => void; onSuccess: () => void }) => {
	const { register, handleSubmit, control, reset, setValue } = useForm<StudentType>({
		defaultValues: {
			studentName: '',
			birthDate: '',
			email: '',
			phoneNumber: '',
			grade: '',
			schoolName: '',
			lectures: [],
		},
	});

	const { fields, append, remove } = useFieldArray({
		control,
		name: 'lectures',
	});

	const [lectures, setLectures] = useState<LectureType[]>([]);

	useEffect(() => {
		const loadLectures = async () => {
			const lectureList = await fetchLectures();
			setLectures(lectureList);
		};
		loadLectures();
	}, []);

	const onSubmit = async (data: StudentType) => {
		try {
			await createStudent(data);
			alert('원생이 성공적으로 등록되었습니다.');
			onSuccess();
			onClose();
		} catch (error) {
			alert('원생 등록에 실패했습니다.');
		}
		reset();
	};

	const handleLectureChange = (index: number, event: React.ChangeEvent<HTMLSelectElement>) => {
		const lectureId = parseInt(event.target.value, 10);
		const lecture = lectures.find((lec) => lec.id === lectureId);
		if (lecture) {
			setValue(`lectures.${index}`, lecture);
		}
	};

	return (
		<PopupContainer>
			<Header>
				<Title>신규 원생 등록</Title>
				<CloseButton onClick={onClose}>×</CloseButton>
			</Header>
			<Form onSubmit={handleSubmit(onSubmit)}>
				<Input {...register('studentName', { required: true })} placeholder="원생명" />
				<Input {...register('birthdate', { required: true })} type="date" placeholder="생년월일" />
				<Input {...register('email', { required: true })} type="email" placeholder="이메일" />
				<Input {...register('phoneNumber', { required: true })} placeholder="연락처" />
				<Input {...register('grade', { required: true })} placeholder="학년" />
				<Input {...register('schoolName', { required: true })} placeholder="학교명" />
				{fields.map((item, index) => (
					<ScheduleContainer key={item.id}>
						<ScheduleInputGroup>
							<PopupSelect onChange={(event) => handleLectureChange(index, event)} defaultValue="">
								<option value="">강의 선택</option>
								{lectures.map((lecture) => (
									<option key={lecture.id} value={lecture.id}>
										{lecture.lectureName} - {lecture.teacherName} - {lecture.price.toLocaleString()}원
									</option>
								))}
							</PopupSelect>
						</ScheduleInputGroup>
						<ScheduleRemoveButton type="button" onClick={() => remove(index)}>
							제거
						</ScheduleRemoveButton>
					</ScheduleContainer>
				))}
				<PopupButton
					type="button"
					onClick={() => append({ id: Date.now(), lectureName: '', price: 0, teacherName: '' })}
				>
					강의 추가
				</PopupButton>
				<ButtonContainer>
					<SaveButton type="submit">저장하기</SaveButton>
				</ButtonContainer>
			</Form>
		</PopupContainer>
	);
};

export default StudentRegistrationPopup;
