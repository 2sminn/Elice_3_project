import { useInfiniteQuery } from '@tanstack/react-query';
import { getUseHistory } from '../api';
import { ChargeHistoryResponseType } from '../type';

export const useGetUseHistoryQuery = () => {
	const queryKey = ['useHistory'];

	const queryFn = async (): Promise<ChargeHistoryResponseType> => {
		const useHistoryDatas = await getUseHistory();

		return useHistoryDatas;
	};

	const getNextPageParam = (lastPage: ChargeHistoryResponseType, allPages: ChargeHistoryResponseType[]) => {
		return !lastPage.last ? allPages.length : undefined;
	};

	return useInfiniteQuery<ChargeHistoryResponseType>({ queryKey, queryFn, getNextPageParam, initialPageParam: 0 });
};
