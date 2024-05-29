import styled from 'styled-components';

export const Container = styled.header`
	min-width: 328px;
	width: 328px;
	height: 100%;
	background-color: #fff;
	border-top-left-radius: 15px;
	border-bottom-left-radius: 15px;
	position: relative;
`;

export const HeaderTop = styled.div`
	width: 100%;
	padding: 20px 5%;
`;

export const LogoBox = styled.div`
	width: 150px;
	margin-bottom: 20px;

	img {
		width: 100%;
	}
`;

export const AcademyInfoBox = styled.div`
	width: 100%;
	padding: 10px;
	display: flex;
	justify-content: space-between;
	align-items: center;
	box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.2);
	margin-top: 10px;

	h2 {
		font-size: 18px;
		font-weight: 700;
		color: #555;
	}

	img {
		width: 25px;
	}
`;

export const PointBox = styled.div`
	width: 100%;
	display: flex;
	flex-direction: column;
	gap: 10px;
`;

export const PointContainer = styled.div`
	width: 100%;
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin: 20px 0 10px;

	&:first-child {
		font-size: 18px;
		font-weight: 500;
		color: #000;
	}

	&:last-child {
		font-size: 18px;
		font-weight: 400;
		color: #000;

		span {
			font-weight: 700;
		}
	}
`;

export const MenuContainer = styled.nav`
	width: 100%;
	margin-top: 25px;
`;

export const Menu = styled.ul`
	width: 100%;
	display: flex;
	flex-direction: column;
	gap: 35px;

	li {
		a {
			font-size: ${({ theme }) => theme.textSize.large};
			font-weight: 500;
			color: #000;
		}

		& a:hover,
		&.active a {
			font-weight: 700;
			color: ${({ theme }) => theme.colors.primary};
		}
	}
`;

export const LogoutBtn = styled.button`
	width: 25px;
	position: absolute;
	right: 5%;
	bottom: 20px;

	img {
		width: 100%;
	}
`;
