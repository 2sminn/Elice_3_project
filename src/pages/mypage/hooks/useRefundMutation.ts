import { useMutation } from '@tanstack/react-query';
import { refundRequest } from '../api';
import { errorAlert, successAlert } from '../../../utils/alert';
import { RefundRequestType } from '../type';

export const useRefundMutation = () => {
	const mutationFn = async (data: RefundRequestType) => {
		await refundRequest(data);
	};

	const onSuccess = () => {
		successAlert('환불신청이 완료되었습니다.');
	};

	const onError = () => {
		errorAlert('환불신청에 실패하였습니다. 관리자에게 문의해주세요.');
	};

	return useMutation({ mutationFn, onSuccess, onError });
};
