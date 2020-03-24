/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccfco.wgyweb.util;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author ccfco
 */
/**
 * 获取批量异常信息
 * @param e
 * @return
 */
public class ConstraintViolationExceptionHandler {
    /**
     * 获取批量异常信息
     * @param e
     * @return 
     */
    public static String getMessage(ConstraintViolationException e) {
		List<String> msgList = new ArrayList<String>();
		for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
			msgList.add(constraintViolation.getMessage());
        }
		String messages = StringUtils.join(msgList.toArray(), ";");
		return messages;
	}

    private ConstraintViolationExceptionHandler() {
    }
}
