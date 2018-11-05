package posts.controller;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Res {

  private String invocationId;
  private String responseStatus;
  private List<String> errors;
  private List<CatData> mappingList;

  public String getInvocationId() {
    return invocationId;
  }

  public void setInvocationId(String invocationId) {
    this.invocationId = invocationId;
  }

  public String getResponseStatus() {
    return responseStatus;
  }

  public void setResponseStatus(String responseStatus) {
    this.responseStatus = responseStatus;
  }

  public List<String> getErrors() {
    return errors;
  }

  public void setErrors(List<String> errors) {
    this.errors = errors;
  }

  public List<CatData> getMappingList() {
    return mappingList;
  }

  public void setMappingList(List<CatData> mappingList) {
    this.mappingList = mappingList;
  }


  @Override
  public String toString() {
    return new ToStringBuilder(this).append("invocationId", invocationId).append("responseStatus", responseStatus).append("errors", errors)
        .append("mappingList", mappingList).toString();
  }
}
