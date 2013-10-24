package vv.tp3;

import net.sourceforge.pmd.lang.ecmascript.ast.ASTWhileLoop;
import net.sourceforge.pmd.lang.java.ast.ASTBooleanLiteral;
import net.sourceforge.pmd.lang.java.ast.JavaNode;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

public class WhileBooleanViolation extends AbstractJavaRule {

    public Object visit(ASTWhileLoop node, Object data) {
        if (hasWhileBoolean(node)) {
            addViolation(data, node);
        }
        return super.visit((JavaNode)node,data);
    }
    
    //q2
    private boolean hasWhileBoolean(ASTWhileLoop node) {
        return node.getCondition() instanceof ASTBooleanLiteral;
    }
}