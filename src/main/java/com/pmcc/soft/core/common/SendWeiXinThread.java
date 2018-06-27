package com.pmcc.soft.core.common;

public class SendWeiXinThread implements Runnable {

    private String content;
    public SendWeiXinThread(String content) {
        super();
        this.content = content;
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//HttpClientPost.post(content, content, content, content);
		
	}

}
