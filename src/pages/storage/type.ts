export interface invoiceType {
	paymentStatusId: number;
	studentId: number;
	studentName: string;
	birthDate: string;
	billId: number;
	paymentId: number;
	billStatus: string;
	updatedAt: string;
	totalPrice: number;
}

export interface StorageResponseType {
	content: invoiceType[];
	pageable: {
		pageNumber: number;
		pageSize: number;
		sort: {
			sorted: boolean;
			empty: boolean;
			unsorted: boolean;
		};
		offset: number;
		paged: boolean;
		unpaged: boolean;
	};
	totalPages: number;
	totalElements: number;
	last: boolean;
	size: number;
	number: number;
	sort: {
		sorted: boolean;
		empty: boolean;
		unsorted: boolean;
	};
	numberOfElements: number;
	first: boolean;
	empty: boolean;
}

export interface FilterSearchType {
	year: string;
	month: string;
	isPaid: string;
	studentName: string;
}
