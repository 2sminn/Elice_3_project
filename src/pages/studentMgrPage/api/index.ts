import axiosApi from '../../../api/axios';

export interface StudentType {
	studentId: string;
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
	classes: string[]; // 추가된 필드
}

export const fetchStudents = async (): Promise<StudentType[]> => {
	const response = await axiosApi.get<StudentType[]>('/academy-students');
	return response.data;
};

export const fetchStudent = async (studentId: string): Promise<StudentType> => {
	const response = await axiosApi.get<StudentType>(`/academy-students/${studentId}`);
	return response.data;
};

export const createStudent = async (student: Omit<StudentType, 'studentId' | 'paymentInfo'>): Promise<StudentType> => {
	const response = await axiosApi.post<StudentType>('/academy-students', student);
	return response.data;
};

export const updateStudent = async (studentId: string, student: Partial<StudentType>): Promise<StudentType> => {
	const response = await axiosApi.put<StudentType>(`/academy-students/${studentId}`, student);
	return response.data;
};

export const deleteStudent = async (studentId: string): Promise<void> => {
	await axiosApi.delete(`/academy-students/${studentId}`);
};
