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
import { StudentType, BillType } from '../../api';

const StudentRegistrationPopup = ({ onClose, onSuccess }: { onClose: () => void; onSuccess: () => void }) => {
	const { register, handleSubmit, control, reset } = useForm<StudentType>({
		defaultValues: {
			academyId: 1, // 기본 값 설정 (필요에 따라 수정)
			academyName: '',
			studentName: '',
			birthdate: '',
			email: '',
			phoneNumber: '',
			schoolName: '',
			grade: '',
			lectures: [{ id: Date.now(), lectureName: '', price: 0, teacherName: '', createdAt: '', updatedAt: '' }],
			bills: [{ id: Date.now(), totalPrice: 0, dueDate: '', createdAt: '', updatedAt: '', status: '', message: '' }],
		},
	});

	const {
		fields: lectureFields,
		append: appendLecture,
		remove: removeLecture,
	} = useFieldArray({
		control,
		name: 'lectures',
	});

	const {
		fields: billFields,
		append: appendBill,
		remove: removeBill,
	} = useFieldArray({
		control,
		name: 'bills',
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
				<Input {...register('academyName', { required: true })} placeholder="학원이름" />
				<Input {...register('studentName', { required: true })} placeholder="원생명" />
				<Input {...register('birthdate', { required: true })} type="date" placeholder="생년월일" />
				<Input {...register('email', { required: true })} type="email" placeholder="이메일" />
				<Input {...register('phoneNumber', { required: true })} placeholder="연락처" />
				<Input {...register('grade', { required: true })} placeholder="학년" />
				<Input {...register('schoolName', { required: true })} placeholder="학교명" />

				<h4>강의 목록</h4>
				{lectureFields.map((item, index) => (
					<ScheduleContainer key={item.id}>
						<ScheduleInputGroup>
							<Input {...register(`lectures.${index}.lectureName` as const)} placeholder="강의명" />
							<Input {...register(`lectures.${index}.teacherName` as const)} placeholder="담임명" />
							<Input {...register(`lectures.${index}.price` as const)} type="number" placeholder="수강료" />
						</ScheduleInputGroup>
						<ScheduleRemoveButton type="button" onClick={() => removeLecture(index)}>
							제거
						</ScheduleRemoveButton>
					</ScheduleContainer>
				))}
				<PopupButton
					type="button"
					onClick={() =>
						appendLecture({ id: Date.now(), lectureName: '', price: 0, teacherName: '', createdAt: '', updatedAt: '' })
					}
				>
					강의 추가
				</PopupButton>

				<h4>청구 목록</h4>
				{billFields.map((item, index) => (
					<ScheduleContainer key={item.id}>
						<ScheduleInputGroup>
							<Input {...register(`bills.${index}.totalPrice` as const)} type="number" placeholder="청구 금액" />
							<Input {...register(`bills.${index}.dueDate` as const)} type="date" placeholder="납부 기한" />
							<Input {...register(`bills.${index}.status` as const)} placeholder="상태" />
							<Input {...register(`bills.${index}.message` as const)} placeholder="메시지" />
						</ScheduleInputGroup>
						<ScheduleRemoveButton type="button" onClick={() => removeBill(index)}>
							제거
						</ScheduleRemoveButton>
					</ScheduleContainer>
				))}
				<PopupButton
					type="button"
					onClick={() =>
						appendBill({
							id: Date.now(),
							totalPrice: 0,
							dueDate: '',
							createdAt: '',
							updatedAt: '',
							status: '',
							message: '',
						})
					}
				>
					청구 추가
				</PopupButton>

				<ButtonContainer>
					<SaveButton type="submit">저장하기</SaveButton>
				</ButtonContainer>
			</Form>
		</PopupContainer>
	);
};

export default StudentRegistrationPopup;
