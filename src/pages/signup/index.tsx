import React, { FC, useState } from 'react';
import { Link } from 'react-router-dom';
import Logo from '/assets/images/logo.svg';
import PrimaryButton from '../../components/buttons/PrimaryButton';
import TextInput from '../../components/inputs/TextInput';
import AddressSearch from './components/AddressSearch/addresssearch';

import {
	Container,
	LogoImage,
	FormWrapper,
	Section,
	SectionTitle,
	FormItemWrapper,
	StyledTextarea,
	CheckboxWrapper,
	SubmitButtonWrapper,
	SignUpTitle,
	Divider,
	InputWithIcon,
} from './style';
import { FaUser, FaLock, FaEnvelope, FaPhone, FaBuilding, FaSearchLocation, FaMapMarkedAlt } from 'react-icons/fa';

const Signup: FC = () => {
	const [form, setForm] = useState({
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
		agreement1: false,
		agreement2: false,
		agreementAll: false,
	});

	const handleFormChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
		const { name, value, type } = e.target;
		let newValue: string | boolean = value;

		if (type === 'checkbox') {
			if (e.target instanceof HTMLInputElement) {
				newValue = e.target.checked;
			}
		}

		setForm((prev) => ({
			...prev,
			[name]: newValue,
		}));
	};

	const handleAgreementAllChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		const { checked } = e.target;
		setForm((prev) => ({
			...prev,
			agreement1: checked,
			agreement2: checked,
			agreementAll: checked,
		}));
	};

	const handleAddressComplete = (data: { address: string; zonecode: string }) => {
		setForm((prev) => ({
			...prev,
			address: data.address,
			zipCode: data.zonecode,
		}));
	};

	const handleSubmit = (e: React.FormEvent) => {
		e.preventDefault();
		// 회원가입 로직 추가
		console.log('Form submitted', form);
	};

	return (
		<Container>
			<LogoImage src={Logo} className="logo" alt="Logo" />
			<FormWrapper onSubmit={handleSubmit}>
				<Section>
					<SectionTitle>기본정보</SectionTitle>
					<FormItemWrapper>
						<InputWithIcon>
							<FaUser />
							<TextInput
								type="email"
								placeholder="이메일"
								name="email"
								value={form.email}
								onChange={handleFormChange}
							/>
						</InputWithIcon>
						<PrimaryButton type="button" text="중복확인" />
					</FormItemWrapper>
					<FormItemWrapper>
						<InputWithIcon>
							<FaLock />
							<TextInput
								type="password"
								placeholder="비밀번호"
								name="password"
								value={form.password}
								onChange={handleFormChange}
							/>
						</InputWithIcon>
						<InputWithIcon>
							<FaLock />
							<TextInput
								type="password"
								placeholder="비밀번호 확인"
								name="confirmPassword"
								value={form.confirmPassword}
								onChange={handleFormChange}
							/>
						</InputWithIcon>
					</FormItemWrapper>
					<p>* 비밀번호는 영문자, 숫자, 특수문자 중 2가지 이상 포함이어야 합니다.</p>
				</Section>

				<Divider />

				<Section>
					<SectionTitle>사업자정보</SectionTitle>
					<FormItemWrapper>
						<InputWithIcon>
							<FaBuilding />
							<TextInput
								type="text"
								placeholder="사업자명"
								name="academyName"
								value={form.academyName}
								onChange={handleFormChange}
							/>
						</InputWithIcon>
						<InputWithIcon>
							<FaUser />
							<TextInput
								type="text"
								placeholder="대표자명"
								name="fullName"
								value={form.fullName}
								onChange={handleFormChange}
							/>
						</InputWithIcon>
						<InputWithIcon>
							<FaBuilding />
							<TextInput
								type="text"
								placeholder="사업자번호"
								name="businessNumber"
								value={form.businessNumber}
								onChange={handleFormChange}
							/>
						</InputWithIcon>
					</FormItemWrapper>
					<FormItemWrapper>
						<InputWithIcon>
							<FaEnvelope />
							<TextInput
								type="email"
								placeholder="사업자이메일"
								name="businessEmail"
								value={form.businessEmail}
								onChange={handleFormChange}
							/>
						</InputWithIcon>
						<InputWithIcon>
							<FaPhone />
							<TextInput
								type="tel"
								placeholder="일반전화"
								name="phoneNumber"
								value={form.phoneNumber}
								onChange={handleFormChange}
							/>
						</InputWithIcon>
					</FormItemWrapper>
					<FormItemWrapper>
						<InputWithIcon>
							<FaSearchLocation />
							<TextInput
								type="text"
								placeholder="우편번호"
								name="zipCode"
								value={form.zipCode}
								onChange={handleFormChange}
							/>
						</InputWithIcon>
						<AddressSearch onComplete={handleAddressComplete} /> {/* 주소 검색 컴포넌트 사용 */}
					</FormItemWrapper>
					<FormItemWrapper>
						<InputWithIcon>
							<FaMapMarkedAlt />
							<TextInput
								type="text"
								placeholder="주소"
								name="address"
								value={form.address}
								onChange={handleFormChange}
							/>
						</InputWithIcon>
						<InputWithIcon>
							<FaMapMarkedAlt />
							<TextInput
								type="text"
								placeholder="상세주소"
								name="detailedAddress"
								value={form.detailedAddress}
								onChange={handleFormChange}
							/>
						</InputWithIcon>
					</FormItemWrapper>
				</Section>

				<Divider />

				<Section>
					<SectionTitle>약관 동의</SectionTitle>
					<CheckboxWrapper>
						<input
							type="checkbox"
							name="agreementAll"
							checked={form.agreementAll}
							onChange={handleAgreementAllChange}
						/>
						<label htmlFor="agreementAll">모든 약관에 동의합니다.</label>
					</CheckboxWrapper>
				</Section>

				<Section>
					<SectionTitle>이용약관</SectionTitle>
					<StyledTextarea
						readOnly
						value="Lorem Ipsum is simply dummy text of the printing and typesetting industry..."
					/>
					<CheckboxWrapper>
						<input type="checkbox" name="agreement1" checked={form.agreement1} onChange={handleFormChange} />
						<label htmlFor="agreement1">이용약관에 동의합니다.</label>
					</CheckboxWrapper>
				</Section>

				<Section>
					<SectionTitle>개인정보 수집 및 이용</SectionTitle>
					<StyledTextarea
						readOnly
						value="Lorem Ipsum is simply dummy text of the printing and typesetting industry..."
					/>
					<CheckboxWrapper>
						<input type="checkbox" name="agreement2" checked={form.agreement2} onChange={handleFormChange} />
						<label htmlFor="agreement2">개인정보 수집 및 이용에 동의합니다.</label>
					</CheckboxWrapper>
				</Section>

				<SubmitButtonWrapper>
					<PrimaryButton type="submit" text="회원가입" isFill />
				</SubmitButtonWrapper>
			</FormWrapper>
			<Link to="/login">
				<SignUpTitle>로그인 하러가기</SignUpTitle>
			</Link>
		</Container>
	);
};

export default Signup;
