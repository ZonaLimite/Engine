package cta.designe.listener;

import java.io.Serializable;
import java.util.Map;
import java.util.Vector;

public class ModelFilter implements Serializable {
	private static final long serialVersionUID = 1L;

	Long ID;

	String nameListener;


	String descripListener;
	Vector<EventMask> vectorMasks; 
	String topicBroker;
	boolean enabled;
	Vector<String> modelColumns = new Vector<String>();
	

	public ModelFilter(Long iD, String nameListener, String descripListener, Vector<EventMask> modelMasks, String topicBroker, boolean enabled) {
		ID = iD;
		this.nameListener = nameListener;
		this.descripListener = descripListener;
		this.vectorMasks = modelMasks;
		this.topicBroker = topicBroker;
		this.enabled = enabled;
		
	}

	public ModelFilter(String nameListener) {
		super();
		this.nameListener = nameListener;
		EventMask eventMask = new EventMask();
		
		this.vectorMasks = new Vector<EventMask>();
		this.vectorMasks.add(eventMask);

		this.modelColumns = eventMask.toVectorColumns();
	}



	public Vector<String> getModelColumns() {
		return modelColumns;
	}

	public void setModelColumns(Vector<String> modelColumns) {
		this.modelColumns = modelColumns;
	}

	public String getDescripListener() {
		return descripListener;
	}

	public void setDescripListener(String descripListener) {
		this.descripListener = descripListener;
	}
	public String getNameListener() {
		return nameListener;
	}

	public void setNameListener(String nameListener) {
		this.nameListener = nameListener;
	}

	public Vector<EventMask> getVectorMasks() {
		return vectorMasks;
	}

	public void setVectorMasks(Vector<EventMask> vectorMasks) {
		this.vectorMasks = vectorMasks;
	}

	public String getTopicBroker() {
		return topicBroker;
	}

	public void setTopicBroker(String topicBroker) {
		this.topicBroker = topicBroker;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/**
	 * 
	 * @param Vector<Object> el vector de vectores que contiene el modelo de la
	 *                       tabla Es decir, el primer vector representa a las
	 *                       lineas el segundo representa a cada campo de la linea y
	 *                       esto es equivalente a un Vector<eventMask>
	 */
	public void updateModelFilter(Vector<Vector> data) {
		Vector<EventMask> updateVectorMask = new Vector<EventMask>();
		data.forEach(objectVector -> {
			EventMask myEventMask = new EventMask();
			myEventMask.setNameMaskEvent((String) objectVector.get(0));
			myEventMask.setNameMaskEventDescrip((String) objectVector.get(1));
			myEventMask.setFormatMaskPublish((String) objectVector.get(2));
			myEventMask.setTopicBroker((String) objectVector.get(3));
			myEventMask.setEnabled((boolean) objectVector.get(4));

			updateVectorMask.add(myEventMask);
		});
		this.vectorMasks = updateVectorMask;
	}

	public Vector<Vector<Object>> getDataVector() {
		Vector<Vector<Object>> dataVector = new Vector<Vector<Object>>();
		vectorMasks.forEach(eventMask -> {
			Vector<Object> vectorEventMask = new Vector<Object>();
			vectorEventMask.add(eventMask.getNameMaskEvent());
			vectorEventMask.add(eventMask.getNameMaskEventDescrip());
			vectorEventMask.add(eventMask.getFormatMaskPublish());
			vectorEventMask.add(eventMask.getTopicBroker());
			vectorEventMask.add(eventMask.isEnabled());
			dataVector.add(vectorEventMask);
		});
		return dataVector;
	}

	@Override
	public String toString() {
		return this.nameListener;
	}

}
