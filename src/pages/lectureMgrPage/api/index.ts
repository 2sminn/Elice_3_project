import axiosApi from '../../../api/axios';

export interface LectureType {
	lectureId: number;
	lectureName: string;
	price: number;
	teacherName: string;
	createdAt: string;
	updatedAt: string;
	lectureStatus: string;
	lectureSchedules: {
		lectureId: number;
		day: string;
		startTime: string;
		endTime: string;
	}[];
	selected?: boolean;
}

export const fetchLectures = async (): Promise<LectureType[]> => {
	const response = await axiosApi.get<LectureType[]>('/lectures');
	return response.data;
};

export const fetchLecture = async (lectureId: number): Promise<LectureType> => {
	const response = await axiosApi.get<LectureType>(`/lectures/${lectureId}`);
	return response.data;
};

export const createLecture = async (lecture: Omit<LectureType, 'lectureId'>): Promise<LectureType> => {
	const response = await axiosApi.post<LectureType>('/lectures', lecture);
	return response.data;
};

export const updateLecture = async (lectureId: number, lecture: Partial<LectureType>): Promise<LectureType> => {
	const response = await axiosApi.put<LectureType>(`/lectures/${lectureId}`, lecture);
	return response.data;
};

export const deleteLecture = async (lectureId: number): Promise<void> => {
	await axiosApi.delete(`/lectures/${lectureId}`);
};
