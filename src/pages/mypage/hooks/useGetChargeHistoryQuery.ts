import { useInfiniteQuery } from '@tanstack/react-query';
import { getChargeHistory } from '../api/getChargeHistory';
import { ChargeHistoryResponseType } from '../type';

export const useGetChargeHistoryQuery = () => {
	const queryKey = ['chargeHistory'];

	const queryFn = async (): Promise<ChargeHistoryResponseType> => {
		const chargeHistoryDatas = await getChargeHistory();

		return chargeHistoryDatas;
	};

	const getNextPageParam = (lastPage: ChargeHistoryResponseType, allPages: ChargeHistoryResponseType[]) => {
		return lastPage.page + 1 <= lastPage.page_size ? allPages.length : undefined;
	};

	return useInfiniteQuery<ChargeHistoryResponseType>({ queryKey, queryFn, getNextPageParam, initialPageParam: 0 });
};
