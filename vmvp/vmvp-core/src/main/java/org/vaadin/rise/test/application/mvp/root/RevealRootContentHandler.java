package org.vaadin.rise.test.application.mvp.root;

import com.google.gwt.thirdparty.guava.common.eventbus.Subscribe;
import org.vaadin.rise.test.application.proxy.events.RevealRootContentEvent;

/**
 * Created by oem on 7/11/16.
 */
public interface RevealRootContentHandler {
	@Subscribe
	void onRevealRootContent(RevealRootContentEvent event);
}
