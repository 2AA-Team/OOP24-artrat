package it.unibo.artrat.controller.api;

import it.unibo.artrat.model.impl.Request;
import it.unibo.artrat.model.impl.Stage;

public interface Requester {
    public Object getData(Request request);

    public void setStage(Stage newStage);
}
