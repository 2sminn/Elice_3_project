export interface UserType {
	userId: number;
	academyId: number;
	username: string;
	phoneNumber: string;
	academyName: string;
	businessNumber: string;
	academyEmail: string;
	zipCode: number;
	address: string;
	addressDetail: string;
	landlineNumber: string;
	point: number;
	totalPaidBill: number;
	lectureCount: number;
	studentCount: number;
}

export interface LectureSchedulesType {
	id: number;
	day: string;
	startTime: string;
	endTime: string;
}

export interface LecturesType {
	id: number;
	lectureName: string;
	price: number;
	teacherName: string;
	createdAt: string;
	updatedAt: string;
	lectureStatus: string;
	lectureSchedules: LectureSchedulesType[];
}

export interface StudentType {
	academyId: number;
	academyName: string;
	studentId: number;
	studentName: string;
	birthdate: string;
	phoneNumber: string;
	email: string;
	schoolName: string;
	grade: string;
	beforePaymentCount: number;
	paidPaymentCount: number;
	lectures: LecturesType[];
}
