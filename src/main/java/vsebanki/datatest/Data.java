package vsebanki.datatest;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Data {
    private String baseMenuName;
    private String subMenuName;
    private String depositAmount;
    private String time;
    private String depositType;
    private List<String> banksList;
    private List<String> listParameters;
    private String checkCountDeposit;
    private String checkBunk;
    private String checkBetBank;
    private String checkPeriodOfDeposit;
    private String checkIncomeBank;


}
