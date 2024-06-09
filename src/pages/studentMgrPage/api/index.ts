import axiosApi from '../../../api/axios';

export interface StudentType {
	id: string;
	studentName: string;
	birthDate: string;
	contact: string;
	email: string;
	schoolName: string;
	grade: string;
	group: string;
	class: string;
	teacher: string;
	paymentInfo: {
		outstanding: number;
		upcoming: number;
	};
	phoneNumber: string;
	selected?: boolean;
}

export const fetchStudents = async (): Promise<StudentType[]> => {
	const response = await axiosApi.get<StudentType[]>('/academy-students');
	return response.data;
};

export const fetchStudent = async (id: string): Promise<StudentType> => {
	const response = await axiosApi.get<StudentType>(`/academy-students/${id}`);
	return response.data;
};

export const createStudent = async (student: Omit<StudentType, 'id' | 'paymentInfo'>): Promise<StudentType> => {
	const response = await axiosApi.post<StudentType>('/academy-students', student);
	return response.data;
};

export const updateStudent = async (id: string, student: Partial<StudentType>): Promise<StudentType> => {
	const response = await axiosApi.put<StudentType>(`/academy-students/${id}`, student);
	return response.data;
};

export const deleteStudent = async (id: string): Promise<void> => {
	await axiosApi.delete(`/academy-students/${id}`);
};
