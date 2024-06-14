import axiosApi from '../../../api/axios';

export interface BillType {
	id: number;
	totalPrice: number;
	dueDate: string;
	createdAt: string;
	updatedAt: string;
	status: string;
	message: string;
}

export interface LectureType {
	id: number;
	lectureName: string;
	price: number;
	teacherName: string;
	createdAt: string;
	updatedAt: string;
}

export interface StudentType {
	academyId: number;
	academyName: string;
	studentId: number;
	studentName: string;
	birthDate: string;
	phoneNumber: string;
	email: string;
	schoolName: string;
	grade: string;
	lectures: LectureType[];
	bills: BillType[];
	selected?: boolean;
}

export const fetchStudents = async (): Promise<StudentType[]> => {
	const response = await axiosApi.get<StudentType[]>('/academy-students');
	console.log('Fetched students:', response.data); // 응답 데이터 확인
	return response.data;
};

export const fetchStudent = async (studentId: number): Promise<StudentType> => {
	const response = await axiosApi.get<StudentType>(`/academy-students/${studentId}`);
	console.log('API Response:', response.data); // API 응답 확인
	return response.data;
};

export const createStudent = async (student: Omit<StudentType, 'studentId'>): Promise<StudentType> => {
	const response = await axiosApi.post<StudentType>('/academy-students', student);
	return response.data;
};

export const updateStudent = async (studentId: number, student: Partial<StudentType>): Promise<StudentType> => {
	const response = await axiosApi.put<StudentType>(`/academy-students/${studentId}`, student);
	return response.data;
};

export const deleteStudent = async (studentId: number): Promise<void> => {
	await axiosApi.delete(`/academy-students/${studentId}`);
};

export const searchStudents = async (term: string, field: keyof StudentType): Promise<StudentType[]> => {
	const response = await axiosApi.post<StudentType[]>('/academy-students/search', { term, field });
	return response.data;
};
