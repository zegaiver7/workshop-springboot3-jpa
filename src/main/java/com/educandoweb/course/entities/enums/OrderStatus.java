package com.educandoweb.course.entities.enums;

public enum OrderStatus {
   //Desta maneira o java coloca como defaut o primeiro elemento como 0...e assim por diante
	/*
	WAITING_PAYMENT,
	PAID,
	SHIPPED,
	DELIVERED,
	CANCELED;
	*/
	
	//E desta maneira, atribuimos manualmente um valor para cada elemento, para evitar dexar notório que os valores antigos não podem ser mudados
	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	private int code;
	
	private OrderStatus(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static OrderStatus valueOf(int code) {
		for (OrderStatus value : OrderStatus.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid OrderStatus code");
	}
	//E assim resolvemos o risco de que alguém quebre a cadeia do enum devido a uma inserção acidental no começo ou metade dos elementos
}
