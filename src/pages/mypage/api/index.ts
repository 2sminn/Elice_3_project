import axiosApi from '../../../api/axios';
import { ChargeHistoryResponseType, RefundRequestType } from '../type';

export const getChargeHistory = async (): Promise<ChargeHistoryResponseType> => {
	const response = await axiosApi.get<ChargeHistoryResponseType>('/point/recharge/log');

	return response.data;
};

export const getUseHistory = async (): Promise<ChargeHistoryResponseType> => {
	const response = await axiosApi.get<ChargeHistoryResponseType>('/point/use/log');

	return response.data;
};

export const refundRequest = async (data: RefundRequestType) => {
	await axiosApi.post('/point/refund', data);
};
