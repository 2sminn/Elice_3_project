import { useForm, Control, FieldErrors, UseFormHandleSubmit, FieldValues, UseFormSetValue } from 'react-hook-form';
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
	const {
		control,
		handleSubmit,
		formState: { errors },
		reset,
		setValue,
	} = useForm<T>({
		resolver: yupResolver(validationSchema) as any,
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
