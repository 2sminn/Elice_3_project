import { useMutation } from '@tanstack/react-query';
import { signUp } from '../api';
import { FormValues } from '../type';
import { errorAlert, successAlert } from '../../../utils/alert';
import { useNavigate } from 'react-router-dom';

export const useSignUpMutation = () => {
	const navigate = useNavigate();

	const mutationFn = async (data: FormValues) => {
		const result = await signUp(data);
		return result;
	};

	const onSuccess = () => {
		successAlert('회원가입이 성공적으로 완료되었습니다.');
		navigate('/');
	};

	const onError = () => {
		errorAlert('회원가입에 실패하였습니다. 관리자에게 문의하세요.');
	};

	return useMutation({ mutationFn, onSuccess, onError });
};
