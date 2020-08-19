package com.chaos.pf4j;

import ro.fortsoft.pf4j.JarPluginManager;
import ro.fortsoft.pf4j.PluginManager;

import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // jar插件管理器
        PluginManager pluginManager = new JarPluginManager();

        // 加载指定路径插件
        //pluginManager.loadPlugin(Paths.get("chaos.war"));

        // 启动指定插件(也可以加载所有插件)
        //pluginManager.startPlugin("welcome-plugin");

        // 执行插件
        List<Greeting> greetings = pluginManager.getExtensions(Greeting.class);
        for (Greeting greeting : greetings) {
            System.out.println(">>> " + greeting.getGreeting());
        }

        // 停止并卸载指定插件
        //pluginManager.stopPlugin("welcome-plugin");
        //pluginManager.unloadPlugin("welcome-plugin");

    }
}
