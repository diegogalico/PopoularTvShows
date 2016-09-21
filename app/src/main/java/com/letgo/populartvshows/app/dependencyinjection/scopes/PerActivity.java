package com.letgo.populartvshows.app.dependencyinjection.scopes;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author diego.galico
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}