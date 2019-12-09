import static org.osgi.service.event.EventConstants.*;



public class AcmeWatchDog implements BundleActivator, EventHandler {
final static String[] topics = new String[] {
"org/osgi/service/log/LogEntry/LOG_WARNING",
"org/osgi/service/log/LogEntry/LOG_ERROR" };
public void start(BundleContext context) {
Dictionary d = new Hashtable();
d.put(EVENT_TOPIC, topics);
d.put(EVENT_FILTER,"(bundle.symbolicName=com.acme.*)");
context.registerService(EventHandler.class.getName(), this, d);
}
public void stop(BundleContext context) {}
public void handleEvent(Event event ) { /*...*/ }
}