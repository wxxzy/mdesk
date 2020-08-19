package com.chaos.pf4j;

import ro.fortsoft.pf4j.ExtensionPoint;

public interface Greeting extends ExtensionPoint {
    String getGreeting();
}
