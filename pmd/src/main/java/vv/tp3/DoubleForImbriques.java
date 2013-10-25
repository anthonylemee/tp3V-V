package vv.tp3;

import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTForStatement;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

/**
 * q1 of tp2
 * @author jimmy dano & anthony le mee
 */
public class DoubleForImbriques extends AbstractJavaRule {

	@Override
	public Object visit(ASTForStatement node, Object data) {
		if (hasDoubleFor(node)) {
            addViolation(data, node);
        }
        return super.visit(node,data);
    }

	/**
	 * avoid having two for one into another one.
	 * @param node the forStatement to analyze
	 * @return if there is another Forstatement in the node.
	 */
    private boolean hasDoubleFor(Node node) {
    	if(node.jjtGetNumChildren() > 0) {
    		for(int i = 0; i < node.jjtGetNumChildren(); i++) {
    			if(node.findDescendantsOfType(ASTForStatement.class).size() >= 1) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
}