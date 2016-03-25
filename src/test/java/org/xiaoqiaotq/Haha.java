package org.xiaoqiaotq;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * author: will@ycode.cn
 * date  : 2016/2/7
 */
public class Haha implements DD {
    /**
     *  模拟骰子运算
     * @param args
     */
    public static void main(String[] args) {
        int N=100000;
        Map<Integer, Double> collect = IntStream.range(1, N)
                .parallel()     //多线程处理 另一种方法
                .mapToObj((i) -> {
                    ThreadLocalRandom current = ThreadLocalRandom.current(); // 哈哈看这个
                    int i1 = current.nextInt(1, 7);
                    int i2 = current.nextInt(1, 7);
                    return i1 + i2;
                }).collect(Collectors.groupingBy(Function.identity(),
                        Collectors.summingDouble((i) -> 1.0 / N)));
        System.err.println(collect);
        System.err.println(collect.values().stream().mapToDouble(i->i).sum());
    }

    @Override
    public void message() {
        DD.super.message();
        System.err.println("Haha ");
    }
    public void message1(String zhsan, String lisi, Consumer<Boolean> consumer) {
        CompletableFuture<Integer> zhsanF = CompletableFuture.supplyAsync(() -> getSize(zhsan));
        CompletableFuture<Integer> lisiF = CompletableFuture.supplyAsync(() -> getSize(lisi));
        zhsanF.thenCombine(lisiF, (z, l) -> z > l).thenAccept(consumer);
    }

    public int getSize(String name){
        return name.length();
    }
}
