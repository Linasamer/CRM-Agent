package com.code.secretary.exceptions;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.code.secretary.exceptions.custom.BusinessException;
import com.code.secretary.exceptions.custom.DataNotFoundException;

@Component
public class ExceptionsHandlerService {

	private ExceptionsHandlerService() {
	}

	/**
	 * Handle any exception
	 * 
	 * @param e
	 * @param methodErrorMessage
	 * @return
	 */
	public static RuntimeException handleException(Exception e, String methodErrorMessage) {
		if (e instanceof BusinessException)
			return (BusinessException) e;
		if (e instanceof DataNotFoundException)
			return (DataNotFoundException) e;
		e.printStackTrace();
		return new BusinessException(methodErrorMessage);
	}

	/**
	 * Handle any exception
	 * 
	 * @param e
	 * @param methodErrorMessage
	 * @return
	 */


	/**
	 * 
	 * @param object
	 * @param errorMessage
	 * @param params
	 * @return
	 */
	public static <T extends Object> T handleNotFoundException(Optional<T> object, String errorMessage, Object... params) {
		if (!object.isPresent())
			throw new DataNotFoundException(errorMessage, params);
		return object.get();
	}
}
