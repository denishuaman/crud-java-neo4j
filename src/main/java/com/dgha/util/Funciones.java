package com.dgha.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Funciones {

	public static Date convertirLocalDateTimeADate(LocalDateTime localDateTimeAConvertir) {
		return Date.from(localDateTimeAConvertir.atZone(ZoneId.systemDefault()).toInstant());
	}
}
