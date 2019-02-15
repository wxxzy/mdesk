package com.mungo.selfprotocol;

/**
 * @author wangxingxiang
 * @Description
 * @date 2019/2/11 11:09
 */
public class SelfMessage {
    private Header header;
    private Object body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "SelfMessage [header="+header+"]";
    }
}
