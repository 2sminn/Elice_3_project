import axiosApi from '../../../api/axios';
import { ChargeHistoryResponseType } from '../type';

export const getChargeHistory = async (): Promise<ChargeHistoryResponseType> => {
	const response = await axiosApi.get<ChargeHistoryResponseType>('/point/recharge/log');

	return response.data;
};
