import styled from 'styled-components';

export const Container = styled.div`
	width: 700px;
	padding: 30px;
	background-color: #fff;
	border-radius: 6px;
`;

export const Title = styled.h3`
	font-size: ${({ theme }) => theme.textSize.large};
	font-weight: 600;
	color: #000;
	margin-bottom: 20px;
`;
