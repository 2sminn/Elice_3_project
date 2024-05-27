import React, { forwardRef } from 'react';
import { StyledInput } from './style';

interface InputFieldProps extends React.InputHTMLAttributes<HTMLInputElement> {
	width?: string;
}

const TextInput = forwardRef<HTMLInputElement, InputFieldProps>(({ width, ...props }, ref) => {
	return <StyledInput ref={ref} width={width} {...props} />;
});

TextInput.displayName = 'TextInput';

export default TextInput;
