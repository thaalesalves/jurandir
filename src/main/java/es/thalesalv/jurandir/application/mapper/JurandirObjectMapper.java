package es.thalesalv.jurandir.application.mapper;

import java.util.List;
import java.util.Set;

public interface JurandirObjectMapper<Input, Output> {

    public Output map(Input input);

    public Set<Output> map(List<Input> input);
}
