package javasource;

import dagger.Module;

@Module(
	includes = {
		RiseSubEntryModule.class,
		RiseSubSubEntryModule.class
	}
)
public class RiseEntryModule {
}
