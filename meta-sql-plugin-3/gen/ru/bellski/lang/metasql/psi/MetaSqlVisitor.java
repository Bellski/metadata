// This is a generated file. Not intended for manual editing.
package ru.bellski.lang.metasql.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;

public class MetaSqlVisitor extends PsiElementVisitor {

  public void visitCodeReferenceElement(@NotNull MetaSqlCodeReferenceElement o) {
    visitPsiReference(o);
  }

  public void visitImportList(@NotNull MetaSqlImportList o) {
    visitPsiCompositeElement(o);
  }

  public void visitImportReferenceElement(@NotNull MetaSqlImportReferenceElement o) {
    visitPsiReference(o);
  }

  public void visitImportStatement(@NotNull MetaSqlImportStatement o) {
    visitPsiCompositeElement(o);
  }

  public void visitMetaQuery(@NotNull MetaSqlMetaQuery o) {
    visitPsiCompositeElement(o);
  }

  public void visitMetadataAssign(@NotNull MetaSqlMetadataAssign o) {
    visitPsiCompositeElement(o);
  }

  public void visitPackageReferenceElement(@NotNull MetaSqlPackageReferenceElement o) {
    visitPsiReference(o);
  }

  public void visitPackageStatement(@NotNull MetaSqlPackageStatement o) {
    visitPsiCompositeElement(o);
  }

  public void visitParameterVariable(@NotNull MetaSqlParameterVariable o) {
    visitPsiCompositeElement(o);
  }

  public void visitParametersAssign(@NotNull MetaSqlParametersAssign o) {
    visitPsiCompositeElement(o);
  }

  public void visitReferenceParameterList(@NotNull MetaSqlReferenceParameterList o) {
    visitPsiCompositeElement(o);
  }

  public void visitReturnStatement(@NotNull MetaSqlReturnStatement o) {
    visitPsiCompositeElement(o);
  }

  public void visitTypeElement(@NotNull MetaSqlTypeElement o) {
    visitPsiCompositeElement(o);
  }

  public void visitPsiReference(@NotNull MetaSqlPsiReference o) {
    visitPsiCompositeElement(o);
  }

  public void visitPsiCompositeElement(@NotNull MetaSqlPsiCompositeElement o) {
    visitElement(o);
  }

}
