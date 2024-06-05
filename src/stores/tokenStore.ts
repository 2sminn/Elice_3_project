import { create } from 'zustand';
import Cookies from 'js-cookie';

interface TokenStoreType {
	accessToken: string | null;
	refreshToken: string | null;
	setTokens: (accessToken: string, refreshToken: string) => void;
	clearTokens: () => void;
}

export const useTokenStore = create<TokenStoreType>((set) => ({
	accessToken: Cookies.get('accessToken') || null,
	refreshToken: Cookies.get('refreshToken') || null,
	setTokens: (accessToken: string, refreshToken: string) => {
		Cookies.set('accessToken', accessToken, { expires: 1 }); // 1일 후 만료
		Cookies.set('refreshToken', refreshToken, { expires: 7 }); // 7일 후 만료
		set({ accessToken, refreshToken });
	},
	clearTokens: () => {
		Cookies.remove('accessToken');
		Cookies.remove('refreshToken');
		set({ accessToken: null, refreshToken: null });
	},
}));
