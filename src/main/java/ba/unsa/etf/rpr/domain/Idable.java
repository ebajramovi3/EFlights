package ba.unsa.etf.rpr.domain;

public interface Idable<T> {

    void setId(T id);

    T getId();
}
