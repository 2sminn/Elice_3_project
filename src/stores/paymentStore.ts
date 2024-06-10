import { create } from 'zustand';

interface SuccessResType {
	apply_num: string;
	bank_name: string | null;
	buyer_addr: string;
	buyer_email: string;
	buyer_name: string;
	buyer_postcode: string;
	buyer_tel: string;
	card_name: string | null;
	card_number: string;
	card_quota: number;
	currency: string;
	custom_data: string | null;
	imp_uid: string;
	merchant_uid: string;
	name: string;
	paid_amount: number;
	paid_at: number;
	pay_method: string;
	pg_provider: string;
	pg_tid: string;
	pg_type: string;
	receipt_url: string;
	status: string;
	success: boolean;
}

interface ErrorResType {
	error_msg: string;
	imp_uid: string;
	merchant_uid: string;
	pay_method: string;
	pg_provider: string;
	pg_type: string;
	success: boolean;
}

interface PaymentSuccessStoreType {
	successRes: SuccessResType | null;
	setSuccessRes: (response: SuccessResType) => void;
}
interface PaymentErrorStoreType {
	errorRes: ErrorResType | null;
	setErrorRes: (response: ErrorResType) => void;
}

export const usePaymentSuccessStore = create<PaymentSuccessStoreType>((set) => ({
	successRes: null,
	setSuccessRes: (response: SuccessResType) => {
		set(() => ({
			successRes: response,
		}));
	},
}));

export const usePaymentErrorStore = create<PaymentErrorStoreType>((set) => ({
	errorRes: null,
	setErrorRes: (response: ErrorResType) => {
		set(() => ({
			errorRes: response,
		}));
	},
}));
