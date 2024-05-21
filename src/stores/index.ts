import { create } from 'zustand';

export const useCounterStore = create(() => ({
	count: 1,
	increse: () => {},
}));
