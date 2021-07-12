package es.thalesalv.jurandir.application.bean;

import javax.script.Bindings;
import javax.script.ScriptEngine;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

@Component
public class JavaScriptEngine {

    @Bean
    public ScriptEngine scriptEngine() {
        // return new ScriptEngineManager().getEngineByName("nashorn");
        return new NashornScriptEngineFactory().getScriptEngine("--language=es6");
    }

    @Bean
    @DependsOn({"scriptEngine"})
    public Bindings scriptEngineBindings() {
        return scriptEngine().createBindings();
    }
}
