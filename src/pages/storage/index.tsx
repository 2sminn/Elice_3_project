import React, { ChangeEventHandler, useEffect, useRef, useState } from 'react';
import PrimaryButton from '../../components/buttons/PrimaryButton';
import Select from '../../components/inputs/Select';
import TextInput from '../../components/inputs/TextInput';
import {
	Container,
	LoadingSpiner,
	PageTitle,
	TableContentBox,
	TableContentContainer,
	TableList,
	TableTitleBox,
	WarningMessage,
} from '../../styles/commonStyle';
import { storageYearOption, storageMonthOption, storageOXOption } from './constants/options';
import { useGetStoragesQuery } from './hooks/useGetStoragesQuery';
import * as S from './style';
import { IoSearchOutline } from 'react-icons/io5';
import { formatDate } from '../../utils/formatDate';
import { useSendReceiptMutation } from './hooks/useSendReceiptMutation';
import { invoiceType } from './type';

const StoragePage = () => {
	const [searchFilter, setSearchFilter] = useState({
		year: '',
		month: '',
		isPaid: 'ALL',
		studentName: '',
	});

	const [page, setPage] = useState(1);

	const {
		data: storageDatas,
		fetchNextPage,
		hasNextPage,
		isFetching,
		refetch,
	} = useGetStoragesQuery(searchFilter, page);

	const { mutate: sendReceiptMutate } = useSendReceiptMutation();

	const handleChangeFilter: ChangeEventHandler<HTMLInputElement | HTMLSelectElement> = (e) => {
		const { name, value } = e.target as HTMLInputElement | HTMLSelectElement;
		setSearchFilter((prev) => ({
			...prev,
			[name]: value,
		}));
	};

	const handleFilterSearch = () => {
		setPage(1);
		refetch();
	};

	const handleSendReceipt = (data: invoiceType) => () => {
		const paymentDate = data.updatedAt;
		const year = Number(paymentDate.split('-')[0]);
		const month = Number(paymentDate.split('-')[1]);

		const receiptData = {
			studentId: Number(data.studentId),
			year,
			month,
		};

		sendReceiptMutate(receiptData);
	};

	const observerRef = useRef(null);

	useEffect(() => {
		const observerOptions: IntersectionObserverInit = {
			root: null,
			rootMargin: '0px',
			threshold: 0.8,
		};

		const handleIntersection: IntersectionObserverCallback = (entries) => {
			entries.forEach((entry) => {
				if (entry.isIntersecting && hasNextPage && !isFetching) {
					setPage((prevPage) => prevPage + 1);
				}
			});
		};

		const observer = new IntersectionObserver(handleIntersection, observerOptions);

		if (observerRef.current) {
			observer.observe(observerRef.current);
		}

		return () => {
			if (observerRef.current) {
				observer.unobserve(observerRef.current);
			}
		};
	}, [fetchNextPage, hasNextPage, isFetching]);

	useEffect(() => {
		if (page > 0) {
			fetchNextPage();
		}
	}, [page, fetchNextPage]);

	return (
		<Container>
			<S.StorageContainer>
				<PageTitle>수납관리</PageTitle>
				{/* <S.TabBox>
					<S.TabMenu $active={true}>전체</S.TabMenu>
				</S.TabBox> */}
				<S.SearchContainer>
					<S.SearchTitle>
						<IoSearchOutline color="#fff" size={25} />
						<h3>검색필터</h3>
					</S.SearchTitle>
					<S.SearchBox>
						<S.SelectTopBox>
							<Select title="year-select" options={storageYearOption} name="year" onChange={handleChangeFilter} />
							<Select title="month-select" options={storageMonthOption} name="month" onChange={handleChangeFilter} />
							<Select title="ox-select" options={storageOXOption} name="isPaid" onChange={handleChangeFilter} />
							<TextInput placeholder="원생이름" name="studentName" onChange={handleChangeFilter} />
						</S.SelectTopBox>
						<PrimaryButton type="button" text="검색" onClick={handleFilterSearch} />
					</S.SearchBox>
				</S.SearchContainer>
				<S.StorageTable>
					<TableTitleBox>
						<TableList $isTitle width="15%">
							이름
						</TableList>
						<TableList $isTitle width="15%">
							생년월일
						</TableList>
						<TableList $isTitle width="30%">
							수강과목
						</TableList>
						<TableList $isTitle width="10%">
							수납여부
						</TableList>
						<TableList $isTitle width="15%">
							수납일
						</TableList>
						<TableList $isTitle width="15%"></TableList>
					</TableTitleBox>
					<TableContentContainer>
						{storageDatas?.pages.map((page, index) => (
							<React.Fragment key={index}>
								{page.content.length > 0 ? (
									page.content.map((storage) => (
										<TableContentBox key={storage.billId}>
											<TableList width="15%">{storage.studentName}</TableList>
											<TableList width="15%">{storage.birthDate}</TableList>
											<TableList width="30%">수학, 과학</TableList>
											<TableList width="10%">O</TableList>
											<TableList width="15%">{formatDate(storage.updatedAt)}</TableList>
											<TableList width="15%">
												<PrimaryButton
													text="영수증 발급"
													width="90%"
													textSize="13px"
													isFill
													onClick={handleSendReceipt(storage)}
												/>
											</TableList>
										</TableContentBox>
									))
								) : (
									<WarningMessage>수납 내역이 존재하지 않습니다.</WarningMessage>
								)}
							</React.Fragment>
						))}
						{isFetching && <LoadingSpiner />}
						<div ref={observerRef} style={{ height: '10px', background: 'transparent' }} />
					</TableContentContainer>
				</S.StorageTable>
			</S.StorageContainer>
		</Container>
	);
};

export default StoragePage;
