import { useInfiniteQuery } from '@tanstack/react-query';
import { getStorages } from '../api';
import { StorageResponseType } from '../type';

export const useGetStoragesQuery = () => {
	const queryKey = ['storage'];

	const queryFn = async (): Promise<StorageResponseType> => {
		const storageDatas = await getStorages();

		return storageDatas;
	};

	const getNextPageParam = (lastPage: StorageResponseType, allPages: StorageResponseType[]) => {
		return lastPage.page + 1 <= lastPage.page_size ? allPages.length : undefined;
	};

	return useInfiniteQuery<StorageResponseType>({ queryKey, queryFn, getNextPageParam, initialPageParam: 0 });
};
