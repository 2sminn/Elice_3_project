import axiosApi from '../../../api/axios';
import { FilterSearchType, StorageResponseType } from '../type';

export const getStorages = async (searchFilter: FilterSearchType): Promise<StorageResponseType> => {
	const response = await axiosApi.get<StorageResponseType>(`payments?status=${searchFilter.isPaid}&page=1&size=10`);

	return response.data;
};
