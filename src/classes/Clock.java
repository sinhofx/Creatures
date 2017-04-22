package classes;

public class Clock {
    
    private long time;
    
    public Clock() {
        this.time = System.currentTimeMillis();
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = System.currentTimeMillis() - this.time;
    }
    
    
}
