import React, { useState } from 'react';
import Logo from '/assets/images/logo.svg';

import { Container, InputContainer, LoginForm, LogoImage, SignUpBtn } from './style';

import PrimaryButton from '../../components/buttons/PrimaryButton';
import TextInput from '../../components/inputs/TextInput';

const Login = () => {
	const [form, setForm] = useState({
		email: '',
		password: '',
	});

	const handleFormChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		const { name, value } = e.target;
		setForm((prev) => ({
			...prev,
			[name]: value,
		}));
	};

	return (
		<Container>
			<LogoImage src={Logo} className="logo" alt="에듀페이 로고" />
			<LoginForm>
				<InputContainer>
					<TextInput type="text" placeholder="이메일" name="email" value={form.email} onChange={handleFormChange} />
					<TextInput
						type="password"
						placeholder="비밀번호"
						name="password"
						value={form.password}
						onChange={handleFormChange}
					/>
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
