package vsebanki.datatest;

import java.util.Arrays;

public class PackageData {

//    List<String> listBank1 = Arrays.asList("ВТБ", "Тинькофф", "Сбербанк", "Газпромбанк");
//    List<String> listParameters1 = Arrays.asList("Со снятием", "С пополнением", "С капитализацией");

    Data pack1 = Data.builder()
            .baseMenuName("Вклады")
            .subMenuName("Вклады")
            .depositAmount("1 000 000")
            .time("6 месяцев")
            .depositType("Обычные вклады")
            .banksList(Arrays.asList("Открытие","ВТБ", "Тинькофф", "Сбербанк", "Газпромбанк"))
            .listParameters(Arrays.asList("Со снятием", "С пополнением", "С капитализацией"))
            .checkCountDeposit("14")
            .checkBunk("Тинькофф")
            .checkBetBank("5,63")
            .checkPeriodOfDeposit("182")
            .checkIncomeBank("27740")
            .build();

//    List<String> listBank2 = Arrays.asList("Ак Барс", "РОССИЯ", "Сбербанк");
//    List<String> listParameters2 = Arrays.asList("С выплатой процентов");


    Data pack2 = Data.builder()
            .baseMenuName("Вклады")
            .subMenuName("Вклады")
            .depositAmount("500 000")
            .time("2 года")
            .depositType("Детский")
            .banksList(Arrays.asList("Ак Барс", "РОССИЯ", "Сбербанк"))
            .listParameters(Arrays.asList("С выплатой процентов"))
            .checkCountDeposit("7")
            .checkBunk("Сбербанк")
            .checkBetBank("6,80")
            .checkPeriodOfDeposit("730")
            .checkIncomeBank("72515")
            .build();

    public Data getPack1() {
        return pack1;
    }

    public Data getPack2() {
        return pack2;
    }


}
