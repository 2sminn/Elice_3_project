import * as Yup from 'yup';

export const signUpValidationSchema = Yup.object().shape({
	email: Yup.string().email('이메일 형식으로 작성해주세요.').required('이메일은 필수로 입력하셔야 합니다.'),
	password: Yup.string()
		.min(8, '비밀번호는 최소 8자 이상이어야 합니다.')
		.matches(/[A-Z]/, '비밀번호에는 대문자가 하나 이상 포함되어야 합니다.')
		.matches(/[a-z]/, '비밀번호에는 소문자가 하나 이상 포함되어야 합니다.')
		.matches(/[0-9]/, '비밀번호에는 숫자가 하나 이상 포함되어야 합니다.')
		.matches(/[@$!%*?&]/, '비밀번호에는 특수문자가 하나 이상 포함되어야 합니다.')
		.required('비밀번호는 필수 입력 사항입니다.'),
	confirmPassword: Yup.string()
		.oneOf([Yup.ref('password'), null], '비밀번호가 일치하지 않습니다.')
		.required('비밀번호 확인은 필수 입력 사항입니다.'),
});
