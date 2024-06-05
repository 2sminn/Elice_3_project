import axiosApi from '../../../api/axios';
import { LoginData, LoginResponse } from '../type';

export const login = async (data: LoginData): Promise<LoginResponse> => {
	const response = await axiosApi.post<LoginResponse>('/auth/login', data);
	return response.data;
};
