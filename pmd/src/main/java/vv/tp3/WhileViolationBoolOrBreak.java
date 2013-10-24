package vv.tp3;

import net.sourceforge.pmd.lang.ecmascript.ast.ASTWhileLoop;
import net.sourceforge.pmd.lang.java.ast.ASTBooleanLiteral;
import net.sourceforge.pmd.lang.java.ast.ASTBreakStatement;
import net.sourceforge.pmd.lang.java.ast.ASTReturnStatement;
import net.sourceforge.pmd.lang.java.ast.JavaNode;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

public class WhileViolationBoolOrBreak extends AbstractJavaRule {

    public Object visit(ASTWhileLoop node, Object data) {
        if (hasWhileBooleanOrBreak(node)) {
            addViolation(data, node);
        }
        return super.visit((JavaNode)node,data);
    }
    
    private boolean hasWhileBooleanOrBreak(ASTWhileLoop node) {
        return (node.getCondition() instanceof ASTBooleanLiteral) ||
        		(hasBreakCond(node));
    }

	private boolean hasBreakCond(ASTWhileLoop node) {
		if((node.findChildrenOfType(ASTBreakStatement.class) != null) || 
				node.findChildrenOfType(ASTReturnStatement.class) != null)
			return true;
		return false;
	}
}