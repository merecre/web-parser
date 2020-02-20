package crawler.core.usecase;

@FunctionalInterface
public interface UseCaseCommand<R> {
    R execute();
}
