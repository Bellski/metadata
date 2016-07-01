package ru.bellski.lang.metasql.formater;

import com.intellij.formatting.*;
import com.intellij.formatting.templateLanguages.BlockWithParent;
import com.intellij.lang.ASTNode;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.formatter.FormatterUtil;
import com.intellij.psi.formatter.common.AbstractBlock;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.bellski.lang.metasql.MetaSqlTokenTypes;

import java.util.ArrayList;
import java.util.List;


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
        if (child.getElementType() != MetaSqlTokenTypes.METAQUERY) {
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


        if (myType1 == MetaSqlTokenTypes.IMPORT_LIST) {
            int lf = mySettings.BLANK_LINES_AFTER_IMPORTS;
            myResult = Spacing.createSpacing(0, 0, lf, mySettings.KEEP_LINE_BREAKS, mySettings.KEEP_BLANK_LINES_IN_DECLARATIONS);
        } else if (myType2 == MetaSqlTokenTypes.IMPORT_LIST) {
            int lf = mySettings.BLANK_LINES_BEFORE_IMPORTS;
            myResult = Spacing.createSpacing(0, 0, lf, mySettings.KEEP_LINE_BREAKS, mySettings.KEEP_BLANK_LINES_IN_DECLARATIONS);
        } else if (myType1 == MetaSqlTokenTypes.IMPORT_STATEMENT) {
            myResult = Spacing.createSpacing(0, 0, 1, mySettings.KEEP_LINE_BREAKS, mySettings.KEEP_BLANK_LINES_IN_DECLARATIONS);
        } else if (myType2 == MetaSqlTokenTypes.IMPORT_STATEMENT) {
            myResult = Spacing.createSpacing(0, 0, 1, mySettings.KEEP_LINE_BREAKS, mySettings.KEEP_BLANK_LINES_IN_DECLARATIONS);
        }

        return myResult;
    }

    @Override
    public Indent getIndent() {
        final IElementType myElemeType = myNode.getElementType();
        final ASTNode myParentNode = myNode.getTreeParent();
        final IElementType parentElementType = myParentNode == null ? null : myParentNode.getElementType();

        if (parentElementType == MetaSqlTokenTypes.META_QUERY) {
            if (myElemeType == MetaSqlTokenTypes.SQLMETADATA) {
                return Indent.getNormalIndent();
            }

            if (myElemeType == MetaSqlTokenTypes.METADATA_ASSIGN) {
                return Indent.getNormalIndent();
            }

            if (myElemeType == MetaSqlTokenTypes.PARAMETERS_ASSIGN) {
                return Indent.getNormalIndent();
            }

            if (myElemeType == MetaSqlTokenTypes.RETURN_STATEMENT) {
                return Indent.getNormalIndent();
            }
        }

        if (parentElementType == MetaSqlTokenTypes.PARAMETERS_ASSIGN) {
            if (myElemeType == MetaSqlTokenTypes.PARAMETER_VARIABLE) {
                return Indent.getNormalIndent();
            }
        }

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
