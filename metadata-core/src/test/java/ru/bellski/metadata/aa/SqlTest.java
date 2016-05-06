package ru.bellski.metadata.aa;

import org.junit.Test;
import ru.bellski.metadata.UserMetadata;
import ru.bellski.metadata.anewone.MetaProperty;
import ru.bellski.metadata.anewone.Metadata;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by oem on 5/6/16.
 */
public class SqlTest {
	@Test
	public void testGenerate(){
		System.out.println(gen(UserMetadata.user));
	}

	public static String gen(Metadata<?> metadata) {
		return gen(metadata, new HashSet<>(), new StringBuilder());
	}

	public static String gen(Metadata<?> metadata, Set<String> res, StringBuilder prefix){
		StringBuilder name =new StringBuilder();
		for (MetaProperty metaProperty : metadata.getProperties()) {
			name.setLength(0);
			name
				.append(prefix)
				.append(metaProperty.getName())
			;
			if(metaProperty.isNested()){
				gen(metaProperty.getMetadata(),res,name.append('.'));
			}else {
				res.add(name.toString());
			}
		}
		return res.toString();
	}
}
