package org.cyclonedx.model.formulation.common;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.cyclonedx.model.ExternalReference;
import org.cyclonedx.util.ResourceReferenceChoiceDeserializer;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(using = ResourceReferenceChoiceDeserializer.class)
public class ResourceReferenceChoice {
  private String ref;
  private ExternalReference externalReference;

  public String getRef() {
    return ref;
  }

  public void setRef(final String ref) {
    this.ref = ref;
  }

  public ExternalReference getExternalReference() {
    return externalReference;
  }

  public void setExternalReference(final ExternalReference externalReference) {
    this.externalReference = externalReference;
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof ResourceReferenceChoice)) {
      return false;
    }
    ResourceReferenceChoice that = (ResourceReferenceChoice) object;
    return Objects.equals(ref, that.ref) && Objects.equals(externalReference, that.externalReference);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ref, externalReference);
  }
}