import axios from 'axios';
import { errorAlert } from './alert';

export const handleError = (error: Error) => {
	if (axios.isAxiosError(error)) {
		errorAlert(error.response?.data?.message);
	} else {
		console.log(error);
	}
};
