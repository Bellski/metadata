// This is a generated file. Not intended for manual editing.
package ru.bellski.lang.metasql.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static ru.bellski.lang.metasql.MetaSqlTokenTypes.*;
import ru.bellski.lang.metasql.psi.*;
import ru.bellski.lang.metasql.util.MetaSqlPsiImplUtil;

public class MetaSqlMetaQueryImpl extends MetaSqlPsiCompositeElementImpl implements MetaSqlMetaQuery {

  public MetaSqlMetaQueryImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull MetaSqlVisitor visitor) {
    visitor.visitMetaQuery(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MetaSqlVisitor) accept((MetaSqlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MetaSqlMetadataAssign getMetadataAssign() {
    return findChildByClass(MetaSqlMetadataAssign.class);
  }

  @Override
  @Nullable
  public MetaSqlParametersAssign getParametersAssign() {
    return findChildByClass(MetaSqlParametersAssign.class);
  }

  @Override
  @Nullable
  public MetaSqlReturnStatement getReturnStatement() {
    return findChildByClass(MetaSqlReturnStatement.class);
  }

  @Override
  @Nullable
  public PsiElement getIdentifier() {
    return findChildByType(IDENTIFIER);
  }

}
