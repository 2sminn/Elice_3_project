export interface FormValues {
	email: string;
	password: string;
}

export interface LoginData {
	email: string;
	password: string;
}

export interface LoginResponse {
	academyId: string;
	roles: string[];
	accessToken: string;
	refreshToken: string;
	userId: string;
	email: string;
}
