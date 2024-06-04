import axiosApi from '../../../../api/axios';
import { BillType } from '../type';

export interface SendBillResponse {
	userId: number;
	message: string;
}

export const sendBill = async (billData: BillType): Promise<SendBillResponse> => {
	const response = await axiosApi.post<SendBillResponse>('/bill/send', billData);
	return response.data;
};
