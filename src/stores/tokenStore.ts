import { create } from 'zustand';
import Cookies from 'js-cookie';

interface TokenStoreType {
	accessToken: string | null;
	setTokens: (accessToken: string) => void;
	clearTokens: () => void;
}

export const useTokenStore = create<TokenStoreType>((set) => ({
	accessToken: Cookies.get('accessToken') || null,
	setTokens: (accessToken: string) => {
		Cookies.set('accessToken', accessToken, { expires: 1 }); // 1일 후 만료
		set({ accessToken });
	},
	clearTokens: () => {
		Cookies.remove('accessToken');
		set({ accessToken: null });
	},
}));
