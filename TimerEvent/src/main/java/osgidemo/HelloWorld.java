import java.awt.Event;

public class TimerEvent extends Thread implements BundleActivator {
ServiceTracker tracker;
public TimerEvent() { super("TimerEvent"); }
public void start(BundleContext context ) {
tracker = new ServiceTracker(context,EventAdmin.class.getName(),null);
tracker.open();
start();//the thread
}
public void stop( BundleContext context ) {
interrupt();
tracker.close();
}
public void run() {
while ( ! Thread.interrupted() )
try {
ZonedDateTime time;
time = ZonedDateTime.now(ZoneId.systemDefault()).toString();
Map<String,Object> m = Collections.singletonMap("time",time);
EventAdmin ea = (EventAdmin) tracker.getService();
if ( ea != null )
ea.sendEvent(new Event("com/acme/timer", m ));
Thread.sleep(60000);
} catch( InterruptedException e ) {
return;
}
}
} 