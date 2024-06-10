export interface invoiceType {
	order_id: string;
	academy_id: string;
	student_id: string;
	student_name: string;
	pay_total_price: number;
	status: string;
	created_at: string;
	due_date: string;
	paid_at: string;
	lecture_id: string;
	lecture_info: {
		lecture_name: string;
		lecture_price: number;
	};
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
