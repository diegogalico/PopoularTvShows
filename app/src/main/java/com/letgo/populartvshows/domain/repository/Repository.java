package com.letgo.populartvshows.domain.repository;

import com.letgo.populartvshows.domain.model.entities.TvShow;

/**
 * @author diego.galico
 *
 * A sample repository with CRUD operations on a model.
 */
public interface Repository {

    boolean insert(TvShow model);

    boolean update(TvShow model);

    TvShow get(Object id);

    boolean delete(TvShow model);
}
