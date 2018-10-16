package org.roman.blockChain.DTO;

import lombok.Getter;
import lombok.Setter;

public class SendToAnotheUserDto {

    @Setter @Getter private int oldUserId;
    @Setter @Getter private int newUserId;
    @Setter @Getter private int blockIs;

}
