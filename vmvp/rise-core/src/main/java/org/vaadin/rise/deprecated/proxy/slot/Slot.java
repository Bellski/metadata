/*
 * Copyright 2014 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.vaadin.rise.deprecated.proxy.slot;


import dagger.Lazy;
import org.vaadin.rise.core.RisePresenterComponent;



public class Slot<PRESENTER extends RisePresenterComponent<?>> implements IsSlot<PRESENTER> {
    protected Lazy<PRESENTER> presenter;

    public Slot() {
    }

    public Slot(Lazy<PRESENTER> presenter) {
        this.presenter = presenter;
    }

    public void setLazyPresenter(Lazy<PRESENTER> presenter) {
        this.presenter = presenter;
    }

    @Override
    public boolean isPopup() {
        return false;
    }

    @Override
    public boolean isRemovable() {
        return true;
    }
}
