package ru.bellski.metasql.lang.style;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.bellski.metasql.lang.MetaSqlFile;
import ru.bellski.metasql.lang.MetaSqlLanguage;
import ru.bellski.metasql.lang.psi.MetaSqlTokenTypes;

/**
 * Created by oem on 6/22/16.
 */
public class MetaSqlFormattingModelBuilder implements FormattingModelBuilder {

    @NotNull
    @Override
    public FormattingModel createModel(PsiElement element, CodeStyleSettings settings) {
        final MetaSqlBlock metaSqlBlock = new MetaSqlBlock(
                element.getNode(),
                Wrap.createWrap(WrapType.NONE, false),
                Alignment.createAlignment()
        );
        return
                FormattingModelProvider
                        .createFormattingModelForPsiFile(element.getContainingFile(), metaSqlBlock, settings);
    }

    @Nullable
    @Override
    public TextRange getRangeAffectingIndent(PsiFile file, int offset, ASTNode elementAtOffset) {
        return null;
    }

}
