import React, { FC, useEffect, useState } from 'react';
import PrimaryButton from '../../../../components/buttons/PrimaryButton';

interface AddressSearchProps {
	onComplete: (data: { address: string; zonecode: string }) => void;
}

const AddressSearch: FC<AddressSearchProps> = ({ onComplete }) => {
	const [isLoaded, setIsLoaded] = useState(false);

	useEffect(() => {
		const script = document.createElement('script');
		script.src = 'https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js';
		script.async = true;
		script.onload = () => setIsLoaded(true);
		document.head.appendChild(script);

		return () => {
			document.head.removeChild(script);
		};
	}, []);

	const handleClick = () => {
		if (!isLoaded) {
			alert('주소 검색 스크립트가 아직 로드되지 않았습니다.');
			return;
		}
		new (window as any).daum.Postcode({
			oncomplete: function (data: any) {
				const fullAddress = data.address;
				const zonecode = data.zonecode;
				onComplete({ address: fullAddress, zonecode: zonecode });
			},
		}).open();
	};

	return <PrimaryButton type="button" text="주소검색" onClick={handleClick} width="15%" />;
};

export default AddressSearch;
