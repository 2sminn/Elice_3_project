import { ReactNode } from 'react';
import MainPage from '../pages/main';
import Login from '../pages/login';
import Signup from '../pages/signup';
import StoragePage from '../pages/storage';
import StudentMgrPage from '../pages/studentMgrPage';

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
		title: '홈',
		path: '/',
		link: '/',
		element: <MainPage />,
	},
	STUDENT_MGR: {
		id: 2,
		title: '원생 관리',
		path: '/student-mgr',
		link: '/student-mgr',
		element: <StudentMgrPage />,
	},
	STORAGE: {
		id: 3,
		title: '수납관리',
		path: '/storage',
		link: '/storage',
		element: <StoragePage />,
	},
	LECTURE: {
		id: 4,
		title: '강의관리',
		path: '/lecture',
		link: '/lecture',
		element: <StoragePage />,
	},
	MYPAGE: {
		id: 5,
		title: '마이페이지',
		path: '/storage',
		link: '/storage',
		element: <StoragePage />,
	},
};
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
