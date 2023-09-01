package cn.elvea.platform.commons.core.oapis.lark.request;

import com.google.gson.annotations.SerializedName;

/**
 * @author elvea
 * @since 0.0.1
 */
public class JsapiTicketRespBody {

    @SerializedName("expire_in")
    private int expire;

    @SerializedName("ticket")
    private String ticket;

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

}
