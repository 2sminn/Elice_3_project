// useCustomForm.ts
import { useForm, Control, FieldErrors, UseFormHandleSubmit, FieldValues } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as Yup from 'yup';

type UseCustomFormReturn<T extends FieldValues> = {
	control: Control<T>;
	handleSubmit: UseFormHandleSubmit<T>;
	errors: FieldErrors<T>;
	reset: () => void;
};

function useCustomForm<T extends FieldValues>(
	validationSchema: Yup.ObjectSchema<T>,
	mode: 'onChange' | 'onBlur' | 'onSubmit' | 'onTouched' = 'onChange',
): UseCustomFormReturn<T> {
	const {
		control,
		handleSubmit,
		formState: { errors },
		reset,
	} = useForm<T>({
		resolver: yupResolver(validationSchema) as any,
		mode,
	});

	return {
		control,
		handleSubmit,
		errors,
		reset,
	};
}

export default useCustomForm;
