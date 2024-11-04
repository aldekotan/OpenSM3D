package code;

public final class Timer {

    private boolean timerJustStarted;
    private boolean countEachLoop;
    private boolean returnLoopCount;
    private boolean timeHasRunOut;
    private int loopsCount;
    private long timeoutTime;
    private long timeToWait;

    //активация подсчёта
    //длительность кругов
    public Timer(boolean counterEnabled, long duration) {
        this(counterEnabled, true, duration);
    }

    //активация подсчёта
    //возврат количества
    //длительность кругов
    public Timer(boolean counterEnabled, boolean returnLoops, long duration) {
        this.timerJustStarted = true;
        this.countEachLoop = counterEnabled;
        this.timeToWait = duration;
        this.returnLoopCount = returnLoops;
    }

    public final int getTimerState() {
        if (this.timeHasRunOut) {
            return -2;
        } else {
            if (this.timerJustStarted) {
                this.timeoutTime = System.currentTimeMillis() + this.timeToWait;
                this.timerJustStarted = false;
            } else if (System.currentTimeMillis() > this.timeoutTime) {
                if (this.countEachLoop) {
                    this.timeoutTime = System.currentTimeMillis() + this.timeToWait;
                    return ++this.loopsCount;
                }

                this.timeHasRunOut = true;
                return 0;//время истекло
            }

            return this.countEachLoop && this.returnLoopCount ? this.loopsCount : -1;
        }
    }

    public final void resetTimer() {
        this.loopsCount = 0;
        this.timerJustStarted = true;
        this.timeHasRunOut = false;
        this.getTimerState();
    }
}
