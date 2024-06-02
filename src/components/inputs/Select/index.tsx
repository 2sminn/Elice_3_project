import React from 'react';
import { StyledSelect } from './style';

export interface OptionType {
	id: number;
	name: string;
	value: string;
}

interface InputFieldProps extends React.InputHTMLAttributes<HTMLSelectElement> {
	width?: string;
	height?: string;
	options: OptionType[];
	active?: boolean;
}

const Select = ({ width, height, options, ...props }: InputFieldProps) => {
	return (
		<StyledSelect width={width} height={height} {...props}>
			{options.map((option) => (
				<option key={option.id + option.name} value={option.value}>
					{option.name}
				</option>
			))}
		</StyledSelect>
	);
};

export default Select;
