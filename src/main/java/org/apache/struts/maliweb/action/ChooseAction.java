package org.apache.struts.maliweb.action;

import org.apache.struts.maliweb.model.EventLog;
import org.apache.struts.maliweb.model.EventsHistory;
import org.apache.struts.maliweb.model.Malicious;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts.maliweb.model.ReplaceMalice;
import org.apache.struts.maliweb.producer.MaliciousProducer;
import org.apache.struts2.interceptor.SessionAware;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Acts as a Struts 2 controller that responds
 * to a user action by setting the value
 * of the Message model class, and returns a String 
 * result.
 * @author Bruce Phillips
 *
 */
public class ChooseAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;

	private Map<String,Object> session;

	public List<Malicious> malis;

	public ChooseAction() {
		malis = new ArrayList<>() ;
		malis.add(new Malicious("Clipboard"));
		malis.add(new Malicious("Delete Semicolons"));
		malis.add(new Malicious("Environment Freeze (T)"));
		malis.add(new Malicious("Hide Window"));
		malis.add(new Malicious("Key Malice"));
		malis.add(new Malicious("Minimalize window"));
		malis.add(new Malicious("Mouse Malice"));
		malis.add(new Malicious("Vanish Window"));
		malis.add(new ReplaceMalice());
	}

	public String execute() throws Exception {

		if(session.get("EventHistory") == null){
			dataHis = new EventsHistory();
		}else{
			dataHis = (EventsHistory) session.get("EventHistory");
		}

		System.out.println(yourMalice + " " + howLong);

		if(yourMalice != null && mqAddress != null){
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			dataHis.addEventToList(new EventLog(yourMalice, dateFormat.format(date)));
			session.put("EventHistory",dataHis);
			System.out.println(dataHis.getEventHistory().size());

			if(yourMalice.equals("Replace") ){
				ReplaceMalice r = new ReplaceMalice();
				r.setFromString(fromString);
				r.setToString(changeToString);
				thread(new MaliciousProducer(r, mqAddress),false);
			}else{
				thread(new MaliciousProducer(new Malicious(getHowLong(),getYourMalice()), mqAddress),false);
			}

		}
		return SUCCESS;
	}

	public static void thread(Runnable runnable, boolean daemon) {
		Thread brokerThread = new Thread(runnable);
		brokerThread.setDaemon(daemon);
		brokerThread.start();
	}

	public List<Malicious> getMalis() {
		return malis;
	}

	public void setMalis(List<Malicious> malis) {
		this.malis = malis;
	}
	
	public String yourMalice = "";

	public static String mqAddress = "tcp://127.0.0.1:61616";

	public String getYourMalice() {
		return yourMalice;
	}

	public void setYourMalice(String yourMalice) {
		this.yourMalice = yourMalice;
	}

	public String howLong;

	public String getHowLong() {
		return howLong;
	}

	public void setHowLong(String howLong) {
		this.howLong = howLong;
	}

	public String getMqAddress() {
		return mqAddress;
	}

	public void setMqAddress(String mqAdres) {
		this.mqAddress = mqAdres;
	}

	public EventsHistory dataHis;

	public EventsHistory getDataHis() {
		return dataHis;
	}

	public void setDataHis(EventsHistory dataHis) {
		this.dataHis = dataHis;
	}

	public String fromString;
	public String changeToString;

	public String getFromString() {
		return fromString;
	}

	public void setFromString(String fromString) {
		this.fromString = fromString;
	}

	public String getToString() {
		return changeToString;
	}

	public void setToString(String toString) {
		this.changeToString = toString;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		session = map;
	}
}
