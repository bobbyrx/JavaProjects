package bg.sofia.uni.fmi.mjt.netflix.platform;

import bg.sofia.uni.fmi.mjt.netflix.account.Account;
import bg.sofia.uni.fmi.mjt.netflix.content.Streamable;
import bg.sofia.uni.fmi.mjt.netflix.exceptions.ContentUnavailableException;

public interface StreamingService {

    void watch(Account user, String videoContentName) throws ContentUnavailableException;

    Streamable findByName(String videoContentName);

    Streamable mostViewed();

    int totalWatchedTimeByUsers();
}
