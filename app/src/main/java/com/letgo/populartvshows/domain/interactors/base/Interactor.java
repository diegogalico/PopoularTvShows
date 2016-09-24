package com.letgo.populartvshows.domain.interactors.base;


/**
 * @author diego.galico
 *
 * This is the main interface of an interactor. Each interactor serves a specific use case.
 */
public interface Interactor {

    /**
     * This is the main method that starts an interactor.
     */
    void execute();
}
