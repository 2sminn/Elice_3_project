import { useMutation } from '@tanstack/react-query';
import { sendReceipt } from '../api';
import { ReceiptRequestType } from '../type';
import { successAlert } from '../../../utils/alert';

export const useSendReceiptMutation = () => {
	const mutationFn = (receipt: ReceiptRequestType) => {
		return sendReceipt(receipt);
	};

	const onSuccess = () => {
		successAlert('영수증 발급이 완료되었습니다.');
	};

	return useMutation({ mutationFn, onSuccess });
};
