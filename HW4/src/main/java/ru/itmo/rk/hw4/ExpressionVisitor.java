package ru.itmo.rk.hw4;

import lombok.RequiredArgsConstructor;
import ru.itmo.rk.hw4.exception.IncompatibleTypesException;
import ru.itmo.rk.hw4.operation.OperationStrategy;
import ru.itmo.rk.hw4.var.VarHeap;

@RequiredArgsConstructor
public class ExpressionVisitor extends CatlinBaseVisitor<Object> {
    private final VarHeap heap;
    private final OperationStrategy operationStrategy;

    @Override
    public Object visitMulDivOp(CatlinParser.MulDivOpContext ctx) {
        var left = visit(ctx.expression(0));
        var right = visit(ctx.expression(1));
        var op = ctx.op.getText();
        return operationStrategy.choose(op).apply(left, right);
    }

    @Override
    public Object visitBrackets(CatlinParser.BracketsContext bc) {
        return visit(bc.expression());
    }

    @Override
    public Object visitCompOp(CatlinParser.CompOpContext ctx) {
        var left = visit(ctx.expression(0));
        var right = visit(ctx.expression(1));
        var op = ctx.op.getText();
        return operationStrategy.choose(op).apply(left, right);
    }

    @Override
    public Object visitBool(CatlinParser.BoolContext bc) {
        return Boolean.parseBoolean(bc.BOOL().getText());
    }

    @Override
    public Object visitEqualOp(CatlinParser.EqualOpContext ctx) {
        var left = visit(ctx.expression(0));
        var right = visit(ctx.expression(1));
        var op = ctx.op.getText();
        return operationStrategy.choose(op).apply(left, right);
    }

    @Override
    public Object visitLong(CatlinParser.LongContext nc) {
        return Long.parseLong(nc.LONG().getText());
    }

    @Override
    public Object visitAddSubOp(CatlinParser.AddSubOpContext ctx) {
        var left = visit(ctx.expression(0));
        var right = visit(ctx.expression(1));
        var op = ctx.op.getText();
        return operationStrategy.choose(op).apply(left, right);
    }

    @Override
    public Object visitRound(CatlinParser.RoundContext rc) {
        Object value = visit(rc.expression());
        if (value instanceof Double d) {
            return Math.round(d);
        } else if (value instanceof Long l) {
            return l;
        }
        throw new IncompatibleTypesException("round", value.getClass());
    }

    @Override
    public Object visitVariable(CatlinParser.VariableContext vc) {
        return heap.getVar(vc.VAR_NAME().getText()).value();
    }

    @Override
    public Object visitString(CatlinParser.StringContext sc) {
        return sc.STRING().getText().substring(1, sc.STRING().getText().length() - 1);
    }

    @Override
    public Object visitBoolOp(CatlinParser.BoolOpContext ctx) {
        var left = visit(ctx.expression(0));
        var right = visit(ctx.expression(1));
        var op = ctx.op.getText();
        return operationStrategy.choose(op).apply(left, right);
    }

    @Override
    public Object visitPrefixOp(CatlinParser.PrefixOpContext poc) {
        var expr = visit(poc.expression());
        var op = "prefix" + poc.op.getText();
        return operationStrategy.choose(op).apply(expr);
    }

    @Override
    public Object visitDouble(CatlinParser.DoubleContext dc) {
        return Double.parseDouble(dc.DOUBLE().getText());
    }
}
