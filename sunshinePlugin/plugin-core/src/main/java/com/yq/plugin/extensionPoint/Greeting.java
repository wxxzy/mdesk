package com.yq.plugin.extensionPoint;

import org.pf4j.ExtensionPoint;

public interface Greeting extends ExtensionPoint {

    String getGreeting();
}
