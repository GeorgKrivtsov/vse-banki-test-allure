package vsebanki.product;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Bank {
    private String name;
    private String bet;
    private String period;
    private String profit;

}
