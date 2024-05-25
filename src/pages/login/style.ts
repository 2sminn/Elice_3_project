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
	height: 70px;
	margin-bottom: 40px;
`;

export const SignUpTitle = styled.div`
	font-size: 16px;
	font-weight: 400;
	color: #616161;
	margin: 2rem;
	&:hover {
		color: #575757;
		font-weight: 400;
	}
`;
