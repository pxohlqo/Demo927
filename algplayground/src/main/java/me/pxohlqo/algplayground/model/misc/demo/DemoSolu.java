package me.pxohlqo.algplayground.model.misc.demo;

import org.jetbrains.annotations.NotNull;

import me.pxohlqo.algplayground.model.BaseSolution;
import me.pxohlqo.soluinfo.SolutionInfo;


@SolutionInfo(title = "demo title", description = "demo description", path = "me.pxohlqo.algplayground.model.misc.demo.DemoSolu")
public class DemoSolu extends BaseSolution {

    @NotNull
    @Override
    public String solve(@NotNull Object... input) {
        return "Demo result";
    }
}
