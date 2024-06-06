// src/store/useStudentStore.ts
import { create } from 'zustand';
import axiosApi from '../api/axios'; // axios 인스턴스

interface StudentType {
	id: string;
	name: string;
	school: string;
	grade: string;
	group: string;
	class: string;
	teacher: string;
	paymentInfo: string;
	contact: string;
}

interface StudentStore {
	students: StudentType[];
	student: StudentType | null;
	loading: boolean;
	error: string | null;
	fetchStudents: () => Promise<void>;
	fetchStudent: (id: string) => Promise<void>;
	createStudent: (student: Omit<StudentType, 'id'>) => Promise<void>;
	updateStudent: (id: string, student: Partial<StudentType>) => Promise<void>;
	deleteStudent: (id: string) => Promise<void>;
}

const useStudentStore = create<StudentStore>((set) => ({
	students: [],
	student: null,
	loading: false,
	error: null,

	// 모든 학생 목록 조회
	fetchStudents: async () => {
		set({ loading: true, error: null });
		try {
			// 주석 처리된 실제 API 호출
			// const response = await axiosApi.get('/academy-students');
			// set({ students: response.data, loading: false });

			// 모의 데이터를 사용한 임시 설정 (배포 전 테스트 용도)
			const mockData = [
				{
					id: '1',
					name: '홍길동',
					school: '학교1',
					grade: '5학년',
					group: '그룹1',
					class: '초등 5반',
					teacher: '선생님1',
					paymentInfo: '미납 0건/예정 1건',
					contact: '010-0000-0000',
				},
				{
					id: '2',
					name: '김철수',
					school: '학교2',
					grade: '4학년',
					group: '그룹2',
					class: '초등 4반',
					teacher: '선생님2',
					paymentInfo: '미납 1건/예정 0건',
					contact: '010-1111-1111',
				},
			];
			set({ students: mockData, loading: false });
		} catch (error) {
			set({ error: error.message, loading: false });
		}
	},

	// 특정 학생 조회
	fetchStudent: async (id: string) => {
		set({ loading: true, error: null });
		try {
			// 주석 처리된 실제 API 호출
			// const response = await axiosApi.get(`/academy-students/${id}`);
			// set({ student: response.data, loading: false });

			// 모의 데이터를 사용한 임시 설정 (배포 전 테스트 용도)
			const mockStudent = {
				id,
				name: '홍길동',
				school: '학교1',
				grade: '5학년',
				group: '그룹1',
				class: '초등 5반',
				teacher: '선생님1',
				paymentInfo: '미납 0건/예정 1건',
				contact: '010-0000-0000',
			};
			set({ student: mockStudent, loading: false });
		} catch (error) {
			set({ error: error.message, loading: false });
		}
	},

	// 새로운 학생 생성
	createStudent: async (student) => {
		set({ loading: true, error: null });
		try {
			// 주석 처리된 실제 API 호출
			// const response = await axiosApi.post('/academy-students', student);
			// set((state) => ({ students: [...state.students, response.data], loading: false }));

			// 모의 데이터 추가 (배포 전 테스트 용도)
			const newStudent = { ...student, id: (Math.random() * 1000).toString() }; // 임의의 ID 생성
			set((state) => ({ students: [...state.students, newStudent], loading: false }));
		} catch (error) {
			set({ error: error.message, loading: false });
		}
	},

	// 기존 학생 정보 업데이트
	updateStudent: async (id: string, student) => {
		set({ loading: true, error: null });
		try {
			// 주석 처리된 실제 API 호출
			// const response = await axiosApi.put(`/academy-students/${id}`, student);
			// set((state) => ({
			//   students: state.students.map((s) => (s.id === id ? response.data : s)),
			//   loading: false,
			// }));

			// 모의 데이터 업데이트 (배포 전 테스트 용도)
			set((state) => ({
				students: state.students.map((s) => (s.id === id ? { ...s, ...student } : s)),
				loading: false,
			}));
		} catch (error) {
			set({ error: error.message, loading: false });
		}
	},

	// 특정 학생 삭제
	deleteStudent: async (id: string) => {
		set({ loading: true, error: null });
		try {
			// 주석 처리된 실제 API 호출
			// await axiosApi.delete(`/academy-students/${id}`);
			// set((state) => ({
			//   students: state.students.filter((s) => s.id !== id),
			//   loading: false,
			// }));

			// 모의 데이터 삭제 (배포 전 테스트 용도)
			set((state) => ({
				students: state.students.filter((s) => s.id !== id),
				loading: false,
			}));
		} catch (error) {
			set({ error: error.message, loading: false });
		}
	},
}));

export default useStudentStore;
