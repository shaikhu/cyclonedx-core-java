package org.cyclonedx.model.component.data;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.cyclonedx.util.deserializer.DatasetsChoiceDeserializer;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(using = DatasetsChoiceDeserializer.class)
public class DatasetChoice
{
  private String ref;
  @JsonUnwrapped
  private ComponentData componentData;

  public String getRef() {
    return ref;
  }

  public void setRef(final String ref) {
    this.ref = ref;
  }

  public ComponentData getComponentData() {
    return componentData;
  }

  public void setComponentData(final ComponentData componentData) {
    this.componentData = componentData;
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof DatasetChoice)) {
      return false;
    }
    DatasetChoice that = (DatasetChoice) object;
    return Objects.equals(ref, that.ref) && Objects.equals(componentData, that.componentData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ref, componentData);
  }
}
