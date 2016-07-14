package org.vaadin.rise.proxy.slot.event;

import com.google.gwt.thirdparty.guava.common.eventbus.Subscribe;

/**
 * Created by oem on 7/11/16.
 */
public interface RevealRootContentHandler {
	@Subscribe
	void onRevealRootContent(RevealRootContentEvent event);
}
