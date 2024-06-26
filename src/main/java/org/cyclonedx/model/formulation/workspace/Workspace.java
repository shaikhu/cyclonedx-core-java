package org.cyclonedx.model.formulation.workspace;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.cyclonedx.model.formulation.common.BasicDataAbstract;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
    "bom-ref", "uid", "name", "aliases", "description", "resourceReferences", "accessMode", "mountPath",
    "managedDataType", "volumeRequest", "volume", "properties"
})
public class Workspace extends BasicDataAbstract
{
  private List<String> aliases;

  private AccessMode accessMode;

  private String mountPath;

  private String managedDataType;

  private String volumeRequest;

  private Volume volume;

  public enum AccessMode {

    @JsonProperty("read-only")
    READ_ONLY("read-only"),
    @JsonProperty("read-write")
    READ_WRITE("read-write"),
    @JsonProperty("read-write-once")
    READ_WRITE_ONCE("read-write-once"),
    @JsonProperty("write-once")
    WRITE_ONCE("write-once"),
    @JsonProperty("write-only")
    WRITE_ONLY("write-only");

    private final String name;

    public String getAccessMode() {
      return this.name;
    }

    AccessMode(String name) {
      this.name = name;
    }
  }

  @JacksonXmlElementWrapper(localName = "aliases")
  @JacksonXmlProperty(localName = "alias")
  public List<String> getAliases() {
    return aliases;
  }

  public void setAliases(final List<String> aliases) {
    this.aliases = aliases;
  }

  public AccessMode getAccessMode() {
    return accessMode;
  }

  public void setAccessMode(final AccessMode accessMode) {
    this.accessMode = accessMode;
  }

  public String getMountPath() {
    return mountPath;
  }

  public void setMountPath(final String mountPath) {
    this.mountPath = mountPath;
  }

  public String getManagedDataType() {
    return managedDataType;
  }

  public void setManagedDataType(final String managedDataType) {
    this.managedDataType = managedDataType;
  }

  public String getVolumeRequest() {
    return volumeRequest;
  }

  public void setVolumeRequest(final String volumeRequest) {
    this.volumeRequest = volumeRequest;
  }

  public Volume getVolume() {
    return volume;
  }

  public void setVolume(final Volume volume) {
    this.volume = volume;
  }
}
