package com.bitkap.event_manager_api.features.user.services;

import com.bitkap.event_manager_api.features.user.entities.User;

import java.util.List;

/**
 * @author SIPOUFO Yvan
 *
 * @implNote That is the service interface use to manage all actions concerning user.
 */
public interface UserService {
    /**
     * Function uses to find one user by his id.
     *
     * @param id is the id of user for whom we want to obtain information.
     * @return the user object that containing all information relating to this user.
     */
    User findOneById(Long id);
}
