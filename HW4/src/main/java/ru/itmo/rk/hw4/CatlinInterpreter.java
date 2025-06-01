package ru.itmo.rk.hw4;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import ru.itmo.rk.hw4.operation.OperationStrategy;
import ru.itmo.rk.hw4.var.VarHeap;

import java.util.List;
import java.util.stream.Stream;

public class CatlinInterpreter {
    private final StatementVisitor statementVisitor;
    private ParseTree tree;

    public CatlinInterpreter() {
        VarHeap varHeap = new VarHeap();
        OperationStrategy operationStrategy = new OperationStrategy();
        ExpressionVisitor expressionVisitor = new ExpressionVisitor(varHeap, operationStrategy);
        statementVisitor = new StatementVisitor(varHeap, expressionVisitor);
    }

    public void load(String program) {
        CharStream charStream = CharStreams.fromString(program);
        CatlinLexer lexer = new CatlinLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CatlinParser parser = new CatlinParser(tokens);
        tree = parser.program();
    }

    public List<Object> run() {
        if (tree == null) {
            throw new RuntimeException("Program was not loaded");
        }

        List<Object> result = filterNulls((List<Object>) statementVisitor.visit(tree)).toList();
        tree = null;
        return result;
    }

    private Stream<Object> filterNulls(List<?> list) {
        return list.stream()
                .flatMap(obj -> {
                    if (obj == null) {
                        return Stream.empty();
                    } else if (obj instanceof List<?> col) {
                        return filterNulls(col);
                    } else {
                        return Stream.of(obj);
                    }
                });
    }
}
