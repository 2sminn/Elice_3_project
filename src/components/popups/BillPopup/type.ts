export interface BillType {
	studentId: number;
	message: string;
}

export interface StudentType {
	academy?: string;
	birthdate: string;
	email: string;
	grade: string;
	lectureDetails?: string | undefined;
	phoneNumber: string;
	studentId: number;
	studentName: string;
}
