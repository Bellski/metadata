package ru.bellski.metasql.lang;

import com.intellij.psi.util.PsiTreeUtil;
import ru.bellski.metasql.lang.psi.MetaSqlMetadataDefinition;

/**
 * Created by oem on 6/21/16.
 */
public class MetaSqlElementUtil {
    public static boolean isMetadataClassExistsOrResolve(MetaSqlFile metaSqlFile) {
        boolean isOk = false;

        final MetaSqlMetadataDefinition metaSqlMetadataDef = PsiTreeUtil.findChildOfType(metaSqlFile, MetaSqlMetadataDefinition.class);

        if (metaSqlMetadataDef != null) {
            isOk = metaSqlMetadataDef.getMetadata() != null && metaSqlMetadataDef.getMetadata().resolve() != null;
        }

        return isOk;
    }
}
