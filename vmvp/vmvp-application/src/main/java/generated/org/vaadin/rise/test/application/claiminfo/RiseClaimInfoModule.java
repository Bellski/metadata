package generated.org.vaadin.rise.test.application.claiminfo;

import dagger.Module;
import dagger.Provides;
import org.vaadin.rise.test.application.application.claiminfo.ClaimInfo;
import org.vaadin.rise.test.application.application.claiminfo.ClaimInfoModule;
import org.vaadin.rise.test.application.application.claiminfo.ClaimInfoPresenter;
import org.vaadin.rise.test.application.application.claiminfo.ClaimInfoView;
import org.vaadin.rise.test.application.application.claimlist.ClaimList;
import org.vaadin.rise.test.application.application.claimlist.ClaimListModule;
import org.vaadin.rise.test.application.application.claimlist.ClaimListPresenter;
import org.vaadin.rise.test.application.application.claimlist.ClaimListView;

import javax.inject.Singleton;

/**
 * Created by Aleksandr on 13.07.2016.
 */
@Module
public class RiseClaimInfoModule extends ClaimInfoModule {

    @Provides
    @Singleton
    ClaimInfo.View providesClaimInfoView(ClaimInfoView view) {
        return view;
    }

    @Provides
    @Singleton
    ClaimInfo.Presenter providesClaimInfoPresenter(ClaimInfoPresenter presenter) {
        return presenter;
    }
}
