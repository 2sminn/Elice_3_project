import React, { FC, useState } from 'react';
import Logo from '/assets/images/logo.svg';

import { Container, LogoImage, SignUpBtn } from './style';

import PrimaryButton from '../../components/buttons/PrimaryButton';
import TextInput from '../../components/inputs/TextInput';

const Login: FC = () => {
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
			<form>
				<TextInput type="text" placeholder="이메일" name="email" value={form.email} onChange={handleFormChange} />
				<TextInput
					type="password"
					placeholder="비밀번호"
					name="password"
					value={form.password}
					onChange={handleFormChange}
				/>
				<PrimaryButton type="submit" text="로그인" isFill />
				<SignUpBtn to="/signup">
					<PrimaryButton type="submit" text="회원가입" />
				</SignUpBtn>
			</form>
		</Container>
	);
};

export default Login;
