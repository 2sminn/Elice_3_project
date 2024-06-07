import React, { useEffect, useRef } from 'react';
import PrimaryButton from '../../components/buttons/PrimaryButton';
import Select from '../../components/inputs/Select';
import TextInput from '../../components/inputs/TextInput';
import { Container, PageTitle, TableList } from '../../styles/commonStyle';
import { storageYearOption, storageMonthOption, storageOXOption } from './constants/options';
import { useGetStoragesQuery } from './hooks/useGetStoragesQuery';
import * as S from './style';
import { IoSearchOutline } from 'react-icons/io5';
import { formatDate } from '../../utils/formatDate';

const StoragePage = () => {
	const { data: storageDatas, fetchNextPage, hasNextPage, isFetching } = useGetStoragesQuery();

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
					fetchNextPage();
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

	return (
		<Container>
			<S.StorageContainer>
				<PageTitle>수납관리</PageTitle>
				<S.TabBox>
					<S.TabMenu $active={true}>전체</S.TabMenu>
					<S.TabMenu>수납내역</S.TabMenu>
					<S.TabMenu>미수납내역</S.TabMenu>
				</S.TabBox>
				<S.SearchContainer>
					<S.SearchTitle>
						<IoSearchOutline color="#fff" size={25} />
						<h3>검색필터</h3>
					</S.SearchTitle>
					<S.SearchBox>
						<S.SelectTopBox>
							<Select title="year-select" options={storageYearOption} />
							<Select title="month-select" options={storageMonthOption} />
							<Select title="ox-select" options={storageOXOption} />
							<TextInput placeholder="원생이름" />
						</S.SelectTopBox>
						<PrimaryButton type="button" text="검색" />
					</S.SearchBox>
				</S.SearchContainer>
				<S.StorageTable>
					<S.TableTitleBox>
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
					</S.TableTitleBox>
					<S.TableContentContainer>
						{storageDatas?.pages.map((page, index) => (
							<React.Fragment key={index}>
								{page.invoices.map((storage) => (
									<S.TableContentBox key={storage.order_id}>
										<TableList width="15%">{storage.student_name}</TableList>
										<TableList width="15%">1997.07.08</TableList>
										<TableList width="30%">{storage.lecture_info.lecture_name}</TableList>
										<TableList width="10%">O</TableList>
										<TableList width="15%">{formatDate(storage.due_date)}</TableList>
										<TableList width="15%">
											<PrimaryButton text="영수증 발급" width="90%" textSize="10px" isFill />
										</TableList>
									</S.TableContentBox>
								))}
							</React.Fragment>
						))}
						{isFetching && <div>Loading...</div>}
						<div ref={observerRef} style={{ height: '10px', background: 'transparent' }} />
					</S.TableContentContainer>
				</S.StorageTable>
			</S.StorageContainer>
		</Container>
	);
};

export default StoragePage;
