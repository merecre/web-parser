package crawler.core.usecase;

public interface NotificationService<T> {
    void notify(T notification);
}
