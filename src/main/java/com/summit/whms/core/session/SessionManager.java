package com.summit.whms.core.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionManager.class);
    private static final int EMPTY_STR_LENGTH = 0;

    public static void setNewSessionData(HttpServletRequest request, SessionModel model) throws Exception {
        LOGGER.debug("setNewSessionData is Started");
        HttpSession session = request.getSession(false);
        session = getNewSession(request);
        session.setAttribute(SessionModel.SESSION_NAME, model);
        LOGGER.debug("setNewSessionData is Finished.");
    }

    private static HttpSession getNewSession(HttpServletRequest request) throws Exception {
        LOGGER.debug("getNewSession is Started.");
        int session_timeout = 60 * 60 ;
        request.getSession(true).setMaxInactiveInterval(session_timeout);

        return request.getSession(true);
    }

    public static SessionModel getUserInfo(HttpServletRequest request) throws Exception {
        if(request == null) {
            throw new Exception("SignIn is Failed.");
        }

        HttpSession session = request.getSession(false);
        if(session != null) {
            SessionModel model = (SessionModel)session.getAttribute(SessionModel.SESSION_NAME);
            model.setUserIp(request.getHeader("NS-CLIENT-IP"));
            return model;
        } else {
            return new SessionModel();
        }
    }

    public static boolean isSignIn(HttpServletRequest request) {
        try {
            String userId = getUserInfo(request).getUserId();
            if(userId.length() <= EMPTY_STR_LENGTH) {
                return false;
            }
        } catch(Exception e) {
            return false;
        }
        return true;
    }

    public static void invalidate(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            try {
                LOGGER.debug("Session cleared" + session.getAttribute(SessionModel.SESSION_NAME));

            } catch(Exception e) {
                LOGGER.debug("Session not clear in SessionManager:" + e);
            }
            session.invalidate();
            session = null;
        }
    }
}
