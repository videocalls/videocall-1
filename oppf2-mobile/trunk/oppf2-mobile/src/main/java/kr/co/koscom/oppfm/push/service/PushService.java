package kr.co.koscom.oppfm.push.service;


public interface PushService{
	public String send(String deviceToken, String message);
}
