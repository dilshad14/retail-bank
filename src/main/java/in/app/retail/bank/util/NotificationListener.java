package in.app.retail.bank.util;

import java.util.LinkedList;
import java.util.Queue;

public class NotificationListener {

	private static Boolean consoleOutput = true;
	private static Queue<String> notificationQueue = new LinkedList<>();
 	
	public static void add(String msg) {
		notificationQueue.add(msg);
		if (consoleOutput == true) {
			display();
		}
	}
	
	public static void display() {
		System.out.println(notificationQueue.poll());
	}
	
	public static void setConsoleOutput(Boolean value) {
		consoleOutput = value;
	}
	
	public static String pollFirstFromNotificationQueue() {
		return notificationQueue.poll();
	}
	
	
}
