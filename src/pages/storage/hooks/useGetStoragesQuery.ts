import { useInfiniteQuery } from '@tanstack/react-query';
import { getStorages } from '../api';
import { FilterSearchType, StorageResponseType } from '../type';

export const useGetStoragesQuery = (searchFilter: FilterSearchType, page: number) => {
	const queryKey = ['storage'];

	const queryFn = async (): Promise<StorageResponseType> => {
		const storageDatas = await getStorages(searchFilter, page);

		return storageDatas;
	};

	const getNextPageParam = (lastPage: StorageResponseType, allPages: StorageResponseType[]) => {
		return !lastPage.last ? allPages.length : undefined;
	};

	return useInfiniteQuery<StorageResponseType>({ queryKey, queryFn, getNextPageParam, initialPageParam: 0 });
};
