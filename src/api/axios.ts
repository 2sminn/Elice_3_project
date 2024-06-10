import axios, { AxiosResponse, InternalAxiosRequestConfig, AxiosError } from 'axios';
import { useTokenStore } from '../stores/tokenStore';

const axiosApi = axios.create({
	baseURL: import.meta.env.VITE_SERVER_URL,
});

axiosApi.interceptors.request.use(
	(config: InternalAxiosRequestConfig) => {
		const { accessToken } = useTokenStore.getState();
		if (accessToken) {
			config.headers = {
				...config.headers,
				Authorization: `Bearer ${accessToken}`,
			} as any;
		}
		return config;
	},
	(error) => {
		return Promise.reject(error);
	},
);

axiosApi.interceptors.response.use(
	(response: AxiosResponse) => {
		return response;
	},
	async (error: AxiosError) => {
		const originalRequest = error.config as InternalAxiosRequestConfig & { _retry?: boolean };
		if (error.response && error.response.status === 401 && !originalRequest._retry) {
			originalRequest._retry = true;

			const { refreshToken, setTokens, clearTokens } = useTokenStore.getState();
			if (refreshToken) {
				try {
					const refreshResponse = await axiosApi.post('/token/refresh', {
						refreshToken,
					});

					const newAccessToken = refreshResponse.data.accessToken;

					// 새로운 엑세스 토큰 저장
					setTokens(newAccessToken, refreshToken);

					// 새로운 엑세스 토큰으로 이전 요청 재시도
					originalRequest.headers.Authorization = `Bearer ${newAccessToken}`;
					return axiosApi(originalRequest);
				} catch (refreshError) {
					console.error('Error refreshing token:', refreshError);
					clearTokens();
					return Promise.reject(refreshError);
				}
			} else {
				// 리프레시 토큰이 없는 경우 로그인 페이지로 리다이렉트 또는 다른 처리를 수행할 수 있습니다.
				console.error('Refresh token is missing');
				clearTokens();
				return Promise.reject(error);
			}
		}
		return Promise.reject(error);
	},
);

export default axiosApi;
