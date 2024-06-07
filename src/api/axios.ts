// src/api/axios.js
import axios from 'axios';

const axiosApi = axios.create({
	baseURL: 'https://797d4f71-da19-4daf-b355-b0b50351dce0.mock.pstmn.io',
	headers: {
		'Content-Type': 'application/json',
	},
});

// 요청 인터셉터
axiosApi.interceptors.request.use(
	(config) => {
		// 요청 전에 수행할 작업 넣기
		return config;
	},
	(error) => {
		return Promise.reject(error);
	},
);

// 응답 인터셉터
axiosApi.interceptors.response.use(
	(response) => {
		return response;
	},
	(error) => {
		return Promise.reject(error);
	},
);

export default axiosApi;
