import axiosApi from '../../../../api/axios';
import { BillType, StudentType } from '../type';

export interface SendBillResponse {
	userId: number;
	message: string;
}

export const sendBill = async (billData: BillType): Promise<SendBillResponse> => {
	const response = await axiosApi.post<SendBillResponse>('/bill', billData);
	return response.data;
};

export const getStudents = async () => {
	const response = await axiosApi.get<StudentType[]>('/academy-students');
	return response;
};
