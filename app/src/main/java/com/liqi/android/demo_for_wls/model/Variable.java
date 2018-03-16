package com.liqi.android.demo_for_wls.model;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by heath on 2018/3/14.
 */

public class Variable<T> {
    private BehaviorSubject<T> _subject;
    private T _value;

    public Variable(T value) {
        _value = value;
        _subject = BehaviorSubject.createDefault(value);
    }

    public void setValue(T value) {
        _value = value;
        _subject.onNext(value);
    }

    public T getValue() {
        return _value;
    }

    public Observable<T> asObservable() {
        return _subject;
    }
}