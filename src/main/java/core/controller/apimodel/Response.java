package core.controller.apimodel;

import java.util.Objects;

public class Response {

	String errorMessage;

	public Response(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Response response = (Response) o;
		return Objects.equals(errorMessage, response.errorMessage);
	}

	@Override
	public int hashCode() {

		return Objects.hash(errorMessage);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Response{");
		sb.append("errorMessage='").append(errorMessage).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
