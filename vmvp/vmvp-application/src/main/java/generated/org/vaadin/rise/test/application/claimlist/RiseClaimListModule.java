package generated.org.vaadin.rise.test.application.claimlist;

import dagger.Module;
import dagger.Provides;
import org.vaadin.rise.test.application.application.claimlist.ClaimList;
import org.vaadin.rise.test.application.application.claimlist.ClaimListModule;
import org.vaadin.rise.test.application.application.claimlist.ClaimListPresenter;
import org.vaadin.rise.test.application.application.claimlist.ClaimListView;

import javax.inject.Singleton;

/**
 * Created by Aleksandr on 13.07.2016.
 */
@Module
public class RiseClaimListModule extends ClaimListModule {

    @Provides
    @Singleton
    ClaimList.View providesCas1View(ClaimListView view) {
        return view;
    }

    @Provides
    @Singleton
    ClaimList.Presenter providesCas1Presenter(ClaimListPresenter presenter) {
        return presenter;
    }
}
