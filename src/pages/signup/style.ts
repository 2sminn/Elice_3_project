import styled from 'styled-components';

export const Container = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	min-height: 90vh;
	padding: 20px;
	background-color: #f5f5f5;
`;

export const LogoImage = styled.img`
	width: 150px;
	height: 50px;
	margin-bottom: 20px;
`;

export const FormWrapper = styled.form`
	display: flex;
	flex-direction: column;
	align-items: center;
	width: 100%;
	max-width: 1000px;
	width: 80vw;
	background-color: #ffffff;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
`;

export const Section = styled.div`
	width: 100%;
	margin-bottom: 20px;
`;

export const SectionTitle = styled.h2`
	font-size: 18px;
	font-weight: 500;
	color: #333;
	margin-bottom: 10px;
`;

export const FormItemWrapper = styled.div`
	display: flex;
	flex-wrap: wrap;
	gap: 10px;
	width: 100%;
	margin-bottom: 10px;

	& > div,
	& > button {
		flex: 1;
		min-width: calc(50% - 10px);
	}
`;

export const StyledTextarea = styled.textarea`
	width: 100%;
	height: 100px;
	margin-bottom: 10px;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
	resize: none;
	background-color: #f9f9f9;
`;

export const CheckboxWrapper = styled.div`
	display: flex;
	align-items: center;
	margin-bottom: 10px;

	input {
		margin-right: 10px;
	}
`;

export const SubmitButtonWrapper = styled.div`
	display: flex;
	justify-content: center;
	width: 100%;
	margin-top: 20px;
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

export const Divider = styled.hr`
	width: 100%;
	border: 0;
	height: 1px;
	background: #ccc;
	margin: 20px 0;
`;

export const InputWithIcon = styled.div`
	display: flex;
	align-items: center;
	background-color: #f9f9f9;
	border: 1px solid #ccc;
	border-radius: 4px;
	padding: 10px;
	width: 100%;

	svg {
		margin-right: 10px;
		color: #666;
	}

	input {
		width: 100%;
		border: none;
		outline: none;
		background: none;
	}
`;
