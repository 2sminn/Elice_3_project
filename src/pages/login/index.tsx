import React, { FC, useState } from 'react';
import { Link } from 'react-router-dom';
import Logo from '/logo.jpeg';
import Button from '../../commons/components/buttons/Primarybutton/index';
import { Input } from '../../commons/components/inputs/Textinput/index';
import { Container, LogoImage, SignUpTitle } from './style';

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
		</Container>
	);
};

export default Login;
