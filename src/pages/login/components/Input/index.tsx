import React from 'react';
import { StyledInput } from './style';

type InputFieldProps = {
	type: string;
	placeholder: string;
	name: string;
	value: string;
	width?: string;
	onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
};

export const Input: React.FC<InputFieldProps> = ({ type, placeholder, name, value, width, onChange }) => {
	return (
		<StyledInput type={type} placeholder={placeholder} name={name} value={value} width={width} onChange={onChange} />
	);
};
