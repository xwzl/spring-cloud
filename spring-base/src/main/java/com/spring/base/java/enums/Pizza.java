package com.spring.base.java.enums;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Pizza {

    private PizzaStatus status;

    /**
     * EnumSet 是一种专门为枚举类型所设计的 Set 类型。
     * <p>
     * 与HashSet相比，由于使用了内部位向量表示，因此它是特定 Enum 常量集的非常有效且紧凑的表示形式。
     * <p>
     * 它提供了类型安全的替代方法，以替代传统的基于int的“位标志”，使我们能够编写更易读和易于维护的简洁代码。
     */
    private static EnumSet<PizzaStatus> undeliveredPizzaStatuses =
            EnumSet.of(PizzaStatus.ORDERED, PizzaStatus.READY);

    /**
     * EnumMap是一个专门化的映射实现，用于将枚举常量用作键。与对应的 HashMap 相比，它是一个高效紧凑的实现，
     * 并且在内部表示为一个数组:
     */
    EnumMap<PizzaStatus, Pizza> map;



    public boolean isDeliverable() {
        return this.status.isReady();
    }

    public void printTimeToDeliver() {
        System.out.println("Time to delivery is " + this.getStatus().getTimeToDelivery());
    }

    //public static EnumMap<PizzaStatus, List<Pizza>>
    //groupPizzaByStatus(List<Pizza> pizzaList) {
    //    EnumMap<PizzaStatus, List<Pizza>> pzByStatus =
    //            new EnumMap<PizzaStatus, List<Pizza>>(PizzaStatus.class);
    //
    //    for (Pizza pz : pizzaList) {
    //        PizzaStatus status = pz.getStatus();
    //        if (pzByStatus.containsKey(status)) {
    //            pzByStatus.get(status).add(pz);
    //        } else {
    //            List<Pizza> newPzList = new ArrayList<Pizza>();
    //            newPzList.add(pz);
    //            pzByStatus.put(status, newPzList);
    //        }
    //    }
    //    return pzByStatus;
    //}

    public static EnumMap<PizzaStatus, List<Pizza>> groupPizzaByStatus(List<Pizza> pzList) {
        return pzList.stream().collect(Collectors.groupingBy(Pizza::getStatus, () -> new EnumMap<>(
                PizzaStatus.class), Collectors.toList()));
    }

    public static List<Pizza> getAllUndeliveredPizzas(List<Pizza> input) {
        return input.stream().filter(
                (s) -> undeliveredPizzaStatuses.contains(s.getStatus()))
                .collect(Collectors.toList());
    }


    public static void main(String[] args) {
        Pizza pizza = new Pizza();
        pizza.status = PizzaStatus.DELIVERED;
        pizza.printTimeToDeliver();
        System.out.println(pizza.status.isDelivered());
        System.out.println(JSONObject.toJSONString(pizza.status));
    }

    @Test
    public void givenPizaOrders_whenRetrievingUnDeliveredPzs_thenCorrectlyRetrieved() {
        List<Pizza> pzList = new ArrayList<>();
        Pizza pz1 = new Pizza();
        pz1.setStatus(PizzaStatus.DELIVERED);

        Pizza pz2 = new Pizza();
        pz2.setStatus(PizzaStatus.ORDERED);

        Pizza pz3 = new Pizza();
        pz3.setStatus(PizzaStatus.ORDERED);

        Pizza pz4 = new Pizza();
        pz4.setStatus(PizzaStatus.READY);

        pzList.add(pz1);
        pzList.add(pz2);
        pzList.add(pz3);
        pzList.add(pz4);

        List<Pizza> undeliveredPzs = Pizza.getAllUndeliveredPizzas(pzList);
        Assert.assertEquals(3, undeliveredPzs.size());
    }

    @Test
    public void givenPizaOrders_whenGroupByStatusCalled_thenCorrectlyGrouped() {
        List<Pizza> pzList = new ArrayList<>();
        Pizza pz1 = new Pizza();
        pz1.setStatus(PizzaStatus.DELIVERED);

        Pizza pz2 = new Pizza();
        pz2.setStatus(PizzaStatus.ORDERED);

        Pizza pz3 = new Pizza();
        pz3.setStatus(PizzaStatus.ORDERED);

        Pizza pz4 = new Pizza();
        pz4.setStatus(PizzaStatus.READY);

        pzList.add(pz1);
        pzList.add(pz2);
        pzList.add(pz3);
        pzList.add(pz4);

        EnumMap<PizzaStatus, List<Pizza>> map = Pizza.groupPizzaByStatus(pzList);
        Assert.assertEquals(1, map.get(PizzaStatus.DELIVERED).size());
        Assert.assertEquals(2, map.get(PizzaStatus.ORDERED).size());
        Assert.assertEquals(1, map.get(PizzaStatus.READY).size());
    }


}
