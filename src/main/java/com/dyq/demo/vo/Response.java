package com.dyq.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 响应 值对象.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {
	private int code;
	private String msg;
	private int count;
	private Object data;
}
