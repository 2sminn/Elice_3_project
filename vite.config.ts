import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

// https://vitejs.dev/config/
export default defineConfig({
	plugins: [react()],
	server: {
		host: '0.0.0.0', // 모든 네트워크 인터페이스에서 연결 수신
		port: 5173, // 포트 설정
	},
});
