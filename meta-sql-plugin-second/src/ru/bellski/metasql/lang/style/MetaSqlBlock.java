package ru.bellski.metasql.lang.style;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.TokenType;
import com.intellij.psi.formatter.common.AbstractBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.bellski.metasql.lang.psi.MetaSqlTokenType;
import ru.bellski.metasql.lang.psi.MetaSqlTokenTypes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by oem on 6/22/16.
 */
public class MetaSqlBlock extends AbstractBlock {


    protected MetaSqlBlock(@NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment) {
        super(node, wrap, alignment);
    }

    @Override
    protected List<Block> buildChildren() {
        List<Block> blocks = new ArrayList<>();
        ASTNode child = myNode.getFirstChildNode();

        while (child != null) {
            if (child.getElementType() != TokenType.WHITE_SPACE) {
                Block block = new MetaSqlBlock(child, Wrap.createWrap(WrapType.NONE, false), Alignment.createAlignment());
                blocks.add(block);
            }
            child = child.getTreeNext();
        }
        return blocks;
    }

    @Nullable
    @Override
    public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {

        if (myNode.getElementType() == MetaSqlTokenTypes.IMPORT_LIST) {
            Spacing.createSpacing(1,2, 1, true, 2);
        }

        return null;
    }

    @Override
    public boolean isLeaf() {
        return myNode.getFirstChildNode() == null;
    }
}
