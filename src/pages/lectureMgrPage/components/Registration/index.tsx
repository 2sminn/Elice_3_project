import React from 'react';
import { useForm, useFieldArray } from 'react-hook-form';
import { createLecture } from '../../api';
import { LectureType } from '../../api';
import {
	PopupContainer,
	PopupTitle,
	PopupForm,
	PopupInput,
	PopupButtonContainer,
	PopupButton,
	PopupSelect,
	ScheduleContainer,
	ScheduleInputGroup,
	ScheduleRemoveButton,
} from './style';

interface LectureRegistrationPopupProps {
	onClose: () => void;
	onSuccess: () => void;
}

const daysOfWeek = [
	{ value: 'Monday', label: '월요일' },
	{ value: 'Tuesday', label: '화요일' },
	{ value: 'Wednesday', label: '수요일' },
	{ value: 'Thursday', label: '목요일' },
	{ value: 'Friday', label: '금요일' },
	{ value: 'Saturday', label: '토요일' },
	{ value: 'Sunday', label: '일요일' },
];

const statuses = [
	{ value: 'OPEN', label: 'OPEN' },
	{ value: 'CLOSE', label: 'CLOSE' },
];

const LectureRegistrationPopup: React.FC<LectureRegistrationPopupProps> = ({ onClose, onSuccess }) => {
	const {
		register,
		handleSubmit,
		formState: { errors },
		control,
	} = useForm<Omit<LectureType, 'id'>>();

	const { fields, append, remove } = useFieldArray({
		control,
		name: 'lectureSchedules',
	});

	const onSubmit = async (data: Omit<LectureType, 'id'>) => {
		try {
			await createLecture(data);
			alert('강의가 성공적으로 등록되었습니다.');
			onSuccess();
			onClose();
		} catch (error) {
			console.error('Error creating lecture:', error);
			alert('강의 등록 중 오류가 발생했습니다. 자세한 내용은 콘솔을 확인하세요.');
		}
	};

	return (
		<PopupContainer>
			<PopupTitle>신규 강의 등록</PopupTitle>
			<PopupForm onSubmit={handleSubmit(onSubmit)}>
				<PopupInput {...register('lectureName', { required: true })} placeholder="강의명" />
				{errors.lectureName && <span>이 필드는 필수입니다</span>}

				<PopupInput {...register('price', { required: true })} placeholder="수강료" type="number" />
				{errors.price && <span>이 필드는 필수입니다</span>}

				<PopupInput {...register('teacherName', { required: true })} placeholder="담임명" />
				{errors.teacherName && <span>이 필드는 필수입니다</span>}

				<PopupSelect {...register('lectureStatus', { required: true })}>
					{statuses.map((status) => (
						<option key={status.value} value={status.value}>
							{status.label}
						</option>
					))}
				</PopupSelect>
				{errors.lectureStatus && <span>이 필드는 필수입니다</span>}

				<PopupTitle>강의 일정</PopupTitle>
				{fields.map((item, index) => (
					<ScheduleContainer key={item.id}>
						<ScheduleInputGroup>
							<PopupSelect {...register(`lectureSchedules.${index}.day` as const, { required: true })}>
								{daysOfWeek.map((day) => (
									<option key={day.value} value={day.value}>
										{day.label}
									</option>
								))}
							</PopupSelect>
							<PopupInput
								{...register(`lectureSchedules.${index}.startTime` as const, { required: true })}
								placeholder="시작 시간"
								type="time"
							/>
							<PopupInput
								{...register(`lectureSchedules.${index}.endTime` as const, { required: true })}
								placeholder="종료 시간"
								type="time"
							/>
						</ScheduleInputGroup>
						<ScheduleRemoveButton type="button" onClick={() => remove(index)}>
							제거
						</ScheduleRemoveButton>
					</ScheduleContainer>
				))}
				<PopupButton type="button" onClick={() => append({ day: '', startTime: '', endTime: '' })}>
					일정 추가
				</PopupButton>

				<PopupButtonContainer>
					<PopupButton type="button" onClick={onClose}>
						취소
					</PopupButton>
					<PopupButton type="submit">등록</PopupButton>
				</PopupButtonContainer>
			</PopupForm>
		</PopupContainer>
	);
};

export default LectureRegistrationPopup;
