import axiosApi from '../../../../api/axios';
import { StudentType } from '../../../../types';
import { BillType, StudentSearchRequestType } from '../type';

export interface SendBillResponse {
	userId: number;
	message: string;
}

export const sendBill = async (billData: BillType): Promise<SendBillResponse> => {
	const response = await axiosApi.post<SendBillResponse>('/bill', billData);
	return response.data;
};

export const studentSearch = async (data: StudentSearchRequestType) => {
	const response = await axiosApi.post<StudentType[]>('/academy-students/search', data);
	return response.data;
};
