// src/api/axios.js
import axios from 'axios';
import { useTokenStore } from '../stores/tokenStore';

const axiosApi = axios.create({
	baseURL: 'http://34.47.70.191:8080',
});

axiosApi.interceptors.request.use(
	(config) => {
		const { accessToken } = useTokenStore();
		if (accessToken) {
			config.headers.Authorization = `Bearer ${accessToken}`;
		}
		return config;
	},
	(error) => {
		return Promise.reject(error);
	},
);

axiosApi.interceptors.response.use(
	(response) => {
		return response;
	},
	(error) => {
		return Promise.reject(error);
	},
);

export default axiosApi;
