import { useMutation } from '@tanstack/react-query';
import { addPoint } from '../api';
import { PointReqType } from '../type';
import { successAlert } from '../../../utils/alert';

export const useAddPointMutation = () => {
	const mutationFn = async (data: PointReqType) => {
		await addPoint(data);
	};

	const onSuccess = () => {
		successAlert('청구서 충전이 완료되었습니다.');
	};

	return useMutation({ mutationFn: (data: PointReqType) => mutationFn(data), onSuccess });
};
