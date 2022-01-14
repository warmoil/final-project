package joy.world.fc.finalproject.api.dto;

import joy.world.fc.finalproject.core.dto.RequestReplyType;


public class ReplyEngagementRequest {
    private RequestReplyType type;

    public ReplyEngagementRequest() {

    }
    public ReplyEngagementRequest(RequestReplyType type) {
        this.type = type;
    }

    public RequestReplyType getType() {
        return type;
    }

}
