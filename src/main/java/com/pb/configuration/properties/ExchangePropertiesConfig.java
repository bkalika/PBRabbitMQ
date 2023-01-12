package com.pb.configuration.properties;

import java.util.List;

/**
 * @author @bkalika
 */
public record ExchangePropertiesConfig (
    String name,
    String type,
    List<BindingPropertiesConfig> binding
) {
}
