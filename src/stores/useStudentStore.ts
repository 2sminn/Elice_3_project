import { create } from 'zustand';
import {
	fetchStudents as apiFetchStudents,
	fetchStudent as apiFetchStudent,
	createStudent as apiCreateStudent,
	updateStudent as apiUpdateStudent,
	deleteStudent as apiDeleteStudent,
	StudentType,
} from '../pages/studentMgrPage/api';

interface StudentStore {
	students: StudentType[];
	student: StudentType | null;
	loading: boolean;
	error: string | null;
	fetchStudents: () => Promise<void>;
	fetchStudent: (id: string) => Promise<StudentType | null>;
	createStudent: (student: Omit<StudentType, 'id' | 'paymentInfo'>) => Promise<void>;
	updateStudent: (id: string, student: Partial<StudentType>) => Promise<void>;
	deleteStudent: (id: string) => Promise<void>;
	searchStudents: (term: string) => void;
}

const useStudentStore = create<StudentStore>((set) => ({
	students: [],
	student: null,
	loading: false,
	error: null,

	fetchStudents: async () => {
		set({ loading: true, error: null });
		try {
			const students = await apiFetchStudents();
			console.log('Fetched Students:', students);
			set({ students, loading: false });
		} catch (error: any) {
			console.error('Error fetching students:', error);
			set({ error: error.message, loading: false });
		}
	},

	fetchStudent: async (id: string) => {
		if (!id) {
			console.error('No ID provided for fetching student');
			return null;
		}
		set({ loading: true, error: null });
		try {
			const student = await apiFetchStudent(id);
			console.log('Fetched Student:', student);
			set({ student, loading: false });
			return student;
		} catch (error: any) {
			console.error('Error fetching student:', error.response ? error.response.data : error.message);
			set({ error: error.message, loading: false });
			return null;
		}
	},

	createStudent: async (student) => {
		set({ loading: true, error: null });
		try {
			const newStudent = await apiCreateStudent(student);
			console.log('Created Student:', newStudent);
			set((state) => ({ students: [...state.students, newStudent], loading: false }));
		} catch (error: any) {
			console.error('Error creating student:', error);
			set({ error: error.message, loading: false });
		}
	},

	updateStudent: async (id: string, student) => {
		set({ loading: true, error: null });
		try {
			const updatedStudent = await apiUpdateStudent(id, student);
			console.log('Updated Student:', updatedStudent);
			set((state) => ({
				students: state.students.map((s) => (s.id === id ? updatedStudent : s)),
				loading: false,
			}));
		} catch (error: any) {
			console.error('Error updating student:', error);
			set({ error: error.message, loading: false });
		}
	},

	deleteStudent: async (id: string) => {
		set({ loading: true, error: null });
		try {
			await apiDeleteStudent(id);
			console.log('Deleted Student ID:', id);
			set((state) => ({
				students: state.students.filter((s) => s.id !== id),
				loading: false,
			}));
		} catch (error: any) {
			console.error('Error deleting student:', error);
			set({ error: error.message, loading: false });
		}
	},

	searchStudents: (term: string) => {
		set((state) => ({
			students: state.students.filter((student) => student.studentName.toLowerCase().includes(term.toLowerCase())),
		}));
	},
}));

export default useStudentStore;
