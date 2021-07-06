package es.thalesalv.jurandir.adapter.bean;

import es.thalesalv.jurandir.adapter.model.ControllerResponse;
import es.thalesalv.jurandir.domain.model.ContextEntry;

public interface GPTAdapter {

    public ControllerResponse addContextEntry(ContextEntry entry);
}
