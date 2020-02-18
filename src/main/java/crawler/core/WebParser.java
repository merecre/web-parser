package crawler.core;

/**
 * Created by DMC on 10/21/2019.
 */
public interface WebParser<T, P> {
    T parse(P html);
}
