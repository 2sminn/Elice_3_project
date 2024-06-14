import axiosApi from '../../../api/axios';
import { BillResponse } from '../types';

export const getBill = async (billId: number): Promise<BillResponse> => {
	const response = await axiosApi.get<BillResponse>(`/bill/${billId}`);

	return response.data;
};
