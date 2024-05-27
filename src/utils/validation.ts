export interface Errors {
	username: string;
	password: string;
	confirmPassword: string;
	academyName: string;
	fullName: string;
	businessNumber: string;
	email: string;
	businessEmail: string;
	phoneNumber: string;
	mobileNumber: string;
	zipCode: string;
	address: string;
	detailedAddress: string;
}

export const validateForm = (form: any): Errors => {
	const errors: Errors = {
		username: '',
		password: '',
		confirmPassword: '',
		academyName: '',
		fullName: '',
		businessNumber: '',
		email: '',
		businessEmail: '',
		phoneNumber: '',
		mobileNumber: '',
		zipCode: '',
		address: '',
		detailedAddress: '',
	};

	if (!form.username.trim()) {
		errors.username = '사용자 이름을 입력해주세요.';
	}

	if (!form.email.includes('@')) {
		errors.email = '유효한 이메일 주소를 입력해주세요.';
	}

	// 비밀번호 유효성 검사
	const password = form.password;
	const hasLetter = /[a-zA-Z]/.test(password);
	const hasNumber = /\d/.test(password);
	const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(password);

	if (
		password.length < 8 ||
		!((hasLetter && hasNumber) || (hasLetter && hasSpecialChar) || (hasNumber && hasSpecialChar))
	) {
		errors.password = '비밀번호는 8자 이상이며, 영문자, 숫자, 특수문자 중 2가지 이상을 포함해야 합니다.';
	}

	if (form.password !== form.confirmPassword) {
		errors.confirmPassword = '비밀번호가 일치하지 않습니다.';
	}

	if (!form.academyName.trim()) {
		errors.academyName = '사업자명을 입력해주세요.';
	}

	if (!form.fullName.trim()) {
		errors.fullName = '대표자명을 입력해주세요.';
	}

	if (!form.businessNumber.trim()) {
		errors.businessNumber = '사업자번호를 입력해주세요.';
	}

	if (!form.businessEmail.includes('@')) {
		errors.businessEmail = '유효한 사업자 이메일 주소를 입력해주세요.';
	}

	if (!form.phoneNumber.trim()) {
		errors.phoneNumber = '일반전화를 입력해주세요.';
	}

	if (!form.mobileNumber.trim()) {
		errors.mobileNumber = '휴대폰번호를 입력해주세요.';
	}

	if (!form.zipCode.trim()) {
		errors.zipCode = '우편번호를 입력해주세요.';
	}

	if (!form.address.trim()) {
		errors.address = '주소를 입력해주세요.';
	}

	if (!form.detailedAddress.trim()) {
		errors.detailedAddress = '상세주소를 입력해주세요.';
	}

	return errors;
};

export const isFormValid = (errors: Errors): boolean => {
	return Object.values(errors).every((error) => error === '');
};
