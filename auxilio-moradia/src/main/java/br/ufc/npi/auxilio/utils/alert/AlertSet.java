package br.ufc.npi.auxilio.utils.alert;

import java.util.ArrayList;

public class AlertSet extends ArrayList<Alert> {

	private static final long serialVersionUID = 1L;
	private static final int SHORT_DELAY = 3000;
	private static final int LONG_DELAY = 5000;
	
	public static Alert createInfo(String message) {
		return new AlertSet().withInfo(message).get(0);
	}
	
	public static Alert createSuccess(String message) {
		return new AlertSet().withSuccess(message).get(0);
	}
	
	public static Alert createWarning(String message) {
		return new AlertSet().withWarning(message).get(0);
	}
	
	public static Alert createError(String message) {
		return new AlertSet().withError(message).get(0);
	}
	
	public static Alert createLongInfo(String message) {
		return new AlertSet().withLongInfo(message).get(0);
	}
	
	public static Alert createLongSuccess(String message) {
		return new AlertSet().withLongSuccess(message).get(0);
	}
	
	public static Alert createLongWarning(String message) {
		return new AlertSet().withLongWarning(message).get(0);
	}
	
	public static Alert createLongError(String message) {
		return new AlertSet().withLongError(message).get(0);
	}
	
	public AlertSet withInfo(String message) {
		add(new Alert(Type.INFO, message, SHORT_DELAY));
		return this;
	}
	
	public AlertSet withSuccess(String message) {
		add(new Alert(Type.SUCCESS, message, SHORT_DELAY));
		return this;
	}
	
	public AlertSet withWarning(String message) {
		add(new Alert(Type.WARNING, message, SHORT_DELAY));
		return this;
	}
	
	public AlertSet withError(String message) {
		add(new Alert(Type.ERROR, message, SHORT_DELAY));
		return this;
	}
	
	public AlertSet withLongInfo(String message) {
		add(new Alert(Type.INFO, message, LONG_DELAY));
		return this;
	}
	
	public AlertSet withLongSuccess(String message) {
		add(new Alert(Type.SUCCESS, message, LONG_DELAY));
		return this;
	}
	
	public AlertSet withLongWarning(String message) {
		add(new Alert(Type.WARNING, message, LONG_DELAY));
		return this;
	}
	
	public AlertSet withLongError(String message) {
		add(new Alert(Type.ERROR, message, LONG_DELAY));
		return this;
	}
	
}
