<#-- @ftlvariable name="" type="org.vaadin.rise.plugin.idea.model.LazyPresenterProviderMethodModel" -->

@Provides
@Singleton
@IntoSet
public static LazyPresenterProvider<?> lazyPresenterProvider(Lazy<${presenterImpl.name}> presenterLazy, SlotRevealBus slotRevealBus) {
    final LazyPresenterProvider<?> lazyPresenterProvider = new LazyPresenterProvider<>(presenterLazy);

    slotRevealBus.registerSlot(${slot.name}, lazyPresenterProvider);

    return lazyPresenterProvider;
}