package com.hd.kzscrm.common.enums;

/**
 * 货币种类枚举
 * @author caiwl
 */
public enum CurrencyTypeEnum {
	RMB("RMB", "¥"), // 人民币
	USD("USD", "$"), // 美元
	EUR("EUR", "€"), // 欧元
	GBP("GBP", "￡"), // 英镑
	AUD("AUD", "$"); // 澳大利亚元
	
	/**
	 * 货币简称
	 */
	private String abbreviation;
	/**
	 * 货币符号
	 */
	private String symbol;
	
	private CurrencyTypeEnum(String abbreviation, String symbol) {
		this.abbreviation = abbreviation;
		this.symbol = symbol;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public static String getSymbolByAbbreviation(String abbreviation) {
		for (CurrencyTypeEnum currencyTypeEnum : CurrencyTypeEnum.values()) {
			if (currencyTypeEnum.getAbbreviation().equals(abbreviation)) {
				return currencyTypeEnum.getSymbol();
			}
		}
		return null;
	}
}
