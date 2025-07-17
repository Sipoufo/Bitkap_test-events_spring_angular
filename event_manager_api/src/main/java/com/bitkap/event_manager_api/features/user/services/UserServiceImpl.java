package com.bitkap.event_manager_api.features.user.services;

import com.bitkap.event_manager_api.exception.ObjectNotFoundException;
import com.bitkap.event_manager_api.features.event.entities.Comment;
import com.bitkap.event_manager_api.features.event.services.CommentServiceImpl;
import com.bitkap.event_manager_api.features.user.entities.User;
import com.bitkap.event_manager_api.features.user.repositories.UserRepository;
import com.bitkap.event_manager_api.utils.GlobalParams;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author SIPOUFO Yvan
 *
 * @implNote That is the service implements UserService interface, use to manage all actions concerning user.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    /**
     * Process:
     *  1. Find user by id
     *  2. Return user
     *
     * @param id is the id of user for whom we want to obtain information.
     * @return the user object that containing all information.
     */
    @Override
    public User findOneById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            logger.info(GlobalParams.ResponseStatusEnum.ERROR.name() + " : ObjectNotFoundException");
            logger.error("User not found !");
            return new ObjectNotFoundException("User not found !");
        });

        logger.info(GlobalParams.ResponseStatusEnum.SUCCESS.name() + ": Comment returned with id = " + user.getId() + " !");
        return user;
    }
}
