export interface ChargeHistoryType {
	id: number;
	userId: number;
	point: number;
	paymentUid: string;
	createdAt: string;
}

export interface ChargeHistoryResponseType {
	content: ChargeHistoryType[];
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

export interface RefundRequestType {
	user_id?: number;
	imp_uid: string;
}
