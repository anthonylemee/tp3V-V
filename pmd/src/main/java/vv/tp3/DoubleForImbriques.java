package vv.tp3;

import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTForStatement;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

public class DoubleForImbriques extends AbstractJavaRule {

    public Object visit(ASTForStatement node, Object data) {
        if (hasDoubleFor(node)) {
            addViolation(data, node);
        }
        return super.visit(node,data);
    }

    private boolean hasDoubleFor(Node node) {
    	if(node.jjtGetNumChildren() > 0) {
    		for(int i = 0; i < node.jjtGetNumChildren(); i++) {
    			if(node.jjtGetChild(i) instanceof ASTForStatement) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
}