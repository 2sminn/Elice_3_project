// stores/useLectureStore.ts
import { create } from 'zustand';
import {
	fetchLectures as apiFetchLectures,
	fetchLecture as apiFetchLecture,
	createLecture as apiCreateLecture,
	updateLecture as apiUpdateLecture,
	deleteLecture as apiDeleteLecture,
	LectureType,
} from '../pages/lectureMgrPage/api';

interface LectureStore {
	lectures: LectureType[];
	filteredLectures: LectureType[];
	lecture: LectureType | null;
	loading: boolean;
	error: string | null;
	fetchLectures: () => Promise<void>;
	fetchLecture: (lectureId: number) => Promise<LectureType | null>;
	createLecture: (lecture: Omit<LectureType, 'id'>) => Promise<void>;
	updateLecture: (lectureId: number, lecture: Partial<LectureType>) => Promise<void>;
	deleteLecture: (lectureId: number) => Promise<void>;
	searchLectures: (term: string, field: keyof LectureType) => void;
	selectLecture: (lectureId: number) => void;
}

const useLectureStore = create<LectureStore>((set) => ({
	lectures: [],
	filteredLectures: [],
	lecture: null,
	loading: false,
	error: null,

	fetchLectures: async () => {
		set({ loading: true, error: null });
		try {
			const lectures = await apiFetchLectures();
			set({ lectures, filteredLectures: lectures, loading: false });
		} catch (error: any) {
			set({ error: error.message, loading: false });
		}
	},

	fetchLecture: async (lectureId: number) => {
		set({ loading: true, error: null });
		try {
			const lecture = await apiFetchLecture(lectureId);
			set({ lecture, loading: false });
			return lecture;
		} catch (error: any) {
			set({ error: error.message, loading: false });
			return null;
		}
	},

	createLecture: async (lecture) => {
		set({ loading: true, error: null });
		try {
			const newLecture = await apiCreateLecture(lecture);
			set((state) => ({
				lectures: [...state.lectures, newLecture],
				filteredLectures: [...state.filteredLectures, newLecture],
				loading: false,
			}));
		} catch (error: any) {
			set({ error: error.message, loading: false });
		}
	},

	updateLecture: async (lectureId: number, lecture) => {
		set({ loading: true, error: null });
		try {
			const updatedLecture = await apiUpdateLecture(lectureId, lecture);
			set((state) => ({
				lectures: state.lectures.map((l) => (l.id === lectureId ? updatedLecture : l)),
				filteredLectures: state.filteredLectures.map((l) => (l.id === lectureId ? updatedLecture : l)),
				loading: false,
			}));
		} catch (error: any) {
			set({ error: error.message, loading: false });
		}
	},

	deleteLecture: async (lectureId: number) => {
		set({ loading: true, error: null });
		try {
			await apiDeleteLecture(lectureId);
			set((state) => ({
				lectures: state.lectures.filter((l) => l.id !== lectureId),
				filteredLectures: state.filteredLectures.filter((l) => l.id !== lectureId),
				loading: false,
			}));
		} catch (error: any) {
			set({ error: error.message, loading: false });
		}
	},

	searchLectures: (term: string, field: keyof LectureType) => {
		set((state) => ({
			filteredLectures: state.lectures.filter((lecture) =>
				lecture[field]?.toString().toLowerCase().includes(term.toLowerCase()),
			),
		}));
	},

	selectLecture: (lectureId: number) => {
		set((state) => ({
			lectures: state.lectures.map((lecture) =>
				lecture.id === lectureId ? { ...lecture, selected: !lecture.selected } : lecture,
			),
			filteredLectures: state.filteredLectures.map((lecture) =>
				lecture.id === lectureId ? { ...lecture, selected: !lecture.selected } : lecture,
			),
		}));
	},
}));

export default useLectureStore;
