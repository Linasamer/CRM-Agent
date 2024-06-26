package com.code.secretary.models.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrxnLst {
	
	@JsonProperty("RecPgCtrlOut")
	private RecPgCtrlOutResponse recPgCtrlOutResponse;
	
	@JsonProperty("TrxnDtls")
	private List<TransactionDetail> transactionDetails;
}
