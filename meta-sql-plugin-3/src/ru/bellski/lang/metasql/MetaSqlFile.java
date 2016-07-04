package ru.bellski.lang.metasql;

import com.intellij.codeInsight.CommentUtil;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.tree.injected.InjectedLanguageUtil;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtil;
import com.intellij.sql.psi.SqlFile;
import com.intellij.sql.psi.SqlTokens;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import ru.bellski.lang.metasql.psi.*;

import java.util.List;
import java.util.StringJoiner;

/**
 * Created by oem on 5/19/16.
 */
public class MetaSqlFile extends PsiFileBase {
    private static String DEFAULT_PACKAGE = "sql.queries";
    private boolean hasErrors;

    protected MetaSqlFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, MetaSqlLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return MetaSqlFileType.INSTANCE;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public boolean isHasErrors() {
        return hasErrors;
    }

    public String getPackagePath() {
        return getPackage().replaceAll("\\.", "/");
    }


    public String getPackage() {
        String packageName = null;
        final MetaSqlPackageStatement packageStatement = findChildByClass(MetaSqlPackageStatement.class);

        if (packageStatement != null) {
            packageName = packageStatement.getPackageReferenceElement().getText();
        }

        return packageName == null ? DEFAULT_PACKAGE : packageName;
    }

    public String getQueryName() {
        String name = null;
        final MetaSqlMetaQuery query = findChildByClass(MetaSqlMetaQuery.class);

        if (query != null) {
            final PsiElement id = query.getIdentifier();

            if (id != null) {
                name = id.getText();
            }
        }

        return name == null ? buildNameFromSqlFileName() : name;
    }

    private String buildNameFromSqlFileName() {
        String name = null;
        final PsiLanguageInjectionHost host = InjectedLanguageUtil.findInjectionHost(this);

        if (host != null && host.getContainingFile() instanceof SqlFile) {
            final String fileName = host.getContainingFile().getName();
            name = StringUtil.capitalize(fileName.substring(0, fileName.lastIndexOf('.')));
        }

        return name == null ? "Undefined name" : name;
    }

    public int getParamsCount() {
        int count = 0;
        final MetaSqlMetaQuery query = findChildByClass(MetaSqlMetaQuery.class);

        if (query != null) {
            final MetaSqlParametersAssign parameterAssign = query.getParametersAssign();

            if (parameterAssign != null) {
                count = parameterAssign.getParameterVariableList().size();
            }
        }

        return count;
    }

    public List<MetaSqlParameterVariable> getParameters() {
        List<MetaSqlParameterVariable> parameters = null;
        final MetaSqlMetaQuery query = findChildByClass(MetaSqlMetaQuery.class);

        if (query != null) {
            final MetaSqlParametersAssign parameterAssign = query.getParametersAssign();

            if (parameterAssign != null) {
                parameters = parameterAssign.getParameterVariableList();
            }
        }

        return parameters;
    }

    public MetaSqlCodeReferenceElement getReturnType() {
        MetaSqlCodeReferenceElement returnType = null;

        final MetaSqlMetaQuery query = findChildByClass(MetaSqlMetaQuery.class);

        if (query != null) {
            final MetaSqlReturnStatement returnStatement = query.getReturnStatement();

            if (returnStatement != null) {
                returnType = returnStatement.getTypeElement().getCodeReferenceElement();
            }
        }
        return returnType;
    }

    public String getQuery() {
        String query = null;
        final PsiLanguageInjectionHost host = InjectedLanguageUtil.findInjectionHost(this);

        if (host != null && host.getContainingFile() instanceof SqlFile) {
            final PsiFile sqlFile = (PsiFile) host.getContainingFile().copy();

            final TextRange metaSqlLangTextRange = host.getTextRange();

            query = sqlFile.getText().substring(metaSqlLangTextRange.getEndOffset());
            query = StringUtil.escapeQuotes(query).trim();

            StringJoiner stringJoiner = new StringJoiner("\n+");

            for (String line : StringUtil.splitByLines(query)) {
                stringJoiner.add("\"" + line.trim() + "\\n\"");
            }


            query = stringJoiner.toString();
        }
        return query;
    }


}
