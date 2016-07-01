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

public class MetaSqlPackageStatementImpl extends MetaSqlPsiCompositeElementImpl implements MetaSqlPackageStatement {

  public MetaSqlPackageStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull MetaSqlVisitor visitor) {
    visitor.visitPackageStatement(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MetaSqlVisitor) accept((MetaSqlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public MetaSqlPackageReferenceElement getPackageReferenceElement() {
    return findNotNullChildByClass(MetaSqlPackageReferenceElement.class);
  }

}
