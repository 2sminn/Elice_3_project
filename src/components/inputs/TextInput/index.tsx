import { StyledInput } from './style';

interface InputFieldProps extends React.InputHTMLAttributes<HTMLInputElement> {
	width?: string;
}

const TextInput = ({ width, ...props }: InputFieldProps) => {
	return <StyledInput width={width} {...props} />;
};

export default TextInput;
