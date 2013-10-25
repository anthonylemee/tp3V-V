package vv.tp3;

import net.sourceforge.pmd.lang.java.ast.ASTBooleanLiteral;
import net.sourceforge.pmd.lang.java.ast.ASTExpression;
import net.sourceforge.pmd.lang.java.ast.ASTWhileStatement;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

/**
 * q2 of tp2
 * 
 * @author jimmy dano & anthony le mee
 */
public class WhileBooleanViolation extends AbstractJavaRule {

	@Override
	public Object visit(ASTWhileStatement node, Object data) {
		if (hasWhileBoolean(node)) {
			addViolation(data, node);
		}
		return super.visit(node, data);
	}

	/**
	 * if we find in the expression of the while condition is a boolean, we
	 * return true
	 * 
	 * @param node
	 *            the while statement to analyze
	 * @return if there is a true/false condition in the while statement
	 */
	private boolean hasWhileBoolean(ASTWhileStatement node) {
		for (int i = 0; i < node.jjtGetNumChildren(); i++) {
			if (node.jjtGetChild(i) instanceof ASTExpression) {
				ASTExpression ast = (ASTExpression) node.jjtGetChild(i);
				if (ast.findDescendantsOfType(ASTBooleanLiteral.class).size() >= 1) {
					return true;
				}
			}
		}
		return false;
	}
}