package com.code.secretary.models.responses;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardLst {

    @JsonProperty("RecPgCtrlOut")
    private RecPgCtrlOutResponse recPgCtrlOut;

    @JsonProperty("CreditCards")
    private List<CreditCard> creditCard;

}