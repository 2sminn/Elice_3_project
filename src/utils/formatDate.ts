export const formatDate = (inputDate: string) => {
	// 입력 날짜를 Date 객체로 변환
	const date = new Date(inputDate);

	const year = date.getFullYear();
	const month = String(date.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 1을 더해줌
	const day = String(date.getDate()).padStart(2, '0');

	return `${year}.${month}.${day}`;
};
