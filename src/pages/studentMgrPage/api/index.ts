import axiosApi from '../../../api/axios';

export interface StudentType {
	id: string;
	name: string;
	school: string;
	grade: string;
	group: string;
	class: string;
	teacher: string;
	paymentInfo: string;
	contact: string;
	selected?: boolean;
}

export const fetchStudents = async (): Promise<StudentType[]> => {
	const response = await axiosApi.get<StudentType[]>('/students');
	return response.data;
};

export const fetchStudent = async (id: string): Promise<StudentType> => {
	const response = await axiosApi.get<StudentType>(`/students/${id}`);
	return response.data;
};

export const createStudent = async (student: Omit<StudentType, 'id'>): Promise<StudentType> => {
	const response = await axiosApi.post<StudentType>('/students', student);
	return response.data;
};

export const updateStudent = async (id: string, student: Partial<StudentType>): Promise<StudentType> => {
	const response = await axiosApi.put<StudentType>(`/students/${id}`, student);
	return response.data;
};

export const deleteStudent = async (id: string): Promise<void> => {
	await axiosApi.delete(`/students/${id}`);
};
