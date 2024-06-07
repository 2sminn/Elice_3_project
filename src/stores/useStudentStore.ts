import { create } from 'zustand';
import axiosApi from '../api/axios';

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

	fetchStudents: async () => {
		set({ loading: true, error: null });
		try {
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
		} catch (error: any) {
			set({ error: error.message, loading: false });
		}
	},

	fetchStudent: async (id: string) => {
		set({ loading: true, error: null });
		try {
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
		} catch (error: any) {
			set({ error: error.message, loading: false });
		}
	},

	createStudent: async (student) => {
		set({ loading: true, error: null });
		try {
			const newStudent = { ...student, id: (Math.random() * 1000).toString() };
			set((state) => ({ students: [...state.students, newStudent], loading: false }));
		} catch (error: any) {
			set({ error: error.message, loading: false });
		}
	},

	updateStudent: async (id: string, student) => {
		set({ loading: true, error: null });
		try {
			set((state) => ({
				students: state.students.map((s) => (s.id === id ? { ...s, ...student } : s)),
				loading: false,
			}));
		} catch (error: any) {
			set({ error: error.message, loading: false });
		}
	},

	deleteStudent: async (id: string) => {
		set({ loading: true, error: null });
		try {
			set((state) => ({
				students: state.students.filter((s) => s.id !== id),
				loading: false,
			}));
		} catch (error: any) {
			set({ error: error.message, loading: false });
		}
	},
}));

export default useStudentStore;
