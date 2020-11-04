package bg.sofia.uni.fmi.mjt.netflix.platform;

import bg.sofia.uni.fmi.mjt.netflix.account.Account;
import bg.sofia.uni.fmi.mjt.netflix.content.Streamable;
import bg.sofia.uni.fmi.mjt.netflix.exceptions.ContentNotFoundException;
import bg.sofia.uni.fmi.mjt.netflix.exceptions.ContentUnavailableException;
import bg.sofia.uni.fmi.mjt.netflix.exceptions.UserNotFoundException;

import java.time.LocalDateTime;

public class Netflix implements StreamingService{

    private Account[] accounts;
    private Streamable[] streamableContent;
    private int[] timeWatchEachStreamableContent;

    private boolean isAccountFound(Account user){
        for(Account name:this.getAccounts()){
            if(name.equals(user))return true;
        }
        return false;
    }

    public Netflix(Account[] accounts, Streamable[] streamableContent) {
        this.accounts = accounts;
        this.streamableContent = streamableContent;
        timeWatchEachStreamableContent=new int[streamableContent.length];
    }

    public Account[] getAccounts() {
        return accounts;
    }

    public void setAccounts(Account[] accounts) {
        this.accounts = accounts;
    }

    public Streamable[] getStreamableContent() {
        return streamableContent;
    }

    public void setStreamableContent(Streamable[] streamableContent) {
        this.streamableContent = streamableContent;
    }

    @Override
    public void watch(Account user, String videoContentName) throws ContentUnavailableException {

       if(!isAccountFound(user)){
           throw new UserNotFoundException(user.username()+" not found!");
       }

        int userAge = LocalDateTime.now().compareTo(user.birthdayDate());

       if(findByName(videoContentName)==null){
           throw new ContentNotFoundException(videoContentName+" not found!");
       }

        switch (findByName(videoContentName).getRating()){
            case PG13:
                if(userAge<14){
                    throw new ContentUnavailableException(user.username()+" must be 14 or above to watch this video!");
                }
                break;
            case NC17:
                if(userAge<18){
                    throw new ContentUnavailableException(user.username()+" must be 18 or above to watch this video!");
                }
                break;
        }

        for(int i=0;i<this.getStreamableContent().length;i++){
            if(this.getStreamableContent()[i].getTitle().equals(videoContentName)){
                this.timeWatchEachStreamableContent[i]++;
                break;
            }
        }

    }

    @Override
    public Streamable findByName(String videoContentName) {
        Streamable streamable=null;
        for(Streamable stream:this.getStreamableContent()){
            if(stream.getTitle().equals(videoContentName)){
                streamable=stream;
            }
        }
        return streamable;
    }

    @Override
    public Streamable mostViewed() {
        int maxViewed= Integer.MIN_VALUE;
        int indexValue=-1;
        for(int i=0;i<this.timeWatchEachStreamableContent.length;i++){
            if(maxViewed<this.timeWatchEachStreamableContent[i]){
                maxViewed=this.timeWatchEachStreamableContent[i];
                indexValue=i;
            }
        }
        Streamable streamable=null;
        if(maxViewed!=0){
            streamable=this.getStreamableContent()[indexValue];
        }
        return streamable;
    }

    @Override
    public int totalWatchedTimeByUsers() {
        int countWatchedMinute=0;
        for(int i=0;i<this.timeWatchEachStreamableContent.length;i++){
            countWatchedMinute+=this.getStreamableContent()[i].getDuration()*this.timeWatchEachStreamableContent[i];
        }
        return countWatchedMinute;
    }
}
