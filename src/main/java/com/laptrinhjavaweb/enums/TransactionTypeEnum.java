package com.laptrinhjavaweb.enums;

public enum TransactionTypeEnum {
	

	 	QUATRINHCSKH("Qúa trình CSKH"),
	 	DANDIXEM("Dẫn đi xem");
	    private final String transactionTypeValue;

	    TransactionTypeEnum(String transactionTypeValue) {
	        this.transactionTypeValue = transactionTypeValue;
	    }

		public String getTransactionTypeValue() {
			return transactionTypeValue;
		}
	
}
