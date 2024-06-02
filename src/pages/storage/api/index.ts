import axiosApi from '../../../api/axios';
import { StorageResponseType } from '../type';

export const getStorages = async (): Promise<StorageResponseType> => {
	const response = await axiosApi.get<StorageResponseType>(
		'https://797d4f71-da19-4daf-b355-b0b50351dce0.mock.pstmn.io/payments/invoices?page=1&page_size=10&status=paid&start_date=2023-01-01T00:00:00&end_date=2023-12-31T23:59:59',
	);

	return response.data;
};
