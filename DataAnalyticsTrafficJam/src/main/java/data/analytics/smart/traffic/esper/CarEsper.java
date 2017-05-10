package data.analytics.smart.traffic.esper;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class CarEsper {
	private final String DIRECTION_PATH = "data.analytics.smart.traffic.model.Direction.";
	private EPServiceProvider enigne = EPServiceProviderManager.getDefaultProvider();

	public EPStatement createStatement(String statement){
		return enigne.getEPAdministrator().createEPL(statement);
	}

	public void addListener(EPStatement statement, UpdateListener listener){
		statement.addListener(listener);
	}

	public void sendEvent(Object object){
		this.enigne.getEPRuntime().sendEvent(object);
	}

}
