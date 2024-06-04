export interface ChargeHistoryType {
	chargeId: string;
	academyName: string;
	createdAt: string;
	isRefund: boolean;
	cashAmount: number;
}

export interface ChargeHistoryResponseType {
	total_count: number;
	chargeHistory: ChargeHistoryType[];
	page: number;
	page_size: number;
}
