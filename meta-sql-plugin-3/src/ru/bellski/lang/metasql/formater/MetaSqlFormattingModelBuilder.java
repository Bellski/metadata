package ru.bellski.lang.metasql.formater;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by oem on 6/22/16.
 */
public class MetaSqlFormattingModelBuilder implements FormattingModelBuilder {

    @NotNull
    @Override
    public FormattingModel createModel(PsiElement element, CodeStyleSettings settings) {
        final MetaSqlBlock metaSqlBlock = new MetaSqlBlock(element.getNode(), Wrap.createWrap(WrapType.NONE, false), Alignment.createAlignment(), settings);
        return FormattingModelProvider.createFormattingModelForPsiFile(element.getContainingFile(), metaSqlBlock, settings);
    }

    @Nullable
    @Override
    public TextRange getRangeAffectingIndent(PsiFile file, int offset, ASTNode elementAtOffset) {
        return null;
    }

}
