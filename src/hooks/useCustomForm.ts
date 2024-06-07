import {
	useForm,
	Control,
	FieldErrors,
	UseFormHandleSubmit,
	FieldValues,
	UseFormSetValue,
	Resolver,
} from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as Yup from 'yup';

type UseCustomFormReturn<T extends FieldValues> = {
	control: Control<T>;
	handleSubmit: UseFormHandleSubmit<T>;
	errors: FieldErrors<T>;
	reset: () => void;
	setValue: UseFormSetValue<T>;
};

function useCustomForm<T extends FieldValues>(
	validationSchema: Yup.ObjectSchema<T>,
	mode: 'onSubmit' | 'onChange',
): UseCustomFormReturn<T> {
	const resolver: Resolver<T> = yupResolver(validationSchema) as unknown as Resolver<T>;

	const {
		control,
		handleSubmit,
		formState: { errors },
		reset,
		setValue,
	} = useForm<T>({
		resolver: resolver,
		mode,
	});

	return {
		control,
		handleSubmit,
		errors,
		reset,
		setValue,
	};
}

export default useCustomForm;
