import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import Logo from '/assets/images/logo.svg';
import PrimaryButton from '../../components/buttons/PrimaryButton';
import TextInput from '../../components/inputs/TextInput';
import AddressSearch from './components/AddressSearch/addresssearch';
import { termsOfService, privacyPolicy } from './termsofService';
import { Controller } from 'react-hook-form';
import * as Yup from 'yup';

import {
	Container,
	LogoImage,
	Section,
	SectionTitle,
	StyledTextarea,
	CheckboxWrapper,
	SubmitButtonWrapper,
	SignUpTitle,
	Divider,
	FormWrapper,
	BetweenBox,
	ColumnGapBox,
} from './style';
import useCustomForm from '../../hooks/useCustomForm';
import ErrorMessage from '../../components/ErrorMessage';
import { FormValues } from './type';
import { useSignUpMutation } from './hooks/useSignUpMutation';
import { errorAlert, successAlert } from '../../utils/alert';
import axiosApi from '../../api/axios';

const schema = Yup.object().shape({
	email: Yup.string().required('이메일은 필수 입력 사항입니다.'),
	password: Yup.string()
		.required('비밀번호는 필수 입력 사항입니다.')
		.matches(
			/^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*])(?=.{10,})/,
			'비밀번호는 영문, 숫자, 특수문자를 포함하여 10자리 이상이어야 합니다.',
		),
	confirmPassword: Yup.string().test('password-match', '비밀번호가 일치하지 않습니다.', function (value) {
		return this.parent.password === value;
	}),
	academyName: Yup.string().required('사업자명은 필수 입력 사항입니다.'),
	username: Yup.string().required('대표자명은 필수 입력 사항입니다.'),
	businessNumber: Yup.string().required('사업자번호는 필수 입력 사항입니다.'),
	academyEmail: Yup.string().email('올바른 이메일 형식이 아닙니다.').required('사업자 이메일은 필수 입력 사항입니다.'),
	phoneNumber: Yup.string().required('핸드폰번호는 필수 입력 사항입니다.'),
	landlineNumber: Yup.string().required('일반 전화번호는 필수 입력 사항입니다.'),
	zipCode: Yup.string().required('우편번호는 필수 입력 사항입니다.'),
	address: Yup.string().required('주소는 필수 입력 사항입니다.'),
	addressDetail: Yup.string().required('상세주소는 필수 입력 사항입니다.'),
});

type SubmitHandler<TSubmitFieldValues extends FormValues> = (
	data: TSubmitFieldValues,
	e?: React.BaseSyntheticEvent,
) => void | Promise<void>;

const Signup = ({ isEdit }: { isEdit?: boolean }) => {
	const [agreements, setAgreements] = useState({
		agreementAll: false,
		agreement1: false,
		agreement2: false,
	});
	const [isCheckEmail, setIsCheckEmail] = useState(true);

	const { control, handleSubmit, errors, setValue, getValues } = useCustomForm<FormValues>(schema, 'onChange');
	const { mutate: signUpMutate } = useSignUpMutation();

	const navigate = useNavigate();

	const handleCheckboxChange = (name: string, checked: boolean) => {
		setAgreements((prev) => {
			const newAgreements = { ...prev, [name]: checked };

			if (name === 'agreementAll') {
				return {
					agreementAll: checked,
					agreement1: checked,
					agreement2: checked,
				};
			} else {
				const allChecked = newAgreements.agreement1 && newAgreements.agreement2;
				return {
					...newAgreements,
					agreementAll: allChecked,
				};
			}
		});
	};

	// 주소 검색 완료 핸들러
	const handleAddressComplete = (data: { address: string; zonecode: string }) => {
		setValue('zipCode', data.zonecode);
		setValue('address', data.address);
	};

	const checkEmail = async () => {
		try {
			const email = getValues('email');
			const isCheck = await axiosApi.get(`/auth/check-email?email=${email}`);
			if (isCheck.data.result) {
				errorAlert('이미 사용중인 이메일 입니다.');
				setIsCheckEmail(true);
			} else {
				successAlert('사용 가능한 이메일입니다.');
				setIsCheckEmail(false);
			}
		} catch (error) {
			console.error(error);
		}
	};

	const onSubmit: SubmitHandler<FormValues> = (data) => {
		if (!agreements.agreementAll) {
			errorAlert('개인정보 활용 동의 및 이용약관에 동의해주세요.');
			return;
		}

		if (isCheckEmail) {
			errorAlert('이메일 중복확인이 필요합니다.');
			return;
		}

		const { confirmPassword, ...formData } = data;
		console.log(confirmPassword);
		signUpMutate(formData);
	};

	useEffect(() => {
		if (isEdit) {
			errorAlert('준비중인 기능입니다.');
			navigate('/');
		}
	}, []);

	return (
		<Container>
			<LogoImage src={Logo} className="logo" alt="Logo" />
			<FormWrapper onSubmit={handleSubmit(onSubmit)}>
				<Section>
					<SectionTitle>기본정보</SectionTitle>
					<ColumnGapBox>
						<BetweenBox>
							<Controller
								name="email"
								control={control}
								render={({ field }) => <TextInput type="email" placeholder="이메일" width="84%" {...field} />}
							/>

							<PrimaryButton type="button" text="중복확인" width="15%" onClick={checkEmail} />
						</BetweenBox>
						{errors.email && <ErrorMessage message={errors.email.message} />}
						<Controller
							name="password"
							control={control}
							render={({ field }) => <TextInput type="password" placeholder="비밀번호" {...field} />}
						/>

						{errors.password && <ErrorMessage message={errors.password.message} />}
						<Controller
							name="confirmPassword"
							control={control}
							render={({ field }) => <TextInput type="password" placeholder="비밀번호 확인" {...field} />}
						/>

						{errors.confirmPassword && <ErrorMessage message={errors.confirmPassword.message} />}
					</ColumnGapBox>
				</Section>
				<Divider />
				<Section>
					<SectionTitle>사업자정보</SectionTitle>
					<ColumnGapBox>
						<Controller
							name="academyName"
							control={control}
							render={({ field }) => <TextInput type="text" placeholder="사업자명" {...field} />}
						/>

						{errors.academyName && <ErrorMessage message={errors.academyName.message} />}
						<Controller
							name="username"
							control={control}
							render={({ field }) => <TextInput type="text" placeholder="대표자명" {...field} />}
						/>

						{errors.username && <ErrorMessage message={errors.username.message} />}
						<Controller
							name="businessNumber"
							control={control}
							render={({ field }) => <TextInput type="text" placeholder="사업자번호" {...field} />}
						/>

						{errors.businessNumber && <ErrorMessage message={errors.businessNumber.message} />}
						<Controller
							name="academyEmail"
							control={control}
							render={({ field }) => <TextInput type="email" placeholder="사업자이메일" {...field} />}
						/>
						{errors.academyEmail && <ErrorMessage message={errors.academyEmail.message} />}
						<Controller
							name="phoneNumber"
							control={control}
							render={({ field }) => <TextInput type="tel" placeholder="핸드폰 번호" {...field} />}
						/>
						{errors.phoneNumber && <ErrorMessage message={errors.phoneNumber.message} />}
						<Controller
							name="landlineNumber"
							control={control}
							render={({ field }) => <TextInput type="tel" placeholder="일반전화" {...field} />}
						/>
						{errors.landlineNumber && <ErrorMessage message={errors.landlineNumber.message} />}
						<BetweenBox>
							<Controller
								name="zipCode"
								control={control}
								render={({ field }) => <TextInput type="text" placeholder="우편번호" width="84%" {...field} />}
							/>

							<AddressSearch onComplete={handleAddressComplete} />
						</BetweenBox>
						{errors.zipCode && <ErrorMessage message={errors.zipCode.message} />}
						<Controller
							name="address"
							control={control}
							render={({ field }) => <TextInput type="text" placeholder="주소" {...field} />}
						/>
						{errors.address && <ErrorMessage message={errors.address.message} />}
						<Controller
							name="addressDetail"
							control={control}
							render={({ field }) => <TextInput type="text" placeholder="상세주소" {...field} />}
						/>
						{errors.addressDetail && <ErrorMessage message={errors.addressDetail.message} />}
					</ColumnGapBox>
				</Section>
				<Divider />
				{!isEdit && (
					<>
						<Section>
							<SectionTitle>약관 동의</SectionTitle>
							<CheckboxWrapper>
								<input
									type="checkbox"
									name="agreementAll"
									checked={agreements.agreementAll}
									onChange={(e) => handleCheckboxChange('agreementAll', e.target.checked)}
								/>
								<label htmlFor="agreementAll">모든 약관에 동의합니다.</label>
							</CheckboxWrapper>
						</Section>

						<Section>
							<SectionTitle>이용약관</SectionTitle>
							<StyledTextarea readOnly value={termsOfService} />
							<CheckboxWrapper>
								<input
									type="checkbox"
									name="agreement1"
									checked={agreements.agreement1}
									onChange={(e) => handleCheckboxChange('agreement1', e.target.checked)}
								/>
								<label htmlFor="agreement1">이용약관에 동의합니다.</label>
							</CheckboxWrapper>
						</Section>

						<Section>
							<SectionTitle>개인정보 수집 및 이용</SectionTitle>
							<StyledTextarea readOnly value={privacyPolicy} />
							<CheckboxWrapper>
								<input
									type="checkbox"
									name="agreement2"
									checked={agreements.agreement2}
									onChange={(e) => handleCheckboxChange('agreement2', e.target.checked)}
								/>
								<label htmlFor="agreement2">개인정보 수집 및 이용에 동의합니다.</label>
							</CheckboxWrapper>
						</Section>
					</>
				)}
				<SubmitButtonWrapper>
					<PrimaryButton type="submit" text={isEdit ? '회원정보 수정' : '회원가입'} isFill />
				</SubmitButtonWrapper>
			</FormWrapper>
			{!isEdit && (
				<Link to="/login">
					<SignUpTitle>로그인 하러가기</SignUpTitle>
				</Link>
			)}
		</Container>
	);
};

export default Signup;
