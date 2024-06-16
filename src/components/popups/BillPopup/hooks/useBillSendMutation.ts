import { useMutation } from '@tanstack/react-query';
import { BillType } from '../type';
import { sendBill } from '../api';
import usePopup from '../../../../hooks/usePopup';
import { errorAlert, successAlert } from '../../../../utils/alert';

export const useBillSendMutation = () => {
	const { closePopup } = usePopup();

	const mutationFn = async (billData: BillType) => {
		await sendBill(billData);
	};

	const onSuccess = () => {
		successAlert('청구서 발송 성공');
		closePopup();
	};

	const onError = (error: any) => {
		console.log(error);
		errorAlert(error.response.data.messageDetail);
	};

	return useMutation({ mutationFn, onSuccess, onError });
};
