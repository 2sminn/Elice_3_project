import { MouseEventHandler } from 'react';
import usePopup from '../../../hooks/usePopup';
import { usePopupStore } from '../../../stores/popupStore';
import Backdrop from '../../Backdrop';

const PopupContainer = () => {
	const { popup } = usePopupStore();
	const { closePopup } = usePopup();

	const popupVisible = !!popup;

	if (!popupVisible) return null;

	const handleClick: MouseEventHandler<HTMLDivElement> = ({ target, currentTarget }) => {
		if (target === currentTarget) closePopup();
	};

	return (
		<Backdrop isView={popupVisible} handleClick={handleClick}>
			{popup}
		</Backdrop>
	);
};

export default PopupContainer;
