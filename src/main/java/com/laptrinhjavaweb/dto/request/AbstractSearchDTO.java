package com.laptrinhjavaweb.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AbstractSearchDTO {
	private Integer page = 1;
	private Integer limit = 10;
}
