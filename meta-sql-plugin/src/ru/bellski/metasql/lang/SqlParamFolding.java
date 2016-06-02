package ru.bellski.metasql.lang;

import com.google.common.collect.Sets;
import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.injected.InjectedLanguageUtil;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.sql.psi.SqlParameter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.bellski.metasql.lang.psi.MetaSqlParameter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by oem on 5/25/16.
 */
public class SqlParamFolding extends FoldingBuilderEx {
    @NotNull
    @Override
    public FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quick) {
        Collection<SqlParameter> params = PsiTreeUtil.findChildrenOfType(root, SqlParameter.class);
        Collection<MetaSqlParameter> metaParams = PsiTreeUtil.findChildrenOfType(InjectedLanguageUtil.findInjectedFile(root.getChildren()[0], MetaSqlFile.class), MetaSqlParameter.class);

        List<FoldingDescriptor> foldingDescriptors = new ArrayList<>();

        SqlParameter[] paramsArray = params.toArray(new SqlParameter[params.size()]);
        MetaSqlParameter[] metaParamsArray = metaParams.toArray(new MetaSqlParameter[metaParams.size()]);

        for (int i = 0; i < metaParamsArray.length; i++) {
            if (i < paramsArray.length) {
                SqlParameter sqlParam = paramsArray[i];
                final MetaSqlParameter metaSqlParam = metaParamsArray[i];

                foldingDescriptors.add(
                        new FoldingDescriptor(sqlParam.getNode(), new TextRange(sqlParam.getTextRange().getStartOffset(), sqlParam.getTextRange().getEndOffset()), null, Sets.newHashSet(), true) {
                            @Nullable
                            @Override
                            public String getPlaceholderText() {
                                return metaSqlParam.getParameterKeyword().getText();
                            }
                        }
                );
            }
        }

        return foldingDescriptors.toArray(new FoldingDescriptor[foldingDescriptors.size()]);
    }

    @Nullable
    @Override
    public String getPlaceholderText(@NotNull ASTNode node) {
        return "...";
    }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode node) {
        return true;
    }

}
