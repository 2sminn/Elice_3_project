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
import { createLecture } from '../../api';
import { LectureType } from '../../api';

const daysOfWeek = [
	{ value: 'Monday', label: '월요일' },
	{ value: 'Tuesday', label: '화요일' },
	{ value: 'Wednesday', label: '수요일' },
	{ value: 'Thursday', label: '목요일' },
	{ value: 'Friday', label: '금요일' },
	{ value: 'Saturday', label: '토요일' },
	{ value: 'Sunday', label: '일요일' },
];

const LectureRegistrationPopup = ({ onClose, onSuccess }: { onClose: () => void; onSuccess: () => void }) => {
	const { register, handleSubmit, control, reset } = useForm<LectureType>({
		defaultValues: {
			lectureName: '',
			price: 0,
			teacherName: '',
			lectureStatus: 'OPEN',
			lectureSchedules: [{ id: Date.now(), day: 'Monday', startTime: '', endTime: '' }],
		},
	});

	const { fields, append, remove } = useFieldArray({
		control,
		name: 'lectureSchedules',
	});

	const onSubmit = async (data: LectureType) => {
		try {
			await createLecture(data);
			alert('강의가 성공적으로 등록되었습니다.');
			onSuccess();
			onClose();
		} catch (error) {
			alert('강의 등록에 실패했습니다.');
		}
		reset();
	};

	return (
		<PopupContainer>
			<Header>
				<Title>신규 강의 등록</Title>
				<CloseButton onClick={onClose}>×</CloseButton>
			</Header>
			<Form onSubmit={handleSubmit(onSubmit)}>
				<Input {...register('lectureName', { required: true })} placeholder="강의명" />
				<Input {...register('price', { required: true })} type="number" placeholder="수강료" />
				<Input {...register('teacherName', { required: true })} placeholder="담임명" />
				<PopupSelect {...register('lectureStatus')}>
					<option value="OPEN">OPEN</option>
					<option value="CLOSE">CLOSE</option>
				</PopupSelect>
				{fields.map((item, index) => (
					<ScheduleContainer key={item.id}>
						<ScheduleInputGroup>
							<PopupSelect {...register(`lectureSchedules.${index}.day` as const)}>
								{daysOfWeek.map((day) => (
									<option key={day.value} value={day.value}>
										{day.label}
									</option>
								))}
							</PopupSelect>
							<Input {...register(`lectureSchedules.${index}.startTime` as const)} type="time" />
							<Input {...register(`lectureSchedules.${index}.endTime` as const)} type="time" />
						</ScheduleInputGroup>
						<ScheduleRemoveButton type="button" onClick={() => remove(index)}>
							제거
						</ScheduleRemoveButton>
					</ScheduleContainer>
				))}
				<PopupButton
					type="button"
					onClick={() => append({ id: Date.now(), day: 'Monday', startTime: '', endTime: '' })}
				>
					일정 추가
				</PopupButton>
				<ButtonContainer>
					<SaveButton type="submit">저장하기</SaveButton>
				</ButtonContainer>
			</Form>
		</PopupContainer>
	);
};

export default LectureRegistrationPopup;
