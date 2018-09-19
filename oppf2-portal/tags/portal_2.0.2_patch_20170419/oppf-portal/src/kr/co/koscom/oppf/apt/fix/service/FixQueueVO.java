package kr.co.koscom.oppf.apt.fix.service;

import java.util.List;

import kr.co.koscom.oppf.cmm.service.ComDefaultListVO;


@SuppressWarnings("serial")
public class FixQueueVO extends ComDefaultListVO{
	
    private String queueId;
    
	private String pendingCount;
	
	private String consumerCount;
    
    private String enqueueCount;
    
    private String dequeueCount;

	public String getQueueId() {
		return queueId;
	}

	public void setQueueId(String queueId) {
		this.queueId = queueId;
	}

	public String getPendingCount() {
		return pendingCount;
	}

	public void setPendingCount(String pendingCount) {
		this.pendingCount = pendingCount;
	}

	public String getConsumerCount() {
		return consumerCount;
	}

	public void setConsumerCount(String consumerCount) {
		this.consumerCount = consumerCount;
	}

	public String getEnqueueCount() {
		return enqueueCount;
	}

	public void setEnqueueCount(String enqueueCount) {
		this.enqueueCount = enqueueCount;
	}

	public String getDequeueCount() {
		return dequeueCount;
	}

	public void setDequeueCount(String dequeueCount) {
		this.dequeueCount = dequeueCount;
	}
    
    
}    