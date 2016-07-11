package ru.bellski.vmvp.mvp.root;

import com.google.gwt.thirdparty.guava.common.eventbus.Subscribe;
import ru.bellski.vmvp.proxy.events.RevealRootContentEvent;

/**
 * Created by oem on 7/11/16.
 */
public interface RevealRootContentHandler {
	@Subscribe
	void onRevealRootContent(RevealRootContentEvent event);
}
