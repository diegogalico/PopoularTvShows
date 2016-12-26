package com.dashlane.populartvshows.domain.mapper;

import com.dashlane.populartvshows.data.entities.TvShowEntity;
import com.dashlane.populartvshows.data.entities.TvShowsWrapperEntity;
import com.dashlane.populartvshows.domain.TvShowData;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * @author diego.galico
 *
 * Test for TvShowEntityDataMapper class
 */
public class TvShowEntityDataMapperTest {

    private static final int FAKE_ID = 123;
    private static final String FAKE_NAME = "The Avengers";
    private static final double FAKE_VOTE_AVERAGE = 8.1;
    private static final String FAKE_OVERVIEW = "The Avengers is a spy-fi British television series created in 1961";

    private TvShowEntityDataMapper tvShowEntityDataMapper;

    @Before
    public void setUp() throws Exception {
        tvShowEntityDataMapper = new TvShowEntityDataMapper();
    }

    @Test
    public void testTransformUserEntity() {
        TvShowEntity tvShowEntity = createFakeUserEntity();
        TvShowData tvShowData = tvShowEntityDataMapper.transform(tvShowEntity);

        assertThat(tvShowData, is(instanceOf(TvShowData.class)));
        assertThat(tvShowData.getId(), is(FAKE_ID));
        assertThat(tvShowData.getName(), is(FAKE_NAME));
        assertThat(tvShowData.getVoteAverage(), is(FAKE_VOTE_AVERAGE));
        assertThat(tvShowData.getOverview(), is(FAKE_OVERVIEW));
    }

    @Test
    public void testTransformUserEntityCollection() {
        TvShowEntity mockTvShowEntityOne = mock(TvShowEntity.class);
        TvShowEntity mockTvShowEntityTwo = mock(TvShowEntity.class);

        List<TvShowEntity> tvShowEntityList = new ArrayList<>(5);
        tvShowEntityList.add(mockTvShowEntityOne);
        tvShowEntityList.add(mockTvShowEntityTwo);

        TvShowsWrapperEntity tvShowWrapperEntityList = new TvShowsWrapperEntity(tvShowEntityList);

        List<TvShowData> tvShowDataList = tvShowEntityDataMapper.transform(tvShowWrapperEntityList);

        assertThat(tvShowDataList.toArray()[0], is(instanceOf(TvShowData.class)));
        assertThat(tvShowDataList.toArray()[1], is(instanceOf(TvShowData.class)));
        assertThat(tvShowDataList.size(), is(2));
    }

    private TvShowEntity createFakeUserEntity() {
        TvShowEntity tvShowEntity = new TvShowEntity();
        tvShowEntity.setName(FAKE_NAME);
        tvShowEntity.setId(FAKE_ID);
        tvShowEntity.setVoteAverage(FAKE_VOTE_AVERAGE);
        tvShowEntity.setOverview(FAKE_OVERVIEW);

        return tvShowEntity;
    }
}