import { ReactNode } from 'react';
import MainPage from '../pages/main';
import Login from '../pages/login';
import Signup from '../pages/signup';
import StoragePage from '../pages/storage';
import Mypage from '../pages/mypage';
interface Route {
	id: number;
	title: string;
	path: string;
	link: string;
	element: ReactNode;
	menu?: boolean;
}

export const LayoutRouteList: { [key: string]: Route } = {
	HOME: {
		id: 1,
		title: '홈',
		path: '/',
		link: '/',
		element: <MainPage />,
		menu: true,
	},
	STUDENT: {
		id: 2,
		title: '원생관리',
		path: '/student',
		link: '/student',
		element: <StoragePage />,
		menu: true,
	},
	STORAGE: {
		id: 3,
		title: '수납관리',
		path: '/storage',
		link: '/storage',
		element: <StoragePage />,
		menu: true,
	},
	LECTURE: {
		id: 4,
		title: '강의관리',
		path: '/lecture',
		link: '/lecture',
		element: <StoragePage />,
		menu: true,
	},
	MYPAGE: {
		id: 5,
		title: '마이페이지',
		path: '/mypage',
		link: '/mypage',
		element: <Mypage />,
		menu: true,
	},
	EDITUSER: {
		id: 5,
		title: '회원정보 수정',
		path: '/edit-profile',
		link: '/edit-profile',
		element: <Signup isEdit={true} />,
		menu: false,
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
export const MENU_ARR = Object.entries(LayoutRouteList)
	.filter(([, value]) => value.menu)
	.map(([, value]) => value);
export const ROUTE_ARR = Object.values(routeList);
