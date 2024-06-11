import axiosApi from '../../../api/axios';
import { UserType } from '../../../types';
import { PointReqType } from '../type';

export const getUserInfo = async (): Promise<UserType> => {
	const response = await axiosApi.get<UserType>('/users');

	return response.data;
};

export const addPoint = async (data: PointReqType) => {
	const response = await axiosApi.post('/point/recharge', data);

	return response;
};
