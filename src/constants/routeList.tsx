import { ReactNode } from 'react';
import MainPage from '../pages/main';
import Login from '../pages/login';
import Signup from '../pages/signup';
interface Route {
	id: number;
	title: string;
	path: string;
	link: string;
	element: ReactNode;
}

export const LayoutRouteList: { [key: string]: Route } = {
	HOME: {
		id: 1,
		title: '메인',
		path: '/',
		link: '/',
		element: <MainPage />,
	},
};

export const routeList: { [key: string]: Route } = {
	LOGIN: {
		id: 1,
		title: '로그인',
		path: '/login',
		link: '/login',
		element: <Login />,
	},
	SIGNUP: {
		id: 2,
		title: '회원가입',
		path: '/signup',
		link: '/signup',
		element: <Signup />,
	},
};

export const LAYOUT_ROUTE_ARR = Object.values(LayoutRouteList);
export const ROUTE_ARR = Object.values(routeList);
