import React, { FC, useState } from 'react';
import { Link } from 'react-router-dom';
import Logo from '/logo.jpeg';
import Button from '../login/components/Button/index';
import { Input } from '../login/components/Input/index';
import { Container, LogoImage, SignUpTitle, SocialLoginTitle } from './style';

const RedirectUri = `${import.meta.env.VITE_REDIRECT_URI}`;
const kakaoURL = `https://kauth.kakao.com/oauth/authorize?client_id=${import.meta.env.VITE_KAKAO_CLIENT_ID}&redirect_uri=${RedirectUri}&response_type=code`;

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

	const kakaohandleLogin = () => {
		window.location.href = kakaoURL;
	};

	return (
		<Container>
			<LogoImage src={Logo} className="logo" alt="Vite logo" />
			<form>
				<Input type="email" placeholder="이메일" name="email" value={form.email} onChange={handleFormChange} />
				<Input
					type="password"
					placeholder="비밀번호"
					name="password"
					value={form.password}
					onChange={handleFormChange}
				/>
				<Button type="submit" text="로그인" backcolor="#FFCB46" textcolor="#000000" />
			</form>
			<Link to="/signup">
				<SignUpTitle>회원가입</SignUpTitle>
			</Link>
			<SocialLoginTitle>SNS 계정으로 로그인</SocialLoginTitle>
			<Button type="button" text="카카오 로그인" backcolor="#FEE500" textcolor="#000000" onClick={kakaohandleLogin} />
		</Container>
	);
};

export default Login;
