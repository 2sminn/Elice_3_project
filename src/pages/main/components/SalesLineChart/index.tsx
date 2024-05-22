import {
	Chart as ChartJS,
	CategoryScale,
	LinearScale,
	PointElement,
	LineElement,
	Title,
	Tooltip,
	Legend,
} from 'chart.js';
import { Line } from 'react-chartjs-2';

ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend);
export const options = {
	responsive: true,
};

const labels = ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월']; //x축 기준

export const data = {
	labels,
	datasets: [
		{
			label: '월 매출', //그래프 분류되는 항목
			data: [500000, 700000, 1500000, 500000, 350000, 2500000, 750000, 750000, 750000, 450000, 2250000, 1750000], //실제 그려지는 데이터(Y축 숫자)
			borderColor: 'rgb(255, 99, 132)', //그래프 선 color
			backgroundColor: 'rgba(255, 99, 132, 0.5)', //마우스 호버시 나타나는 분류네모 표시 bg
		},
	],
};

const LineChart = () => {
	return (
		<div className="contentWrap">
			<div className="contentInner">
				<Line options={options} data={data} />
			</div>
		</div>
	);
};

export default LineChart;
