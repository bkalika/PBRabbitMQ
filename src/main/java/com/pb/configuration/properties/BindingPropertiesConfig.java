package com.pb.configuration.properties;

import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Map;

/**
 * @author @bkalika
 */
public record BindingPropertiesConfig(
    String queue,
    @DefaultValue("")
    String route,
    Map<String, String> header
) {
}
