package ru.bellski.metasql.lang;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MetaSqlFileType extends LanguageFileType {
  public static final MetaSqlFileType INSTANCE = new MetaSqlFileType();

  private MetaSqlFileType() {
    super(MetaSqlLanguage.INSTANCE);
  }

  @NotNull
  @Override
  public String getName() {
    return "MetaSql file";
  }

  @NotNull
  @Override
  public String getDescription() {
    return "MetaSql language file";
  }

  @NotNull
  @Override
  public String getDefaultExtension() {
    return "metasql";
  }

  @Nullable
  @Override
  public Icon getIcon() {
    return AllIcons.FileTypes.Any_type;
  }
}