package com.zzz.fun.rpc;


import com.zzz.fun.rpc.comsumer.CalculatorRemoteImpl;
import com.zzz.fun.rpc.provider.Calculator;

public class ConsumerTest {

    public static void main(String[] args) {
        Calculator calculator = new CalculatorRemoteImpl();
        int result = calculator.add(1, 2);
        System.out.println(String.format("result is %s", result));
    }
}
