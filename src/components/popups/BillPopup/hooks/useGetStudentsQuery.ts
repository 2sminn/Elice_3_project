import { useQuery } from '@tanstack/react-query';
import { getStudents } from '../api';

export const useGetStudentsQuery = (studentName?: string) => {
	const queryKey = ['students', studentName];

	const queryFn = async (studentName?: string) => {
		const students = await getStudents();

		const filterStudents = students.data.filter((student) => student.studentName === studentName);

		return studentName ? filterStudents : students.data;
	};

	return useQuery({ queryKey, queryFn: () => queryFn(studentName) });
};
