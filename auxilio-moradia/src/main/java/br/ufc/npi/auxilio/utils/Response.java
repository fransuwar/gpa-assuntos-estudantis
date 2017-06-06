package br.ufc.npi.auxilio.utils;

public class Response {
	private ResponseStatus status;
	private Object object;
	
	
	public Response() {
		this(ResponseStatus.DONE, null);
	}
	
	public Response(ResponseStatus status, Object object) {
		this.status = status;
		this.object = object;
	}
	
	public Response withDoneStatus() {
		this.status = ResponseStatus.DONE;
		return this;
	}
	
	public Response withFailStatus() {
		this.status = ResponseStatus.FAIL;
		return this;
	}
	
	public Response withObject(Object object) {
		this.object = object;
		return this;
	}

	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

}
