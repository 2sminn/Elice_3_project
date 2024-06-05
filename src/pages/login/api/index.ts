import axiosApi from '../../../api/axios';
import { LoginData, LoginResponse } from '../type';

export const login = async (data: LoginData): Promise<LoginResponse> => {
	const formData = new FormData();
	formData.append('email', data.email);
	formData.append('password', data.password);
	const response = await axiosApi.post<LoginResponse>('/auth/login', formData, {
		headers: {
			'Content-Type': 'multipart/form-data',
		},
	});
	return response.data;
};
