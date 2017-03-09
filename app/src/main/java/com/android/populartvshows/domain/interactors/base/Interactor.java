package com.android.populartvshows.domain.interactors.base;


import com.android.populartvshows.domain.executors.PostExecutionThread;
import com.android.populartvshows.domain.executors.ThreadExecutor;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * @author diego.galico
 *
 * This is the main interface of an interactor. Each interactor serves a specific use case.
 */
public abstract class Interactor<T> {

    private final ThreadExecutor mThreadExecutor;
    private final PostExecutionThread mPostExecutionThread;

    private Subscription mSubscription = Subscriptions.empty();

    protected Interactor(ThreadExecutor threadExecutor,
                      PostExecutionThread postExecutionThread) {
        this.mThreadExecutor = threadExecutor;
        this.mPostExecutionThread = postExecutionThread;
    }

    /**
     * Builds an {@link rx.Observable} which will be used when executing the current {@link Interactor}.
     */
    protected abstract Observable<T> buildInteractorObservable();

    /**
     * Executes the current use case.
     *
     * @param useCaseSubscriber The guy who will be listen to the observable build
     * with {@link #buildInteractorObservable()}.
     */
    @SuppressWarnings("unchecked")
    public void execute(Subscriber useCaseSubscriber) {
        this.mSubscription = this.buildInteractorObservable()
                .subscribeOn(Schedulers.from(mThreadExecutor))
                .observeOn(mPostExecutionThread.getScheduler())
                .subscribe(useCaseSubscriber);
    }

    /**
     * Unsubscribes from current {@link rx.Subscription}.
     */
    public void unsubscribe() {
        if (!mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
