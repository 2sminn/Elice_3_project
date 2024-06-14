import React from 'react';
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
	PopupButton,
} from './style';
import { createStudent } from '../../api';
import { StudentType } from '../../api';

const StudentRegistrationPopup = ({ onClose, onSuccess }: { onClose: () => void; onSuccess: () => void }) => {
	const { register, handleSubmit, control, reset } = useForm<StudentType>({
		defaultValues: {
			studentName: '',
			birthDate: '',
			email: '',
			phoneNumber: '',
			grade: '',
			schoolName: '',
			lectures: [{ id: Date.now(), lectureName: '', price: 0, teacherName: '' }],
		},
	});

	const { fields, append, remove } = useFieldArray({
		control,
		name: 'lectures',
	});

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
							<Input {...register(`lectures.${index}.lectureName` as const)} placeholder="강의명" />
							<Input {...register(`lectures.${index}.teacherName` as const)} placeholder="담임명" />
							<Input {...register(`lectures.${index}.price` as const)} type="number" placeholder="수강료" />
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
