package org.vaadin.rise.place;

import com.google.web.bindery.event.shared.Event;
import com.vaadin.server.Page;
import org.vaadin.rise.core.event.HasHandlers;
import org.vaadin.rise.core.event.RiseEventBus;
import org.vaadin.rise.place.event.PlaceRequestInternalEvent;
import org.vaadin.rise.place.token.TokenFormatException;
import org.vaadin.rise.place.token.TokenFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oem on 7/12/16.
 */
public class PlaceManagerImpl implements PlaceManager, Page.UriFragmentChangedListener, HasHandlers {
	private final RiseEventBus eventBus;
	private final TokenFormatter tokenFormatter;
	private final Page page;

	private String currentHistoryToken = "";
	private boolean internalError;
	private String onLeaveQuestion;
//	private Command defferedNavigation;

//	private HandlerRegistration windowClosingHandlerRegistration;

	private boolean locked;

	private List<PlaceRequest> placeHierarchy = new ArrayList<>();

	public PlaceManagerImpl(RiseEventBus eventBus, TokenFormatter tokenFormatter, Page page) {
		this.eventBus = eventBus;
		this.tokenFormatter = tokenFormatter;
		this.page = page;

		page.addUriFragmentChangedListener(this);
	}


	@Override
	public String buildHistoryToken(PlaceRequest request) {
		return tokenFormatter.toPlaceToken(request);
	}

	@Override
	public String buildRelativeHistoryToken(int level) {
		List<PlaceRequest> placeHierarchyCopy = truncatePlaceHierarchy(level);
		if (placeHierarchyCopy.size() == 0) {
			return "";
		}
		return tokenFormatter.toHistoryToken(placeHierarchyCopy);
	}

	@Override
	public String buildRelativeHistoryToken(PlaceRequest request) {
		return buildRelativeHistoryToken(request, 0);
	}

	@Override
	public String buildRelativeHistoryToken(PlaceRequest request, int level) {
		List<PlaceRequest> placeHierarchyCopy = truncatePlaceHierarchy(level);
		placeHierarchyCopy.add(request);
		return tokenFormatter.toHistoryToken(placeHierarchyCopy);
	}

//	private boolean confirmLeaveState() {
//		if (onLeaveQuestion == null) {
//			return true;
//		}
//		boolean confirmed = Window.confirm(onLeaveQuestion);
//		if (confirmed) {
//			// User has confirmed, don't ask any more question.
//			setOnLeaveConfirmation(null);
//		} else {
//			NavigationRefusedEvent.fire(this);
//			setBrowserHistoryToken(currentHistoryToken, false);
//		}
//		return confirmed;
//	}

	protected void doRevealPlace(PlaceRequest request, boolean updateBrowserUrl) {
		PlaceRequestInternalEvent requestEvent = new PlaceRequestInternalEvent(request,
				updateBrowserUrl);
		fireEvent(requestEvent);
		if (!requestEvent.isHandled()) {
			unlock();
			error(tokenFormatter.toHistoryToken(placeHierarchy));
		} else if (!requestEvent.isAuthorized()) {
			unlock();
			illegalAccess(tokenFormatter.toHistoryToken(placeHierarchy));
		}
	}

	private void illegalAccess(String historyToken) {
		startError();
		revealUnauthorizedPlace(historyToken);
		stopError();
	}

	private void error(String invalidHistoryToken) {
		startError();
		revealErrorPlace(invalidHistoryToken);
		stopError();
	}

	private void startError() {
		if (this.internalError) {
			throw new RuntimeException("Encountered repeated errors resulting in an infinite loop. Make sure all users "
					+ "have access to the pages revealed by revealErrorPlace and revealUnauthorizedPlace. (Note that "
					+ "the default implementations call revealDefaultPlace)");
		}
		internalError = true;
	}

	private void stopError() {
		internalError = false;
	}

	@Override
	public List<PlaceRequest> getCurrentPlaceHierarchy() {
		return placeHierarchy;
	}

	@Override
	public PlaceRequest getCurrentPlaceRequest() {
		if (placeHierarchy.size() > 0) {
			return placeHierarchy.get(placeHierarchy.size() - 1);
		} else {
			return new PlaceRequest.Builder().build();
		}
	}

	@Override
	public RiseEventBus getEventBus() {
		return eventBus;
	}

	@Override
	public int getHierarchyDepth() {
		return placeHierarchy.size();
	}

	@Override
	public void navigateBack() {

	}

	@Override
	public void updateHistory(PlaceRequest request, boolean updateBrowserUrl) {
		try {
			// Make sure the request match
			assert request.hasSameNameToken(getCurrentPlaceRequest()) : "Internal error, PlaceRequest passed to" +
					"updateHistory doesn't match the tail of the place hierarchy.";
			placeHierarchy.set(placeHierarchy.size() - 1, request);
			if (updateBrowserUrl) {
				String historyToken = tokenFormatter.toHistoryToken(placeHierarchy);
				String browserHistoryToken = getBrowserHistoryToken();
				if (browserHistoryToken == null
						|| !browserHistoryToken.equals(historyToken)) {
					setBrowserHistoryToken(historyToken, false);
				}
				saveHistoryToken(historyToken);
			}
		} catch (TokenFormatException e) {
			// Do nothing.
		}
	}

	String getBrowserHistoryToken() {
		return page.getUriFragment();
	}

	void setBrowserHistoryToken(String historyToken, boolean issueEvent) {
		page.setUriFragment(historyToken, issueEvent);
	}

	private void saveHistoryToken(String historyToken) {
		currentHistoryToken = historyToken;
	}

	@Override
	public void revealCurrentPlace() {
		handleTokenChange(page.getUriFragment());
	}

	@Override
	public void revealDefaultPlace() {

	}

	@Override
	public void revealErrorPlace(String invalidHistoryToken) {
		revealDefaultPlace();
	}

	@Override
	public void revealUnauthorizedPlace(String unauthorizedHistoryToken) {
		revealErrorPlace(unauthorizedHistoryToken);
	}

	@Override
	public void setOnLeaveConfirmation(String question) {

	}

	@Override
	public void revealPlace(PlaceRequest request) {
		revealPlace(request, true);
	}

	@Override
	public void revealPlace(PlaceRequest request, boolean updateBrowserUrl) {
//		if (locked) {
//			defferedNavigation = () -> revealPlace(request, updateBrowserUrl);
//			return;
//		}
//		if (!getLock()) {
//			return;
//		}
		placeHierarchy.clear();
		placeHierarchy.add(request);
		doRevealPlace(request, updateBrowserUrl);
	}

	@Override
	public void revealPlaceHierarchy(List<PlaceRequest> placeRequestHierarchy) {
//		if (locked) {
//			defferedNavigation = () -> revealPlaceHierarchy(placeRequestHierarchy);
//			return;
//		}
//		if (!getLock()) {
//			return;
//		}
		if (placeRequestHierarchy.size() == 0) {
			unlock();
			revealDefaultPlace();
		} else {
			placeHierarchy = placeRequestHierarchy;
			doRevealPlace(getCurrentPlaceRequest(), true);
		}
	}

	@Override
	public void revealRelativePlace(int level) {
//		if (locked) {
//			defferedNavigation = () -> revealRelativePlace(level);
//			return;
//		}
//		if (!getLock()) {
//			return;
//		}
		placeHierarchy = truncatePlaceHierarchy(level);
		int hierarchySize = placeHierarchy.size();
		if (hierarchySize == 0) {
			unlock();
			revealDefaultPlace();
		} else {
			PlaceRequest request = placeHierarchy.get(hierarchySize - 1);
			doRevealPlace(request, true);
		}
	}

	@Override
	public void revealRelativePlace(PlaceRequest request) {
		revealRelativePlace(request, 0);
	}

	@Override
	public void revealRelativePlace(PlaceRequest request, int level) {
//		if (locked) {
//			defferedNavigation = () -> revealRelativePlace(request, level);
//			return;
//		}
//		if (!getLock()) {
//			return;
//		}
		placeHierarchy = truncatePlaceHierarchy(level);
		placeHierarchy.add(request);
		doRevealPlace(request, true);
	}

	@Override
	public void unlock() {

	}

	@Override
	public boolean hasPendingNavigation() {
		return false;
	}

	@Override
	public void uriFragmentChanged(Page.UriFragmentChangedEvent event) {
		handleTokenChange(event.getUriFragment());
	}

	private List<PlaceRequest> truncatePlaceHierarchy(int level) {
		int size = placeHierarchy.size();
		if (level < 0) {
			if (-level >= size) {
				return new ArrayList<>();
			} else {
				return new ArrayList<>(placeHierarchy.subList(0, size + level));
			}
		} else if (level > 0) {
			if (level >= size) {
				return new ArrayList<>(placeHierarchy);
			} else {
				return new ArrayList<>(placeHierarchy.subList(0, level));
			}
		}
		return new ArrayList<>(placeHierarchy);
	}

	private void handleTokenChange(final String historyToken) {
//		if (locked) {
//			defferedNavigation = () -> handleTokenChange(historyToken);
//			return;
//		}
//		if (!getLock()) {
//			return;
//		}
		try {
			if (historyToken.trim().isEmpty()) {
				unlock();
				revealDefaultPlace();
			} else {
				placeHierarchy = tokenFormatter.toPlaceRequestHierarchy(historyToken);
				doRevealPlace(getCurrentPlaceRequest(), true);
			}
		} catch (TokenFormatException e) {
			unlock();
			error(historyToken);
//			NavigationEvent.fire(this, null);
		}
	}

	@Override
	public void fireEvent(Event<?> event) {
		eventBus.fireEvent(event);
	}
}
