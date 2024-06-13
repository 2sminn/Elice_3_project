// import { StudentType } from '../../../types';

export interface BillType {
	studentId: number;
	message: string;
}

export interface StudentSearchRequestType {
	studentName?: string;
	phoneNumber?: string;
	email?: string;
}

// export interface StudentSearchResponseType {
// 	content: StudentType[];
// }
