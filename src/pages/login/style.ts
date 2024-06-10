import { Link } from 'react-router-dom';
import styled from 'styled-components';

export const Container = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	min-height: 90vh;
`;

export const LogoImage = styled.img`
	width: 250px;
	margin-bottom: 40px;
`;

export const LoginForm = styled.form`
	width: 500px;
`;

export const InputContainer = styled.div`
	display: flex;
	flex-direction: column;
	gap: 10px;
	margin-bottom: 20px;
`;

export const SignUpBtn = styled(Link)`
	display: block;
	margin-top: 10px;
`;
