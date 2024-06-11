import { useForm } from 'react-hook-form';
import { createStudent } from '../../api';
import { StudentType } from '../../api';

const StudentRegistrationPopup = ({ onClose }: { onClose: () => void }) => {
	const {
		register,
		handleSubmit,
		formState: { errors },
	} = useForm<Omit<StudentType, 'studentId' | 'paymentInfo'>>();

	const onSubmit = async (data: Omit<StudentType, 'studentId' | 'paymentInfo'>) => {
		try {
			await createStudent(data);
			alert('Student created successfully');
			onClose();
		} catch (error) {
			console.error('Error creating student:', error);
			alert('Error creating student. Please check the console for more details.');
		}
	};

	return (
		<form onSubmit={handleSubmit(onSubmit)}>
			<input {...register('studentName', { required: true })} placeholder="Student Name" />
			{errors.studentName && <span>This field is required</span>}

			<input {...register('birthDate', { required: true })} placeholder="Birth Date" />
			{errors.birthDate && <span>This field is required</span>}

			<input {...register('contact', { required: true })} placeholder="Contact" />
			{errors.contact && <span>This field is required</span>}

			<input {...register('email', { required: true })} placeholder="Email" />
			{errors.email && <span>This field is required</span>}

			<input {...register('schoolName', { required: true })} placeholder="School Name" />
			{errors.schoolName && <span>This field is required</span>}

			<input {...register('grade', { required: true })} placeholder="Grade" />
			{errors.grade && <span>This field is required</span>}

			<input {...register('group', { required: true })} placeholder="Group" />
			{errors.group && <span>This field is required</span>}

			<input {...register('class', { required: true })} placeholder="Class" />
			{errors.class && <span>This field is required</span>}

			<input {...register('teacher', { required: true })} placeholder="Teacher" />
			{errors.teacher && <span>This field is required</span>}

			<input {...register('phoneNumber', { required: true })} placeholder="Phone Number" />
			{errors.phoneNumber && <span>This field is required</span>}

			<button type="submit">Create Student</button>
		</form>
	);
};

export default StudentRegistrationPopup;
