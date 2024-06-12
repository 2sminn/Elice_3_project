import { create } from 'zustand';
import {
	fetchStudents as apiFetchStudents,
	fetchStudent as apiFetchStudent,
	createStudent as apiCreateStudent,
	updateStudent as apiUpdateStudent,
	deleteStudent as apiDeleteStudent,
	searchStudents as apiSearchStudents,
	StudentType,
} from '../pages/studentMgrPage/api';

interface StudentStore {
	students: StudentType[];
	filteredStudents: StudentType[];
	student: StudentType | null;
	loading: boolean;
	error: string | null;
	fetchStudents: () => Promise<void>;
	fetchStudent: (studentId: number) => Promise<StudentType | null>;
	createStudent: (student: Omit<StudentType, 'studentId'>) => Promise<void>;
	updateStudent: (studentId: number, student: Partial<StudentType>) => Promise<void>;
	deleteStudent: (studentId: number) => Promise<void>;
	searchStudents: (term: string, field: keyof StudentType) => void;
	selectStudent: (studentId: number) => void;
}

const useStudentStore = create<StudentStore>((set) => ({
	students: [],
	filteredStudents: [],
	student: null,
	loading: false,
	error: null,

	fetchStudents: async () => {
		set({ loading: true, error: null });
		try {
			const students = await apiFetchStudents();
			console.log('Fetched students in store:', students); // 상태 관리에서 데이터 확인
			set({ students, filteredStudents: students, loading: false });
		} catch (error: any) {
			set({ error: error.message, loading: false });
		}
	},

	fetchStudent: async (studentId: number) => {
		set({ loading: true, error: null });
		try {
			const student = await apiFetchStudent(studentId);
			set({ student, loading: false });
			return student;
		} catch (error: any) {
			set({ error: error.message, loading: false });
			return null;
		}
	},

	createStudent: async (student) => {
		set({ loading: true, error: null });
		try {
			const newStudent = await apiCreateStudent(student);
			set((state) => ({
				students: [...state.students, newStudent],
				filteredStudents: [...state.filteredStudents, newStudent],
				loading: false,
			}));
		} catch (error: any) {
			set({ error: error.message, loading: false });
		}
	},

	updateStudent: async (studentId: number, student) => {
		set({ loading: true, error: null });
		try {
			const updatedStudent = await apiUpdateStudent(studentId, student);
			set((state) => ({
				students: state.students.map((s) => (s.studentId === studentId ? updatedStudent : s)),
				filteredStudents: state.filteredStudents.map((s) => (s.studentId === studentId ? updatedStudent : s)),
				loading: false,
			}));
		} catch (error: any) {
			set({ error: error.message, loading: false });
		}
	},

	deleteStudent: async (studentId: number) => {
		set({ loading: true, error: null });
		try {
			await apiDeleteStudent(studentId);
			set((state) => ({
				students: state.students.filter((s) => s.studentId !== studentId),
				filteredStudents: state.filteredStudents.filter((s) => s.studentId !== studentId),
				loading: false,
			}));
		} catch (error: any) {
			set({ error: error.message, loading: false });
		}
	},

	searchStudents: (term: string, field: keyof StudentType) => {
		set((state) => ({
			filteredStudents: state.students.filter((student) =>
				student[field]?.toString().toLowerCase().includes(term.toLowerCase()),
			),
		}));
	},

	selectStudent: (studentId: number) => {
		set((state) => ({
			students: state.students.map((student) =>
				student.studentId === studentId ? { ...student, selected: !student.selected } : student,
			),
			filteredStudents: state.filteredStudents.map((student) =>
				student.studentId === studentId ? { ...student, selected: !student.selected } : student,
			),
		}));
	},
}));

export default useStudentStore;
