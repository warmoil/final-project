package joy.world.fc.finalproject.api.service;

import joy.world.fc.finalproject.api.dto.AuthUser;
import joy.world.fc.finalproject.core.dto.RequestReplyType;
import joy.world.fc.finalproject.core.dto.RequestStatus;
import joy.world.fc.finalproject.core.exception.CalendarException;
import joy.world.fc.finalproject.core.exception.ErrorCode;
import joy.world.fc.finalproject.core.repository.EngagementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EngagementService {
    private final EngagementRepository engagementRepository;

    @Transactional
    public RequestStatus update(AuthUser authUser, Long engagementId, RequestReplyType type) {
        return engagementRepository.findById(engagementId)
                .filter(e -> e.getRequestStatus() == RequestStatus.REQUESTED)
                .filter(e -> e.getAttendee().getId().equals(authUser.getId()))
                .map(e -> e.reply(type))
                .orElseThrow(() -> new CalendarException(ErrorCode.BAD_REQUEST))
                .getRequestStatus();
    }

}
