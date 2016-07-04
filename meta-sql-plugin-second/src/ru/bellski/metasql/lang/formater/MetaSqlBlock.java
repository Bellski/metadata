package ru.bellski.metasql.lang.formater;

import com.intellij.formatting.*;
import com.intellij.formatting.templateLanguages.BlockWithParent;
import com.intellij.lang.ASTNode;
import com.intellij.psi.TokenType;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.formatter.FormatterUtil;
import com.intellij.psi.formatter.common.AbstractBlock;
import com.intellij.psi.impl.source.tree.JavaElementType;
import com.intellij.psi.tree.IElementType;
import com.intellij.sql.dialects.mysql.MysqlElementTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.bellski.metasql.lang.psi.MetaSqlTokenTypes;

import java.util.ArrayList;
import java.util.List;

import static ru.bellski.metasql.lang.psi.MetaSqlTokenTypes.LBRACE;
import static ru.bellski.metasql.lang.psi.MetaSqlTokenTypes.META_QUERY_DEFINITION;
import static ru.bellski.metasql.lang.psi.MetaSqlTokenTypes.RBRACE;

/**
 * Created by oem on 6/22/16.
 */
public class MetaSqlBlock extends AbstractBlock implements BlockWithParent {
    private final CodeStyleSettings mySettings;
    private boolean myChildrenBuilt = false;
    private BlockWithParent myParent;


    protected MetaSqlBlock(@NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment, CodeStyleSettings settings) {
        super(node, wrap, alignment);

        this.mySettings = settings;
    }

    @Override
    protected List<Block> buildChildren() {
        myChildrenBuilt = true;
        if (isLeaf()) {
            return EMPTY;
        }
        final ArrayList<Block> tlChildren = new ArrayList<>();
        for (ASTNode childNode = getNode().getFirstChildNode(); childNode != null; childNode = childNode.getTreeNext()) {
            if (FormatterUtil.containsWhiteSpacesOnly(childNode))
                continue;
            final MetaSqlBlock childBlock = new MetaSqlBlock(childNode, Wrap.createWrap(WrapType.NONE, true), createChildAlignment(childNode), mySettings);
            childBlock.setParent(this);
            tlChildren.add(childBlock);
        }
        return tlChildren;
    }

    @Nullable
    protected Alignment createChildAlignment(ASTNode child) {
        if (child.getElementType() != META_QUERY_DEFINITION) {
            return Alignment.createAlignment();
        }
        return Alignment.createAlignment();
    }


    @Nullable
    @Override
    public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
        Spacing myResult = Spacing.createSpacing(0, 1, 0, true, mySettings.KEEP_BLANK_LINES_IN_CODE);

        IElementType myType1 = child1 != null ? ((AbstractBlock) child1).getNode().getElementType() : null;
        IElementType myType2 = ((AbstractBlock) child2).getNode().getElementType();

        if (myType1 == MetaSqlTokenTypes.PACKAGE_DEFINITION) {
            int lf = mySettings.BLANK_LINES_AFTER_PACKAGE;
            myResult = Spacing.createSpacing(0, 0, lf, mySettings.KEEP_LINE_BREAKS, mySettings.KEEP_BLANK_LINES_IN_DECLARATIONS);
        } else
            if (myType2 == MetaSqlTokenTypes.PACKAGE_DEFINITION) {
                int lf = mySettings.BLANK_LINES_BEFORE_PACKAGE;
                myResult = Spacing.createSpacing(0, 0, lf, mySettings.KEEP_LINE_BREAKS, mySettings.KEEP_BLANK_LINES_IN_DECLARATIONS);
            } else
                if (myType1 == MetaSqlTokenTypes.IMPORT_LIST) {
                    int lf = mySettings.BLANK_LINES_AFTER_IMPORTS;
                    myResult = Spacing.createSpacing(0, 0, lf, mySettings.KEEP_LINE_BREAKS, mySettings.KEEP_BLANK_LINES_IN_DECLARATIONS);
                } else
                    if (myType2 == MetaSqlTokenTypes.IMPORT_LIST) {
                        int lf = mySettings.BLANK_LINES_BEFORE_IMPORTS;
                        myResult = Spacing.createSpacing(0, 0, lf, mySettings.KEEP_LINE_BREAKS, mySettings.KEEP_BLANK_LINES_IN_DECLARATIONS);
                    } else
                        if (myType1 == MetaSqlTokenTypes.IMPORT_STATEMENT) {
                            myResult = Spacing.createSpacing(0, 0, 1, mySettings.KEEP_LINE_BREAKS, mySettings.KEEP_BLANK_LINES_IN_DECLARATIONS);
                        } else
                            if (myType2 == MetaSqlTokenTypes.IMPORT_STATEMENT) {
                                myResult = Spacing.createSpacing(0, 0, 1, mySettings.KEEP_LINE_BREAKS, mySettings.KEEP_BLANK_LINES_IN_DECLARATIONS);
                            }

        return myResult;
    }

    private Spacing addSingleSpaceIf(boolean condition, boolean linesFeed) {
        final int spaces = condition ? 1 : 0;
        final int lines = linesFeed ? 1 : 0;
        return Spacing.createSpacing(spaces, spaces, lines, mySettings.KEEP_LINE_BREAKS, mySettings.KEEP_BLANK_LINES_IN_CODE);
    }

    @Override
    public Indent getIndent() {
        final IElementType myElemeType = myNode.getElementType();
        final ASTNode myParentNode = myNode.getTreeParent();
        final IElementType parentElementType = myParentNode == null ? null : myParentNode.getElementType();

        //        if (parentElementType == META_QUERY_DEFINITION) {
        //            if (myElemeType != LBRACE) {
        //                return Indent.getNormalIndent();
        //            }
        //
        //            if (myElemeType != RBRACE) {
        //                return Indent.getNormalIndent();
        //            }
        //        }

        return Indent.getNoneIndent();
    }

    @NotNull
    @Override
    public ChildAttributes getChildAttributes(int newChildIndex) {

        return new ChildAttributes(Indent.getNormalIndent(), null);
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public BlockWithParent getParent() {
        return myParent;
    }

    @Override
    public void setParent(BlockWithParent newParent) {
        myParent = newParent;
    }
}
