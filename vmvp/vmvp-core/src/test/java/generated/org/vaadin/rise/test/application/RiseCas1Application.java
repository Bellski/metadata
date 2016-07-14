package generated.org.vaadin.rise.test.application;

import dagger.Module;
import dagger.Provides;
import generated.org.vaadin.rise.test.application.claimlist.RiseClaimListModule;
import org.vaadin.rise.test.application.Cas1;
import org.vaadin.rise.test.application.Cas1Application;
import org.vaadin.rise.test.application.Cas1Presenter;
import generated.RiseBootstrapModule;
import org.vaadin.rise.test.application.Cas1View;
import org.vaadin.rise.vaadin.VaadinModule;

import javax.inject.Singleton;


/**
 * Created by oem on 7/12/16.
 */
@Module
public class RiseCas1Application extends Cas1Application {

    @Provides
    @Singleton
    Cas1Presenter.Slot1 providesCas1PresenterSlot1(RiseCas1PresenterStandardProxy.NestedSlot1 nestedSlot1) {
        return nestedSlot1;
    }

    @Provides
    @Singleton
    Cas1.View providesCas1View(Cas1View view) {
        return view;
    }

    @Provides
    @Singleton
    Cas1.Presenter providesCas1Presenter(Cas1Presenter presenter) {
        return presenter;
    }
}
