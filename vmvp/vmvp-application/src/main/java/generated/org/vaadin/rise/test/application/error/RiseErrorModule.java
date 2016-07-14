package generated.org.vaadin.rise.test.application.error;

import dagger.Module;
import dagger.Provides;
import org.vaadin.rise.test.application.application.claiminfo.ClaimInfo;
import org.vaadin.rise.test.application.application.claiminfo.ClaimInfoModule;
import org.vaadin.rise.test.application.application.claiminfo.ClaimInfoPresenter;
import org.vaadin.rise.test.application.application.claiminfo.ClaimInfoView;
import org.vaadin.rise.test.application.application.error.Error;
import org.vaadin.rise.test.application.application.error.ErrorModule;
import org.vaadin.rise.test.application.application.error.ErrorPresenter;
import org.vaadin.rise.test.application.application.error.ErrorView;

import javax.inject.Singleton;

/**
 * Created by Aleksandr on 13.07.2016.
 */
@Module
public class RiseErrorModule extends ErrorModule {

    @Provides
    @Singleton
    Error.View providesClaimInfoView(ErrorView view) {
        return view;
    }

    @Provides
    @Singleton
    Error.Presenter providesClaimInfoPresenter(ErrorPresenter presenter) {
        return presenter;
    }
}
