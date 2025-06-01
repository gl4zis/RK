package ru.itmo.rk.hw4;

import lombok.RequiredArgsConstructor;
import ru.itmo.rk.hw4.exception.IncompatibleTypesException;
import ru.itmo.rk.hw4.var.VarHeap;
import ru.itmo.rk.hw4.var.Variable;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class StatementVisitor extends CatlinBaseVisitor<Object> {
    private final VarHeap heap;
    private final ExpressionVisitor expressionVisitor;

    @Override
    public Object visitProgram(CatlinParser.ProgramContext pc) {
        List<Object> results = new ArrayList<>();
        for (CatlinParser.StatementContext statement : pc.statement()) {
            results.add(visit(statement));
        }
        return results;
    }

    @Override
    public Object visitDeclaration(CatlinParser.DeclarationContext dc) {
        String varName = dc.variable().VAR_NAME().getText();
        boolean isConstant = isConstant(dc);
        Object value = expressionVisitor.visit(dc.expression());

        heap.newVar(new Variable(varName, isConstant, value));

        return null;
    }

    @Override
    public Object visitAssigment(CatlinParser.AssigmentContext ac) {
        String varName = ac.variable().VAR_NAME().getText();
        Object value = expressionVisitor.visit(ac.expression());

        heap.updateVar(new Variable(varName, false, value));

        return null;
    }

    @Override
    public Object visitPrint(CatlinParser.PrintContext pc) {
        Object value = expressionVisitor.visit(pc.expression());
        System.out.println(value);

        return value;
    }

    @Override
    public Object visitIf(CatlinParser.IfContext ic) {
        if (checkBoolCondition(ic.expression())) {
            return visit(ic.block(0));
        } else {
            return visit(ic.block(1));
        }
    }

    @Override
    public Object visitWhile(CatlinParser.WhileContext wc) {
        List<Object> results = new ArrayList<>();
        while (checkBoolCondition(wc.expression())) {
            results.add(visit(wc.block()));
        }
        return results;
    }

    @Override
    public Object visitBlock(CatlinParser.BlockContext bc) {
        List<Object> results = new ArrayList<>();
        for (CatlinParser.StatementContext statement : bc.statement()) {
            results.add(visit(statement));
        }
        return results;
    }

    private static boolean isConstant(CatlinParser.DeclarationContext dc) {
        String keyword = dc.type.getText();
        return DeclarationPrefix.of(keyword).isConstant();
    }

    private boolean checkBoolCondition(CatlinParser.ExpressionContext expression) {
        Object value = expressionVisitor.visit(expression);
        if (value instanceof Boolean condition) {
            return condition;
        } else {
            throw new IncompatibleTypesException("while", value.getClass());
        }
    }
}
