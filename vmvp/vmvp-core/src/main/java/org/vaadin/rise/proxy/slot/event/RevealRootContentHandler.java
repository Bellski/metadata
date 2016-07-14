package org.vaadin.rise.proxy.slot.event;

import com.google.gwt.thirdparty.guava.common.eventbus.Subscribe;
import org.vaadin.rise.core.event.RiseEventHandler;

/**
 * Created by oem on 7/11/16.
 */
public interface RevealRootContentHandler extends RiseEventHandler {
	@Subscribe
	void onRevealRootContent(RevealRootContentEvent event);
}
