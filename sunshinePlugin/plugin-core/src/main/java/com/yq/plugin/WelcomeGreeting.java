package com.yq.plugin;

import com.yq.plugin.extensionPoint.Greeting;
import org.pf4j.Extension;

@Extension
public class WelcomeGreeting implements Greeting {

    public String getGreeting() {
        return "Welcome";
    }
}