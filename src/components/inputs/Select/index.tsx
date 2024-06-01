import React, { forwardRef } from 'react';
import { StyledSelect } from './style';

export interface optionType {
	id: number;
	name: string;
	value: string;
}

interface InputFieldProps extends React.InputHTMLAttributes<HTMLSelectElement> {
	width?: string;
	height?: string;
	options: optionType[];
}

const Select = forwardRef<HTMLSelectElement, InputFieldProps>(({ width, height, options, ...props }, ref) => {
	return (
		<StyledSelect ref={ref} width={width} height={height} {...props}>
			{options.map((option) => (
				<option key={option.id} value={option.value}>
					{option.name}
				</option>
			))}
		</StyledSelect>
	);
});

Select.displayName = 'Select';

export default Select;
