package ru.itmo.rk.hw4.var;

import ru.itmo.rk.hw4.exception.TryUpdateConstantException;
import ru.itmo.rk.hw4.exception.VarAlreadyDeclaredException;
import ru.itmo.rk.hw4.exception.VarNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class VarHeap {
    private final Map<String, Variable> vars = new HashMap<>();

    public void newVar(Variable variable) {
        if (vars.containsKey(variable.name())) {
            throw new VarAlreadyDeclaredException(variable.name());
        }

        vars.put(variable.name(), variable);
    }

    public void updateVar(Variable variable) {
        Variable oldVar = getVar(variable.name());
        if (oldVar.constant()) {
            throw new TryUpdateConstantException(variable.name());
        }

        vars.put(variable.name(), variable);
    }

    public Variable getVar(String name) {
        if (!vars.containsKey(name)) {
            throw new VarNotFoundException(name);
        }

        return vars.get(name);
    }

    public Set<String> snapshot() {
        return Set.copyOf(vars.keySet());
    }

    public void filter(Set<String> neededVars) {
        vars.keySet().removeIf(key -> !neededVars.contains(key));
    }
}
