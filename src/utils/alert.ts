import Swal from 'sweetalert2';

export const successAlert = (title: string | undefined, text?: string | undefined) => {
	Swal.fire({
		icon: 'success',
		title: title,
		text: text || '',
	});
};

export const errorAlert = (title: string | undefined, text?: string | undefined) => {
	Swal.fire({
		icon: 'error',
		title: title,
		text: text || '',
	});
};
