package com.chaos.pf4j;

import ro.fortsoft.pf4j.Extension;

@Extension
public class WelcomeGreeting implements Greeting  {
    @Override
    public String getGreeting() {
        return "Welcome";
    }
}
