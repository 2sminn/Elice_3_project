import axiosApi from '../../../api/axios';
import { UserType } from '../../../types';

export const getUserInfo = async (): Promise<UserType> => {
	const response = await axiosApi.get<UserType>('/users');

	return response.data;
};
