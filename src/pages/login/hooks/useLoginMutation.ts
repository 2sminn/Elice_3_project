import { useMutation } from '@tanstack/react-query';
import { login } from '../api';
import { LoginData, LoginResponse } from '../type';
import { successAlert } from '../../../utils/alert';
import { useTokenStore } from '../../../stores/tokenStore';
import { useNavigate } from 'react-router-dom';

export const useLoginMutation = () => {
	const { setTokens } = useTokenStore();

	const navigate = useNavigate();

	const mutationFn = async (data: LoginData) => {
		return await login(data);
	};

	const onSuccess = (success: LoginResponse) => {
		const accessToken = success.accessToken;
		setTokens(accessToken);

		successAlert('로그인 성공!');

		navigate('/');
	};

	const onError = (error: Error) => {
		console.error(error.message);
	};

	return useMutation({ mutationFn, onSuccess, onError });
};
