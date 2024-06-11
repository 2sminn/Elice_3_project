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
	fetchStudent: (studentId: string) => Promise<StudentType | null>;
	createStudent: (student: Omit<StudentType, 'studentId' | 'paymentInfo'>) => Promise<void>;
	updateStudent: (studentId: string, student: Partial<StudentType>) => Promise<void>;
	deleteStudent: (studentId: string) => Promise<void>;
	searchStudents: (term: string) => void;
	filterStudents: (filters: Partial<StudentType>) => void;
	selectStudent: (studentId: string) => void;
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

	fetchStudent: async (studentId: string) => {
		if (!studentId) {
			console.error('No studentId provided for fetching student');
			return null;
		}
		set({ loading: true, error: null });
		try {
			const student = await apiFetchStudent(studentId);
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

	updateStudent: async (studentId: string, student) => {
		set({ loading: true, error: null });
		try {
			const updatedStudent = await apiUpdateStudent(studentId, student);
			console.log('Updated Student:', updatedStudent);
			set((state) => ({
				students: state.students.map((s) => (s.studentId === studentId ? updatedStudent : s)),
				loading: false,
			}));
		} catch (error: any) {
			console.error('Error updating student:', error);
			set({ error: error.message, loading: false });
		}
	},

	deleteStudent: async (studentId: string) => {
		set({ loading: true, error: null });
		try {
			await apiDeleteStudent(studentId);
			console.log('Deleted Student ID:', studentId);
			set((state) => ({
				students: state.students.filter((s) => s.studentId !== studentId),
				loading: false,
			}));
		} catch (error: any) {
			console.error('Error deleting student:', error);
			set({ error: error.message, loading: false });
		}
	},

	searchStudents: (term: string) => {
		set((state) => ({
			students: state.students.filter((student) => student.studentName?.toLowerCase().includes(term.toLowerCase())),
		}));
	},

	filterStudents: (filters: Partial<StudentType>) => {
		set((state) => ({
			students: state.students.filter((student) => {
				return Object.entries(filters).every(([key, value]) => {
					if (!value) return true;
					return student[key as keyof StudentType]?.toString().toLowerCase().includes(value.toString().toLowerCase());
				});
			}),
		}));
	},

	selectStudent: (studentId: string) => {
		set((state) => ({
			students: state.students.map((student) => {
				if (student.studentId === studentId) {
					return { ...student, selected: !student.selected };
				} else {
					return student;
				}
			}),
		}));
	},
}));

export default useStudentStore;
