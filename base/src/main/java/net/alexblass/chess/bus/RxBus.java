package net.alexblass.chess.bus;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * An event bus to broadcast events across the app.
 */
public final class RxBus {
    private PublishSubject<Object> mBus = PublishSubject.create();

    public void post(Object event) {
        mBus.onNext(event);
    }

    public Observable<Object> toObservable() {
        return mBus;
    }
}