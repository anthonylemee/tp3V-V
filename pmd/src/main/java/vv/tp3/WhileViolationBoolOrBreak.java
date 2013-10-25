package vv.tp3;

import net.sourceforge.pmd.lang.java.ast.ASTBooleanLiteral;
import net.sourceforge.pmd.lang.java.ast.ASTBreakStatement;
import net.sourceforge.pmd.lang.java.ast.ASTExpression;
import net.sourceforge.pmd.lang.java.ast.ASTReturnStatement;
import net.sourceforge.pmd.lang.java.ast.ASTStatement;
import net.sourceforge.pmd.lang.java.ast.ASTWhileStatement;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

/**
 * q3 of tp2
 * @author jimmy dano & anthony le mee
 */
public class WhileViolationBoolOrBreak extends AbstractJavaRule {

	@Override
    public Object visit(ASTWhileStatement node, Object data) {
        if (hasWhileBooleanOrReturnOrBreak(node)) {
            addViolation(data, node);
        }
        return super.visit(node,data);
    }

	/**
	 * if we find in the expression of the while condition is a boolean, we return true
	 * and if there is a return or a break in the statement of the while, we return true
	 * @param node the while statement to analyze
	 * @return if there is a true/false condition in the while or if it contains a return or break statemetn
	 */
	private boolean hasWhileBooleanOrReturnOrBreak(ASTWhileStatement node) {
    	for(int i = 0; i < node.jjtGetNumChildren(); i++){
    		if(node.jjtGetChild(i) instanceof ASTExpression){
    			ASTExpression ast = (ASTExpression)node.jjtGetChild(i);
				if (ast.findDescendantsOfType(ASTBooleanLiteral.class).size() >= 1) {
					return true;
				}
    		} else if(node.jjtGetChild(i) instanceof ASTStatement) {
    			ASTStatement ast = (ASTStatement)node.jjtGetChild(i);
    			if (ast.findDescendantsOfType(ASTReturnStatement.class).size() >= 1 || 
    					ast.findDescendantsOfType(ASTBreakStatement.class).size() >= 1) {
					return true;
				}
    		}
    	}
        return false;
    }
}