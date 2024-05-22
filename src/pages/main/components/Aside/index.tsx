import * as S from './style';
import addBanner from './assets/asideBanner.png';

const Aside = () => {
	return (
		<S.AsideContainer>
			<S.BannerContainer>
				<img src={addBanner} alt="광고배너" />
			</S.BannerContainer>
		</S.AsideContainer>
	);
};

export default Aside;
