import axiosApi from '../../../api/axios';
import { FormValues } from '../type';

export interface SignUpResponse {
	userId: number;
}

export const signUp = async (signUpData: FormValues): Promise<SignUpResponse> => {
	const response = await axiosApi.post<SignUpResponse>('/auth/sign-up', signUpData);
	return response.data;
};
