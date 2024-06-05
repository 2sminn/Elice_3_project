import Logo from '/assets/images/logo.svg';

import { Container, InputContainer, LoginForm, LogoImage, SignUpBtn } from './style';

import PrimaryButton from '../../components/buttons/PrimaryButton';
import TextInput from '../../components/inputs/TextInput';
import { FormValues } from './type';
import useCustomForm from '../../hooks/useCustomForm';
import * as Yup from 'yup';
import ErrorMessage from '../../components/ErrorMessage';
import { Controller } from 'react-hook-form';
import { useLoginMutation } from './hooks/useLoginMutation';

const schema = Yup.object().shape({
	email: Yup.string().required('이메일은 필수 입력 사항입니다.'),
	password: Yup.string()
		.required('비밀번호는 필수 입력 사항입니다.')
		.matches(
			/^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*])(?=.{10,})/,
			'비밀번호는 영문, 숫자, 특수문자를 포함하여 10자리 이상이어야 합니다.',
		),
});

type SubmitHandler<TSubmitFieldValues extends FormValues> = (
	data: TSubmitFieldValues,
	e?: React.BaseSyntheticEvent,
) => void | Promise<void>;

const Login = () => {
	const { control, handleSubmit, errors, reset } = useCustomForm<FormValues>(schema, 'onChange');
	const { mutate: loginMutate } = useLoginMutation();

	const onSubmitLogin: SubmitHandler<FormValues> = (data) => {
		loginMutate(data);
		reset();
	};

	return (
		<Container>
			<LogoImage src={Logo} className="logo" alt="에듀페이 로고" />
			<LoginForm onSubmit={handleSubmit(onSubmitLogin)}>
				<InputContainer>
					<Controller
						name="email"
						control={control}
						render={({ field }) => <TextInput type="text" placeholder="이메일" {...field} />}
					/>
					{errors.email && <ErrorMessage message={errors.email.message} />}
					<Controller
						name="password"
						control={control}
						render={({ field }) => <TextInput type="password" placeholder="비밀번호" {...field} />}
					/>
					{errors.password && <ErrorMessage message={errors.password.message} />}
				</InputContainer>

				<PrimaryButton type="submit" text="로그인" isFill />
				<SignUpBtn to="/signup">
					<PrimaryButton type="submit" text="회원가입" />
				</SignUpBtn>
			</LoginForm>
		</Container>
	);
};

export default Login;
