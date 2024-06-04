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
	total_count: number;
	invoices: invoiceType[];
	page: number;
	page_size: number;
}
