import { ReactNode } from 'react';
import { create } from 'zustand';

interface PopupStoreType {
	popup: null | ReactNode;
	setPopup: (popupNode: ReactNode) => void;
}

export const usePopupStore = create<PopupStoreType>((set) => ({
	popup: null,
	setPopup: (popupNode: ReactNode) => {
		set(() => ({
			popup: popupNode,
		}));
	},
}));
