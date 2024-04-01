package com.code.secretary.enums.workflow;

public enum WFTaskActionsEnum {
	APPROVE("\u0645\u0640\u0640\u0648\u0627\u0641\u0642\u0640\u0640\u0629"),
	REJECT("\u0631\u0641\u0640\u0640\u0636"),
	REDIRECT("\u062A\u0648\u062C\u064A\u0647"),
	SUPER_SIGN("\u0625\u0639\u062A\u0645\u0627\u062F"),
	REVIEW("\u062A\u062F\u0642\u064A\u0642"),
	NOTIFIED("\u062A\u0640\u0640\u0640\u0645 \u0627\u0644\u0625\u0637\u0640\u0640\u0640\u0644\u0627\u0639"),
	RETURN("\u0627\u0631\u062C\u0627\u0639"),
	SEND_FOR_ADVISE("\u0627\u0631\u0633\u0627\u0644 \u0644\u0625\u0628\u062F\u0627\u0621 \u0627\u0644\u0631\u0623\u064A"),
	PREPARE("\u0625\u0639\u062F\u0627\u062F"),
	NO_ACTION("\u0644\u0645 \u064A\u062A\u0645 \u0625\u062A\u062E\u0627\u0630 \u0625\u062C\u0631\u0627\u0621");

	private String code;

	private WFTaskActionsEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
