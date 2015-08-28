package hackit;

public class DynamicThread {
  private Runnable runnable;
  private boolean catchExceptions;

  public DynamicThread(Runnable runnable,boolean catchExceptions) {
    this.runnable = runnable;
    this.catchExceptions = catchExceptions;
  }
  public void dispatch(){
    if(catchExceptions){
      try {
        runnable.run();
      }catch (Exception e) {System.out.println("Exception in DynamicThread dispatch(): " + e);}
    }
    else{
      runnable.run();
    }
  }

}