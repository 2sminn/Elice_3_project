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
	ScheduleContainer,
	ScheduleInputGroup,
	ScheduleRemoveButton,
	PopupSelect,
	ScheduleItem, // Import ScheduleItem
	PopupButton,
} from './style';
import useLectureStore from '../../../../stores/useLectureStore';
import { LectureType } from '../../api';
import { useFieldArray, useForm } from 'react-hook-form';

interface LectureDetailPopupProps {
	lecture: LectureType;
	onClose: () => void;
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

const LectureDetailPopup: React.FC<LectureDetailPopupProps> = ({ lecture, onClose }) => {
	const [isEditing, setIsEditing] = useState(false);
	const { updateLecture } = useLectureStore();
	const { register, handleSubmit, control, setValue } = useForm<LectureType>({
		defaultValues: { ...lecture },
	});

	const { fields, append, remove } = useFieldArray({
		control,
		name: 'lectureSchedules',
	});

	useEffect(() => {
		setValue('lectureSchedules', lecture.lectureSchedules);
	}, [lecture, setValue]);

	const handleEditClick = () => {
		setIsEditing(true);
	};

	const onSubmit = async (data: LectureType) => {
		try {
			await updateLecture(data.id, data);
			alert('강의 정보가 업데이트 되었습니다.');
			onClose(); // 강의 정보 업데이트 후 팝업창 닫기
		} catch (error) {
			alert('강의 정보 업데이트에 실패했습니다.');
		}
		setIsEditing(false);
	};

	return (
		<PopupContainer>
			<Header>
				<Title>강의정보</Title>
				<CloseButton onClick={onClose}>×</CloseButton>
			</Header>
			<DetailList as="form" onSubmit={handleSubmit(onSubmit)}>
				<DetailItem>
					<strong>강의명:</strong>
					{isEditing ? <Input {...register('lectureName')} /> : lecture.lectureName}
				</DetailItem>
				<DetailItem>
					<strong>수강료:</strong>
					{isEditing ? <Input {...register('price')} type="number" /> : lecture.price.toLocaleString()}
				</DetailItem>
				<DetailItem>
					<strong>담임명:</strong>
					{isEditing ? <Input {...register('teacherName')} /> : lecture.teacherName}
				</DetailItem>
				<DetailItem>
					<strong>반 상태:</strong>
					{isEditing ? (
						<PopupSelect {...register('lectureStatus')}>
							<option value="OPEN">OPEN</option>
							<option value="CLOSE">CLOSE</option>
						</PopupSelect>
					) : (
						lecture.lectureStatus
					)}
				</DetailItem>
				<DetailItem>
					<strong>강의 일정:</strong>
					{isEditing ? (
						fields.map((item, index) => (
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
						))
					) : (
						<ul>
							{lecture.lectureSchedules.map((schedule, index) => (
								<ScheduleItem key={index}>
									{daysOfWeek.find((day) => day.value === schedule.day)?.label} {schedule.startTime} -{' '}
									{schedule.endTime}
								</ScheduleItem>
							))}
						</ul>
					)}
				</DetailItem>
				{isEditing && (
					<PopupButton type="button" onClick={() => append({ day: 'Monday', startTime: '', endTime: '' })}>
						일정 추가
					</PopupButton>
				)}
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

export default LectureDetailPopup;
