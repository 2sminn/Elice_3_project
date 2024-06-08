import { getUserInfo } from '../api';
import { UserType } from '../../../types';
import { useQuery } from '@tanstack/react-query';

export const useGetUserQuery = () => {
	const queryKey = ['userInfo'];

	const queryFn = async (): Promise<UserType> => {
		const userInfo = await getUserInfo();

		return userInfo;
	};

	return useQuery({ queryKey, queryFn });
};
