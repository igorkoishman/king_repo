package posts.controller;

import java.util.List;

public class ValidationStatus {

	String status;
	List<String> errorMessages;

	public ValidationStatus(String status, List<String> errorMessages) {
		this.status = status;
		this.errorMessages = errorMessages;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ValidationStatus{");
		sb.append("status='").append(status).append('\'');
		sb.append(", errorMessages=").append(errorMessages);
		sb.append('}');
		return sb.toString();
	}

	public static ValidationStatus successValidation() {
		ValidationStatus validationStatus = new ValidationStatus("SUCCESS", null);
		return validationStatus;

	}
}
