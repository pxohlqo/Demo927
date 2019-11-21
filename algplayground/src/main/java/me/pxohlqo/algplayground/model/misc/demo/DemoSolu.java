package me.pxohlqo.algplayground.model.misc.demo;

import org.jetbrains.annotations.NotNull;

import me.pxohlqo.algplayground.model.BaseSolution;
import me.pxohlqo.soluinfo.SolutionInfo;


@SolutionInfo(title = "demo title", description = "demo description")
public class DemoSolu extends BaseSolution {

    @NotNull
    @Override
    protected Object performSolve(@NotNull Object... input) {
        return "demo output";
    }
}
