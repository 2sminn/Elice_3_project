import { useQuery } from '@tanstack/react-query';
import { getBill } from '../api';
import { BillResponse } from '../types';

export const useGetBillQuery = (billId: number) => {
	const queryKey = ['bill', billId];

	const queryFn = async (): Promise<BillResponse> => {
		const response = await getBill(billId);

		return response;
	};

	return useQuery({ queryKey, queryFn });
};
