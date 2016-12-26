package com.dashlane.populartvshows.presentation.presenters;

import android.content.Context;
import android.test.AndroidTestCase;

import com.dashlane.populartvshows.domain.interactors.TvShowsInteractor;
import com.dashlane.populartvshows.presentation.mapper.TvShowModelDataMapper;
import com.dashlane.populartvshows.presentation.presenters.impl.PopularTvShowsPresenterImpl;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import rx.Subscriber;

/**
 * @author diego.galico
 *
 * Test for PopularTvShowsPresenterTest class
 */
public class PopularTvShowsPresenterTest extends AndroidTestCase {

  private PopularTvShowsPresenterImpl userListPresenter;

  @Mock
  private Context mockContext;
  @Mock
  private PopularTvShowsPresenter.PopularTvShowsView mockPopularTvShowView;
  @Mock
  private TvShowsInteractor mockTvShowInteractor;
  @Mock
  private TvShowModelDataMapper mockTvShowModelDataMapper;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    MockitoAnnotations.initMocks(this);
    userListPresenter = new PopularTvShowsPresenterImpl(mockTvShowInteractor, mockTvShowModelDataMapper);
    userListPresenter.attachView(mockPopularTvShowView);
  }

  @Test
  public void testPopularTvShowsPresenterInitialize() {
    BDDMockito.given(mockPopularTvShowView.getContext()).willReturn(mockContext);
    userListPresenter.updateTvShowsList();
    Mockito.verify(mockTvShowInteractor).execute(Matchers.any(Subscriber.class));
  }
}
