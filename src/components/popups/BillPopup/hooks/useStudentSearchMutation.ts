import { useMutation } from '@tanstack/react-query';
import { studentSearch } from '../api';
import { StudentSearchRequestType } from '../type';

export const useStudentSearchMutation = () => {
	const mutationFn = async (data: StudentSearchRequestType) => {
		const students = await studentSearch(data);
		return students;
	};

	return useMutation({ mutationFn });
};
