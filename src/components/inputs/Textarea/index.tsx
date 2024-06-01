import React, { forwardRef } from 'react';
import { StyledTextarea } from './style';

interface InputFieldProps extends React.InputHTMLAttributes<HTMLTextAreaElement> {
	width?: string;
	height?: string;
}

const Textarea = forwardRef<HTMLTextAreaElement, InputFieldProps>(({ width, height, ...props }, ref) => {
	return <StyledTextarea ref={ref} width={width} height={height} {...props} />;
});

Textarea.displayName = 'Textarea';

export default Textarea;
