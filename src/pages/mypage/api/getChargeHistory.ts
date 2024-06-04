import axiosApi from '../../../api/axios';
import { ChargeHistoryResponseType } from '../type';

export const getChargeHistory = async (): Promise<ChargeHistoryResponseType> => {
	const response = await axiosApi.get<ChargeHistoryResponseType>(
		'https://797d4f71-da19-4daf-b355-b0b50351dce0.mock.pstmn.io/charge-history',
	);

	return response.data;
};
