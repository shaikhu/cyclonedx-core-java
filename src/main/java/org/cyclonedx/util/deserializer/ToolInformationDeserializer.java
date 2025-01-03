/*
 * This file is part of CycloneDX Core (Java).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 * Copyright (c) OWASP Foundation. All Rights Reserved.
 */
package org.cyclonedx.util.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cyclonedx.model.Component;
import org.cyclonedx.model.Service;
import org.cyclonedx.model.metadata.ToolInformation;

import java.io.IOException;
import java.util.List;

public class ToolInformationDeserializer
    extends JsonDeserializer<ToolInformation>
{
  private final ObjectMapper mapper = new ObjectMapper();

  @Override
  public ToolInformation deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws IOException
  {
    JsonNode node = jsonParser.getCodec().readTree(jsonParser);
    return parseToolInformation(node);
  }

  private ToolInformation parseToolInformation(JsonNode toolsNode) {
    ToolInformation toolInformation = new ToolInformation();
    if (toolsNode.has("components")) {
      parseComponents(toolsNode.get("components"), toolInformation);
    }
    if (toolsNode.has("services")) {
      parseServices(toolsNode.get("services"), toolInformation);
    }
    return toolInformation;
  }

  private void parseComponents(JsonNode componentsNode, ToolInformation toolInformation) {
    if (componentsNode != null) {
      // Case JSON input where "components" is an array
      if (componentsNode.isArray()) {
        List<Component> components = mapper.convertValue(componentsNode, new TypeReference<List<Component>>() {});
        toolInformation.setComponents(components);
      }
      // Case XML-like input where "components" contains "component"
      else if (componentsNode.isObject() && componentsNode.has("component")) {
        JsonNode componentNode = componentsNode.get("component");
        if (componentNode.isArray()) {
          List<Component> components = mapper.convertValue(componentNode, new TypeReference<List<Component>>() {});
          toolInformation.setComponents(components);
        } else if (componentNode.isObject()) {
          Component component = mapper.convertValue(componentNode, Component.class);
          toolInformation.getComponents().add(component);
        }
      }
    }
  }

  private void parseServices(JsonNode servicesNode, ToolInformation toolInformation) {
    if (servicesNode != null) {
      // Case JSON input where "services" is an array
      if (servicesNode.isArray()) {
        List<Service> services = mapper.convertValue(servicesNode, new TypeReference<List<Service>>() {});
        toolInformation.setServices(services);
      }
      // Case XML-like input where "services" contains "service"
      else if (servicesNode.isObject() && servicesNode.has("service")) {
        JsonNode serviceNode = servicesNode.get("service");
        if (serviceNode.isArray()) {
          List<Service> services = mapper.convertValue(servicesNode, new TypeReference<List<Service>>() {});
          toolInformation.setServices(services);
        } else if (serviceNode.isObject()) {
          Service service = mapper.convertValue(servicesNode, Service.class);
          toolInformation.getServices().add(service);
        }
      }
    }
  }
}
